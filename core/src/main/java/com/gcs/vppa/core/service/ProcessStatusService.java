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
package com.gcs.vppa.core.service;

import com.gcs.vppa.common.service.BaseDataService;
import com.gcs.vppa.core.converter.ProcessStatusConverter;
import com.gcs.vppa.dto.ProcessStatusDTO;
import com.gcs.vppa.entity.ProcessStatus;
import com.gcs.vppa.repository.ProcessStatusRepository;

/**
 * The Interface ParameterDataService.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */
public interface ProcessStatusService
  extends BaseDataService<Integer, ProcessStatus, ProcessStatusDTO, ProcessStatusRepository, ProcessStatusConverter> {

}
