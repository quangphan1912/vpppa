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
 * The Class CenterDTO.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 *
 */
/**
 * Gets the center.
 *
 * @return the center
 */
@Getter

/**
 * Sets the center.
 *
 * @param center the new center
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
public class CenterDTO extends BaseDTO<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 318831807718728344L;

  /** The center id. */
  private int id;

  /** The code. */
  private String code;

  /** The name. */
  private String name;

  /** The divition id. */
  private Integer divitionId;

  /** The division. */
  private DivisionDTO division;

  /** The schemes. */
  private List<SchemeDTO> schemes;

  /** The centers. */
  private List<DepartmentDTO> departments;

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

  public CenterDTO(String encryptedId, String code, String name, Integer divitionId,
      DivisionDTO dision) {
    this.setEncryptedId(encryptedId);
    this.code = code;
    this.name = name;
    this.divitionId = divitionId;
    this.division = dision;
  }

}
