/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 10, 2020     ********           hangttran.ht            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.entity;

import com.gcs.vppa.common.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author hangttran.ht
 *
 */
@Entity
@Table(name = "VW_PROCESS")

/**
 * Gets the process
 *
 * @return the process
 */
@Getter

/**
 * Sets the process.
 *
 * @param process the new process
 */
@Setter

/**
 * Instantiates a new process
 */
@NoArgsConstructor
public class ProcessView extends BaseEntity<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 8107092578377879894L;

  /** The process id. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", unique = true)
  private Integer id;

  /** The name. */
  @Column(name = "NAME", length = 128)
  private String name;

  /** The name. */
  @Column(name = "KEY", length = 128)
  private String key;

  /** The description. */
  @Column(name = "DESCRIPTION", length = 128)
  private String description;

  /** The expression. */
  @Column(name = "EXPRESSION", length = 128)
  private String expression;

  /** The scheme type. */
  @Column(name = "PROCESS_TYPE_ID")
  private String processTypeId;

  /** The scheme type. */
  @Column(name = "PROCESS_TYPE_NAME")
  private String processTypeName;

  /** The division. */
  @Column(name = "DIVISION_ID")
  private String divisionId;

  /** The division. */
  @Column(name = "DIVISION_NAME")
  private String divisionName;

  /** The center. */
  @Column(name = "CENTER_ID")
  private String centerId;

  /** The center. */
  @Column(name = "CENTER_NAME")
  private String centerName;

  /**
   * Instantiates a new process.
   *
   * @param id the process id
   */
  public ProcessView(Integer id) {
    this.id = id;
  }

  /**
   * {@inheritDoc}
   * @see BaseEntity#getIdentifier()
   */
  @Override
  public Integer getIdentifier() {
    return this.id;
  }

}
