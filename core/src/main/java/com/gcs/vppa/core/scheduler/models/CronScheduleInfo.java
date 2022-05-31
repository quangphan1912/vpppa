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
 * Gets the time zone.
 *
 * @return the time zone
 */
@Getter

/**
 * Sets the time zone.
 *
 * @param timeZone the new time zone
 */
@Setter
public class CronScheduleInfo<TJob extends Job> extends BaseScheduleInfo<TJob> {

  /** The hours. */
  private Integer hours;

  /** The minutes. */
  private Integer minutes;

  /** The seconds. */
  private Integer seconds;

  /** The interval hours. */
  private Integer intervalHours;

  /** The time zone. */
  private String timeZone;

  /**
   * Instantiates a new job schedule info.
   */
  public CronScheduleInfo() {
    this.timeZone = "Asia/Ho_Chi_Minh";
  }
}
