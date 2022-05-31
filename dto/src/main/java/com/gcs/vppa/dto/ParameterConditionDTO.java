/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 27, 2020     ********           hangttran.ht            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gcs.vppa.common.base.BaseDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author hangttran.ht
 *
 */
/**
 * Gets the param condition.
 *
 * @return the param condition
 */
@Getter

/**
 * Sets the param condition.
 *
 * @param paramCondition param condition
 */
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(callSuper = true)

/**
 * Instantiates a new param condition DTO.
 */
@NoArgsConstructor
public class ParameterConditionDTO extends BaseDTO<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 318831807718728344L;

  /** The param condition id. */
  private int id;

  /** The parameter id. */
  private int parameterId;

  /** The parameter id. */
  private String parameterIdEncrypt;

  /** The parameter. */
  private ParameterDTO parameter;

  /** The name. */
  private String name;

  /** The value. */
  private String value;

  /** The updated date. */
  private LocalDateTime updatedDate;

  /** The updated by. */
  private String updatedBy;

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
