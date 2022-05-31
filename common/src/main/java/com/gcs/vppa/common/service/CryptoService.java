/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 15, 2020     ********           Administrator            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.common.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcs.vppa.common.config.model.CryptoProperties;

/**
 * The Class CryptoService.
 *
 * @author Administrator
 */
public class CryptoService {
  /** The Constant KEY_PATTERN. */
  private static final String KEY_PATTERN = "%s:%s";

  /** The crypto properties. */
  @Autowired
  private CryptoProperties cryptoProperties;

  /**
   * Gets the product key.
   *
   * @return the product key
   */
  public String getProductKey() {
    return String.format(KEY_PATTERN, cryptoProperties.getOriginal(),
        cryptoProperties.getProduct());
  }

  /**
   * Gets the sub product key.
   *
   * @return the sub product key
   */
  public String getSubProductKey() {
    return String.format(KEY_PATTERN, cryptoProperties.getOriginal(),
        cryptoProperties.getProduct());
  }

  /**
   * Gets the center key.
   *
   * @return the center key
   */
  public String getCenterKey() {
    return String.format(KEY_PATTERN, cryptoProperties.getOriginal(),
        cryptoProperties.getCenter());
  }

  /**
   * Gets the parameter key.
   *
   * @return the parameter key
   */
  public String getParameterKey() {
    return String.format(KEY_PATTERN, cryptoProperties.getOriginal(),
        cryptoProperties.getParameter());
  }

  /**
   * Gets the division key.
   *
   * @return the divion key
   */
  public String getDivisionKey() {
    return String.format(KEY_PATTERN, cryptoProperties.getOriginal(),
        cryptoProperties.getDivision());
  }

  /**
   * Gets the division Proposal key.
   *
   * @return the division Proposal key
   */
  public String getDivisionProposalKey() {
    return String.format(KEY_PATTERN, cryptoProperties.getOriginal(),
        cryptoProperties.getDivision());
  }

  /**
   * Gets the scheme key.
   *
   * @return the scheme key
   */
  public String getSchemeKey() {
    return String.format(KEY_PATTERN, cryptoProperties.getOriginal(),
        cryptoProperties.getScheme());
  }

  /**
   * Gets the scheme type key.
   *
   * @return the scheme type key
   */
  public String getSchemeTypeKey() {
    return String.format(KEY_PATTERN, cryptoProperties.getOriginal(),
        cryptoProperties.getSchemetype());
  }

  /**
   * Gets the scheme type key.
   *
   * @return the scheme type key
   */
  public String getProcessTypeKey() {
    return String.format(KEY_PATTERN, cryptoProperties.getOriginal(),
            cryptoProperties.getProcesstype());
  }

  /**
   * Gets the process key.
   *
   * @return the process key
   */
  public String getProcessKey() {
    return String.format(KEY_PATTERN, cryptoProperties.getOriginal(),
        cryptoProperties.getProcess());
  }

  /**
   * Gets the process report.
   *
   * @return the process report
   */
  public String getProcessReport() {
    return String.format(KEY_PATTERN, cryptoProperties.getOriginal(),
            cryptoProperties.getProcessreport());
  }

  /**
   * Gets the process report.
   *
   * @return the process report
   */
  public String getSchemeExecutor() {
    return String.format(KEY_PATTERN, cryptoProperties.getOriginal(),
            cryptoProperties.getSchemeexecutor());
  }

  /**
   * Gets the channel key.
   *
   * @return the channel key
   */
  public String getChannelKey() {
    return String.format(KEY_PATTERN, cryptoProperties.getOriginal(),
        cryptoProperties.getChannel());
  }

  /**
   * Gets the department key.
   *
   * @return the department key
   */
  public String getDepartmentKey() {
    return String.format(KEY_PATTERN, cryptoProperties.getOriginal(),
        cryptoProperties.getDepartment());
  }

  /**
   * Gets the campaign key.
   *
   * @return the campaign key
   */
  public String getCampaignKey() {
    return String.format(KEY_PATTERN, cryptoProperties.getOriginal(),
        cryptoProperties.getCampaign());
  }

  /**
   * Gets the position key.
   *
   * @return the position key
   */
  public String getPositionKey() {
    return String.format(KEY_PATTERN, cryptoProperties.getOriginal(),
        cryptoProperties.getPosition());
  }

  /**
   * Gets the scheme status key.
   *
   * @return the scheme status key
   */
  public String getSchemeStatusKey() {
    return String.format(KEY_PATTERN, cryptoProperties.getOriginal(),
        cryptoProperties.getSchemestatus());
  }
  
  /**
   * Gets the user role key.
   *
   * @return the user role key
   */
  public String getUserRoleKey() {
    return String.format(KEY_PATTERN, cryptoProperties.getOriginal(),
      cryptoProperties.getUserRole());
  }

  /**
   * Gets the Parameter condition key.
   *
   * @return the Parameter condition key
   */
  public String getParameterConditionKey() {
    return String.format(KEY_PATTERN, cryptoProperties.getOriginal(),
        cryptoProperties.getParameterCondition());
  }

  /**
   * Gets the user role key.
   *
   * @return the user role key
   */
  public String getUserGroupKey() {
    return String.format(KEY_PATTERN, cryptoProperties.getOriginal(),
      cryptoProperties.getUserGroup());
  }
  
  /**
   * Gets the user role key.
   *
   * @return the user role key
   */
  public String getPermissionKey() {
    return String.format(KEY_PATTERN, cryptoProperties.getOriginal(),
      cryptoProperties.getPermission());
  }
  
  /**
   * Gets the ldap user key.
   *
   * @return the ldap user key
   */
  public String getLdapUserKey() {
    return String.format(KEY_PATTERN, cryptoProperties.getOriginal(),
      cryptoProperties.getLdapUser());
  }
  
  /**
   * Gets the vppa user key.
   *
   * @return the vppa user key
   */
  public String getVppaUserKey() {
    return String.format(KEY_PATTERN, cryptoProperties.getOriginal(),
      cryptoProperties.getVppaUser());
  }
}
