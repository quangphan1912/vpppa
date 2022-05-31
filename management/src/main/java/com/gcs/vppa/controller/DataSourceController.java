/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * July 27, 2020     ********           PhoVo            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.controller;

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
import com.gcs.vppa.common.constant.ErrorCodes;
import com.gcs.vppa.common.controller.BaseController;
import com.gcs.vppa.common.dto.ResponseTemplate;
import com.gcs.vppa.common.repository.SearchCondition;
import com.gcs.vppa.common.service.I18nMessageService;
import com.gcs.vppa.core.converter.DataSourceConverter;
import com.gcs.vppa.core.service.DataSourceService;
import com.gcs.vppa.core.service.PermissionService;
import com.gcs.vppa.dto.DataSourceDTO;
import com.gcs.vppa.entity.DataSource;
import com.gcs.vppa.repository.DataSourceRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class DataSourceController.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */
@RestController
@RequestMapping("/datasource")

/** The Constant log. */
@Slf4j
public class DataSourceController extends
  BaseController<Integer, DataSource, DataSourceDTO, DataSourceRepository, DataSourceConverter, DataSourceService> {

  /** The i 18 n message service. */
  @Autowired
  private I18nMessageService i18nMessageService;

  /**
   * Gets the all Params.
   *
   * @return the all Params
   */
  @GetMapping(value = "")
  @Permitted(features = {
    PermissionService.WRITE_PARAMETER,
    PermissionService.WRITE_SCHEME,
    PermissionService.WRITE_PROCESS,
    PermissionService.READ_PARAMETER,
    PermissionService.READ_SCHEME,
    PermissionService.READ_PROCESS})
  public ResponseEntity<Object> getAllParam() {
    return this.getAllItems();
  }

  /**
   * Search param.
   *
   * @param searchCondition the search condition
   * @return the response entity
   */
  @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
  @Permitted(features = {
    PermissionService.WRITE_PARAMETER,
    PermissionService.WRITE_SCHEME,
    PermissionService.WRITE_PROCESS,
    PermissionService.READ_PARAMETER,
    PermissionService.READ_SCHEME,
    PermissionService.READ_PROCESS})
  public ResponseEntity<Object> searchParam(@RequestBody SearchCondition searchCondition) {
    return this.searchItems(searchCondition);
  }

  /**
   * Insert Param.
   *
   * @param dto the dto
   * @return the response entity
   */
  @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Permitted(features = {
    PermissionService.WRITE_PARAMETER,
    PermissionService.WRITE_SCHEME,
    PermissionService.WRITE_PROCESS,
    PermissionService.READ_PARAMETER,
    PermissionService.READ_SCHEME,
    PermissionService.READ_PROCESS})
  public ResponseEntity<Object> insertParam(@RequestBody DataSourceDTO dto) {
    if (dto == null) {
      return new ResponseEntity<>(new ResponseTemplate(HttpStatus.BAD_REQUEST,
        i18nMessageService.getMessage(ErrorCodes.uc3_warn_07)), HttpStatus.BAD_REQUEST);
    }

    if (this.dataService.findBySource(dto.getSource()) != null) {
      log.warn("data source - found other data source with the same source");
      return new ResponseEntity<>(
        new ResponseTemplate(HttpStatus.BAD_REQUEST, ErrorCodes.DUPLICATE_DEVICE_ID),
        HttpStatus.BAD_REQUEST);
    }
    return this.insertItem(dto, null);
  }

  /**
   * Update param.
   *
   * @param dto the dto
   * @return the response entity
   */
  @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Permitted(features = {
    PermissionService.WRITE_PARAMETER,
    PermissionService.WRITE_SCHEME,
    PermissionService.WRITE_PROCESS,
    PermissionService.READ_PARAMETER,
    PermissionService.READ_SCHEME,
    PermissionService.READ_PROCESS})
  public ResponseEntity<Object> updateParam(@RequestBody DataSourceDTO dto) {
    if (dto == null) {
      return new ResponseEntity<>(new ResponseTemplate(HttpStatus.BAD_REQUEST,
        i18nMessageService.getMessage(ErrorCodes.uc3_warn_07)), HttpStatus.BAD_REQUEST);
    }

    log.debug("Update Item to : [{}]", dto);

    return this.updateItem(dto.getId(), dto, null);
  }

  /**
   * Delete param.
   *
   * @param id the id
   * @return the response entity
   */
  @DeleteMapping(value = "/{id}")
  @Permitted(features = {
    PermissionService.WRITE_PARAMETER,
    PermissionService.WRITE_SCHEME,
    PermissionService.WRITE_PROCESS,
    PermissionService.READ_PARAMETER,
    PermissionService.READ_SCHEME,
    PermissionService.READ_PROCESS})
  public ResponseEntity<Object> deleteParam(@PathVariable("id") Integer id) {
    return this.deleteItem(id);
  }
}
