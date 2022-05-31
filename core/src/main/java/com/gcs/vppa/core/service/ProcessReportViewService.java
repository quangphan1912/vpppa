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
import com.gcs.vppa.core.converter.ProcessReportViewConverter;
import com.gcs.vppa.dto.ProcessReportDTO;
import com.gcs.vppa.dto.ProcessReportViewDTO;
import com.gcs.vppa.entity.ProcessReportView;
import com.gcs.vppa.repository.ProcessReportViewRepository;

/**
 * The Interface ProcessReportService.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */
public interface ProcessReportViewService
  extends BaseDataService<Integer, ProcessReportView, ProcessReportViewDTO, ProcessReportViewRepository, ProcessReportViewConverter> {

  /**
   * Find by parameter name.
   *
   * @param name the name
   * @return the parameter DTO
   */
  ProcessReportViewDTO findByName(String name);
}
