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
package com.gcs.vppa.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gcs.vppa.common.base.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * The class ProcessStatusDTO.
 * 
 * @author hangttran.ht
 *
 */

/**
 * Gets the process.
 *
 * @return the process type
 */
@Getter

/**
 * Sets the process type.
 *
 * @param process
 *          the new process
 */
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(callSuper = true)

/**
 * Instantiates a new product DTO.
 */
@NoArgsConstructor
public class ProcessStatusViewDTO extends BaseDTO<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 318831807718728345L;

  /** The process id. */
  private Integer id;

  /** The process. */
  private Integer processId;

  /** The process Name. */
  private String processName;

  /** The scheme. */
  private Integer schemeId;

  /** The scheme Name. */
  private String schemeName;

  /** The status. */
  private String status;

  /** The executeBy. */
  private String executeBy;

  /** The execute date. */
  private Date executeDate;

  /**
   * {@inheritDoc}
   *
   * @see BaseDTO#getIdentifier()
   */
  @Override
  @JsonIgnore
  public Integer getIdentifier() {
    return this.id;
  }

  /**
   * {@inheritDoc}
   *
   * @see BaseDTO#setIdentifier(Object)
   */
  @Override
  @JsonIgnore
  public void setIdentifier(Integer id) {
    this.id = id;
  }
}
