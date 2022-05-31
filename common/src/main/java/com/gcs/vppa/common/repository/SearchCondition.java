/**
 * Copyright (C) 2019, GCS Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Jul 15, 2019     ********            Tuan Le            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.common.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class SearchCondition.
 */

/**
 * Sets the sort direction.
 *
 * @param sortDirection the new sort direction
 */
@Setter

/**
 * Gets the sort direction.
 *
 * @return the sort direction
 */
@Getter

/**
 * Instantiates a new search condition.
 */
@NoArgsConstructor
public class SearchCondition implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 7414289465365231673L;

  /** The conditions. */
  private List<Condition> conditions = new ArrayList<>();

  /** The page. */
  private Integer page;

  /** The size. */
  private Integer size;

  /** The sort name. */
  private String sortName;

  /** The sort direction. */
  private Boolean sortDirection;
}
