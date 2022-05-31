/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 18, 2020     ********           hangttran.ht            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.core.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.gcs.vppa.common.service.BaseDataServiceImpl;
import com.gcs.vppa.core.converter.SchemeStatusConverter;
import com.gcs.vppa.dto.SchemeStatusDTO;
import com.gcs.vppa.entity.SchemeStatus;
import com.gcs.vppa.repository.SchemeStatusRepository;

import lombok.NoArgsConstructor;

/**
 * The class SchemeStatusServiceImpl.
 * 
 * @author hangttran.ht
 *
 */
/**
 * Instantiates a new SchemeStatus service impl.
 */
@NoArgsConstructor
@Service
@ComponentScan
public class SchemeStatusServiceImpl extends
    BaseDataServiceImpl<Integer, SchemeStatus, SchemeStatusDTO, SchemeStatusRepository, SchemeStatusConverter>
    implements SchemeStatusService {

  @Override
  public String getEntityName() {
    return "SchemeStatus";
  }

}
