/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * July 27, 2020     ********           hieuxd            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.gcs.vppa.common.base.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class UserGroup.
 *
 * @author hieuxd
 */
@Entity
@Table(name = "tbl_user_group")

/**
 * Gets the user_group.
 *
 * @return the user_group
 */
@Getter

/**
 * Sets the user_group.
 *
 * @param user_group
 *          the new user_group
 */
@Setter

/**
 * Instantiates a new user_group.
 */
@NoArgsConstructor
public class UserGroup extends BaseEntity<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 8107092578377879894L;

  /** The param id. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", unique = true)
  private int id;

  /** The code. */
  @Column(name = "code", length = 128, nullable = false)
  private String code;

  /** The name. */
  @Column(name = "name", length = 128, nullable = false)
  private String name;

  /** The description. */
  @Column(name = "description", length = 128)
  private String description;

  /** The list user roles. */
  @OneToMany(mappedBy = "userGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<UserRole> userRoles;

  /**
   * {@inheritDoc}
   *
   * @see com.gcs.vppa.common.base.BaseEntity#getIdentifier()
   */
  @Override
  public Integer getIdentifier() {
    return this.id;
  }

  /**
   * Instantiates a new vehicle.
   *
   * @param id
   *          the UserGroup id
   */
  public UserGroup(Integer id) {
    this.id = id;
  }
}
