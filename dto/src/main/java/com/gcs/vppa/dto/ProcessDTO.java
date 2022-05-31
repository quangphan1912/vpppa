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
import com.gcs.vppa.common.dto.FileDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

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
public class ProcessDTO extends BaseDTO<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 318831807718728344L;

  /** The process id. */
  private int id;

  /** The code. */
  private String code;

  /** The name. */
  private String name;

  /** The name. */
  private String key;

  /** The description. */
  private String description;

  /** The expression. */
  private String expression;

  /** The scheme type. */
  private ProcessTypeDTO processType;

  /** The division. */
  private DivisionDTO division;

  /** The center. */
  private CenterDTO center;

  private List<ProcessReportDTO> reportList;

  /**
   * Instantiates a new process DTO.
   *
   * @param encryptedId
   * @param code
   * @param name
   */
  public ProcessDTO(String encryptedId, String code, String name) {
    this.setEncryptedId(encryptedId);
    this.code = code;
    this.name = name;
  }

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

}
