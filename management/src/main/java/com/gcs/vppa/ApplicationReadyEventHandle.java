/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 19, 2020     ********           Administrator            Initialize                  
 *                                                                                
 */
package com.gcs.vppa;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import com.gcs.vppa.common.config.model.GlobalProperties;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class SeedLoaded.
 */
@Component

/** The Constant log. */

/** The Constant log. */
@Slf4j
public class ApplicationReadyEventHandle {
  /** The global properties. */
  @Autowired
  private GlobalProperties globalProperties;

  /** The Constant POSTGRES_DRIVER. */
  private static final String POSTGRES_DRIVER = "org.postgresql.Driver";

  /** The Constant SEED_FOLDER_PATH. */
  private static final String SEED_FOLDER_PATH = "db/seed";
  
  /** The Constant SEED_UPDATE_FOLDER_PATH. */
  private static final String SEED_UPDATE_FOLDER_PATH = "db/seed/update";

  /** The Constant SCHEMA_FOLDER_PATH. */
  private static final String SCHEMA_FOLDER_PATH = "db/schema";
  
  /** The Constant SCHEMA_UPDATE_FOLDER_PATH. */
  private static final String SCHEMA_UPDATE_FOLDER_PATH = "db/schema/update";

  /** The Constant VIEW_FOLDER_PATH. */
  private static final String VIEW_FOLDER_PATH = "db/view";

  /**
   * Run.
   *
   * @param event the event
   */
  @EventListener
  public void run(ApplicationReadyEvent event) {
    log.info("Application initialize ...");
    log.info("Application Ready.");
  }

  /**
   * Master data source.
   *
   * @return the data source
   */
  public DataSource masterDataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();

    dataSource.setDriverClassName(POSTGRES_DRIVER);
    dataSource.setUrl(globalProperties.getUrl());
    dataSource.setUsername(globalProperties.getUserLogin());
    dataSource.setPassword(globalProperties.getPassword());

    return dataSource;
  }

  /**
   * Data source initializer.
   *
   * @return the data source initializer
   * @throws IOException 
   */
  @Bean
  public DataSourceInitializer dataSourceInitializer() {
    // Configuration files data master which import in database master
    DataSource ds = masterDataSource();
    
    // update database schema, view, seed.
    if (globalProperties.isEnableDatabaseSchema()) {
      this.updateDatabase(SCHEMA_FOLDER_PATH, ds);
    }

    // update database schema, view, seed.
    if (globalProperties.isEnableDatabaseUpdateSchema()) {
      this.updateDatabase(SCHEMA_UPDATE_FOLDER_PATH, ds);
    }

    if (globalProperties.isEnableDatabaseView()) {
      this.updateDatabase(VIEW_FOLDER_PATH, ds);
    }

    if (globalProperties.isEnableInitSeedData()) {
      this.updateDatabase(SEED_FOLDER_PATH, ds);
    }
    
    if (globalProperties.isEnableInitSeedUpgradeData()) {
      this.updateDatabase(SEED_UPDATE_FOLDER_PATH, ds);
    }

    // DataSourceInitializer
    DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
    dataSourceInitializer.setDataSource(ds);

    return dataSourceInitializer;
  }

  /**
   * Update master data.
   *
   * @param resourceDatabasePopulator the resource database populator
   * @throws IOException Signals that an I/O exception has occurred.
   */
  private void updateDatabase(String folderPath, DataSource ds) {
    try {
      ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
      ClassLoader cl = this.getClass().getClassLoader();
      ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
      Resource[] resources = resolver.getResources(String.format("classpath:/%s/*.sql", folderPath));
     
      Arrays.sort(resources, Comparator.comparing(Resource::getFilename));
      
      for (Resource resource : resources) {
        log.info(String.format("Add file: [%s]", resource.getFilename()));
        resourceDatabasePopulator
          .addScript(new ClassPathResource(String.format("/%s/%s", folderPath, resource.getFilename())));
      }
      
      resourceDatabasePopulator.execute(ds);
    } catch (IOException e) {
      log.warn(e.getMessage());
    }
  }
}
