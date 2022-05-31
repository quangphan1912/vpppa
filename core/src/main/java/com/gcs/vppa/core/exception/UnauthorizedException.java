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
 * The Class UnauthorizedException.
 *
 * @author Administrator
 */
public class UnauthorizedException extends RuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -444145532783644543L;

  /**
   * Instantiates a new unauthorized exception.
   */
  public UnauthorizedException() {
    super();
  }
}
