/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * July 27, 2020     ********           PhoVo            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.dto;

import java.time.LocalDateTime;

import com.gcs.vppa.common.base.BaseDTO;
import com.gcs.vppa.common.dto.UserType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class User.
 */

/**
 * Gets the last updated.
 *
 * @return the last updated
 */
@Getter

/**
 * Sets the last updated.
 *
 * @param lastUpdated the new last updated
 */
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@NoArgsConstructor
public class VppaUserDTO extends BaseDTO<Integer> {
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  private Integer id;

  /** The encrypted id. */
  private String encryptedId;

  /** The email. */
  private String email;

  /** The fullname. */
  private String fullname;

  /** The title. */
  private String title;

  /** The userRole. */
  private Integer userRoleId;

  /** The userRoleId. */
  private UserRoleDTO userRole;

  /** The status. */
  private String status;

  /** The remark. */
  private String remark;

  /** The createdDate. */
  private LocalDateTime createdDate;

  /** The createdBy. */
  private Integer createdBy;

  /** The updatedDate. */
  private LocalDateTime updatedDate;

  /** The updatedBy. */
  private Integer updatedBy;

  /** The user type. */
  private UserType userType = UserType.NORMAL;

  /**
   * Gets the identifier.
   *
   * @return the identifier
   */
  @Override
  public Integer getIdentifier() {
    return id;
  }

  /**
   * Sets the identifier.
   *
   * @param id the new identifier
   */
  @Override
  public void setIdentifier(Integer id) {
    this.id = id;
  }
}
