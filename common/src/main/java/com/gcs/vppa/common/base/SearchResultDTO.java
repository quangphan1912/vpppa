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

import java.io.Serializable;
import java.util.List;

@Setter
@Getter

/**
 * Instantiates a new search result DTO.
 */
@NoArgsConstructor
public class SearchResultDTO<T extends SerializableDTO> implements Serializable {
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -8889916279528418133L;

  /** The items. */
  private List<T> items;

  /** The total items. */
  private Long totalItems;
}
