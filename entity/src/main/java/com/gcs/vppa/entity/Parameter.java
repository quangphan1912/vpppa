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
package com.gcs.vppa.entity;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.gcs.vppa.common.base.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class Parameter.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */
@Entity
@Table(name = "TBL_PARAMETER")

/**
 * Gets the parameter.
 *
 * @return the parameter
 */
@Getter

/**
 * Sets the parameter.
 *
 * @param parameter
 *          the new parameter
 */
@Setter

/**
 * Instantiates a new parameter.
 */
@NoArgsConstructor
public class Parameter extends BaseEntity<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 8107092578377879894L;

  /** The param id. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", unique = true)
  private Integer id;

  /** The Parameter name. */
  @Column(name = "NAME", length = 200)
  private String paramName;

  /** The Parameter source. */
  @Column(name = "SOURCE")
  private String source;

  /** The Parameter description. */
  @Column(name = "DESCRIPTION", length = 4000)
  private String description;

  /** The Parameter NOTE. */
  @Column(name = "NOTE", length = 4000)
  private String note;

  /** The Parameter status. */
  @Column(name = "STATUS")
  private String status;

  /** The created date. */
  @Column(name = "CREATED_DATE")
  private Timestamp createdDate;

  /** The created by. */
  @Column(name = "CREATED_BY")
  private String createdBy;

  /** The updated date. */
  @Column(name = "UPDATED_DATE")
  private Timestamp updatedDate;

  /** The updated by. */
  @Column(name = "UPDATED_BY")
  private String updatedBy;

  @ManyToMany(mappedBy = "parameters")
  private Set<Scheme> schemes;

  /** The parameter condition. */
  @OneToMany(mappedBy = "parameter", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<ParameterCondition> parameterConditions;

  /**
   * Instantiates a new parameter.
   *
   * @param paramId
   *          the param id
   */
  public Parameter(Integer id) {
    this.id = id;
  }

  /**
   * {@inheritDoc}
   *
   * @see com.gcs.vppa.common.base.BaseEntity#getIdentifier()
   */
  @Override
  public Integer getIdentifier() {
    return this.id;
  }

}
