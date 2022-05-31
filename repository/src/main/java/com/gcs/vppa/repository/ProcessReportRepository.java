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
import com.gcs.vppa.entity.ProcessReport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hangttran.ht
 *
 */
@Repository
public interface ProcessReportRepository extends BaseRepository<ProcessReport, Integer> {
    /**
     * find by name.
     *
     * @param name the name
     * @return parameter
     */
    ProcessReport findByName(String name);

    List<ProcessReport> findAllByProcessId(Integer processId);
}
