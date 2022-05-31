/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 18, 2020     ********           hangttran.ht            Initialize                  
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
 * The class SchemeStatusDTO.
 * 
 * @author hangttran.ht
 *
 */
/**
 * Gets the scheme status.
 *
 * @return the scheme status
 */
@Getter

/**
 * Sets the scheme status.
 *
 * @param schemeType
 *          the new scheme status
 */
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(callSuper = true)

/**
 * Instantiates a new scheme status DTO.
 */
@NoArgsConstructor
public class SchemeStatusDTO extends BaseDTO<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 318831807718728344L;

  /** The scheme status id. */
  private Integer id;

  /** The scheme. */
  private String schemeId;

  /** The scheme. */
  private SchemeDTO scheme;

  /** The status. */
  private String status;

  /** The updated date. */
  private String updatedDate;

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

  public SchemeStatusDTO(String schemeId, String status, String updatedDate, String updatedBy) {
    this.schemeId = schemeId;
    this.status = status;
    this.updatedDate = updatedDate;
    this.updatedBy = updatedBy;
  }

}
