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
package com.gcs.vppa.common.dto;

/**
 * The Enum UserType.
 *
 * @author Administrator
 */
public enum UserType {

  /** The admin. */
  ADMIN(1),

  /** The Normal user. */
  NORMAL(2);

  /** The value. */
  private final int value;

  /**
   * Instantiates a new user type.
   *
   * @param value the value
   */
  private UserType(int value) {
    this.value = value;
  }

  /**
   * Gets the value.
   *
   * @return the value
   */
  public int getValue() {
    return value;
  }
}
