/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 29, 2020     ********           hangttran.ht            Initialize                  
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
 * The class SchemeExecutor.
 * 
 * @author hangttran.ht
 */
@Entity
@Table(name = "vw_scheme_executor")
/**
 * Gets the scheme.
 *
 * @return the scheme
 */
@Getter

/**
 * Sets the scheme.
 *
 * @param scheme
 *          the new scheme
 */
@Setter

/**
 * Instantiates a new scheme.
 */
@NoArgsConstructor
public class SchemeExecutorView extends BaseEntity<Integer> implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 8107092578377879894L;

  /** The id. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true)
  private Integer id;

  /** The executor id. */
  @Column(name = "executor_id")
  private String executorId;

  /** The result id. */
  @Column(name = "result_file")
  private String resultFile;

  /** The status. */
  @Column(name = "status")
  private String status;

  /** The type. */
  @Column(name = "type")
  private String type;

  /** The scheme id. */
  @Column(name = "scheme_id")
  private Integer sid;
 
  /** The scheme id. */
  @Column(name = "scheme_id_name")
  private String schemeId;

  /** The scheme name. */
  @Column(name = "scheme_name")
  private String schemeName;

  /** The process id. */
  @Column(name = "process_id")
  private Integer processId;

  /** The process name. */
  @Column(name = "process_name")
  private String processName;

  /** The execute date. */
  @Column(name = "execute_date")
  private Timestamp executeDate;

  /** The execute by. */
  @Column(name = "execute_by")
  private String executeBy;

  /** The status scheme. */
  @Column(name = "status_scheme")
  private String statusScheme;
  /**
   * Instantiates a new vehicle.
   *
   * @param paramId
   *          the param id
   */
  public SchemeExecutorView(Integer id) {
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
