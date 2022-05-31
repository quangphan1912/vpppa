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

/**
 * The Interface ProcessSequence.
 */
public interface ProcessSequence {
  /**
   * Gets the name.
   *
   * @return the name
   */
  String getName();

  /**
   * Execute.
   *
   * @return the process result
   */
  ProcessResult execute();

  /**
   * Register step.
   *
   * @param step
   *            the step
   */
  void registerStep(ProcessStep step);
}
