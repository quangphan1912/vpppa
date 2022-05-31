/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 15, 2020     ********           hangttran.ht            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gcs.vppa.common.base.BaseDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class ParameterViewDTO.
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
public class ParameterViewDTO extends BaseDTO<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The param id. */
  private int id;

  /** The Parameter name. */
  private String paramName;

  /** The Parameter description. */
  private String description;

  /** The Parameter NOTE. */
  private String note;

  /** The Parameter status. */
  private String status;

  /** The scheme id */
  private String schemeId;

  /** The scheme name. */
  private String schemeName;

  /** The product id */
  private String productId;

  /** The product name. */
  private String productName;

  /** The division id. */
  private String divisionId;

  /** The division name. */
  private String divisionName;

  /** The center id. */
  private String centerId;

  /** The center name. */
  private String centerName;

  /** The created date. */
  private LocalDateTime createdDate;

  /** The updated date. */
  private LocalDateTime updatedDate;

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
