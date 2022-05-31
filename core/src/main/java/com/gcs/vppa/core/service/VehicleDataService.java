/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * July 27, 2020     ********           PhoVo            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.core.service;

import com.gcs.vppa.common.service.BaseDataService;
import com.gcs.vppa.core.converter.VehicleConverter;
import com.gcs.vppa.dto.VehicleDTO;
import com.gcs.vppa.entity.Vehicle;
import com.gcs.vppa.repository.VehicleRepository;

/**
 * The Interface VehicleDataService.
 */
public interface VehicleDataService
  extends BaseDataService<Integer, Vehicle, VehicleDTO, VehicleRepository, VehicleConverter> {

  /**
   * Find by register number.
   *
   * @param registerNumber the register number
   * @return the vehicle DTO
   */
  VehicleDTO findByRegisterNumber(String registerNumber);

}
