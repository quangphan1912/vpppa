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
import com.gcs.vppa.core.converter.DataSourceConverter;
import com.gcs.vppa.dto.DataSourceDTO;
import com.gcs.vppa.dto.ParameterDTO;
import com.gcs.vppa.entity.DataSource;
import com.gcs.vppa.repository.DataSourceRepository;

/**
 * The Interface ParameterDataService.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */
public interface DataSourceService
  extends BaseDataService<Integer, DataSource, DataSourceDTO, DataSourceRepository, DataSourceConverter> {

  /**
   * Find by parameter name.
   *
   * @param source the source name
   * @return the parameter DTO
   */
  DataSourceDTO findBySource(String source);
}
