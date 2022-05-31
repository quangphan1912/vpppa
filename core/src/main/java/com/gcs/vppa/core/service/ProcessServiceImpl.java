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
import com.gcs.vppa.core.converter.ProcessConverter;
import com.gcs.vppa.dto.DataSourceDTO;
import com.gcs.vppa.dto.ProcessDTO;
import com.gcs.vppa.entity.DataSource;
import com.gcs.vppa.entity.Process;
import com.gcs.vppa.repository.DataSourceRepository;
import com.gcs.vppa.repository.ProcessRepository;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The Class ProcessServiceImpl.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */

/**
 * Instantiates a new parameter data service impl.
 */
@NoArgsConstructor
@Service
@ComponentScan
public class ProcessServiceImpl
  extends BaseDataServiceImpl<Integer, Process, ProcessDTO, ProcessRepository, ProcessConverter>
  implements ProcessService {

  /**
   * Gets the entity name.
   *
   * @return the entity name
   */
  @Override
  public String getEntityName() {
    return "Process";
  }

  /**
   * Find by param name.
   *
   * @param name the name
   * @return the parameter DTO
   */
  @Override
  public ProcessDTO findByName(String name) {
    return this.converter.toDto(this.repository.findByName(name));
  }
}
