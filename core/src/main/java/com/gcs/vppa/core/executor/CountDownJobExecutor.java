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
package com.gcs.vppa.core.executor;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import com.gcs.vppa.common.constant.Constants;
import com.gcs.vppa.common.constant.ErrorCodes;
import com.gcs.vppa.core.exception.UserDefinedException;

import lombok.extern.slf4j.Slf4j;

/** The Constant log. */
@Slf4j
public final class CountDownJobExecutor {

  /**
   * Instantiates a new count down job executor.
   */
  private CountDownJobExecutor() {
    // Do nothing.
  }

  /**
   * Execute.
   *
   * @param <T>
   *            the generic type
   * @param taskName
   *            the task name
   * @param list
   *            the list
   * @param consumer
   *            the consumer
   * @param numThread
   *            the num thread
   * @param logCountDownInfo
   *            the log count down info
   */
  public static <T> void execute(String taskName, List<T> list, Consumer<T> consumer, Integer numThread,
    Boolean logCountDownInfo) {
    doExecute(Executors.newFixedThreadPool(numThread), taskName, list, consumer, logCountDownInfo);
  }

  /**
   * Execute.
   *
   * @param <T>            the generic type
   * @param taskName            the task name
   * @param list            the list
   * @param consumer            the consumer
   * @param numThread the num thread
   */
  public static <T> void execute(String taskName, List<T> list, Consumer<T> consumer, Integer numThread) {
    doExecute(Executors.newFixedThreadPool(numThread), taskName, list, consumer, false);
  }

  /**
   * Execute.
   *
   * @param <T>            the generic type
   * @param taskName            the task name
   * @param list            the list
   * @param consumer            the consumer
   * @param logCountDownInfo the log count down info
   */
  public static <T> void execute(String taskName, List<T> list, Consumer<T> consumer, Boolean logCountDownInfo) {
    doExecute(Executors.newFixedThreadPool(Constants.NUM_THREADS), taskName, list, consumer, logCountDownInfo);
  }

  /**
   * Execute.
   *
   * @param <T>
   *            the generic type
   * @param taskName
   *            the task name
   * @param list
   *            the list
   * @param consumer
   *            the consumer
   */
  public static <T> void execute(String taskName, List<T> list, Consumer<T> consumer) {
    doExecute(Executors.newFixedThreadPool(Constants.NUM_THREADS), taskName, list, consumer, false);
  }

  /**
   * Execute seq.
   *
   * @param <T>            the generic type
   * @param taskName            the task name
   * @param list            the list
   * @param consumer            the consumer
   * @param logCountDownInfo the log count down info
   */
  public static <T> void executeSeq(String taskName, List<T> list, Consumer<T> consumer, Boolean logCountDownInfo) {
    doExecute(Executors.newSingleThreadExecutor(), taskName, list, consumer, logCountDownInfo);
  }

  /**
   * Execute seq.
   *
   * @param <T>
   *            the generic type
   * @param taskName
   *            the task name
   * @param list
   *            the list
   * @param consumer
   *            the consumer
   */
  public static <T> void executeSeq(String taskName, List<T> list, Consumer<T> consumer) {
    doExecute(Executors.newSingleThreadExecutor(), taskName, list, consumer, false);
  }

  /**
   * Do execute.
   *
   * @param <T>            the generic type
   * @param taskExecutor            the task executor
   * @param taskName            the task name
   * @param list            the list
   * @param consumer            the consumer
   * @param logCountDownInfo the log count down info
   */
  private static <T> void doExecute(ExecutorService taskExecutor, String taskName, List<T> list, Consumer<T> consumer,
    Boolean logCountDownInfo) {
    log.info("{} - TOTAL=[{}]...", taskName, list.size());
    
    final CountDownLatch latch = new CountDownLatch(list.size());
    
    list.stream().forEach(item -> taskExecutor.submit(() -> {
      try {
        consumer.accept(item);
      } catch (Exception e) {
        log.error("{} - failed to execute task, count=[{}], error: {}", taskName, latch.getCount(), e);
      } finally {
        latch.countDown();
        if (logCountDownInfo) {
          log.info("{} - COUNT=[{}]", taskName, latch.getCount());
        } else {
          log.debug("{} - COUNT=[{}]", taskName, latch.getCount());
        }
      }
    }));

    try {
      latch.await();
      taskExecutor.shutdown();
    } catch (InterruptedException e) {
      String errorMsg = String.format("%s - failed to parallel execute", taskName);
      log.error(errorMsg, e);
      throw new UserDefinedException(ErrorCodes.ERROR_UNKNOW, errorMsg, e);
    }

    log.info("{} - TOTAL=[{}]...DONE", taskName, list.size());
  }
}
