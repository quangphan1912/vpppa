/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 13, 2020     ********           hangttran.ht            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.dto;

import java.util.List;

import com.gcs.vppa.common.base.BaseDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class MasterDataParameterDTO.
 *
 * @author hangttran.ht
 */
/**
 * Gets the masterdata param.
 *
 * @return the masterdata param
 */

/**
 * Gets the centers.
 *
 * @return the centers
 */
@Getter

/**
 * Sets the masterdata param.
 *
 * @param masterdataparam the new masterdata
 */

/**
 * Sets the centers.
 *
 * @param centers the new centers
 */
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(callSuper = true)

/**
 * Instantiates a new masterdata param DTO.
 */

/**
 * Instantiates a new master data parameter DTO.
 */
@NoArgsConstructor
public class MasterDataParameterDTO extends BaseDTO<Integer> {

  /**
   * The Constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The id. */
  private Integer id;

  /** The product. */
  private List<ProductDTO> products;

  /** The division. */
  private List<DivisionDTO> divisions;

  /** The center. */
  private List<CenterDTO> centers;

  /**
   * Gets the identifier.
   *
   * @return the identifier
   */
  @Override
  public Integer getIdentifier() {
    return this.id;
  }

  /**
   * Sets the identifier.
   *
   * @param id
   *          the new identifier
   */
  @Override
  public void setIdentifier(Integer id) {
    this.id = id;
  }
}
