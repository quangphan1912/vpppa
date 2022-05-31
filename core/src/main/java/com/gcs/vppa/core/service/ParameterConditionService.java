/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 27, 2020     ********           hangttran.ht            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.core.service;

import java.util.List;

import com.gcs.vppa.common.service.BaseDataService;
import com.gcs.vppa.core.converter.ParameterConditionConverter;
import com.gcs.vppa.dto.ParameterConditionDTO;
import com.gcs.vppa.entity.ParameterCondition;
import com.gcs.vppa.repository.ParameterConditionRepository;

/**
 * The interface ParameterConditionService.
 * 
 * @author hangttran.ht
 */
public interface ParameterConditionService extends
    BaseDataService<Integer, ParameterCondition, ParameterConditionDTO, ParameterConditionRepository, ParameterConditionConverter> {

  /**
   * Find by name.
   *
   * @param name the name
   * @return the ParameterCondition DTO
   */
  List<ParameterConditionDTO> findByName(String name);

  /**
   * Find by parameterId.
   *
   * @param parameterId
   * @return the ParameterCondition DTO
   */
  List<ParameterConditionDTO> findByParameterId(int parameterId);

}
