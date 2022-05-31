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
import java.sql.Timestamp;

/**
 * @author hangttran.ht
 *
 */
@Entity
@Table(name = "TBL_SCHEME_EXECUTOR")
@Getter
@Setter
@NoArgsConstructor
public class SchemeExecutor extends BaseEntity<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 8107092578377879834L;

  /** The process id. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", unique = true)
  private Integer id;

  /** The code. */
  @Column(name = "status", length = 128)
  private String status;

  /** The code. */
  @Column(name = "type", length = 128)
  private String type;

  /** The description. */
  @Column(name = "result_file", length = 128)
  private String resultFile;

  /** The description. */
  @Column(name = "execute_month", length = 128)
  private String executeMonth;

  /** The executorId. */
  @Column(name = "executor_id", length = 128)
  private String executorId;

  /** The scheme. */
  @Column(name = "scheme_id")
  private Integer schemeId;

  /** The division. */
  @Column(name = "process_id")
  private Integer processId;

  /** The execute date. */
  @Column(name = "execute_date")
  private Timestamp executeDate;

  /** The execute by. */
  @Column(name = "execute_by")
  private String executeBy;

  /**
   * Instantiates a new process.
   *
   * @param id the process id
   */
  public SchemeExecutor(Integer id) {
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
