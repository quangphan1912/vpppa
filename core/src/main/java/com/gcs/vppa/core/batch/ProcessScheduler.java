package com.gcs.vppa.core.batch;

import com.gcs.vppa.common.config.model.GlobalProperties;
import com.gcs.vppa.common.service.CryptoService;
import com.gcs.vppa.common.util.CryptoUtils;
import com.gcs.vppa.common.util.DateTimeUtil;
import com.gcs.vppa.core.service.ProcessViewService;
import com.gcs.vppa.core.service.SchemeExecutorService;
import com.gcs.vppa.core.service.SchemeViewDataService;
import com.gcs.vppa.dto.ProcessViewDTO;
import com.gcs.vppa.dto.SchemeExecutorDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 10/10/2019.
 */
@Service("ProcessScheduler")
@Slf4j
public class ProcessScheduler extends AbstractBatch {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessScheduler.class);

    private ProcessResult processResult = null;

    private static final String PROCESS_THREAD = "Camunda Process@System";

    @Autowired
    private ProcessViewService processViewService;

    @Autowired
    private SchemeViewDataService schemeViewDataService;

    @Autowired
    private SchemeExecutorService schemeExecutorService;

    @Autowired
    private GlobalProperties globalProperties;

    /**
     * The cryptography properties.
     */
    @Autowired
    private CryptoService cryptoService;

    /**
     *
     * @param cron
     * @return
     */
    protected boolean isMonthlyRun(String cron) {
        try {
            String dateTime = DateTimeUtil.getServerDateTime();
            int dd = Integer.parseInt(dateTime.substring(0, 2));
            int hh = Integer.parseInt(dateTime.substring(8, 10));
            int mm = Integer.parseInt(dateTime.substring(10, 12));

            String[] expression = cron.split(" "); //ssmmhhDDMMYY
            if (dd == Integer.parseInt(expression[3]) &&
                    hh == Integer.parseInt(expression[2]) &&
                    mm == Integer.parseInt(expression[1]))
            {
                return true;
            }
        } catch (Exception e) {
            // nop
        }

        return  false;
    }

    /*
     * 1st working day monthly @03:00
     */
    @Scheduled(cron = "0 * * * * ?")
    public void scheduleCamundaProcess() {
        Thread existingThread = getExistingThread();
        if (existingThread == null) {
            ProcessThread thread = new ProcessThread();
            thread.setName(PROCESS_THREAD);
            thread.start();
        }
    }

    /**
     * get existing thread
     *
     * @return exist thread
     */
    private Thread getExistingThread() {
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        for (Thread thread : threadSet) {
            if (PROCESS_THREAD.equalsIgnoreCase(thread.getName()) && thread.isAlive()) {
                return thread;
            }
        }
        return null;
    }

    /**
     * check if processThread is ready for stop
     *
     * @param processThread
     * @return isStop
     */
    private boolean isStop(ProcessThread processThread) {
        if (processThread.isStop()) {
            synchronized (processThread) {
                processThread.notify();
            }
            return true;
        }

        return false;
    }

    /**
     * executeCamundaProcess
     */
    private void executeCamundaProcess() {
        LOGGER.debug("START camunda process...");
        this.processResult = new ProcessResult();
        this.processResult.setStatus("RUNNING");
        try {
            ProcessThread processThread = (ProcessThread) Thread.currentThread();
            List<ProcessViewDTO> processList = processViewService.findAll();
            if (CollectionUtils.isEmpty(processList)
                    || isStop(processThread)) {
                LOGGER.debug("Not found process");
                return;
            }

            processList.forEach(process -> {
                if (isMonthlyRun(process.getExpression())) {
                    // insert executor list
                    SchemeExecutorDTO schemeExecutorDTO = new SchemeExecutorDTO();
                    String dateTime = DateTimeUtil.getServerDateTime();
                    schemeExecutorDTO.setExecutorId(dateTime);
                    schemeExecutorDTO.setType("Autorun");
                    schemeExecutorDTO.setStatus("Started");
                    schemeExecutorDTO.setExecuteBy("System");
                    schemeExecutorDTO.setExecuteMonth(dateTime.substring(2, 4).concat("/").concat(dateTime.substring(4, 8)));
                    schemeExecutorDTO.setExecuteDate(Timestamp.valueOf(LocalDateTime.now()));
                    schemeExecutorDTO.setProcessId(CryptoUtils.encrypt(String.valueOf(process.getId()), cryptoService.getProcessKey()));
                    List<String> schemeIdList = schemeViewDataService.findAllByProcessIdAndStatus(process.getId(), "Active");
                    schemeIdList.forEach(id -> {
                        schemeExecutorDTO.setSchemeId(CryptoUtils.encrypt(id, cryptoService.getSchemeKey()));
                        schemeExecutorService.insert(schemeExecutorDTO);
                    });

                    // call camunda
                    postCamundaProcess(globalProperties.getCamundaUrl(), process.getKey(), schemeExecutorDTO.getExecutorId(), String.valueOf(process.getId()), getExecutedMonth(schemeExecutorDTO.getExecuteMonth()),
                            String.join(",", schemeIdList), globalProperties.getBicc(), globalProperties.getBau());
                }
            });

            this.processResult.setStatus("COMPLETED");
        } catch (Exception e) {
            LOGGER.error("Error occurred: " + e.getMessage(), e);
            if (this.processResult != null) {
                this.processResult.setStatus("ERROR");
                this.processResult.setErrorMessage(e.getMessage());
            }
        }

        LOGGER.debug("END camunda process......");
    }

    class ProcessThread extends Thread {
        private volatile boolean stop = false;

        @SuppressWarnings("synthetic-access")
        @Override
        public void run() {
            executeCamundaProcess();
        }

        public boolean isStop() {
            return stop;
        }

        public void setStop(boolean stop) {
            this.stop = stop;
        }
    }
}
