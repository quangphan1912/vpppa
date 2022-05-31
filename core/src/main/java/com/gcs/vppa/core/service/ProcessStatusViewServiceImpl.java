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
import com.gcs.vppa.core.converter.ProcessStatusViewConverter;
import com.gcs.vppa.dto.ProcessStatusViewDTO;
import com.gcs.vppa.entity.ProcessStatusView;
import com.gcs.vppa.repository.ProcessStatusViewRepository;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

/**
 * The Class ProcessStatusViewServiceImpl.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */

/**
 * Instantiates a new parameter data service impl.
 */
@NoArgsConstructor
@Service
@ComponentScan
public class ProcessStatusViewServiceImpl
  extends BaseDataServiceImpl<Integer, ProcessStatusView, ProcessStatusViewDTO, ProcessStatusViewRepository, ProcessStatusViewConverter>
  implements ProcessStatusViewService {

  /**
   * Gets the entity name.
   *
   * @return the entity name
   */
  @Override
  public String getEntityName() {
    return "ProcessStatusView";
  }
}
