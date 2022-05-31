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
import com.gcs.vppa.entity.Scheme;

/**
 * The Interface SchemeRepository.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */
@Repository
public interface SchemeRepository extends BaseRepository<Scheme, Integer> {

  /**
   * Find by id scheme.
   *
   * @param idScheme the idScheme
   * @return the Scheme
   */
  Scheme findByIdScheme(String idScheme);

  /**
   * Find by source.
   * 
   * @param source
   * @return
   */
  Scheme findBySource(String source);
}
