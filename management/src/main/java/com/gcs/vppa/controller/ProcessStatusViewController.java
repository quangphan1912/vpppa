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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcs.vppa.annotations.Permitted;
import com.gcs.vppa.common.controller.BaseController;
import com.gcs.vppa.common.repository.SearchCondition;
import com.gcs.vppa.core.converter.ProcessStatusViewConverter;
import com.gcs.vppa.core.service.PermissionService;
import com.gcs.vppa.core.service.ProcessStatusViewService;
import com.gcs.vppa.dto.ProcessStatusViewDTO;
import com.gcs.vppa.entity.ProcessStatusView;
import com.gcs.vppa.repository.ProcessStatusViewRepository;

/**
 * The Class ProcessStatusViewController.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */
@RestController
@RequestMapping("/process-status")

/** The Process Status log. */
public class ProcessStatusViewController
  extends
  BaseController<Integer, ProcessStatusView, ProcessStatusViewDTO, ProcessStatusViewRepository, ProcessStatusViewConverter, ProcessStatusViewService> {

  /**
   * Gets the all Process Status.
   *
   * @return the all Process Status
   */
  @GetMapping(value = "")
  @Permitted(features = {
    PermissionService.WRITE_PROCESS,
    PermissionService.READ_PROCESS})
  public ResponseEntity<Object> getAllProcessStatus() {
    return this.getAllItems();
  }

  /**
   * Search Process Status.
   *
   * @param searchCondition the search condition
   * @return the response entity
   */
  @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
  @Permitted(features = {
    PermissionService.WRITE_PROCESS,
    PermissionService.READ_PROCESS})
  public ResponseEntity<Object> searchProcessStatus(@RequestBody SearchCondition searchCondition) {
    return this.searchItems(searchCondition);
  }
}
