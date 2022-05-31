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
package com.gcs.vppa.core.service;

import com.gcs.vppa.common.service.BaseDataService;
import com.gcs.vppa.core.converter.ProcessReportConverter;
import com.gcs.vppa.dto.ProcessReportDTO;
import com.gcs.vppa.entity.ProcessReport;
import com.gcs.vppa.repository.ProcessReportRepository;

import java.util.List;

/**
 * The Interface ProcessReportService.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */
public interface ProcessReportService
  extends BaseDataService<Integer, ProcessReport, ProcessReportDTO, ProcessReportRepository, ProcessReportConverter> {

  /**
   * Find by parameter name.
   *
   * @param name the name
   * @return the parameter DTO
   */
  ProcessReportDTO findByName(String name);

  List<ProcessReportDTO> findByProcessId(Integer processId);
}
