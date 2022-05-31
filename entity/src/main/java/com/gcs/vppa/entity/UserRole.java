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

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.gcs.vppa.common.base.BaseEntity;
import com.gcs.vppa.common.entity.arraytype.IntArrayType;
import com.gcs.vppa.common.entity.arraytype.StringArrayType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class UserRole.
 *
 * @author hieuxd
 */
@Entity
@Table(name = "tbl_user_role")

/**
 * Gets the user_role.
 *
 * @return the user_role
 */
@Getter

/**
 * Sets the user_role.
 *
 * @param user_role
 *          the new user_role
 */
@Setter

/**
 * Instantiates a new user_role.
 */
@NoArgsConstructor
@TypeDefs({@TypeDef(name = "stringArray", typeClass = StringArrayType.class),
  @TypeDef(name = "intArray", typeClass = IntArrayType.class)})
public class UserRole extends BaseEntity<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 8107092578377879894L;

  /** The param id. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", unique = true)
  private int id;

  /** The name. */
  @Column(name = "name", length = 200, nullable = false)
  private String name;

  /** The description. */
  @Column(name = "description", length = 4000)
  private String description;

  /** The user Group Id. */
  @Column(name = "user_group_id", length = 10, updatable = false, insertable = false)
  private Integer userGroupId;

  /** The user Group Id. */
  @ManyToOne(fetch = FetchType.EAGER, optional = true)
  @JoinColumn(name = "user_group_id")
  private UserGroup userGroup;

  /** The permission. */
  @Type(type = "intArray")
  @Column(name = "permission", columnDefinition = "integer[]")
  private int[] permission;

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
  @OneToMany(mappedBy = "userRole", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<VppaUser> users;

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
   *          the UserRole id
   */
  public UserRole(Integer id) {
    this.id = id;
  }
}
