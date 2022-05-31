/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Sep 3, 2020     ********           Administrator            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.repository;

import com.gcs.vppa.common.repository.BaseRepository;
import com.gcs.vppa.entity.VppaUserLoggined;

/**
 * The Interface VppaUserLogginedRepository.
 *
 * @author Administrator
 */
public interface VppaUserLogginedRepository extends BaseRepository<VppaUserLoggined, Integer> {

  /**
   * Find by token.
   *
   * @param token the token
   * @return the vppa user loggined DTO
   */
  VppaUserLoggined findByToken(String token);

  /**
   * Delete by token.
   *
   * @param token the token
   */
  void deleteByToken(String token);
}
