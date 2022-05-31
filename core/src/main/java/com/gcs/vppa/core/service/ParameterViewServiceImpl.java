/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 15, 2020     ********           hangttran.ht            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.core.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.gcs.vppa.common.service.BaseDataServiceImpl;
import com.gcs.vppa.core.converter.ParameterViewConverter;
import com.gcs.vppa.dto.ParameterViewDTO;
import com.gcs.vppa.entity.ParameterView;
import com.gcs.vppa.repository.ParameterViewRepository;

import lombok.NoArgsConstructor;

/** The class ParameterViewServiceImpl.
 * 
 * @author hangttran.ht
 *
 */
/**
 * Instantiates a new scheme data service impl.
 */
@NoArgsConstructor
@Service
@ComponentScan
public class ParameterViewServiceImpl extends
    BaseDataServiceImpl<Integer, ParameterView, ParameterViewDTO, ParameterViewRepository, ParameterViewConverter>
    implements ParameterViewService {

  /**
   * Gets the entity name.
   *
   * @return the entity name
   */
  @Override
  public String getEntityName() {
    return "ParameterView";
  }

}
