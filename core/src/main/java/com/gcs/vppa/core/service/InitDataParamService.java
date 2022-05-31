/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 13, 2020     ********           hangttran.ht            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.core.service;

import com.gcs.vppa.dto.MasterDataParameterDTO;

/**
 * The Interface InitDataParamService.
 * 
 * @author hangttran.ht
 */
public interface InitDataParamService {

  /**
   * Gets the all master data.
   *
   * @return MasterDataParameter DTO
   */
  MasterDataParameterDTO getAllMasterData();

}
