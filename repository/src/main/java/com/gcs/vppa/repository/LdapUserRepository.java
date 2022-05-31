/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 28, 2020     ********           Administrator            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gcs.vppa.common.repository.BaseRepository;
import com.gcs.vppa.entity.LdapUser;

/**
 * The Interface UserRepository.
 */
@Repository
public interface LdapUserRepository extends BaseRepository<LdapUser, Integer> {

  /**
   * Find by email.
   *
   * @param email the email
   * @return the list
   */
  List<LdapUser> findByEmail(String email);
  
  /**
   * Find by email and password.
   *
   * @param email the email
   * @return the list
   */
  LdapUser findByEmailAndPassword(String email, String password);
}
