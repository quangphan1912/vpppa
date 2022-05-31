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
package com.gcs.vppa.core.service;

import com.gcs.vppa.common.service.BaseDataService;
import com.gcs.vppa.core.converter.LdapUserConverter;
import com.gcs.vppa.dto.LdapUserDTO;
import com.gcs.vppa.entity.LdapUser;
import com.gcs.vppa.repository.LdapUserRepository;

/**
 * @author Administrator
 *
 */
public interface LdapUserDataService extends
BaseDataService<Integer, LdapUser, LdapUserDTO, LdapUserRepository, LdapUserConverter> {
  
  /**
   * Gets the all master data.
   *
   * @param email the email
   * @return MasterDataParameter DTO
   */
  LdapUserDTO findByEmail(String email);
  
  /**
   * Find by email and password.
   *
   * @param email the email
   * @param password the password
   * @return the ldap user DTO
   */
  String login(String email, String password);
}
