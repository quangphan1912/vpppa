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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gcs.vppa.common.base.BaseDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class UserRole.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */

/**
 * Gets the UserRole.
 *
 * @return the UserRole
 */
@Getter

/**
 * Sets the UserRole
 *
 * @param UserRole the new UserRole
 */
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(callSuper = true)
public class UserRoleDTO extends BaseDTO<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  private Integer id;

  /** The encrypted id. */
  private String encryptedId;

  /** The name. */
  private String name;

  /** The description. */
  private String description;

  /** The user group Id. */
  private Integer userGroupId;

  /** The user group. */
  private UserGroupDTO userGroup;

  /** The permission. */
  private int[] permission;
  
  /** The permission items. */
  private PermissionDTO[] permissionItems;

  /** The createdDate. */
  private LocalDateTime createdDate;

  /** The createdBy. */
  private Integer createdBy;

  /** The updatedDate. */
  private LocalDateTime updatedDate;

  /** The updatedBy. */
  private Integer updatedBy;

  /**
   * {@inheritDoc}
   * 
   * @see com.gcs.vppa.common.base.BaseDTO#getIdentifier()
   */
  @Override
  @JsonIgnore
  public Integer getIdentifier() {
    return this.id;
  }

  /**
   * {@inheritDoc}
   * 
   * @see com.gcs.vppa.common.base.BaseDTO#setIdentifier(java.lang.Object)
   */
  @Override
  @JsonIgnore
  public void setIdentifier(Integer id) {
    this.id = id;
  }
}

