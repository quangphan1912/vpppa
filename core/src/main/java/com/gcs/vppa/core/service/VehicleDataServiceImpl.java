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

import com.gcs.vppa.common.service.BaseDataServiceImpl;
import com.gcs.vppa.core.converter.VehicleConverter;
import com.gcs.vppa.dto.VehicleDTO;
import com.gcs.vppa.entity.Vehicle;
import com.gcs.vppa.repository.VehicleRepository;
import lombok.NoArgsConstructor;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

/**
 * Instantiates a new vehicle data service impl.
 */
@NoArgsConstructor
@Service
@ComponentScan
public class VehicleDataServiceImpl
  extends BaseDataServiceImpl<Integer, Vehicle, VehicleDTO, VehicleRepository, VehicleConverter>
  implements VehicleDataService {
  /**
   * Gets the entity name.
   *
   * @return the entity name
   */
  @Override
  public String getEntityName() {
    return "Vehicle";
  }

  /**
   * Find by register number.
   *
   * @param registerNumber the register number
   * @return the vehicle DTO
   */
  @Override
  public VehicleDTO findByRegisterNumber(String registerNumber) {
    return this.converter.toDto(this.repository.findByRegisterNumber(registerNumber));
  }
}
