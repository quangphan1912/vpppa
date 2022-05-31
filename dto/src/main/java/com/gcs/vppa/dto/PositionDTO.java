/*
 * (C) Copyright Global Cybersoft (GCS) 2020. All rights reserved. Proprietary and confidential.
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
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 *
 */
/**
 * Gets the position.
 *
 * @return the position
 */
@Getter

/**
 * Sets the position.
 *
 * @param position
 *          the new position
 */
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(callSuper = true)

/**
 * Instantiates a new Position DTO.
 */
@NoArgsConstructor
public class PositionDTO extends BaseDTO<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 318831807718728344L;

  /** The position id. */
  private int id;

  /** The encrypted Id. */
  private String encryptedId;

  /** The code. */
  private String code;

  /** The name. */
  private String name;

  /** The schemes. */
  private List<SchemeDTO> schemes;

  /**
   * Instantiates a new Position DTO.
   * 
   * @param encrypt
   * @param code
   * @param name
   */
  public PositionDTO(String encrypt, String code, String name) {
    this.setEncryptedId(encrypt);
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
