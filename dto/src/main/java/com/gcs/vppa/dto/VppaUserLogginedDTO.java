/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Sep 3, 2020     ********           Administrator            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.dto;

import java.time.LocalDateTime;

import com.gcs.vppa.common.base.BaseDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class VppaUserLogginedDTO.
 *
 * @author Administrator
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

/**
 * Instantiates a new vppa user loggined DTO.
 */
@NoArgsConstructor
public class VppaUserLogginedDTO extends BaseDTO<Integer> {
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  private Integer id;
  
  /** The user_id. */
  private Integer userId;

  /** The user. */
  private VppaUserDTO user;

  /** The token. */
  private String token;

  /** The last accessed time. */
  private LocalDateTime lastAccessedTime;
  
  /** The is expired. */
  private boolean isExpired = false;

  /** The createdDate. */
  private LocalDateTime createdDate;

  /** The createdBy. */
  private Integer createdBy;

  /** The updatedDate. */
  private LocalDateTime updatedDate;

  /** The updatedBy. */
  private Integer updatedBy;

  /**
   * Gets the identifier.
   *
   * @return the identifier
   */
  @Override
  public Integer getIdentifier() {
    return null;
  }

  /**
   * Sets the identifier.
   *
   * @param id the new identifier
   */
  @Override
  public void setIdentifier(Integer id) {
    // do nothing.
  }
}
