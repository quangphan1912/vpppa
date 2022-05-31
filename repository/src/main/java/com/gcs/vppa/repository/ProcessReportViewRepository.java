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
import com.gcs.vppa.entity.ProcessReportView;
import org.springframework.stereotype.Repository;

/**
 * @author hangttran.ht
 *
 */
@Repository
public interface ProcessReportViewRepository extends BaseRepository<ProcessReportView, Integer> {
    /**
     * find by name.
     *
     * @param name the name
     * @return parameter
     */
    ProcessReportView findByName(String name);
}
