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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.gcs.vppa.common.constant.ErrorCodes;
import com.gcs.vppa.common.util.CollectionUtil;
import com.gcs.vppa.core.exception.UserDefinedException;
import com.gcs.vppa.core.executor.CountDownJobExecutor;

import lombok.extern.slf4j.Slf4j;

/** The Constant log. */
@Slf4j
public abstract class BaseProcessSequence<TContext> implements ProcessSequence {

  /** The name. */
  protected String name;

  /** The context. */
  protected TContext context;

  /** The process steps. */
  protected List<ProcessStep> processSteps = new ArrayList<>();

  /**
   * Instantiates a new base process sequence.
   *
   * @param name
   *            the name
   */
  protected BaseProcessSequence(String name) {
    this.name = name;
    this.context = null;
  }

  /**
   * Instantiates a new base process sequence.
   *
   * @param name
   *            the name
   * @param context
   *            the context
   */
  protected BaseProcessSequence(String name, TContext context) {
    this.name = name;
    this.context = context;
  }

  /**
   * Gets the name.
   *
   * @return the name
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * {@inheritDoc}
   * 
   * @see com.gcs.vppa.core.processflow.ProcessSequence#execute()
   */
  @Override
  public ProcessResult execute() {
    log.info("{} - execute <- Enter", this.name);
    ProcessResult result = new ProcessResult(this.name);
    try {
      this.preExecute();
      List<ProcessResult> stepResultList = Collections.synchronizedList(new ArrayList<ProcessResult>());
      
      log.debug("{} - execute: num_steps=[{}]", this.name, this.processSteps.size());
      CountDownJobExecutor.execute(this.name, this.processSteps, step -> stepResultList.add(step.execute()), true);
      
      log.debug("{} - execute: all steps...DONE", this.name);
      List<ProcessResult> failedResults = stepResultList.stream().filter(n -> !n.isSuccess())
        .collect(Collectors.toList());
      
      // Found any failed
      if (!CollectionUtil.isNullOrEmpty(failedResults)) {
        UserDefinedException rootCause = failedResults.get(0).getError();
        throw new UserDefinedException(ErrorCodes.GENERIC, rootCause.getMessage(), rootCause);
      }
    } catch (UserDefinedException e) {
      log.error("{} - execute...FAILED. {}", this.name, e);
      result.setSuccess(false);
      result.setError(e);
    } catch (Exception e) {
      log.error("{} - execute...FAILED. {}", this.name, e);
      result.setSuccess(false);
      result.setError(new UserDefinedException(ErrorCodes.GENERIC, e.getMessage(), e));
    }

    try {
      this.posExecute(result);
    } catch (Exception e) {
      log.error("{} - failed to execute pos-step. {}", this.name, e);
    }

    log.info("{} - execute -> Leave", this.name);
    return result;
  }

  /**
   * Register step.
   *
   * @param step
   *            the step
   * @return the process step
   */
  public void registerStep(ProcessStep step) {
    if (step != null && !this.processSteps.contains(step)) {
      this.processSteps.add(step);
      log.debug("registerStep - registered [{}]", step.getName());
    }
  }

  /**
   * Pre execute.
   */
  protected void preExecute() {
    log.debug("{} - preExecute...IGNORED", this.name);
  }

  /**
   * Post execute.
   *
   * @param result
   *            the result
   */
  protected void posExecute(ProcessResult result) {
    log.debug("{} - postExecute...IGNORED", this.name);
  }
}
