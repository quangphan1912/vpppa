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
package com.gcs.vppa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * The Class ManagementBootApplication.
 */
@SpringBootApplication

/**
 * Instantiates a new management boot application.
 */
@ComponentScan({"com.gcs.vppa"})
@EnableTransactionManagement
@EnableScheduling
public class ManagementBootApplication {
  
  /**
   * Instantiates a new management boot application.
   */
  public ManagementBootApplication() {
    // Do nothing.
  }
  
  /**
   * The main method.
   *
   * @param args
   *            the arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(ManagementBootApplication.class, args);
  }
}
