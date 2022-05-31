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
package com.gcs.vppa.repository;

import com.gcs.vppa.common.repository.BaseRepository;
import com.gcs.vppa.entity.UserGroup;

/**
 * The Interface UserGroupReponsitory.
 *
 * @author Administrator
 */
public interface UserGroupReponsitory extends BaseRepository<UserGroup, Integer> {

  /**
   * find by parameter name.
   *
   * @param name the name
   * @return parameter
   */
  UserGroup findByName(String name);
}
