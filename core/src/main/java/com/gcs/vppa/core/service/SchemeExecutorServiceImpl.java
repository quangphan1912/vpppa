/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 29, 2020     ********           hangttran.ht            Initialize
 *
 */
package com.gcs.vppa.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.gcs.vppa.common.service.BaseDataServiceImpl;
import com.gcs.vppa.core.converter.SchemeExecutorConverter;
import com.gcs.vppa.dto.SchemeExecutorDTO;
import com.gcs.vppa.entity.SchemeExecutor;
import com.gcs.vppa.repository.SchemeExecutorRepository;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

/**
 * The Class ProcessServiceImpl.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */

/**
 * Instantiates a new parameter data service impl.
 */
@NoArgsConstructor
@Service
@ComponentScan
public class SchemeExecutorServiceImpl
  extends BaseDataServiceImpl<Integer, SchemeExecutor, SchemeExecutorDTO, SchemeExecutorRepository, SchemeExecutorConverter>
  implements SchemeExecutorService {

  /**
   * Gets the entity name.
   *
   * @return the entity name
   */
  @Override
  public String getEntityName() {
    return "SchemeExecutor";
  }

  /**
   * Find by schemeId.
   *
   * @param schemeId
   * @return the list SchemeExecutor dto
   */
  @Override
  public List<SchemeExecutorDTO> findBySchemeId(Integer schemeId) {
    List<SchemeExecutor> objectEntitys = this.repository.findBySchemeId(schemeId);
    List<SchemeExecutorDTO> objectDtos = new ArrayList<>(objectEntitys.size());
    for (SchemeExecutor object : objectEntitys) {
      objectDtos.add(this.converter.toDto(object));
    }
    return objectDtos;
  }

}
