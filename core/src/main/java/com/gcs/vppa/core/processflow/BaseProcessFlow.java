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
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import com.gcs.vppa.common.constant.Constants;
import com.gcs.vppa.common.constant.ErrorCodes;
import com.gcs.vppa.common.logging.InjectLog;
import com.gcs.vppa.core.exception.UserDefinedException;

import lombok.extern.slf4j.Slf4j;

/** The Constant log. */
@Slf4j
public abstract class BaseProcessFlow<TContext> implements ProcessFlow {

  /** The name. */
  protected String name;

  /** The process sequences. */
  protected List<ProcessSequence> processSequences = new ArrayList<>();

  /** The context. */
  protected TContext context;

  /** The on exception handling. */
  protected Consumer<Exception> onException;

  /**
   * Instantiates a new base process flow.
   *
   * @param inName
   *            the in name
   * @param inContext
   *            the in context
   */
  protected BaseProcessFlow(String inName, TContext inContext) {
    this.name = inName;
    this.context = inContext;
  }

  /**
   * Instantiates a new base process flow.
   *
   * @param inName
   *            the in name
   */
  protected BaseProcessFlow(String inName) {
    this.name = inName;
    this.context = null;
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
  public ProcessFlow registerOnException(Consumer<Exception> onException) {
    this.onException = onException;
    return this;
  }

  /**
   * {@inheritDoc}
   * 
   * @see com.gcs.vppa.core.processflow.ProcessFlow#execute()
   */
  @Override
  public ProcessResult execute() {
    log.info("{} - execute <- Enter", this.name);
    ProcessResult result = new ProcessResult(this.name);
    try {
      this.preExecute();
      for (ProcessSequence sequence : this.processSequences) {
        ProcessResult sequenceResult = sequence.execute();
        if (!sequenceResult.isSuccess()) {
          result.setSuccess(false);
          result.setError(sequenceResult.getError());
          break;
        }
      }
    } catch (UserDefinedException e) {
      log.error("{} - execute...FAILED. {}", this.name, e);
      result.setSuccess(false);
      result.setError(e);
      if (this.onException != null) {
        this.onException.accept(e);
      }

    } catch (Exception e) {
      log.error("{} - execute...FAILED. {}", this.name, e);
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

    log.info("{} - execute -> Leave", this.name);
    return result;
  }

  /**
   * {@inheritDoc}
   * 
   * @see com.gcs.vppa.core.processflow.ProcessFlow#executeAsync()
   */
  @Override
  @InjectLog(logParams = false)
  public void executeAsync() {
    final ExecutorService taskExecutor = Executors.newFixedThreadPool(Constants.NUM_THREADS);
    taskExecutor.submit(() -> {
      ProcessResult result = this.execute();
      log.debug("{} - processResult: [{}]", this.name, result);
    });
  }

  /**
   * Register sequence.
   *
   * @param sequence
   *            the sequence
   * @return the process sequence
   */
  @Override
  public void registerSequence(ProcessSequence sequence) {
    if (sequence != null && !this.processSequences.contains(sequence)) {
      this.processSequences.add(sequence);
      log.debug("registerSequence - registered [{}]", sequence.getName());
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
    log.debug("{} - posExecute...IGNORED", this.name);
  }
}
