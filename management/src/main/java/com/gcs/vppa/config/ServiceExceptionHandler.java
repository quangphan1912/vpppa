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
package com.gcs.vppa.config;

import com.gcs.vppa.core.exception.handler.BaseExceptionHandler;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * Instantiates a new service exception handler.
 */
@NoArgsConstructor
@ControllerAdvice
public class ServiceExceptionHandler extends BaseExceptionHandler {

}
