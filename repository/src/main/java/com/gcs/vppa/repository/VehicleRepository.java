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
package com.gcs.vppa.repository;

import com.gcs.vppa.common.repository.BaseRepository;
import com.gcs.vppa.entity.Vehicle;
import org.springframework.stereotype.Repository;

/**
 * The Interface VehicleRepository.
 */
@Repository
public interface VehicleRepository extends BaseRepository<Vehicle, Integer> {

  /**
   * Find by name.
   *
   * @param name the name
   * @return the vehicle
   */
  Vehicle findByName(String name);

  /**
   * Find by register number.
   *
   * @param registerNumber the register number
   * @return the vehicle
   */
  Vehicle findByRegisterNumber(String registerNumber);

}
