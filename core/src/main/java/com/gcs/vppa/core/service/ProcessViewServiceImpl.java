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
import com.gcs.vppa.core.converter.ProcessConverter;
import com.gcs.vppa.core.converter.ProcessViewConverter;
import com.gcs.vppa.dto.ProcessDTO;
import com.gcs.vppa.dto.ProcessViewDTO;
import com.gcs.vppa.entity.Process;
import com.gcs.vppa.entity.ProcessView;
import com.gcs.vppa.repository.ProcessRepository;
import com.gcs.vppa.repository.ProcessViewRepository;
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
public class ProcessViewServiceImpl
  extends BaseDataServiceImpl<Integer, ProcessView, ProcessViewDTO, ProcessViewRepository, ProcessViewConverter>
  implements ProcessViewService {

  /**
   * Gets the entity name.
   *
   * @return the entity name
   */
  @Override
  public String getEntityName() {
    return "ProcessView";
  }
}
