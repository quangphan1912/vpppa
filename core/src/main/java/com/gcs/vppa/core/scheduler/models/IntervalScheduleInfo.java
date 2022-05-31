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
package com.gcs.vppa.core.scheduler.models;

import lombok.Getter;
import lombok.Setter;
import org.quartz.Job;

/**
 * Gets the interval second.
 *
 * @return the interval second
 */
@Getter

/**
 * Sets the interval second.
 *
 * @param intervalSecond the new interval second
 */
@Setter
public class IntervalScheduleInfo<TJob extends Job> extends BaseScheduleInfo<TJob> {

  /** The interval second. */
  private Integer intervalSecond;
}
