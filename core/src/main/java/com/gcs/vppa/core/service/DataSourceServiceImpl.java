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

import com.gcs.vppa.common.service.BaseDataServiceImpl;
import com.gcs.vppa.core.converter.DataSourceConverter;
import com.gcs.vppa.dto.DataSourceDTO;
import com.gcs.vppa.dto.ParameterDTO;
import com.gcs.vppa.entity.DataSource;
import com.gcs.vppa.repository.DataSourceRepository;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

/**
 * The Class ParameterDataServiceImpl.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */

/**
 * Instantiates a new parameter data service impl.
 */
@NoArgsConstructor
@Service
@ComponentScan
public class DataSourceServiceImpl
  extends BaseDataServiceImpl<Integer, DataSource, DataSourceDTO, DataSourceRepository, DataSourceConverter>
  implements DataSourceService {

  /**
   * Gets the entity name.
   *
   * @return the entity name
   */
  @Override
  public String getEntityName() {
    return "DataSource";
  }

  /**
   * Find by param name.
   *
   * @param source the source name
   * @return the parameter DTO
   */
  @Override
  public DataSourceDTO findBySource(String source) {
    return this.converter.toDto(this.repository.findBySource(source));
  }
}
