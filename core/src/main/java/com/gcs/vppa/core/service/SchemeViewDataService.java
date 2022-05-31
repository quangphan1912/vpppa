/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 19, 2020     ********           hangttran.ht            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.core.service;

import com.gcs.vppa.common.service.BaseDataService;
import com.gcs.vppa.core.converter.SchemeViewConverter;
import com.gcs.vppa.dto.SchemeViewDTO;
import com.gcs.vppa.entity.SchemeView;
import com.gcs.vppa.repository.SchemeViewRepository;

import java.util.List;

/**
 * The interface SchemeViewDataService.
 * 
 * @author hangttran.ht
 */
public interface SchemeViewDataService extends
    BaseDataService<Integer, SchemeView, SchemeViewDTO, SchemeViewRepository, SchemeViewConverter> {

    List<String> findAllByProcessIdAndStatus(Integer processId, String status);
}
