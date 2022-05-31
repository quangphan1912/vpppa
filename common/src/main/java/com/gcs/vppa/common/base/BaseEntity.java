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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * The Class BaseEntity.
 *
 * @param <TId> the generic type
 */
@MappedSuperclass
@Getter
@Setter

/**
 * Instantiates a new base entity.
 */
@NoArgsConstructor
public abstract class BaseEntity<TId> implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 7757898620597038466L;

  /**
   * Gets the identifier.
   *
   * @return the identifier
   */
  public abstract TId getIdentifier();
}
