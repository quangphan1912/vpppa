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

import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class GlobalProperties.
 *
 * @author Administrator
 */

/**
 * Checks if is enable init seed data.
 *
 * @return true, if is enable init seed data
 */
@Getter
@Setter
@Component
@ConfigurationProperties
@ToString
public class GlobalProperties {
  
  /** The url. */
  @Value("${spring.datasource.url}")
  private String url;
  
  /** The username. */
  @Value("${spring.datasource.username}")
  private String userLogin;
  
  /** The password. */
  @Value("${spring.datasource.password}")
  private String password;
  
  /** The enable database schema. */
  @Value("${spring.database.enable-db-schema}")
  private boolean enableDatabaseSchema;
  
  /** The enable database update schema. */
  @Value("${spring.database.enable-db-update}")
  private boolean enableDatabaseUpdateSchema;
  
  /** The enable database view. */
  @Value("${spring.database.enable-db-view}")
  private boolean enableDatabaseView;
  
  /** The enable init seed data. */
  @Value("${spring.database.enable-db-seeddata}")
  private boolean enableInitSeedData;

  /** The enable init seed data. */
  @Value("${spring.email.bicc}")
  private String bicc;

  /** The enable init seed data. */
  @Value("${spring.email.bau}")
  private String bau;

  /** The enable init seed data. */
  @Value("${spring.camunda.url}")
  private String camundaUrl;
  
  /** The enable init seed data. */
  @Value("${spring.database.enable-db-seed-update}")
  private boolean enableInitSeedUpgradeData;
}