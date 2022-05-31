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
package com.gcs.vppa.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class ErrorDetail.
 */
@JsonInclude(Include.NON_NULL)
@Getter
@Setter
@ToString
public class ErrorDetail {

  /** The code. */
  private String code;

  /** The message. */
  private String message;

  /**
   * Instantiates a new error detail.
   *
   * @param code
   *            the code
   */
  public ErrorDetail(String code) {
    super();
    this.code = code;
  }

  /**
   * Instantiates a new error detail.
   *
   * @param code
   *            the code
   * @param message
   *            the message
   */
  public ErrorDetail(String code, String message) {
    super();
    this.code = code;
    this.message = message;
  }
}
