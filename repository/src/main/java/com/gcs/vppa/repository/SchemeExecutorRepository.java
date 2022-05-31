/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 15, 2020     ********           hangttran.ht            Initialize                  
 * Aug 29, 2020     ********           hangttran.ht            Initialize
 *
 */
package com.gcs.vppa.repository;
import java.util.List;
import com.gcs.vppa.common.repository.BaseRepository;
import com.gcs.vppa.entity.SchemeExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author hangttran.ht
 *
 */
@Repository
public interface SchemeExecutorRepository extends BaseRepository<SchemeExecutor, Integer> {
  /**
   * Find by schemeId.
   *
   * @param schemeId
   * @return the list SchemeExecutor
   */
  List<SchemeExecutor> findBySchemeId(Integer schemeId);
}