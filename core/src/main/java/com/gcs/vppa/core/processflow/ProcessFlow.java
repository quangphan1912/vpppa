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

import java.util.function.Consumer;

/**
 * The Interface ProcessFlow.
 */
public interface ProcessFlow {
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
   * Execute async.
   */
  void executeAsync();

  /**
   * Register sequence.
   *
   * @param sequence
   *            the sequence
   */
  void registerSequence(ProcessSequence sequence);

  /**
   * Register exception handling.
   *
   * @param onException the on exception
   * @return the process step
   */
  ProcessFlow registerOnException(Consumer<Exception> onException);
}
