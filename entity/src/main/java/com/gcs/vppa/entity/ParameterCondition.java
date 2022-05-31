/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 27, 2020     ********           hangttran.ht            Initialize                  
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
 * The class ParameterCondition.
 * 
 * @author hangttran.ht
 */
@Entity
@Table(name = "TBL_PARAMETER_CONDITION")

/**
 * Gets the parameter condition.
 *
 * @return the parameter condition
 */
@Getter

/**
 * Sets the paramete conditionr.
 *
 * @param parameter
 *          the new parameter condition
 */
@Setter

/**
 * Instantiates a new parameter condition.
 */
@NoArgsConstructor
public class ParameterCondition extends BaseEntity<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 8107092578377879894L;

  /** The param condition id. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", unique = true)
  private Integer id;

  /** The parameter id. */
  @Column(name = "parameter_id", updatable = false, insertable = false)
  private Integer parameterId;

  /** The parameter. */
  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "parameter_id")
  private Parameter parameter;

  /** The name. */
  @Column(name = "name", length = 200)
  private String name;

  /** The value. */
  @Column(name = "value", length = 200)
  private String value;

  /** The updated date. */
  @Column(name = "updated_date")
  private Timestamp updatedDate;

  /** The updated by. */
  @Column(name = "UPDATED_BY", length = 128)
  private String updatedBy;

  /**
   * Instantiates a new ParameterCondition.
   *
   * @param id the ParameterCondition id
   */
  public ParameterCondition(Integer id) {
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
