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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gcs.vppa.common.base.BaseDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class User.
 */

/**
 * Gets the last updated.
 *
 * @return the last updated
 */
@Getter

/**
 * Sets the last updated.
 *
 * @param lastUpdated the new last updated
 */
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(callSuper = true)
public class VehicleDTO extends BaseDTO<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 7549192977292211379L;

  /** The user id. */
  private Integer vehicleId;

  /** The name. */
  private String name;

  /** The register number. */
  private String registerNumber;

  /** The user name. */
  private String ownerName;

  /** The is created on. */
  private LocalDateTime createOn;

  /** The last updated. */
  private LocalDateTime lastUpdated;

  /**
   * {@inheritDoc}
   * @see com.gcs.vppa.common.base.BaseDTO#getIdentifier()
   */
  @Override
  @JsonIgnore
  public Integer getIdentifier() {
    return this.vehicleId;
  }

  /**
   * {@inheritDoc}
   * @see com.gcs.vppa.common.base.BaseDTO#setIdentifier(java.lang.Object)
   */
  @Override
  @JsonIgnore
  public void setIdentifier(Integer id) {
    this.vehicleId = id;
  }
}
