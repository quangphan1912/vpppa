/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 19, 2020     ********           hangttran.ht            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.repository;

import com.gcs.vppa.common.repository.BaseRepository;
import com.gcs.vppa.entity.SchemeView;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface SchemeViewRepository.
 * 
 * @author hangttran.ht
 */
@Repository
public interface SchemeViewRepository extends BaseRepository<SchemeView, Integer> {
    /**
     * findAllByProcessId
     * @param processId
     * @return
     */
    List<SchemeView> findAllByProcessIdAndStatus(Integer processId, String status);
}
