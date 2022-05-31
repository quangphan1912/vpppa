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

import java.util.List;

import javax.persistence.*;

import com.gcs.vppa.common.base.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author hangttran.ht
 *
 */
@Entity
@Table(name = "TBL_PROCESS")

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
public class Process extends BaseEntity<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 8107092578377879894L;

  /** The process id. */
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
  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "PROCESS_TYPE_ID")
  private ProcessType processType;

  /** The division. */
  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "DIVISION_ID")
  private Division division;

  /** The center. */
  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "CENTER_ID")
  private Center center;

  /**
   * Instantiates a new process.
   *
   * @param id the process id
   */
  public Process(Integer id) {
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
