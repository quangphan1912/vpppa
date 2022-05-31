/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 26, 2020     ********           Administrator            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.core.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.gcs.vppa.common.service.BaseDataServiceImpl;
import com.gcs.vppa.core.converter.UserGroupConverter;
import com.gcs.vppa.dto.UserGroupDTO;
import com.gcs.vppa.entity.UserGroup;
import com.gcs.vppa.repository.UserGroupReponsitory;

import lombok.NoArgsConstructor;

/**
 * @author Administrator
 *
 */
@NoArgsConstructor
@Service
@ComponentScan
public class UserGroupDataServiceImpl
  extends BaseDataServiceImpl<Integer, UserGroup, UserGroupDTO, UserGroupReponsitory, UserGroupConverter>
  implements UserGroupDataService {

  /**
   * Gets the entity name.
   *
   * @return the entity name
   */
  @Override
  public String getEntityName() {
    return UserGroup.class.getSimpleName();
  }

  /**
   * Find by name.
   *
   * @param name the name
   * @return the user role DTO
   */
  @Override
  public UserGroupDTO findByName(String name) {
    return this.converter.toDto(this.repository.findByName(name));
  }

  /**
   * Find by user group.
   *
   * @param userRole the user role
   * @return the user role DTO
   */
  @Override
  public UserGroupDTO findByUserGroup(String userRole) {
    return null;
  }

}
