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
 * @author Administrator
 *
 */
public class RequestNotFoundException extends Exception {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -5955313774014340283L;

  /**
   * Instantiates a new request not found exception.
   */
  public RequestNotFoundException() {
    super();
  }
}
