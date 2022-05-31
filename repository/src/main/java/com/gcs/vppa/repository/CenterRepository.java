/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 15, 2020     ********           hangttran.ht            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gcs.vppa.common.repository.BaseRepository;
import com.gcs.vppa.entity.Center;

/**
 * @author hangttran.ht
 *
 */
@Repository
public interface CenterRepository extends BaseRepository<Center, Integer> {
  
  /**
   * Fetch by division id.
   *
   * @param divitionId the division id
   * @return the list
   */
  List<Center> findByDivisionId(Integer divisionId);
}
