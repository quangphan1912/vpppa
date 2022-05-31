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

@Getter
@Setter
@ToString(callSuper = true)

/**
 * Instantiates a new ProcessReport DTO.
 */
@NoArgsConstructor
public class ProcessReportDTO extends BaseDTO<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 318831807718728343L;

  /** The process id. */
  private int id;

  /** The name. */
  private String name;

  /** The source. */
  private String source;

  private String sourceData;

  /** The description. */
  private String description;

  /** The template. */
  private String template;

  /** The fileData. */
  private byte[] templateData;

  /** The schemeId. */
  private String schemeId;

  /** The processId. */
  private Integer processId;

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
