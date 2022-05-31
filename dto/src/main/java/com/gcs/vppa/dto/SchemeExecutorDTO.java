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
import com.gcs.vppa.common.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author hangttran.ht
 *
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class SchemeExecutorDTO extends BaseDTO<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  private Integer id;

  /** The status. */
  private String status;

  /** The type. */
  private String type;

  /** The resultFile. */
  private String resultFile;

  /** The executeMonth. */
  private String executeMonth;

  /** The executorId. */
  private String executorId;

  /** The schemeId. */
  private String schemeId;

  /** The processId. */
  private String processId;

  /** The execute date. */
  private Timestamp executeDate;

  /** The execute by. */
  private String executeBy;

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
