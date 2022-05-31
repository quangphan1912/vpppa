/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 18, 2020     ********           hangttran.ht            Initialize                  
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
 * The class SchemeStatus.
 * 
 * @author hangttran.ht
 */
@Entity
@Table(name = "TBL_SCHEME_STATUS")

/**
 * Gets the scheme status.
 *
 * @return the scheme status
 */
@Getter

/**
 * Sets the scheme status.
 *
 * @param SchemeStatus
 *          the new scheme status
 */
@Setter

/**
 * Instantiates a new scheme status.
 */
@NoArgsConstructor
public class SchemeStatus extends BaseEntity<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 8107092578377879894L;

  /** The scheme status id. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", unique = true)
  private Integer id;

  /** The id scheme. */
  @Column(name = "scheme_id", updatable = false, insertable = false)
  private Integer schemeId;

  /** The scheme. */
  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "scheme_id")
  private Scheme scheme;

  /** The STATUS. */
  @Column(name = "STATUS", length = 128)
  private String status;

  /** The updated date. */
  @Column(name = "UPDATED_DATE")
  private Timestamp updatedDate;

  /** The updated by. */
  @Column(name = "UPDATED_BY", length = 128)
  private String updatedBy;

  /**
   * Instantiates a new schemeStatus.
   *
   * @param id the schemeStatus id
   */
  public SchemeStatus(Integer id) {
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
