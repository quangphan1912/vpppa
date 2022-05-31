/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 29, 2020     ********           hangttran.ht            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.core.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.gcs.vppa.common.service.BaseDataServiceImpl;
import com.gcs.vppa.core.converter.SchemeExecutorViewConverter;
import com.gcs.vppa.dto.SchemeExecutorViewDTO;
import com.gcs.vppa.entity.SchemeExecutorView;
import com.gcs.vppa.repository.SchemeExecutorViewRepository;

import lombok.NoArgsConstructor;

/**
 * The class SchemeExecutorViewServiceImpl.
 * 
 * @author hangttran.ht
 */
/**
 * Instantiates a new SchemeExecutorView service impl.
 */
@NoArgsConstructor
@Service
@ComponentScan
public class SchemeExecutorViewServiceImpl extends
    BaseDataServiceImpl<Integer, SchemeExecutorView, SchemeExecutorViewDTO, SchemeExecutorViewRepository, SchemeExecutorViewConverter>
    implements SchemeExecutorViewService {

  @Override
  public String getEntityName() {
    return "SchemeExecutorView";
  }

}
