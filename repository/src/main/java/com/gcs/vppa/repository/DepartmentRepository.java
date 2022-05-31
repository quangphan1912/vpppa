/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 17, 2020     ********           hangttran.ht            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.repository;

import org.springframework.stereotype.Repository;

import com.gcs.vppa.common.repository.BaseRepository;
import com.gcs.vppa.entity.Department;

/**
 * The interface DepartmentRepository.
 * 
 * @author hangttran.ht
 */
@Repository
public interface DepartmentRepository extends BaseRepository<Department, Integer> {

}
