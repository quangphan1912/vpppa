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
import com.gcs.vppa.core.converter.ProcessViewConverter;
import com.gcs.vppa.core.service.PermissionService;
import com.gcs.vppa.core.service.ProcessViewService;
import com.gcs.vppa.dto.ProcessViewDTO;
import com.gcs.vppa.entity.ProcessView;
import com.gcs.vppa.repository.ProcessViewRepository;

/**
 * The Class ProcessController.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */
@RestController
@RequestMapping("/process")

/** The Constant log. */
public class ProcessViewController extends
    BaseController<Integer, ProcessView, ProcessViewDTO, ProcessViewRepository, ProcessViewConverter, ProcessViewService> {

  /**
   * Gets the all process.
   *
   * @return the all process
   */
  @GetMapping(value = "")
  public ResponseEntity<Object> getAllParam() {
    return this.getAllItems();
  }

  /**
   * Search process.
   *
   * @param searchCondition
   *          the search condition
   * @return the response entity
   */
  @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
  @Permitted(features = {PermissionService.READ_PROCESS})
  public ResponseEntity<Object> searchParam(@RequestBody SearchCondition searchCondition) {

    String statusFilter = null;
    this.modifySearchCondition(statusFilter, searchCondition, new String[]{},
        condition -> null);

    return this.searchItems(searchCondition);
  }
}
