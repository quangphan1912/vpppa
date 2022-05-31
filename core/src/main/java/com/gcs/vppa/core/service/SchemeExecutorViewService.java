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

import com.gcs.vppa.common.service.BaseDataService;
import com.gcs.vppa.core.converter.SchemeExecutorViewConverter;
import com.gcs.vppa.dto.SchemeExecutorViewDTO;
import com.gcs.vppa.entity.SchemeExecutorView;
import com.gcs.vppa.repository.SchemeExecutorViewRepository;

/**
 * The interface SchemeExecutorViewService.
 * 
 * @author hangttran.ht
 *
 */
public interface SchemeExecutorViewService extends
BaseDataService<Integer, SchemeExecutorView, SchemeExecutorViewDTO, SchemeExecutorViewRepository, SchemeExecutorViewConverter> {

}
