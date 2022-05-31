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

import com.gcs.vppa.common.service.BaseDataServiceImpl;
import com.gcs.vppa.core.converter.ProcessReportViewConverter;
import com.gcs.vppa.dto.ProcessReportViewDTO;
import com.gcs.vppa.entity.ProcessReportView;
import com.gcs.vppa.repository.ProcessReportViewRepository;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

/**
 * The Class ProcessServiceImpl.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */

/**
 * Instantiates a new parameter data service impl.
 */
@NoArgsConstructor
@Service
@ComponentScan
public class ProcessReportViewServiceImpl
  extends BaseDataServiceImpl<Integer, ProcessReportView, ProcessReportViewDTO, ProcessReportViewRepository, ProcessReportViewConverter>
  implements ProcessReportViewService {

  /**
   * Gets the entity name.
   *
   * @return the entity name
   */
  @Override
  public String getEntityName() {
    return "ProcessReport";
  }

  /**
   * Find by param name.
   *
   * @param name the name
   * @return the parameter DTO
   */
  @Override
  public ProcessReportViewDTO findByName(String name) {
    return this.converter.toDto(this.repository.findByName(name));
  }
}
