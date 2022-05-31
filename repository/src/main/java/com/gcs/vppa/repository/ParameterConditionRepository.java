/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 27, 2020     ********           hangttran.ht            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gcs.vppa.common.repository.BaseRepository;
import com.gcs.vppa.entity.ParameterCondition;

/**
 * The interface ParameterConditionRepository.
 * 
 * @author hangttran.ht
 */
@Repository
public interface ParameterConditionRepository
    extends BaseRepository<ParameterCondition, Integer> {

  /**
   * Find by name.
   *
   * @param name
   *          the name
   * @return the ParameterCondition
   */
  List<ParameterCondition> findByName(String name);

  /**
   * Find by parameterId.
   *
   * @param parameterId
   * @return the ParameterCondition
   */
  List<ParameterCondition> findByParameterId(int parameterId);
}
