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
package com.gcs.vppa.core.scheduler;

import com.gcs.vppa.core.scheduler.models.BaseScheduleInfo;
import org.quartz.Job;

/**
 * The Interface JobScheduler.
 *
 * @param <TJob> the generic type
 * @param <TScheduleInfo> the generic type
 */
public interface JobScheduler<TJob extends Job, TScheduleInfo extends BaseScheduleInfo<TJob>> {

  /**
   * Schedule job.
   *
   * @param scheduleInfo
   *            the schedule
   */
  void scheduleJob(TScheduleInfo scheduleInfo);
}
