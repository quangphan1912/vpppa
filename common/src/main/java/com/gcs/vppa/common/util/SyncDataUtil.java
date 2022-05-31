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
package com.gcs.vppa.common.util;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Instantiates a new sync data utilities.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)

/** The Constant log. */
@Slf4j
public final class SyncDataUtil {

  /**
   * Gets the start sync day of month.
   *
   * @param startTime the start time
   * @return the start synchronize day of month
   */
  public static Integer getStartSyncDayOfMonth(Time startTime) {
    LocalDate localCurrDate = LocalDate.now();
    LocalTime localCurrTime = LocalTime.now();
    LocalTime localStartTime = startTime.toLocalTime();

    Integer startDayOfMonth = 0;

    if (localCurrTime.isBefore(localStartTime)) {
      startDayOfMonth = localCurrDate.getDayOfMonth();
    } else {
      startDayOfMonth = localCurrDate.getDayOfMonth() + 1;
    }

    log.debug("getStartSyncDayOfMonth - currDate=[{}], startTime=[{}], startDayOfMonth=[{}]",
      localCurrDate, startTime, startDayOfMonth);

    return startDayOfMonth;
  }
}
