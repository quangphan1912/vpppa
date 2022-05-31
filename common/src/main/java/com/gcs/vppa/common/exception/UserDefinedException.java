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
package com.gcs.vppa.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class UserDefinedException.
 */

/**
 * Instantiates a new user defined exception.
 */

/**
 * Gets the checks if is handled.
 *
 * @return the checks if is handled
 */
@Getter

/**
 * Sets the checks if is handled.
 *
 * @param isHandled the new checks if is handled
 */
@Setter
public class UserDefinedException extends RuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -7252949981915663662L;

  /** The error code. */
  private final String errorCode;

  /** The is handled. */
  private final Boolean isHandled;

  /**
   * Instantiates a new base exception.
   *
   * @param errorCode
   *            the error code
   * @param errorText
   *            the error text
   */
  public UserDefinedException(final String errorCode, final String errorText) {
    super(errorText);
    this.errorCode = errorCode;
    this.isHandled = false;
  }

  /**
   * Instantiates a new base exception.
   *
   * @param errorCode
   *            the error code
   * @param errorText
   *            the error text
   * @param cause
   *            the cause
   */
  public UserDefinedException(final String errorCode, final String errorText, final Throwable cause) {
    super(errorText, cause);
    this.errorCode = errorCode;
    this.isHandled = false;
  }
}
