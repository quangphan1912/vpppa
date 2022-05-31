/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * July 27, 2020     ********           PhoVo            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gcs.vppa.common.base.BaseDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class DataSourceDTO.
 *
 * @author hangttran.ht
 */
/**
 * Gets the last updated.
 *
 * @return the last updated
 */
@Getter

/**
 * Sets the last updated.
 *
 * @param lastUpdated
 *          the new last updated
 */
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(callSuper = true)
public class DataSourceDTO extends BaseDTO<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 7549192977292211374L;

  /** The user id. */
  private Integer id;

  /** The source. */
  private String source;

  /** The content. */
  private String content;

  /** The createdDate. */
  private LocalDateTime createdDate;

  /** The createdBy. */
  private String createdBy;

  /** The updatedDate. */
  private LocalDateTime updatedDate;

  /** The updatedBy. */
  private String updatedBy;

  /**
   * Instantiates a new DataSource DTO.
   */
  public DataSourceDTO() {
  }

  /**
   * Instantiates a new DataSource DTO.
   *
   * @param source
   * @param content
   */
  public DataSourceDTO(String source, String content) {
    this.source = source;
    this.content = content;
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
