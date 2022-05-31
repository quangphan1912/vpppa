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

import com.gcs.vppa.common.repository.BaseRepository;
import com.gcs.vppa.entity.Process;
import org.springframework.stereotype.Repository;

/**
 * @author hangttran.ht
 *
 */
@Repository
public interface ProcessRepository extends BaseRepository<Process, Integer> {
    /**
     * find by name.
     *
     * @param name the name
     * @return parameter
     */
    Process findByName(String name);
}
