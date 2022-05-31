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
 * @author hangttran.ht
 *
 */
/**
 * Gets the department.
 *
 * @return the department
 */
@Getter

/**
 * Sets the department.
 *
 * @param department
 *          the new department
 */
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(callSuper = true)

/**
 * Instantiates a new department DTO.
 */
@NoArgsConstructor
public class DepartmentDTO extends BaseDTO<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 318831807718728344L;

  /** The department id. */
  private int id;

  /** The code. */
  private String code;

  /** The name. */
  private String name;

  /** The center id. */
  private String centerId;

  /** The center. */
  private CenterDTO center;

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
   * The new Department dto.
   * 
   * @param encryptedId
   * @param code
   * @param name
   */
  public DepartmentDTO(String encryptedId, String code, String name, CenterDTO center) {
    this.setEncryptedId(encryptedId);
    this.code = code;
    this.name = name;
    this.center = center;
  }

}
