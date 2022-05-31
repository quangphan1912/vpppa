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

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.gcs.vppa.common.service.BaseDataServiceImpl;
import com.gcs.vppa.core.converter.ParameterConverter;
import com.gcs.vppa.dto.ParameterDTO;
import com.gcs.vppa.entity.Parameter;
import com.gcs.vppa.repository.ParameterRepository;

import lombok.NoArgsConstructor;

/**
 * The Class ParameterDataServiceImpl.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */

/**
 * Instantiates a new parameter data service impl.
 */
@NoArgsConstructor
@Service
@ComponentScan
public class ParameterDataServiceImpl
  extends BaseDataServiceImpl<Integer, Parameter, ParameterDTO, ParameterRepository, ParameterConverter>
  implements ParameterDataService {

  /**
   * Gets the entity name.
   *
   * @return the entity name
   */
  @Override
  public String getEntityName() {
    return "Parameter";
  }

  /**
   * Find by param name.
   *
   * @param paramName the param name
   * @return the parameter DTO
   */
  @Override
  public ParameterDTO findByParamName(String paramName) {
    return this.converter.toDto(this.repository.findByParamName(paramName));
  }

  /**
   * Gets the parameter.
   *
   * @param source the source
   * @return the parameter
   */
  @Override
  public ParameterDTO findBySource(String source) {
    return this.converter.toDto(this.repository.findBySource(source));
  }
}
