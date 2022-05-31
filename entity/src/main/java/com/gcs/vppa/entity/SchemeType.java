/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 5, 2020     ********           hangttran.ht            Initialize                  
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
 * The class schemetype
 * 
 * @author hangttran.ht
 *
 */
@Entity
@Table(name = "TBL_SCHEME_TYPE")

/**
 * Gets the scheme type.
 *
 * @return the scheme type
 */
@Getter

/**
 * Sets the scheme type.
 *
 * @param schemeType the new scheme type
 */
@Setter

/**
 * Instantiates a new scheme type.
 */
@NoArgsConstructor
public class SchemeType extends BaseEntity<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 8107092578377879894L;

  /** The type id. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", unique = true)
  private Integer id;

  /** The code. */
  @Column(name = "CODE", length = 128)
  private String code;

  /** The name. */
  @Column(name = "NAME", length = 128)
  private String name;

  /** The params. */
  @OneToMany(mappedBy = "schemeType", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Scheme> schemes;

  /**
   * Instantiates a new type.
   *
   * @param typeId
   *          the type id
   */
  public SchemeType(Integer id) {
    this.id = id;
  }

  /**
   * {@inheritDoc}
   * @see com.gcs.vppa.common.base.BaseEntity#getIdentifier()
   */
  @Override
  public Integer getIdentifier() {
    return this.id;
  }

}
