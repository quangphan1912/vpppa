/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Sep 1, 2020     ********           Administrator            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.core.exception;

/**
 * The Class TokenExpiredException.
 *
 * @author Administrator
 */
public class TokenExpiredException extends RuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 7608157964961848795L;

  /**
   * Instantiates a new token expired exception.
   */
  public TokenExpiredException() {
    super("TokenExpiredException");
  }
}
