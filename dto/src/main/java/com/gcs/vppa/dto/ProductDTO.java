/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 10, 2020     ********           hangttran.ht            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gcs.vppa.common.base.BaseDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class ProductDTO.
 *
 * @author hangttran.ht
 */
/**
 * Gets the product.
 *
 * @return the product type
 */
@Getter

/**
 * Sets the product type.
 *
 * @param product
 *          the new product
 */
@Setter
/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(callSuper = true)
/**
 * Instantiates a new product DTO.
 */
@NoArgsConstructor
public class ProductDTO extends BaseDTO<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 318831807718728344L;

  /** The product id. */
  private int id;

  /** The code. */
  private String code;

  /** The name. */
  private String name;

  /** The divition id. */
  private String parentId;

  /** The schemes. */
  private List<SchemeDTO> schemes;

  /**
   * {@inheritDoc}
   * 
   * @see com.gcs.vppa.common.base.BaseDTO#getIdentifier()
   */
  @Override
  @JsonIgnore
  public Integer getIdentifier() {
    return this.id;
  }

  /**
   * {@inheritDoc}
   * 
   * @see com.gcs.vppa.common.base.BaseDTO#setIdentifier(java.lang.Object)
   */
  @Override
  @JsonIgnore
  public void setIdentifier(Integer id) {
    this.id = id;
  }

  /**
   * Instantiates a new product DTO.
   *
   * @param id the id
   * @param code the code
   * @param name the name
   * @param parentId the parent id
   * @param subProduct the sub product
   */
  public ProductDTO(Integer id, String code, String name, String parentId) {
    this.id = id;
    this.code = code;
    this.name = name;
    this.parentId = parentId;
  }

  /**
   * Instantiates a new product DTO.
   *
   * @param id the id
   * @param code the code
   * @param name the name
   */
  public ProductDTO(Integer id, String code, String name) {
    this.id = id;
    this.code = code;
    this.name = name;
  }

}
