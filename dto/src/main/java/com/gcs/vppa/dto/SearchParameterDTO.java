/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 14, 2020     ********           hangttran.ht            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The class SearchParameterDTO.
 * 
 * @author hangttran.ht
 *
 */
/**
 * Sets the sort direction.
 *
 * @param sortDirection
 *          the new sort direction
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
public class SearchParameterDTO implements Serializable {

  /**
   * The Constant serialVersionUID,
   */
  private static final long serialVersionUID = 1L;

  /** The param Id. */
  private int paramId;

  /** The param name. */
  private String paramName;

  /** The scheme Id. */
  private Integer schemeId;

  /** The encrypted Id. */
  private String encryptedSchemeId;

  /** The scheme name. */
  private String schemeName;

  /** The division. */
  private String division;

  /** The status. */
  private String status;

  /** The product. */
  private String product;

  /** The center. */
  private String center;
}
