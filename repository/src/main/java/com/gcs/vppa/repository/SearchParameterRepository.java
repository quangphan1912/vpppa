/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 14, 2020     ********           hangttran.ht            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.repository;

import org.springframework.stereotype.Repository;

import com.gcs.vppa.entity.Parameter;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hangttran.ht
 *
 */
@Repository
public interface SearchParameterRepository extends JpaRepository<Parameter, Integer> {

}
