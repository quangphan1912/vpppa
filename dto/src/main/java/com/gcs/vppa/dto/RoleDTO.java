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

import java.util.List;

/**
 * The Class Role.
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
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(callSuper = true)

/**
 * Instantiates a new role DTO.
 */
@NoArgsConstructor
public class RoleDTO extends BaseDTO<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 318831807718728344L;

  /** The role id. */
  private Integer roleId;

  /** The name. */
  private String name;

  /** The description. */
  private String description;

  /** The users. */
  private List<VppaUserDTO> users;

  /** The role privileges. */
  private List<RolePrivilegeDTO> rolePrivileges;

  /**
   * {@inheritDoc}
   * @see com.gcs.vppa.common.base.BaseDTO#getIdentifier()
   */
  @Override
  @JsonIgnore
  public Integer getIdentifier() {
    return this.roleId;
  }

  /**
   * {@inheritDoc}
   * @see com.gcs.vppa.common.base.BaseDTO#setIdentifier(java.lang.Object)
   */
  @Override
  @JsonIgnore
  public void setIdentifier(Integer id) {
    this.roleId = id;
  }
}
