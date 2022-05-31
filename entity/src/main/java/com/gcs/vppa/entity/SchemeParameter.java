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
import com.opencsv.bean.CsvBindByName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author hangttran.ht
 */
@Entity
@Table(name = "TBL_SCHEME_PARAMETER")

/**
 * Gets the kind schemes.
 *
 * @return the kindschemes
 */
@Getter

/**
 * Sets the kindschemes.
 *
 * @param kindschemes the new kind schemes
 */
@Setter

/**
 * Instantiates a new kind scheme.
 */
@NoArgsConstructor
public class SchemeParameter extends BaseEntity<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 8107092578377879894L;

  /** The id. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", unique = true)
  private Integer schemeParamsId;

  /** The scheme id. */
  @Column(name = "SCHEME_ID", updatable = false, insertable = false)
  @CsvBindByName(column = "SCHEME_ID")
  private Long schemeId;

  /** The scheme. */
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "SCHEME_ID")
  private Scheme scheme;

  /** The parameter id. */
  @Column(name = "PARAMETER_ID", updatable = false, insertable = false)
  @CsvBindByName(column = "PARAMETER_ID")
  private Integer parameterId;

  /** The parameter. */
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "PARAMETER_ID")
  private Parameter parameter;

  /**
   * Instantiates a new schemeParams
   *
   * @param schemeParamsId
   *          the id
   */
  public SchemeParameter(Integer id) {
    this.schemeParamsId = id;
  }

  /**
   * {@inheritDoc}
   *
   * @see com.gcs.fas.entity.base.BaseEntity#getIdentifier()
   */
  @Override
  public Integer getIdentifier() {
    return this.schemeParamsId;
  }

}
