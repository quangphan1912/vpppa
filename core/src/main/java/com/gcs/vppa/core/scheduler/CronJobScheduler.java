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

import static org.quartz.TriggerBuilder.newTrigger;

import java.time.LocalDateTime;
import java.util.TimeZone;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.Trigger;

import com.gcs.vppa.core.scheduler.models.CronScheduleInfo;

import lombok.extern.slf4j.Slf4j;

/** The Constant log. */
@Slf4j
public abstract class CronJobScheduler<TJob extends Job, TScheduleInfo extends CronScheduleInfo<TJob>>
  extends BaseJobScheduler<TJob, TScheduleInfo> {

  /**
   * Creates the trigger.
   *
   * @param scheduleInfo
   *            the schedule info
   * @param triggerName
   *            the trigger name
   * @param triggerGroup
   *            the trigger group
   * @return the trigger
   */
  protected Trigger createTrigger(TScheduleInfo scheduleInfo, String triggerName, String triggerGroup) {
    String cronTime = this.createCronTime(scheduleInfo);
    log.info("createTrigger - cronTime=[{}], timeZone=[{}], currentTime=[{}]", cronTime, scheduleInfo.getTimeZone(),
      LocalDateTime.now());
    
    return newTrigger().withIdentity(triggerName, triggerGroup).withSchedule(
      CronScheduleBuilder.cronSchedule(cronTime).inTimeZone(TimeZone.getTimeZone(scheduleInfo.getTimeZone())))
      .build();
  }

  /**
   * Creates the job time.
   * https://www.freeformatter.com/cron-expression-generator-quartz.html
   *
   * @param schedule
   *            the schedule
   * @return the string
   */
  protected String createCronTime(TScheduleInfo schedule) {
    return String.format("%d %d %d/%d ? * * *", schedule.getSeconds(), schedule.getMinutes(), schedule.getHours(),
      schedule.getIntervalHours());
  }
}
