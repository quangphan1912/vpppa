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

import com.gcs.vppa.common.service.BaseDataService;
import com.gcs.vppa.core.converter.SchemeStatusConverter;
import com.gcs.vppa.dto.SchemeStatusDTO;
import com.gcs.vppa.entity.SchemeStatus;
import com.gcs.vppa.repository.SchemeStatusRepository;

/**
 * The interface SchemeStatusService.
 * 
 * @author hangttran.ht
 *
 */
public interface SchemeStatusService extends BaseDataService<Integer, SchemeStatus, SchemeStatusDTO, SchemeStatusRepository, SchemeStatusConverter> {

}
