/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 27, 2020     ********           linhnkl.bq            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.core.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.gcs.vppa.common.constant.Constants;
import com.gcs.vppa.common.constant.EnumsCommon.Enum.Status;
import com.gcs.vppa.common.service.BaseDataServiceImpl;
import com.gcs.vppa.common.service.CryptoService;
import com.gcs.vppa.common.service.I18nMessageService;
import com.gcs.vppa.common.util.CryptoUtils;
import com.gcs.vppa.core.converter.VppaUserConverter;
import com.gcs.vppa.core.converter.VppaUserLogginedConverter;
import com.gcs.vppa.core.exception.LdapLoginFailException;
import com.gcs.vppa.core.exception.NotExistVppaUserException;
import com.gcs.vppa.dto.VppaUserDTO;
import com.gcs.vppa.dto.VppaUserLogginedDTO;
import com.gcs.vppa.entity.VppaUser;
import com.gcs.vppa.entity.VppaUserLoggined;
import com.gcs.vppa.repository.VppaUserLogginedRepository;
import com.gcs.vppa.repository.VppaUserRepository;
import com.microsoft.sqlserver.jdbc.StringUtils;

import lombok.NoArgsConstructor;

/**
 * The Class UserDataServiceImpl.
 *
 * @author linhnkl.bq
 */

/**
 * Instantiates a new user data service impl.
 */
@NoArgsConstructor
@Service
@ComponentScan
public class VppaUserDataServiceImpl extends
  BaseDataServiceImpl<Integer, VppaUser, VppaUserDTO, VppaUserRepository, VppaUserConverter>
  implements VppaUserDataService {

  /** The vppa user loggined repository. */
  @Autowired
  private VppaUserLogginedRepository vppaUserLogginedRepository;

  /** The vppa user loggined converter. */
  @Autowired
  private VppaUserLogginedConverter vppaUserLogginedConverter;

  /** The ldap user data service. */
  @Autowired
  private LdapUserDataService ldapUserDataService;

  /** The crypto service. */
  @Autowired
  private CryptoService cryptoService;

  /** The user role data service. */
  @Autowired
  private UserRoleDataService userRoleDataService;

  /** The i 18 n message service. */
  @Autowired
  protected I18nMessageService i18nMessageService;

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
   * @return the vppa user DTO
   */
  @Override
  public VppaUserDTO findByEmail(String email) {
    VppaUser entity = this.repository.findByEmail(email);

    if (entity == null) {
      return null;
    }

    return this.converter.toDto(entity);
  }

  /**
   * Find by token.
   *
   * @param token the token
   * @return the vppa user loggined DTO
   */
  @Override
  public VppaUserLogginedDTO findByToken(String token) {
    return this.vppaUserLogginedConverter.toDto(vppaUserLogginedRepository.findByToken(token));
  }

  /**
   * Login.
   *
   * @param email the email
   * @param password the password
   * @return the vppa user loggined DTO
   */
  @Override
  public VppaUserLogginedDTO login(String email, String password) {
    String validEmail = email.trim();
    String validPassword = password.trim();

    // get user login in vppa system.
    VppaUser vppaUser = this.getUserInVppaSystem(validEmail);

    // check exist user in the vppa system.
    if (!this.isExistedUserInVppaSystem(vppaUser)) {
      throw new NotExistVppaUserException();
    }

    // check status user
    if (vppaUser != null && vppaUser.getStatus().equalsIgnoreCase(Status.INACTIVE.getValue())) {
      // The user cannot log in system
      throw new LdapLoginFailException();
    }

    // ldap login.
    String token = this.loginLdap(validEmail, validPassword);

    // save login session.
    return this.vppaUserLogginedConverter
      .toDto(this.vppaUserLogginedRepository.save(
        this.buildUserLogined(token, vppaUser)), true);
  }

  /**
   * Update user loggined.
   *
   * @param vppaUserLogginedDTO the vppa user loggined DTO
   */
  @Override
  public void updateUserLoggined(VppaUserLogginedDTO vppaUserLogginedDTO) {
    this.vppaUserLogginedRepository.save(
      this.vppaUserLogginedConverter.toEntity(vppaUserLogginedDTO));
  }

  /**
   * Logout.
   *
   * @param token the token
   * @return true, if successful
   */
  @Override
  public void logout(String token) {
    VppaUserLoggined userlogginedDeleted = this.vppaUserLogginedRepository.findByToken(token);
    
    if (userlogginedDeleted != null) {
      this.vppaUserLogginedRepository.delete(userlogginedDeleted);
    }
  }

  /**
   * Update.
   *
   * @param dto the dto
   * @return the vppa user DTO
   */
  @Override
  public VppaUserDTO update(VppaUserDTO dto) {
    int userRoleId = CryptoUtils.decryptId(dto.getUserRole().getEncryptedId(), cryptoService.getUserRoleKey());
    int userId = CryptoUtils.decryptId(dto.getEncryptedId(), cryptoService.getVppaUserKey());
    VppaUser oldUser = this.repository.findByIdentifier(userId);

    if (dto.getUserType().getValue() != Constants.ADMIN_TYPE && oldUser.getUserRoleId() != userRoleId) {
      // update user loggin info of this user.
      this.userRoleDataService.updateUserLogginedByUser(oldUser.getUserLoggineds(), dto.getUpdatedBy());
    }

    // update user.
    return super.update(userId, dto);
  }

  /**
   * Login ldap.
   *
   * @param email the email
   * @param password the password
   * @return the string
   */
  private String loginLdap(String email, String password) {
    String token = ldapUserDataService.login(email, password);

    if (StringUtils.isEmpty(token)) {
      throw new LdapLoginFailException();
    }

    return token;
  }

  /**
   * Builds the user logined.
   *
   * @param email the email
   * @param token the token
   * @return the vppa user loggined
   */
  private VppaUserLoggined buildUserLogined(String token, VppaUser vppaUser) {
    VppaUserLoggined logginedUser = new VppaUserLoggined();

    Timestamp now = java.sql.Timestamp.valueOf(LocalDateTime.now());

    logginedUser.setToken(token);
    logginedUser.setLastAccessedTime(now);
    logginedUser.setUserId(vppaUser.getId());
    logginedUser.setUser(vppaUser);

    logginedUser.setCreatedDate(now);
    logginedUser.setCreatedBy(vppaUser.getId());

    logginedUser.setUpdatedBy(null);
    logginedUser.setUpdatedDate(null);

    return logginedUser;
  }

  /**
   * Checks if is existed user in vppa system.
   *
   * @param email the email
   * @return true, if is existed user in vppa system
   */
  private boolean isExistedUserInVppaSystem(VppaUser user) {
    return user != null;
  }

  /**
   * Gets the user in vppa system.
   *
   * @param email the email
   * @return the user in vppa system
   */
  private VppaUser getUserInVppaSystem(String email) {
    return this.repository.findByEmail(email);
  }
}
