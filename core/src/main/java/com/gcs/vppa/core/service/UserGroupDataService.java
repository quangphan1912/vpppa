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

import com.gcs.vppa.common.service.BaseDataService;
import com.gcs.vppa.core.converter.UserGroupConverter;
import com.gcs.vppa.dto.UserGroupDTO;
import com.gcs.vppa.entity.UserGroup;
import com.gcs.vppa.repository.UserGroupReponsitory;

/**
 * The Interface UserGroupDataService.
 *
 * @author Administrator
 */
/**
 * @author linhnkl.bq
 *
 */
public interface UserGroupDataService
  extends BaseDataService<Integer, UserGroup, UserGroupDTO, UserGroupReponsitory, UserGroupConverter> {

  /**
   * Find by parameter name.
   *
   * @param name the name
   * @return the parameter DTO
   */
  UserGroupDTO findByName(String name);

  /**
   * Find by source.
   *
   * @param userRole the user role
   * @return the parameter DTO
   */
  UserGroupDTO findByUserGroup(String userRole);
}
