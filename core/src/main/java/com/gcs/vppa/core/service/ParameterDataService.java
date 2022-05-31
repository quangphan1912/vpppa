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
import com.gcs.vppa.core.converter.ParameterConverter;
import com.gcs.vppa.dto.ParameterDTO;
import com.gcs.vppa.entity.Parameter;
import com.gcs.vppa.repository.ParameterRepository;

/**
 * The Interface ParameterDataService.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */
public interface ParameterDataService
  extends BaseDataService<Integer, Parameter, ParameterDTO, ParameterRepository, ParameterConverter> {

  /**
   * Find by parameter name.
   *
   * @param paramName the parameter name
   * @return the parameter DTO
   */
  ParameterDTO findByParamName(String paramName);

  /**
   * Find by source.
   *
   * @param source the source
   * @return the parameter DTO
   */
  ParameterDTO findBySource(String source);

}
