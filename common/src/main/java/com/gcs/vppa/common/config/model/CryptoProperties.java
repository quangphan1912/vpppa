/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 13, 2020     ********           Administrator            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.common.config.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class CryptoProperties.
 *
 * @author Administrator
 */

/**
 * Gets the scheme.
 *
 * @return the scheme
 */
@Getter

/**
 * Sets the scheme.
 *
 * @param scheme
 *          the new scheme
 */
@Setter
@Component
@ConfigurationProperties("crypto")
public class CryptoProperties {

  /** The original. */
  private String original;

  /** The parameter. */
  private String parameter;

  /** The scheme. */
  private String scheme;

  /** The salt. */
  private String salt;

  /** The division. */
  private String division;

  /** The product. */
  private String product;

  /** The center. */
  private String center;

  /** The schemetype. */
  private String schemetype;

  /** The process. */
  private String process;

  /** The schemetype. */
  private String processtype;

  /** The schemetype. */
  private String processTypeKey;

  /** The process. */
  private String processreport;

  private String schemeexecutor;

  /** The channel. */
  private String channel;

  /** The department. */
  private String department;

  /** The campaign. */
  private String campaign;

  /** The position. */
  private String position;

  /** The scheme status. */
  private String schemestatus;

  /** The parametercondition. */
  private String parameterCondition;

  /** The user role. */
  @Value("${crypto.user-role}")
  private String userRole;
  
  /** The user role. */
  @Value("${crypto.user-group}")
  private String userGroup;
  
  /** The permission. */
  private String permission;
  
  /** The ldap user. */
  @Value("${crypto.ldap}")
  private String ldapUser;
  
  /** The user. */
  @Value("${crypto.vppa-user}")
  private String vppaUser;
  
  
}
