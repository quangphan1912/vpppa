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

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcs.vppa.annotations.Permitted;
import com.gcs.vppa.common.controller.BaseController;
import com.gcs.vppa.core.converter.ProcessStatusConverter;
import com.gcs.vppa.core.service.PermissionService;
import com.gcs.vppa.core.service.ProcessStatusService;
import com.gcs.vppa.dto.ProcessStatusDTO;
import com.gcs.vppa.entity.ProcessStatus;
import com.gcs.vppa.repository.ProcessStatusRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class ProcessStatusController.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */
@RestController
@RequestMapping("/process-status")

/** The Constant log. */
@Slf4j
public class ProcessStatusController
  extends
  BaseController<Integer, ProcessStatus, ProcessStatusDTO, ProcessStatusRepository, ProcessStatusConverter, ProcessStatusService> {

  /**
   * Insert process.
   *
   * @param dto the dto
   * @return the response entity
   */
  @PostMapping(value = "",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  @Permitted(features = {
    PermissionService.WRITE_PROCESS,
    PermissionService.READ_PROCESS})
  public ResponseEntity<Object> insertParam(@RequestBody ProcessStatusDTO dto) {
    log.warn("starting insert process status!!!!!!!!");
    return this.insertItem(dto, null);
  }

  /**
   * Update process.
   *
   * @param dto the dto
   * @return the response entity
   */
  @PutMapping(value = "",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  @Permitted(features = {
    PermissionService.WRITE_PROCESS,
    PermissionService.READ_PROCESS})
  public ResponseEntity<Object> updateParam(@RequestBody ProcessStatusDTO dto) {
    log.debug("Update Item to : [{}]", dto);

    return this.updateItem(dto.getId(), dto, null);
  }

  /**
   * Delete process.
   *
   * @param id the id
   * @return the response entity
   */
  @DeleteMapping(value = "/{id}")
  @Permitted(features = {
    PermissionService.WRITE_PROCESS,
    PermissionService.READ_PROCESS})
  public ResponseEntity<Object> deleteParam(@PathVariable("id") Integer id) {
    return this.deleteItem(id);
  }
}
