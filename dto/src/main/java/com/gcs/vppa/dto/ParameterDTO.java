/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * July 27, 2020     ********           PhoVo            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gcs.vppa.common.base.BaseDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class ParameterDTO.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */

/**
 * Gets the scheme name.
 *
 * @return the scheme name
 */
@Getter

/**
 * Sets the scheme name.
 *
 * @param schemeName
 *          the new scheme name
 */
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(callSuper = true)
public class ParameterDTO extends BaseDTO<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The parameter id. */
  private int id;

  /** The clone parameter id. */
  private String cloneParamId;

  /** The Parameter name. */
  private String paramName;

  /** The Parameter source. */
  private String source;

  /** The data source. */
  private String dataSource;

  /** The Parameter description. */
  private String description;

  /** The Parameter NOTE. */
  private String note;

  /** The Parameter status. */
  private String status;

  /** The created date. */
  private LocalDateTime createdDate;

  /** The created by. */
  private String createdBy;

  /** The updated date. */
  private LocalDateTime updatedDate;

  /** The updated by. */
  private String updatedBy;

  /** The list scheme. */
  private List<SchemeDTO> schemes;

  /** The list parameterConditions. */
  private List<ParameterConditionDTO> parameterConditions;

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
