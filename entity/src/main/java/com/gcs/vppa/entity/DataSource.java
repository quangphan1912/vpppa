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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.gcs.vppa.common.base.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Data Source.
 */
@Entity
@Table(name = "tbl_data_source")

/**
 * Gets the last updated.
 *
 * @return the last updated
 */
@Getter

/**
 * Sets the last updated.
 *
 * @param lastUpdated
 *          the new last updated
 */
@Setter

/**
 * Instantiates a new DataSource.
 */
@NoArgsConstructor
public class DataSource extends BaseEntity<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 8107092578377879895L;

  /** The data source id. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true)
  private Integer id;

  /** The source. */
  @Column(name = "source", unique = true)
  private String source;

  /** The content. */
  @Column(columnDefinition = "TEXT")
  private String content;

  /** The created Date. */
  @Column(name = "created_date", updatable = false)
  private Timestamp createdDate;

  /** The created By. */
  @Column(name = "created_by", updatable = false)
  @CreatedDate
  private String createdBy;

  /** The updated Date. */
  @Column(name = "updated_date")
  private Timestamp updatedDate;

  /** The updated By. */
  @Column(name = "updated_by")
  private String updatedBy;

  /**
   * Instantiates a new vehicle.
   *
   * @param id
   */
  public DataSource(Integer id) {
    this.id = id;
  }

  /**
   * {@inheritDoc}
   * 
   * @see BaseEntity#getIdentifier()
   */
  @Override
  public Integer getIdentifier() {
    return this.id;
  }
}
