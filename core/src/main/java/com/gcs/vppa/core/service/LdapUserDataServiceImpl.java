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

import java.time.LocalDateTime;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.gcs.vppa.common.service.BaseDataServiceImpl;
import com.gcs.vppa.common.util.CryptoUtils;
import com.gcs.vppa.core.converter.LdapUserConverter;
import com.gcs.vppa.dto.LdapUserDTO;
import com.gcs.vppa.entity.LdapUser;
import com.gcs.vppa.entity.VppaUser;
import com.gcs.vppa.repository.LdapUserRepository;

import lombok.NoArgsConstructor;

/**
 * @author Administrator
 *
 */
@NoArgsConstructor
@Service
@ComponentScan
public class LdapUserDataServiceImpl extends
  BaseDataServiceImpl<Integer, LdapUser, LdapUserDTO, LdapUserRepository, LdapUserConverter>
  implements LdapUserDataService {

  /** The login token key. */
  private static String LOGIN_TOKEN_KEY = "vppa-login-token-ken-%$(17460)jeid";

  /**
   * Gets the entity name.
   *
   * @return the entity name
   */
  @Override
  public String getEntityName() {
    return VppaUser.class.getSimpleName();
  }

  /**
   * Find by email.
   *
   * @param email the email
   * @return the ldap user DTO
   */
  @Override
  public LdapUserDTO findByEmail(String email) {
    return null;
  }

  /**
   * Login.
   *
   * @param email the email
   * @param password the password
   * @return the string
   */
  @Override
  public String login(String email, String password) {
    // get ldap user by salt.
    LdapUser ldapUser = this.repository.findByEmailAndPassword(email, password);

    return buildLdapLoginToken(ldapUser);
  }

  /**
   * Generate ldap login token.
   *
   * @param ldapUser the ldap user
   * @return the string
   */
  private String buildLdapLoginToken(LdapUser ldapUser) {
    if (ldapUser == null) {
      return null;
    }

    return CryptoUtils.hash(String.format("%s-%s-%s-%s",
      ldapUser.getEmail(), ldapUser.getPassword(), LocalDateTime.now(), LOGIN_TOKEN_KEY));
  }
}
