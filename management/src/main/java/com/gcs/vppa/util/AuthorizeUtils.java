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
package com.gcs.vppa.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Administrator
 *
 */
public final class AuthorizeUtils {

  /** The authorize token prefix. */
  private static String AUTHORIZE_TOKEN_PREFIX = "Bearer: ";

  /**
   * Instantiates a new authorize utility.
   */
  private AuthorizeUtils() {
    // do nothing.
  }
  
  /**
   * Gets the token.
   *
   * @param bearerToken the bearer token
   * @return the token
   */
  public static String getToken(String bearerToken) {
    if (StringUtils.isEmpty(bearerToken) || !bearerToken.startsWith(AUTHORIZE_TOKEN_PREFIX)) {
      return null;
    }

    return bearerToken.substring(AUTHORIZE_TOKEN_PREFIX.length()).trim();
  }
}
