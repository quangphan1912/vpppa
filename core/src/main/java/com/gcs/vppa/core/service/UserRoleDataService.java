/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 26, 2020     ********           linhnkl.bq            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.core.service;

import java.util.List;

import com.gcs.vppa.common.service.BaseDataService;
import com.gcs.vppa.core.converter.UserRoleConverter;
import com.gcs.vppa.dto.UserRoleDTO;
import com.gcs.vppa.entity.UserRole;
import com.gcs.vppa.entity.VppaUserLoggined;
import com.gcs.vppa.repository.UserRoleReponsitory;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserRoleDataService.
 *
 * @author linhnkl.bq
 */
public interface UserRoleDataService extends BaseDataService<Integer, UserRole, UserRoleDTO, UserRoleReponsitory, UserRoleConverter>{

  /**
   * Find by parameter name.
   *
   * @param name the name
   * @return the parameter DTO
   */
  UserRoleDTO findByName(String name);

  /**
   * Find by source.
   *
   * @param userRole the user role
   * @return the parameter DTO
   */
  UserRoleDTO findByUserGroup(String userRole);

  String checkIdAndDelete(Integer decryptId);
  
  /**
   * Update.
   *
   * @param id the id
   * @param dto the dto
   * @param updatedBy the updated by
   * @return the user role DTO
   */
  UserRoleDTO update(Integer id, UserRoleDTO dto, Integer updatedBy);
  
  /**
   * Update user loggined.
   *
   * @param entity the entity
   * @param updatedBy the updated by
   */
  void updateUserLoggined(UserRole entity, Integer updatedBy);
  
  /**
   * Update user loggined by user.
   *
   * @param userLoggineds the user loggineds
   * @param updatedBy the updated by
   */
  void updateUserLogginedByUser(List<VppaUserLoggined> userLoggineds, Integer updatedBy);
  
}
