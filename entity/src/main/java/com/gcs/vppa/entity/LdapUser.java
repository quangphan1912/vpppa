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
package com.gcs.vppa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gcs.vppa.common.base.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class LdapUser.
 *
 * @author hieuxd
 */
@Entity
@Table(name = "tbl_ldap_user")

/**
 * Gets the ldap_user.
 *
 * @return the ldap_user
 */
@Getter

/**
 * Sets the ldap_user.
 *
 * @param ldap_user
 *          the new ldap_user
 */
@Setter

/**
 * Instantiates a new ldap_user.
 */
@NoArgsConstructor
public class LdapUser extends BaseEntity<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 8107092578377879894L;

  /** The param id. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", unique = true)
  private int id;

  /** The email. */
  @Column(name = "email", length = 200, nullable = false, unique = true)
  private String email;

  /** The fullname. */
  @Column(name = "fullname", length = 200)
  private String fullname;

  /** The title. */
  @Column(name = "title", length = 200)
  private String title;
  
  /** The salt. */
  @Column(name = "password", length = 200)
  private String password;

  /**
   * {@inheritDoc}
   *
   * @see com.gcs.vppa.common.base.BaseEntity#getIdentifier()
   */
  @Override
  public Integer getIdentifier() {
    return 0;
  }

  /**
   * Instantiates a new vehicle.
   *
   * @param id
   *          the LdapUser id
   */
  public LdapUser(Integer id) {
  }
}
