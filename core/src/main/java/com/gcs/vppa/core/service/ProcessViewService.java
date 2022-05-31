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
import com.gcs.vppa.core.converter.ProcessViewConverter;
import com.gcs.vppa.dto.ProcessDTO;
import com.gcs.vppa.dto.ProcessViewDTO;
import com.gcs.vppa.entity.ProcessView;
import com.gcs.vppa.repository.ProcessViewRepository;

/**
 * The Interface ParameterDataService.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */
public interface ProcessViewService
  extends BaseDataService<Integer, ProcessView, ProcessViewDTO, ProcessViewRepository, ProcessViewConverter> {

}
