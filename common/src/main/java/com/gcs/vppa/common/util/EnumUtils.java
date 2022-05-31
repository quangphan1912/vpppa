/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 28, 2020     ********           Administrator            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.common.util;

import java.util.Arrays;

/**
 * The Class EnumUtils.
 *
 * @author Administrator
 */
public final class EnumUtils {

  /**
   * Instantiates a new enum utils.
   */
  private EnumUtils() {
    // do nothing.
  }

  /**
   * Gets the names.
   *
   * @param e the e
   * @return the names
   */
  public static String[] toArray(Class<? extends Enum<?>> e) {
    return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
  }
}
