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
package com.gcs.vppa.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gcs.vppa.common.base.BaseDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The class SchemeExecutorDTO.
 * 
 * @author hangttran.ht
 *
 */
/**
 * Gets the scheme Executor.
 *
 * @return the scheme Executor
 */
@Getter

/**
 * Sets the scheme Executor.
 *
 * @param SchemeExecutor
 *          the new scheme Executor
 */
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(callSuper = true)
public class SchemeExecutorViewDTO extends BaseDTO<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 8107092578377879894L;

  /** The id. */
  private int id;

  /** The executor id. */
  private String executorId;

  /** The result id. */
  private String resultFile;

  /** The status. */
  private String status;

  /** The type. */
  private String type;

  /** The scheme id. */
  private String sid;

  /** The scheme id. */
  private String schemeId;

  /** The scheme name. */
  private String schemeName;

  /** The process id. */
  private String processId;

  /** The process name. */
  private String processName;

  /** The execute date. */
  private LocalDateTime executeDate;

  /** The execute by. */
  private String executeBy;

  /** The status scheme. */
  private String statusScheme;

  /**
   * {@inheritDoc}
   * 
   * @see com.gcs.vppa.common.base.BaseDTO#getIdentifier()
   */
  @Override
  @JsonIgnore
  public Integer getIdentifier() {
    return this.id;
  }

  /**
   * {@inheritDoc}
   * 
   * @see com.gcs.vppa.common.base.BaseDTO#setIdentifier(java.lang.Object)
   */
  @Override
  @JsonIgnore
  public void setIdentifier(Integer id) {
    this.id = id;
  }
}
