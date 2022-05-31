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
package com.gcs.vppa.core.processflow;

import com.gcs.vppa.core.exception.UserDefinedException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Gets the error.
 *
 * @return the error
 */
@Getter

/**
 * Sets the error.
 *
 * @param error the new error
 */
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString()
public class ProcessResult {

  /** The process name. */
  private String processName;

  /** The has success. */
  private boolean isSuccess;

  /** The error. */
  private UserDefinedException error;

  /**
   * Instantiates a new process result.
   *
   * @param inProcessName the in process name
   */
  ProcessResult(String inProcessName) {
    this.processName = inProcessName;
    this.isSuccess = true;
  }
}
