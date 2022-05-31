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
package com.gcs.vppa.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Instantiates a new collection util.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CollectionUtil {

  /**
   * Checks if is null or empty.
   *
   * @param <T> the generic type
   * @param items the items
   * @return true, if is null or empty
   */
  public static <T> boolean isNullOrEmpty(List<T> items) {
    return items == null || items.isEmpty();
  }

  /**
   * Checks if is null or empty.
   *
   * @param <K> the key type
   * @param <V> the value type
   * @param items the items
   * @return true, if is null or empty
   */
  public static <K, V> boolean isNullOrEmpty(Map<K, V> items) {
    return items == null || items.size() == 0;
  }
}
