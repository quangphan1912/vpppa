/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 * <p>
 * Description: The file class
 * <p>
 * Change history:
 * Date             Defect#             Person             Comments
 * -------------------------------------------------------------------------------
 * July 27, 2020     ********           PhoVo            Initialize
 */
package com.gcs.vppa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcs.vppa.annotations.Permitted;
import com.gcs.vppa.common.constant.Constants;
import com.gcs.vppa.common.constant.ErrorCodes;
import com.gcs.vppa.common.controller.BaseController;
import com.gcs.vppa.common.dto.ResponseTemplate;
import com.gcs.vppa.common.service.CryptoService;
import com.gcs.vppa.common.service.I18nMessageService;
import com.gcs.vppa.common.util.CollectionUtil;
import com.gcs.vppa.common.util.CryptoUtils;
import com.gcs.vppa.common.util.FileUtil;
import com.gcs.vppa.core.converter.ProcessConverter;
import com.gcs.vppa.core.service.DataSourceService;
import com.gcs.vppa.core.service.InitDataProcessService;
import com.gcs.vppa.core.service.PermissionService;
import com.gcs.vppa.core.service.ProcessReportService;
import com.gcs.vppa.core.service.ProcessService;
import com.gcs.vppa.dto.DataSourceDTO;
import com.gcs.vppa.dto.MasterDataProcessDTO;
import com.gcs.vppa.dto.ProcessDTO;
import com.gcs.vppa.dto.ProcessReportDTO;
import com.gcs.vppa.entity.Process;
import com.gcs.vppa.repository.ProcessRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class ProcessController.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */
@RestController
@RequestMapping("/process")

/** The Constant log. */
@Slf4j
public class ProcessController
  extends
  BaseController<Integer, Process, ProcessDTO, ProcessRepository, ProcessConverter, ProcessService> {

  /**
   * The crypt service.
   */
  @Autowired
  private CryptoService cryptoService;

  /**
   * The i 18 n message service.
   */
  @Autowired
  private I18nMessageService i18nMessageService;

  /**
   * The init Data Service.
   */
  @Autowired
  private InitDataProcessService initDataService;

  /**
   * The processReportService.
   */
  @Autowired
  private ProcessReportService processReportService;

  /**
   * The Data source Service.
   */
  @Autowired
  private DataSourceService dataSourceService;

  /**
   * Gets the all Params.
   *
   * @return the all Params
   */
  @GetMapping(value = "/init")
  @Permitted(features = {PermissionService.READ_PROCESS, PermissionService.WRITE_PROCESS})
  public MasterDataProcessDTO initData() {
    return this.initDataService.getAllMasterData();
  }

  private void addFiles(List<ProcessReportDTO> reportList) {
    if (!CollectionUtil.isNullOrEmpty(reportList)) {
      reportList.forEach(report -> {
        if (report.getTemplateData() != null) {
          try {
            FileUtil.uploadFileToServer(report.getTemplateData(), Constants.TEMPLATE, report.getTemplate());
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      });
    }
  }

  private void deleteFiles(List<ProcessReportDTO> reportList) {
    if (!CollectionUtil.isNullOrEmpty(reportList)) {
      reportList.forEach(report -> {
        try {
          FileUtil.deleteFileOnServer(Constants.TEMPLATE, report.getTemplate());
        } catch (Exception e) {
          e.printStackTrace();
        }
      });
    }
  }

  /**
   * Checks if is contains.
   *
   * @param items the items
   * @return true, if is null or empty
   */
  private boolean contains(List<ProcessReportDTO> items, ProcessReportDTO item) {
    return items.stream()
      .filter(file -> file.getTemplate() != null && file.getTemplate().equalsIgnoreCase(item.getTemplate()))
      .collect(Collectors.toList()).size() > 0;
  }

  private List<ProcessReportDTO> filterByNotContainReport(List<ProcessReportDTO> reportList,
    List<ProcessReportDTO> existingReportList) {
    return existingReportList.stream()
      .filter(report -> !contains(reportList, report))
      .collect(Collectors.toList());
  }

  /**
   * Insert process.
   *
   * @param dto the dto
   * @return the response entity
   */
  @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Permitted(features = {PermissionService.WRITE_PROCESS})
  public ResponseEntity<Object> insertProcess(@RequestBody ProcessDTO dto) {
    log.warn("starting insert process!!!!!!!!");

    if (this.dataService.findByName(dto.getName()) != null) {
      log.warn("process - found other process with the same process");
      return new ResponseEntity<>(new ResponseTemplate(HttpStatus.BAD_REQUEST, ErrorCodes.DUPLICATE_DEVICE_ID),
        HttpStatus.BAD_REQUEST);
    }
    return this.insertItem(dto, null);
  }

  /**
   * Update process.
   *
   * @param dto the dto
   * @return the response entity
   */
  @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Permitted(features = {PermissionService.WRITE_PROCESS})
  public ResponseEntity<Object> updateProcess(@RequestBody ProcessDTO dto) {
    log.debug("Update Item to : [{}]", dto);

    // check exist
    if (dto == null) {
      log.debug("The action is invalid, process does not exist!");
      return new ResponseEntity<>(new ResponseTemplate(HttpStatus.BAD_REQUEST,
        i18nMessageService.getMessage(ErrorCodes.uc1_warn_06)), HttpStatus.BAD_REQUEST);
    }

    ResponseEntity<Object> response = this.updateItem(dto.getId(), dto, null);
    // search all existing report
    List<ProcessReportDTO> existingReportList = processReportService.findByProcessId(dto.getId());
    // search all deleted report
    List<ProcessReportDTO> deletedReportList = filterByNotContainReport(dto.getReportList(), existingReportList);
    // remove deleted file from server
    deleteFiles(deletedReportList);
    // remove from database
    processReportService.deleteList(deletedReportList);
    // add new deleted file from server
    addFiles(dto.getReportList());
    dto.getReportList().forEach(report -> processReportService.insert(report));

    // delete table datasource
    List<DataSourceDTO> deletedDataSourceList = new ArrayList<DataSourceDTO>(deletedReportList.size());
    for (ProcessReportDTO processDelete : deletedReportList) {
      if (processDelete.getSource() != null) {
        deletedDataSourceList.add(dataSourceService.findBySource(processDelete.getSource()));
      }
    }
    if (CollectionUtil.isNullOrEmpty(deletedDataSourceList)) {
      dataSourceService.deleteList(deletedDataSourceList);
    }

    // insert/update
    updateDataSource(dto.getReportList());

    return response;
  }

  /**
   * Delete process.
   *
   * @param encryptId the id
   * @return the response entity
   */
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Object> deleteProcess(@PathVariable("id") String encryptId) {
    Integer id = CryptoUtils.decryptId(encryptId, cryptoService.getProcessKey());
    return this.deleteItem(id, i18nMessageService.getMessage(ErrorCodes.uc4_info_001));
  }

  /**
   * Update Data Source.
   *
   * @param reportList
   */
  private void updateDataSource(List<ProcessReportDTO> reportList) {
    DataSourceDTO dataSourceDto = null;
    for (ProcessReportDTO processReport : reportList) {
      if (processReport.getSource() != null) {
        dataSourceDto = new DataSourceDTO(processReport.getSource(), processReport.getSourceData());
        DataSourceDTO dataSourceDB = dataSourceService.findBySource(processReport.getSource());

        if (dataSourceDB == null) {
          dataSourceService.insert(dataSourceDto);
        } else if (dataSourceDto.getContent() != null) {
          dataSourceService.update(dataSourceDB.getId(), dataSourceDto);
        }
      }

    }
  }

}
