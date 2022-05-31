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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gcs.vppa.common.base.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Gets the privilege name.
 *
 * @return the privilege name
 */
@Getter

/**
 * Sets the privilege name.
 *
 * @param privilegeName the new privilege name
 */
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(callSuper = true)

/**
 * Instantiates a new role privilege DTO.
 */
@NoArgsConstructor
public class RolePrivilegeDTO extends BaseDTO<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 2612277626105148996L;

  /** The role privilege id. */
  private Integer rolePrivilegeId;

  /** The role id. */
  private Integer roleId;

  /** The privilege id. */
  private Integer privilegeId;

  /** The role name. */
  private String roleName;

  /** The Name of Privilege. */
  private String privilegeName;

  /**
   * {@inheritDoc}
   * @see com.gcs.vppa.common.base.BaseDTO#getIdentifier()
   */
  @Override
  @JsonIgnore
  public Integer getIdentifier() {
    return this.rolePrivilegeId;
  }

  /**
   * {@inheritDoc}
   * @see com.gcs.vppa.common.base.BaseDTO#setIdentifier(java.lang.Object)
   */
  @Override
  @JsonIgnore
  public void setIdentifier(Integer id) {
    this.rolePrivilegeId = id;
  }
}
