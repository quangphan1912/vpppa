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

import lombok.Getter;
import lombok.Setter;

/**
 * Gets the out data.
 *
 * @return the out data
 */
@Getter

/**
 * Sets the out data.
 *
 * @param outData the new out data
 */
@Setter
public abstract class BaseOutputProcessStep<TContext, TOut> extends BaseProcessStep<TContext>
  implements ProcessOutputStep<TOut> {

  /** The out data. */
  protected TOut outData;

  /**
   * Instantiates a new base process step.
   *
   * @param inName
   *            the in name
   * @param inContext
   *            the in context
   */
  protected BaseOutputProcessStep(String inName, TContext inContext) {
    super(inName, inContext);
    this.name = inName;
    this.context = inContext;
  }
}
