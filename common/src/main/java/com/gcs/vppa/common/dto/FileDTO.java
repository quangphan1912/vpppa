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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class File.
 */
@Getter
@Setter
@ToString
public class FileDTO {

  /** The fileName. */
  private String name;

  /** The fileType. */
  private String type;

  /** The fileData. */
  private byte[] data;

  /** The folderName. */
  private String dirFolder = "";

  /**
   * Instantiates a new File
   */
  public FileDTO() {
    super();
  }

  /**
   * Instantiates a new File
   *
   * @param name the name
   * @param type the type
   * @param data the data
   */
  public FileDTO(String dirFolder, String name, String type, byte[] data) {
    super();
    this.dirFolder = dirFolder;
    this.name = name;
    this.type = type;
    this.data = data;
  }
}
