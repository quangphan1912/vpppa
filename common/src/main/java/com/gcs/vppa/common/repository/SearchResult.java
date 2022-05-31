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
package com.gcs.vppa.common.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.io.Serializable;

/**
 * Sets the total items.
 *
 * @param totalItems the new total items
 */
@Setter

/**
 * Gets the total items.
 *
 * @return the total items
 */
@Getter

/**
 * Instantiates a new search result.
 */
@NoArgsConstructor
public class SearchResult<T> implements Serializable {
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -8889916279528418133L;

  /** The items. */
  private Page<T> page;

  /** The total items. */
  private Long totalItems;
}
