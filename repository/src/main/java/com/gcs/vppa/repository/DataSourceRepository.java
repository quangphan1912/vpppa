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
package com.gcs.vppa.repository;

import org.springframework.stereotype.Repository;

import com.gcs.vppa.common.repository.BaseRepository;
import com.gcs.vppa.entity.DataSource;

/**
 * The Interface ParameterRepository.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */
@Repository
public interface DataSourceRepository extends BaseRepository<DataSource, Integer> {

  /**
   * find by source name.
   *
   * @param source the source name
   * @return parameter
   */
  DataSource findBySource(String source);
}
