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
import com.gcs.vppa.core.converter.ProcessConverter;
import com.gcs.vppa.dto.ProcessDTO;
import com.gcs.vppa.entity.Process;
import com.gcs.vppa.repository.ProcessRepository;

import java.util.List;

/**
 * The Interface ParameterDataService.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */
public interface ProcessService
  extends BaseDataService<Integer, Process, ProcessDTO, ProcessRepository, ProcessConverter> {

  /**
   * Find by parameter name.
   *
   * @param name the name
   * @return the parameter DTO
   */
  ProcessDTO findByName(String name);
}
