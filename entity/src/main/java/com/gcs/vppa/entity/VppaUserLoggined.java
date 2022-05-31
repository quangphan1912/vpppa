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
package com.gcs.vppa.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tbl_user_loggined")

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

/**
 * Instantiates a new vppa user loggined.
 */
@NoArgsConstructor
public class VppaUserLoggined extends BaseEntity<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", unique = true)
  private int id;
  
  /** The user_id. */
  @Column(name = "user_id", updatable = false, insertable = false)
  private Integer userId;

  /** The user. */
  @ManyToOne(fetch = FetchType.EAGER, optional = true)
  @JoinColumn(name = "user_id")
  private VppaUser user;

  /** The token. */
  @Column(name = "token", unique = true)
  private String token;

  /** The last accessed time. */
  @Column(name = "last_accessed_time")
  private Timestamp lastAccessedTime;
  
  /** The is_expired. */
  @Column(name = "is_expired")
  private boolean isExpired;

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

  /**
   * Gets the identifier.
   *
   * @return the identifier
   */
  @Override
  public Integer getIdentifier() {
    return null;
  }
}
