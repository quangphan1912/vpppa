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
package com.gcs.vppa.common.constant;

/**
 * The Class Constants.
 */
public final class Constants {
  
  /** The admin type. */
  public static int ADMIN_TYPE = 1;
  
  /** The normal type. */
  public static int NORMAL_TYPE = 2;
  
  /** The Constant NUM_CORES. */
  public static final Integer NUM_CORES = Runtime.getRuntime().availableProcessors();

  /** The Constant NUM_THREADS. */
  public static final Integer NUM_THREADS = (NUM_CORES > 1 ? NUM_CORES * 3 : 10);

  /** The Constant HEX_FF. */
  public static final Integer HEX_FF = 0xFF;

  /** The Constant DATE_TIME_FORMAT. */
  public static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";

  /** The Constant DATE_TIME_FORMAT_2. */
  public static final String DATE_TIME_FORMAT_2 = "yyyy-MM-dd HH:mm:ss";

  /** The Constant DATE_FORMAT. */
  public static final String DATE_FORMAT = "dd/MM/yyyy";

  /** The Constant DATE_STRING_FORMAT. */
  public static final String DATE_STRING_FORMAT = "dd-MM-yyyy";

  /** The Constant DATE_STRING_FORMAT_DIR. */
  public static final String DATE_STRING_FORMAT_DIR = "dd-MM-yyyy-HH-mm-ss";

  /** The Constant TIME_FORMAT. */
  public static final String TIME_FORMAT = "HH:mm";

  /** The Constant MAX_PAGE_SIZE. */
  public static final Integer MAX_PAGE_SIZE = 999_999_999;

  /** The Constant ARCHIVING_FOLDER_NAME. */
  public static final String ARCHIVING_FOLDER_NAME = "DataArchiving";

  /** The Constant CSV_CLASS. */
  public static final String CSV_CLASS = "Class.csv";

  /** The Constant CSV_FILE_EXTENSION. */
  public static final String CSV_FILE_EXTENSION = ".csv";

  /** The Constant FIRST_ATTENDANCE_VALID. */
  public static final Integer FIRST_ATTENDANCE_VALID = 0;

  /** The Constant DEFAULT_ATTENDANCE_VALID. */
  public static final Integer DEFAULT_ATTENDANCE_VALID = 60;

  /** The Constant PURGE_JOB_ID. */
  public static final Integer PURGE_JOB_ID = 1;

  /** The Constant PURGE_JOB_MIN_CONFIGURATION. */
  public static final Integer PURGE_JOB_MIN_CONFIGURATION = 1;

  /** The Constant PURGE_JOB_MAX_CONFIGURATION. */
  public static final Integer PURGE_JOB_MAX_CONFIGURATION = 65535;

  /** The Constant TIME_FORMAT_HH_MM_SS. */
  public static final String TIME_FORMAT_HH_MM_SS = "HH:mm:ss";

  /** The Constant JPA_MAX_PARAMETER. */
  public static final Integer JPA_MAX_PARAMETER = 1000;

  /** The Constant NUMBER_OF_DAY_IN_MONTH. */
  public static final Integer NUMBER_OF_DAY_IN_MONTH = 30;

  /** The Constant NUMBER_OF_DAY_IN_YEAR. */
  public static final Integer NUMBER_OF_DAY_IN_YEAR = 365;

  /** The Constant STATUS. */
  public static final String KEY_STATUS = "status";
  
  /** The Constant KEY_STATUS_SCHEME. */
  public static final String KEY_STATUS_SCHEME = "statusScheme";

  /** The Constant STRING. */
  public static final String TYPE_STRING = "string";

  /** The Constant OPERATOR_NOT_EQ_IN. */
  public static final String OPERATOR_NOT_EQ_IN = "notEqIn";

  /** The Constant JOIN_TYPE_INNER. */
  public static final String JOIN_TYPE_INNER = "inner";

  /** The Constant RESOURCE_SERVER. */
  public static final String RESOURCE_SERVER = "/home/data/";

  /** The Constant TEMPLATE. */
  public static final String TEMPLATE = "template";

  /** The Constant RESULT. */
  public static final String RESULT = "result";
  
  
  /** The Constant USER_STATUS_ACTIVE. */
  public static final String USER_STATUS_ACTIVE = "Active";
  
  /** The Constant USER_STATUS_ACTIVE. */
  public static final String USER_STATUS_INACTIVE = "Inactive";

  /** The Constant ACCESS_USER. */
  public static final String ACCESS_USER = "access_user";
  
  /**
   * Init the Constants.
   */
  private Constants() {
    // Do nothing.
  }
}
