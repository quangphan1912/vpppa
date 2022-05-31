/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 15, 2020     ********           hangttran.ht            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.entity;

import java.io.Serializable;
import java.sql.Timestamp;

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
 * @author hangttran.ht
 */
@Entity
@Table(name = "VW_PARAMETER")
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
public class ParameterView extends BaseEntity<Integer> implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 8107092578377879894L;

  /** The param id. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", unique = true)
  private Integer id;

  /** The Parameter name. */
  @Column(name = "NAME")
  private String paramName;

  /** The Parameter description. */
  @Column(name = "DESCRIPTION")
  private String description;

  /** The Parameter NOTE. */
  @Column(name = "NOTE")
  private String note;

  /** The Parameter status. */
  @Column(name = "STATUS")
  private String status;

  /** The scheme id */
  @Column(name = "SCHEME_ID")
  private String schemeId;

  /** The scheme name. */
  @Column(name = "SCHEME_NAME")
  private String schemeName;

  /** The product id */
  @Column(name = "PRODUCT_ID")
  private String productId;

  /** The product name. */
  @Column(name = "PRODUCT_NAME")
  private String productName;

  /** The division id. */
  @Column(name = "DIVISION_ID")
  private String divisionId;

  /** The division name. */
  @Column(name = "DIVISION_NAME")
  private String divisionName;

  /** The center id. */
  @Column(name = "CENTER_ID")
  private String centerId;

  /** The center name. */
  @Column(name = "CENTER_NAME")
  private String centerName;

  /** The created date. */
  @Column(name = "CREATED_DATE")
  private Timestamp createdDate;

  /** The updated date. */
  @Column(name = "UPDATED_DATE")
  private Timestamp updatedDate;
  /**
   * Instantiates a new vehicle.
   *
   * @param paramId
   *          the param id
   */
  public ParameterView(Integer id) {
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
