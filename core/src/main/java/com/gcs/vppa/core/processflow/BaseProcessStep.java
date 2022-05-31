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

import java.util.function.Consumer;

import com.gcs.vppa.common.constant.ErrorCodes;
import com.gcs.vppa.core.exception.UserDefinedException;

import lombok.extern.slf4j.Slf4j;

/** The Constant log. */
@Slf4j
public abstract class BaseProcessStep<TContext> implements ProcessStep {

  /** The name. */
  protected String name;

  /** The context. */
  protected TContext context;

  /** The on exception handling. */
  protected Consumer<Exception> onException;

  /**
   * Instantiates a new base process step.
   *
   * @param inName
   *            the in name
   * @param inContext
   *            the in context
   */
  protected BaseProcessStep(String inName, TContext inContext) {
    this.name = inName;
    this.context = inContext;
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
   * Register exception handling.
   *
   * @param onException the on exception
   * @return the process step
   */
  public ProcessStep registerOnException(Consumer<Exception> onException) {
    this.onException = onException;
    return this;
  }

  /**
   * {@inheritDoc}
   * 
   * @see com.gcs.vppa.core.processflow.ProcessStep#execute()
   */
  @Override
  public ProcessResult execute() {
    log.debug("{} - execute <- Enter", this.name);
    ProcessResult result = new ProcessResult(this.name);
    try {
      this.preExecute();
      this.doExecute();
    } catch (UserDefinedException e) {
      log.error("{} - execute...FAILED. {}", this.name, e);
      result.setSuccess(false);
      result.setError(e);
      if (this.onException != null) {
        this.onException.accept(e);
      }

    } catch (Exception e) {
      log.error("{} - unknown exception!!! {}", this.name, e);
      result.setSuccess(false);
      result.setError(new UserDefinedException(ErrorCodes.GENERIC, e.getMessage(), e));
      if (this.onException != null) {
        this.onException.accept(e);
      }
    }

    try {
      this.posExecute(result);
    } catch (Exception e) {
      log.error("{} - failed to execute pos-step. {}", this.name, e);
    }
    log.debug("{} - execute -> Leave", this.name);
    return result;
  }

  /**
   * Do execute.
   */
  protected abstract void doExecute();

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
