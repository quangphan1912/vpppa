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
package com.gcs.vppa.core.exception;

/**
 * The Class ForbiddenException.
 */
public class ForbiddenException extends UserDefinedException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1956589999038442433L;

  /**
   * Constructor.
   *
   * @param errorCode
   *            the error code
   * @param errorText
   *            the error text
   */
  public ForbiddenException(final String errorCode, final String errorText) {
    super(errorCode, errorText);
  }

  /**
   * The Constructor.
   *
   * @param errorCode
   *            the error code
   * @param errorText
   *            the error text
   * @param cause
   *            the cause
   */
  public ForbiddenException(final String errorCode, final String errorText, final Throwable cause) {
    super(errorCode, errorText, cause);
  }
}
