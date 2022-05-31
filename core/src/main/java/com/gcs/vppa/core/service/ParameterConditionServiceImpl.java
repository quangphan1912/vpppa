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

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.gcs.vppa.common.service.BaseDataServiceImpl;
import com.gcs.vppa.core.converter.ParameterConditionConverter;
import com.gcs.vppa.dto.ParameterConditionDTO;
import com.gcs.vppa.entity.ParameterCondition;
import com.gcs.vppa.repository.ParameterConditionRepository;

import lombok.NoArgsConstructor;

/**
 * The class ParameterConditionServiceImpl.
 * 
 * @author hangttran.ht
 *
 */
/**
 * Instantiates a new ParameterCondition service impl.
 */
@NoArgsConstructor
@Service
@ComponentScan
public class ParameterConditionServiceImpl extends
    BaseDataServiceImpl<Integer, ParameterCondition, ParameterConditionDTO, ParameterConditionRepository, ParameterConditionConverter>
    implements ParameterConditionService {

  /**
   * Get entity name.
   */
  @Override
  public String getEntityName() {
    return "ParameterCondition";
  }

  @Override
  public List<ParameterConditionDTO> findByParameterId(int parameterId) {
    List<ParameterCondition> objectEntitys = this.repository.findByParameterId(parameterId);
    List<ParameterConditionDTO> objectDtos = new ArrayList<>(objectEntitys.size());
    for (ParameterCondition object : objectEntitys) {
      objectDtos.add(this.converter.toDto(object));
    }
    return objectDtos;
  }

  @Override
  public List<ParameterConditionDTO> findByName(String name) {
    List<ParameterCondition> objectEntitys = this.repository.findByName(name);
    List<ParameterConditionDTO> objectDtos = new ArrayList<>(objectEntitys.size());
    for (ParameterCondition object : objectEntitys) {
      objectDtos.add(this.converter.toDto(object));
    }
    return objectDtos;
  }

}
