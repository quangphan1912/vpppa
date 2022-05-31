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
 * Gets the job type.
 *
 * @return the job type
 */
@Getter

/**
 * Sets the job type.
 *
 * @param jobType the new job type
 */
@Setter
public abstract class BaseScheduleInfo<TJob extends Job> {

  /** The id. */
  protected Integer id;

  /** The job. */
  protected Class<TJob> jobType;
}
