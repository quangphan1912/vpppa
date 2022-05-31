/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 25, 2020     ********           linhnkl.bq            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.repository;

import org.springframework.stereotype.Repository;

import com.gcs.vppa.common.repository.BaseRepository;
import com.gcs.vppa.entity.UserRole;

/**
 * @author linhnkl.bq
 *
 */
/**
 * The Interface UserRoleReponsitory.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */
@Repository
public interface UserRoleReponsitory extends BaseRepository<UserRole, Integer> {
  /**
   * find by parameter name.
   *
   * @param paramName
   *          the parameter name
   * @return parameter
   */
  UserRole findByName(String name);
}
