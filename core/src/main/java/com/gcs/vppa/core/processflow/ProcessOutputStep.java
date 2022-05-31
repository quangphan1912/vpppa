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
 * The Interface ProcessOutputStep.
 *
 * @param <TOut> the generic type
 */
public interface ProcessOutputStep<TOut> extends ProcessStep {
  /**
   * Gets the out data.
   *
   * @return the out data
   */
  TOut getOutData();
}
