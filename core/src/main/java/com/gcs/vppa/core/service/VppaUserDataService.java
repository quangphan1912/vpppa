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

import com.gcs.vppa.common.service.BaseDataService;
import com.gcs.vppa.core.converter.VppaUserConverter;
import com.gcs.vppa.dto.VppaUserDTO;
import com.gcs.vppa.dto.VppaUserLogginedDTO;
import com.gcs.vppa.entity.VppaUser;
import com.gcs.vppa.repository.VppaUserRepository;

/**
 * @author linhnkl.bq
 *
 */
public interface VppaUserDataService extends
  BaseDataService<Integer, VppaUser, VppaUserDTO, VppaUserRepository, VppaUserConverter> {
  
  /**
   * Find by email.
   *
   * @param email the email
   * @return the vppa user DTO
   */
  VppaUserDTO findByEmail(String email);
  
  /**
   * Find by token.
   *
   * @param token the token
   * @return the vppa user loggined DTO
   */
  VppaUserLogginedDTO findByToken(String token);
  
  /**
   * Login.
   *
   * @param username the username
   * @param password the password
   * @return the vppa user loggined DTO
   */
  VppaUserLogginedDTO login(String username, String password);
  
  /**
   * Logout.
   *
   * @param token the token
   * @return true, if successful
   */
  void logout(String token);
  
  /**
   * Update user loggined.
   *
   * @param vppaUserLogginedDTO the vppa user loggined DTO
   */
  void updateUserLoggined(VppaUserLogginedDTO vppaUserLogginedDTO);
  
  /**
   * Update.
   *
   * @param dto the dto
   * @return the vppa user DTO
   */
  VppaUserDTO update(VppaUserDTO dto);
}
