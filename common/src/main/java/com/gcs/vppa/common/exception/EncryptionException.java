/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 13, 2020     ********           Administrator            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class EncryptionException.
 *
 * @author Administrator
 */
/**
 * Gets the checks if is handled.
 *
 * @return the checks if is handled
 */

/**
 * Gets the error code.
 *
 * @return the error code
 */
@Getter

/**
 * Sets the checks if is handled.
 *
 * @param isHandled the new checks if is handled
 */

/**
 * Sets the error code.
 *
 * @param errorCode the new error code
 */
@Setter
public class EncryptionException extends RuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;
  
  /** The error code. */
  private String errorCode;

  /**
   * Constructor.
   *
   * @param errorCode            the error code
   * @param message the message
   */
  public EncryptionException(final String errorCode, final String message) {
    super(message);
    this.errorCode = errorCode;
  }

  /**
   * The Constructor.
   *
   * @param errorCode            the error code
   * @param message the message
   * @param cause            the cause
   */
  public EncryptionException(final String errorCode, final String message, final Throwable cause) {
    super(message, cause);
    this.errorCode = errorCode;
  }
}
