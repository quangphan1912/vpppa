package com.gcs.vppa.core.batch;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.vppa.common.config.model.GlobalProperties;
import com.gcs.vppa.common.service.CryptoService;
import com.gcs.vppa.common.util.CryptoUtils;
import com.gcs.vppa.common.util.DateTimeUtil;
import com.gcs.vppa.core.service.ProcessViewService;
import com.gcs.vppa.core.service.SchemeExecutorService;
import com.gcs.vppa.dto.ProcessViewDTO;
import com.gcs.vppa.dto.SchemeExecutorDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Admin on 10/10/2019.
 */
@Service("ProcessExecutor")
@Slf4j
public class ProcessExecutor extends AbstractBatch {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessExecutor.class);

    private ProcessResult processResult = null;

    @Autowired
    private SchemeExecutorService schemeExecutorService;

    @Autowired
    private ProcessViewService processViewService;

    @Autowired
    private GlobalProperties globalProperties;

    /**
     * The cryptography properties.
     */
    @Autowired
    private CryptoService cryptoService;

    /*
     * schedule Camunda Process
     */
    public void scheduleCamundaProcess(SchemeExecutorDTO schemeExecutor, List<String> schemeIdList) {
        LOGGER.debug("START camunda process...");
        this.processResult = new ProcessResult();
        this.processResult.setStatus("RUNNING");
        try {
            // insert executor list
            String proId = schemeExecutor.getProcessId();
            ProcessViewDTO processView = processViewService.findById(Integer.parseInt(proId));
            String dateTime = DateTimeUtil.getServerDateTime();
            schemeExecutor.setExecutorId(dateTime);
            schemeExecutor.setStatus("Started");
            schemeExecutor.setExecuteMonth(schemeExecutor.getExecuteMonth());
            schemeExecutor.setExecuteDate(Timestamp.valueOf(LocalDateTime.now()));
            schemeExecutor.setProcessId(CryptoUtils.encrypt(proId, cryptoService.getProcessKey()));
            schemeIdList.forEach(id -> {
                schemeExecutor.setSchemeId(CryptoUtils.encrypt(id, cryptoService.getSchemeKey()));
                schemeExecutorService.insert(schemeExecutor);
            });

            // call camunda
            postCamundaProcess(globalProperties.getCamundaUrl(), processView.getKey(), schemeExecutor.getExecutorId(), String.valueOf(proId), getExecutedMonth(schemeExecutor.getExecuteMonth()),
                    String.join(",", schemeIdList), globalProperties.getBicc(), globalProperties.getBau());
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

    @Override
    protected String getExecutedMonth(String calculateMonth) {
        try {
            if (calculateMonth != null) {
                int[] array = Arrays.stream(calculateMonth.split("/"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                return String.valueOf(array[1]).concat("-").concat(array[0] > 10 ? String.valueOf(array[0]) : "0" + String.valueOf(array[0]));
            }
        } catch (Exception e) {
            // nop
        }

        return "";
    }
}
