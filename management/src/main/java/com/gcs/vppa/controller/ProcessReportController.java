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

import com.gcs.vppa.common.constant.EnumsCommon.Enum.Status;
import com.gcs.vppa.annotations.Permitted;
import com.gcs.vppa.common.controller.BaseController;
import com.gcs.vppa.common.repository.SearchCondition;
import com.gcs.vppa.core.converter.ProcessReportViewConverter;
import com.gcs.vppa.core.service.PermissionService;
import com.gcs.vppa.core.service.ProcessReportViewService;
import com.gcs.vppa.dto.ProcessReportViewDTO;
import com.gcs.vppa.entity.ProcessReportView;
import com.gcs.vppa.repository.ProcessReportViewRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The Class ProcessController.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */
@RestController
@RequestMapping("/process-report")

/** The Constant log. */
public class ProcessReportController extends
    BaseController<Integer, ProcessReportView, ProcessReportViewDTO, ProcessReportViewRepository, ProcessReportViewConverter, ProcessReportViewService> {

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
  @Permitted(features = {
    PermissionService.WRITE_PROCESS,
    PermissionService.READ_PROCESS})
  public ResponseEntity<Object> searchParam(@RequestBody SearchCondition searchCondition) {

    String statusFilter = Status.DISABLED.getValue();
    this.modifySearchCondition(statusFilter, searchCondition, new String[]{},
        condition -> null);

    return this.searchItems(searchCondition);
  }
}
