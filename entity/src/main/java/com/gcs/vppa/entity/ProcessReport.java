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
@Table(name = "TBL_PROCESS_REPORT")
@Getter
@Setter
@NoArgsConstructor
public class ProcessReport extends BaseEntity<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 8107092578377879834L;

  /** The process id. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", unique = true)
  private Integer id;

  /** The name. */
  @Column(name = "NAME", length = 128)
  private String name;

  /** The code. */
  @Column(name = "SOURCE", length = 128)
  private String source;

  /** The code. */
  @Column(name = "TEMPLATE", length = 128)
  private String template;

  /** The description. */
  @Column(name = "DESCRIPTION", length = 128)
  private String description;

  /** The scheme. */
  @Column(name = "scheme_id")
  private Integer schemeId;

  /** The division. */
  @Column(name = "process_id")
  private Integer processId;

  /**
   * Instantiates a new process.
   *
   * @param id the process id
   */
  public ProcessReport(Integer id) {
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
