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
 * The Class ChannelDTO.
 * 
 * @author hangttran.ht
 *
 */
/**
 * Gets the channel.
 *
 * @return the channel
 */
@Getter

/**
 * Sets the channel.
 *
 * @param channel
 *          the new channel
 */
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(callSuper = true)

/**
 * Instantiates a new channel DTO.
 */
@NoArgsConstructor
public class ChannelDTO extends BaseDTO<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 318831807718728344L;

  /** The channel id. */
  private int id;

  /** The code. */
  private String code;

  /** The name. */
  private String name;

  /** The schemes. */
  private List<SchemeDTO> schemes;

  /**
   * Instantiates a new channel DTO.
   *
   * @param string
   * @param code
   * @param name
   */
  public ChannelDTO(String encryptedId, String code, String name) {
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
