/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 31, 2020     ********           Administrator            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class Role.
 */

/**
 * Gets the role privileges.
 *
 * @return the role privileges
 */
@Getter

/**
 * Sets the role privileges.
 *
 * @param rolePrivileges the new role privileges
 */
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(callSuper = true)

/**
 * Instantiates a new role DTO.
 */
@NoArgsConstructor
public class RunSchemeDTO {

  /** The process id. */
  private String processId;

  /** The run type. */
  private String runType;

  /** The revise month. */
  private String reviseMonth;

  /** The selected schemes. */
  private String[] selectedSchemes;
}
