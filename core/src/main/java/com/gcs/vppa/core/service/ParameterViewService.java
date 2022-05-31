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

import com.gcs.vppa.common.service.BaseDataService;
import com.gcs.vppa.core.converter.ParameterViewConverter;
import com.gcs.vppa.dto.ParameterViewDTO;
import com.gcs.vppa.entity.ParameterView;
import com.gcs.vppa.repository.ParameterViewRepository;

/**
 * The Interface SearchParameterService.
 * 
 * @author hangttran.ht
 */
public interface ParameterViewService extends
    BaseDataService<Integer, ParameterView, ParameterViewDTO, ParameterViewRepository, ParameterViewConverter> {

}
