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

import lombok.Getter;
import lombok.Setter;

// TODO: Auto-generated Javadoc
/**
 * The Class UserBlockedException.
 *
 * @author Administrator
 */
@Getter

/**
 * Sets the message.
 *
 * @param message the new message
 */
@Setter
public class UserBlockedException extends RuntimeException {
  /** The code. */
  private long code;

  /** The message. */
  private String message;

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1255489903621716542L;

  /**
   * Instantiates a new user blocked exception.
   *
   * @param code the code
   * @param message the message
   */
  public UserBlockedException(long code, String message) {
    super();
    this.code = code;
    this.message = message;
  }
}
