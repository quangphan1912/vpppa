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
package com.gcs.vppa.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class RequestLoginDTO.
 *
 * @author Administrator
 */
/**
 * Gets the role privileges.
 *
 * @return the role privileges
 */
@Getter

/**
 * Sets the role privileges.
 *
 * @param rolePrivileges the new role privileges
 */
@Setter
/**
 * Instantiates a new role DTO.
 */
@NoArgsConstructor
public class RequestLoginDTO {

  /** The email. */
  private String email;

  /** The password. */
  private String password;
}
