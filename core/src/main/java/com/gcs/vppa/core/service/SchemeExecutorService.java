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
package com.gcs.vppa.core.service;

import java.util.List;

import com.gcs.vppa.common.service.BaseDataService;
import com.gcs.vppa.core.converter.SchemeExecutorConverter;
import com.gcs.vppa.dto.SchemeExecutorDTO;
import com.gcs.vppa.entity.SchemeExecutor;
import com.gcs.vppa.repository.SchemeExecutorRepository;

/**
 * The Interface ParameterDataService.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */
public interface SchemeExecutorService
  extends BaseDataService<Integer, SchemeExecutor, SchemeExecutorDTO, SchemeExecutorRepository, SchemeExecutorConverter> {
    /**
     * Find by schemeId.
     *
     * @param schemeId
     * @return the list SchemeExecutor dto
     */
    List<SchemeExecutorDTO> findBySchemeId(Integer schemeId);
}

