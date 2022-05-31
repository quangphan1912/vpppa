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
package com.gcs.vppa.common.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * The Class Condition.
 */

/**
 * Sets the join type.
 *
 * @param joinType the new join type
 */
@Setter

/**
 * Gets the join type.
 *
 * @return the join type
 */
@Getter

/**
 * Instantiates a new condition.
 */
@NoArgsConstructor
public class Condition implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 8421542667431354316L;

  /** The search key. */
  private String key;

  /** The search value of key. */
  private transient Object value;

  /** The search type, like eq, ge, gt, le, lt..... */
  private String operator;

  /** The data type (string, number, date). */
  private String type;

  /** The join table. */
  private String joinTable;

  /** The join type. */
  private String joinType;
}
