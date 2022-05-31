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
package com.gcs.vppa.common.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class BaseDTO.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public abstract class BaseDTO<TId> extends SerializableDTO {
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 8891946479630525308L;

  /** The object encrypted id . */
  private String encryptedId;
  
  /**
   * Gets the identifier.
   *
   * @return the identifier
   */
  @JsonIgnore
  public abstract TId getIdentifier();

  /**
   * Sets the identifier.
   *
   * @param id the new identifier
   */
  @JsonIgnore
  public abstract void setIdentifier(TId id);
}
