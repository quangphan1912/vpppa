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
 * The Class ArgumentException.
 */
public class InvalidFormatException extends UserDefinedException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -1766513462051137185L;

  /**
   * Constructor.
   *
   * @param errorCode
   *            the error code
   * @param errorText
   *            the error text
   */
  public InvalidFormatException(final String errorCode, final String errorText) {
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
  public InvalidFormatException(final String errorCode, final String errorText, final Throwable cause) {
    super(errorCode, errorText, cause);
  }
}
