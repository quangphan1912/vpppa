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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;

import com.gcs.vppa.common.constant.ErrorCodes;
import com.gcs.vppa.common.logging.InjectLog;
import com.gcs.vppa.core.exception.InvalidArgumentException;
import com.gcs.vppa.core.exception.UserDefinedException;
import com.gcs.vppa.core.scheduler.models.BaseScheduleInfo;

import lombok.extern.slf4j.Slf4j;

/** The Constant log. */
@Slf4j
public abstract class BaseJobScheduler<TJob extends Job, TScheduleInfo extends BaseScheduleInfo<TJob>>
  implements JobScheduler<TJob, TScheduleInfo> {

  /** The Constant JOB_NAME. */
  public static final String JOB_NAME = "JOB_NAME";

  /** The Constant GROUP. */
  public static final String GROUP = "GROUP";

  /** The Constant TRIGGER_NAME. */
  public static final String TRIGGER_NAME = "TRIGGER_NAME";

  /** The scheduler. */
  @Autowired
  protected Scheduler scheduler;

  /**
   * {@inheritDoc}
   * @see com.gcs.vppa.core.scheduler.JobScheduler#scheduleJob(com.gcs.vppa.core.scheduler.models.BaseScheduleInfo)
   */
  @InjectLog
  @Override
  public void scheduleJob(TScheduleInfo scheduleInfo) {
    if (scheduleInfo == null) {
      log.debug("scheduleJob - input schedule is null");
      throw new InvalidArgumentException(ErrorCodes.INVALID_ARGUMENT, "No schedule data");
    }
    try {
      this.deleteIfExists(scheduleInfo.getId());
      this.doScheduleJob(scheduleInfo);

    } catch (UserDefinedException e) {
      log.error("scheduleJob - failed to schedule: {}", e);
      throw e;
    } catch (Exception e) {
      log.error("scheduleJob - failed to schedule: {}", e);
      throw new UserDefinedException(ErrorCodes.SCHEDULING_FAILED, e.getMessage(), e);
    }
  }

  /**
   * Delete if exists.
   *
   * @param scheduleId the schedule id
   */
  @InjectLog
  protected void deleteIfExists(Integer scheduleId) {
    Map<String, String> mapValue = this.createQuartzIdentity(scheduleId);
    String jobName = mapValue.get(JOB_NAME);
    String triggerGroup = mapValue.get(GROUP);
    JobKey jobKey = new JobKey(jobName, triggerGroup);
    try {
      if (this.scheduler.checkExists(jobKey)) {
        this.scheduler.deleteJob(jobKey);
        log.debug("deleteIfExists - Delete job successfuly.");
      }
    } catch (SchedulerException e) {
      throw new UserDefinedException(ErrorCodes.SCHEDULING_FAILED, e.getMessage(), e);
    }
  }

  /**
   * Do schedule job.
   *
   * @param scheduleInfo the schedule info
   */
  protected void doScheduleJob(TScheduleInfo scheduleInfo) {
    Map<String, String> mapValue = this.createQuartzIdentity(scheduleInfo.getId());
    String jobName = mapValue.get(JOB_NAME);
    String triggerName = mapValue.get(TRIGGER_NAME);
    String triggerGroup = mapValue.get(GROUP);
    Trigger trigger = this.createTrigger(scheduleInfo, triggerName, triggerGroup);
    JobDetail job = JobBuilder.newJob(scheduleInfo.getJobType()).withIdentity(jobName, triggerGroup).build();
    this.startSchedule(trigger, job);
  }

  /**
   * Start schedule.
   *
   * @param trigger
   *            the trigger
   * @param job
   *            the job
   */
  @InjectLog(logParams = false)
  protected void startSchedule(Trigger trigger, JobDetail job) {
    try {
      if (this.scheduler.checkExists(job.getKey())) {
        log.debug("startSchedule - Delete existing schedule");
        this.scheduler.deleteJob(job.getKey());
      }

      Set<Trigger> triggers = new HashSet<>();
      
      triggers.add(trigger);
      scheduler.scheduleJob(job, triggers, true);
      
      if (scheduler.isInStandbyMode()) {
        scheduler.start();
      }
      
    } catch (Exception e) {
      throw new UserDefinedException(ErrorCodes.SCHEDULING_FAILED, e.getMessage(), e);
    }
  }

  /**
   * Create Quartz Identity for trigger and job detail.
   *
   * @param scheduleId            schedule id
   * @return Identity map
   */
  protected Map<String, String> createQuartzIdentity(Integer scheduleId) {
    Map<String, String> quartzIdentity = new HashMap<>();
    quartzIdentity.put(JOB_NAME, Long.toString(scheduleId));
    quartzIdentity.put(TRIGGER_NAME, "trigger-" + scheduleId);
    quartzIdentity.put(GROUP, "group-" + scheduleId);
    return quartzIdentity;
  }

  /**
   * Creates the trigger.
   *
   * @param scheduleInfo            the schedule info
   * @param triggerName            the trigger name
   * @param triggerGroup            the trigger group
   * @return the trigger
   */
  protected abstract Trigger createTrigger(TScheduleInfo scheduleInfo, String triggerName,
    String triggerGroup);
}
