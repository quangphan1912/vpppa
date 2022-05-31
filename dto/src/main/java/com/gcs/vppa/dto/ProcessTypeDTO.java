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

import java.util.List;

/**
 * The class SchemeTypeDTO.
 * 
 * @author hangttran.ht
 *
 */
@Getter
@Setter
@ToString(callSuper = true)

/**
 * Instantiates a new Process type DTO.
 */
@NoArgsConstructor
public class ProcessTypeDTO extends BaseDTO<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 318831807718728344L;

  /** The type id. */
  private int id;

  /** The code. */
  private String code;

  /** The name. */
  private String name;

  /** The scheme. */
  private List<ProcessDTO> processes;

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

  public ProcessTypeDTO(String encryptedId, String code, String name) {
    this.setEncryptedId(encryptedId);
    this.code = code;
    this.name = name;
  }

}
