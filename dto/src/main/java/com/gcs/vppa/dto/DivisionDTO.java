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
 * The class DivisionDTO.
 * 
 * @author hangttran.ht
 *
 */
/**
 * Gets the division.
 *
 * @return the division
 */
@Getter

/**
 * Sets the division.
 *
 * @param division
 *          the new division
 */
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(callSuper = true)

/**
 * Instantiates a new division DTO.
 */
@NoArgsConstructor
public class DivisionDTO extends BaseDTO<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 318831807718728344L;

  /** The division id. */
  private int id;

  /** The code. */
  private String code;

  /** The name. */
  private String name;

  /** The channel id. */
  private String channelId;

  /** The channel. */
  private ChannelDTO channel;

  /** The schemes. */
  private List<SchemeDTO> schemes;

  /**
   * Instantiates a new division DTO.
   *
   * @param encryptedId
   * @param code
   * @param name
   */
  public DivisionDTO(String encryptedId, String code, String name) {
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
