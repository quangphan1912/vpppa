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
package com.gcs.vppa.core.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * The Class BaseWsNotifier.
 *
 * @param <TSubject> the generic type
 */
public abstract class AbstractBaseWsNotifier<TSubject> {
  /** The message sender. */
  @Autowired
  private SimpMessagingTemplate messageSender;
  
  /**
   * Gets the subscription.
   *
   * @return the subscription
   */
  protected abstract String getSubscription();

  /**
   * Notify.
   *
   * @param subject the subject
   */
  public void notify(TSubject subject) {
    if (this.messageSender != null) {
      this.messageSender.convertAndSend("/topic/" + this.getSubscription(), subject);
    }
  }
}
