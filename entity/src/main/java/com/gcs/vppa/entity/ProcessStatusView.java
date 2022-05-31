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
import java.util.Date;

/**
 * @author hangttran.ht
 *
 */
@Entity
@Table(name = "VW_PROCESS_STATUS")

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
public class ProcessStatusView extends BaseEntity<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 8107192578374679894L;

  /** The process view id. */
  /** The process id. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", unique = true)
  private Integer id;

  /** The processId. */
  @Column(name = "PROCESS_ID")
  private Integer processId;

  /** The status. */
  @Column(name = "PROCESS_NAME", length = 128)
  private String processName;

  /** The schemeId. */
  @Column(name = "SCHEME_ID")
  private Integer schemeId;

  /** The status. */
  @Column(name = "SCHEME_NAME", length = 128)
  private String schemeName;

  /** The status. */
  @Column(name = "STATUS", length = 128)
  private String status;

  /** The execute by. */
  @Column(name = "EXECUTE_BY", length = 128)
  private String executeBy;

  /** The updated by. */
  @Column(name = "EXECUTE_DATE")
  @Temporal(TemporalType.TIMESTAMP)
  private Date executeDate;


  /**
   * {@inheritDoc}
   * @see BaseEntity#getIdentifier()
   */
  @Override
  public Integer getIdentifier() {
    return this.id;
  }

  /**
   * Instantiates a new process.
   *
   * @param id the process id
   */
  public ProcessStatusView(Integer id) {
    this.id = id;
  }
}
