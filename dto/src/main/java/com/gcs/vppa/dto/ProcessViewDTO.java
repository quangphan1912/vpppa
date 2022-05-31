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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gcs.vppa.common.base.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The class ProcessDTO.
 * 
 * @author hangttran.ht
 *
 */

/**
 * Gets the process.
 *
 * @return the process type
 */
@Getter

/**
 * Sets the process type.
 *
 * @param process
 *          the new process
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
public class ProcessViewDTO extends BaseDTO<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 318831807718728344L;

  /** The process id. */
  private int id;

  /** The name. */
  private String name;

  /** The name. */
  private String key;

  /** The description. */
  private String description;

  /** The expression. */
  private String expression;

  /** The process type. */
  private String processTypeId;

  /** The process type. */
  private String processTypeName;

  /** The division. */
  private String divisionId;

  /** The division. */
  private String divisionName;

  /** The center. */
  private String centerId;

  /** The center. */
  private String centerName;

  /**
   * Instantiates a new process DTO.
   *
   * @param encryptedId
   */
  public ProcessViewDTO(String encryptedId) {
    this.setEncryptedId(encryptedId);
  }

  /**
   * {@inheritDoc}
   * 
   * @see BaseDTO#getIdentifier()
   */
  @Override
  @JsonIgnore
  public Integer getIdentifier() {
    return this.id;
  }

  /**
   * {@inheritDoc}
   * 
   * @see BaseDTO#setIdentifier(Object)
   */
  @Override
  @JsonIgnore
  public void setIdentifier(Integer id) {
    this.id = id;
  }

}
