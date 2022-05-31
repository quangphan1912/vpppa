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

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.gcs.vppa.common.base.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class User.
 *
 * @author hieuxd
 */
@Entity
@Table(name = "tbl_user")

/**
 * Gets the user.
 *
 * @return the user
 */

/**
 * Gets the updated by.
 *
 * @return the updated by
 */

/**
 * Gets the updated by.
 *
 * @return the updated by
 */

/**
 * Gets the updated by.
 *
 * @return the updated by
 */
@Getter

/**
 * Sets the user.
 *
 * @param user
 *          the new user
 */

/**
 * Sets the updated by.
 *
 * @param updatedBy the new updated by
 */

/**
 * Sets the updated by.
 *
 * @param updatedBy the new updated by
 */

/**
 * Sets the updated by.
 *
 * @param updatedBy the new updated by
 */
@Setter

/**
 * Instantiates a new user.
 */

/**
 * Instantiates a new user.
 */

/**
 * Instantiates a new user.
 */

/**
 * Instantiates a new user.
 */
@NoArgsConstructor
public class VppaUser extends BaseEntity<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 8107092578377879894L;

  /** The id. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", unique = true)
  private int id;

  /** The email. */
  @Column(name = "email", length = 200, nullable = false)
  private String email;

  /** The fullname. */
  @Column(name = "fullname", length = 200)
  private String fullname;

  /** The title. */
  @Column(name = "title", length = 200)
  private String title;

  /** The userRole. */
  @Column(name = "user_role_id", updatable = false, insertable = false)
  private Integer userRoleId;

  /** The userRoleId. */
  @ManyToOne(fetch = FetchType.EAGER, optional = true)
  @JoinColumn(name = "user_role_id")
  private UserRole userRole;

  /** The status. */
  @Column(name = "status", nullable = false)
  private String status;

  /** The remark. */
  @Column(name = "remark", length = 500)
  private String remark;

  /** The token. */
  @Column(name = "user_type")
  private Integer userType;

  /** The createdDate. */
  @Column(name = "created_date")
  private Timestamp createdDate;

  /** The createdBy. */
  @Column(name = "created_by", length = 10)
  private Integer createdBy;

  /** The updatedDate. */
  @Column(name = "updated_date")
  private Timestamp updatedDate;

  /** The updatedBy. */
  @Column(name = "updated_by", length = 10)
  private Integer updatedBy;
  
  /** The list user. */
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<VppaUserLoggined> userLoggineds;

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
   *          the User id
   */
  public VppaUser(Integer id) {
    this.id = id;
  }
}
