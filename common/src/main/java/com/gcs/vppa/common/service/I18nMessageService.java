/*
 * (C) Copyright Global Cybersoft (GCS) 2020. All rights reserved. Proprietary and confidential.
 */
package com.gcs.vppa.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */
/**
 * Instantiates a new base data service.
 */
public class I18nMessageService {

  /** The message source. */
  @Autowired
  private MessageSource messageSource;

  /**
   * Gets the message.
   *
   * @param messageCode the message code
   * @return the message
   */
  public String getMessage(String messageCode) {
    return messageSource.getMessage(messageCode, null, LocaleContextHolder.getLocale());
  }

  /**
   * Gets the message.
   *
   * @param messageCode the message code
   * @param params the params
   * @return the message
   */
  public String getMessage(String messageCode, Object... params) {
    return String.format(messageSource.getMessage(messageCode, null, LocaleContextHolder.getLocale()), params);
  }
}
