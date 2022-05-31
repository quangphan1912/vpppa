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

import com.gcs.vppa.core.scheduler.models.IntervalScheduleInfo;
import org.quartz.Job;
import org.quartz.Trigger;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * The Class IntervalJobScheduler.
 *
 * @param <TJob> the generic type
 * @param <TScheduleInfo> the generic type
 */
public abstract class IntervalJobScheduler<TJob extends Job, TScheduleInfo extends IntervalScheduleInfo<TJob>>
  extends BaseJobScheduler<TJob, TScheduleInfo> {

  /**
   * {@inheritDoc}
   * @see com.gcs.vppa.core.scheduler.BaseJobScheduler#createTrigger(com.gcs.vppa.core.scheduler.models.BaseScheduleInfo, java.lang.String, java.lang.String)
   */
  @Override
  protected Trigger createTrigger(TScheduleInfo scheduleInfo, String triggerName, String triggerGroup) {
    return newTrigger()
      .withSchedule(simpleSchedule().withIntervalInSeconds(scheduleInfo.getIntervalSecond()).repeatForever())
      .build();
  }
}
