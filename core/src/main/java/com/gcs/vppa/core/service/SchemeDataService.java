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

import com.gcs.vppa.common.service.BaseDataService;
import com.gcs.vppa.core.converter.SchemeConverter;
import com.gcs.vppa.dto.RunSchemeDTO;
import com.gcs.vppa.dto.SchemeDTO;
import com.gcs.vppa.entity.Scheme;
import com.gcs.vppa.repository.SchemeRepository;

/**
 * The Interface SchemeDataService.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */
public interface SchemeDataService
  extends BaseDataService<Integer, Scheme, SchemeDTO, SchemeRepository, SchemeConverter> {

  /**
   * Find by name.
   *
   * @param name          the name
   * @param encryptedKey the encrypted key
   * @return the scheme DTO
   */
  SchemeDTO findByIdScheme(String idScheme);

  /**
   * Find by source.
   *
   * @param source the source
   * @return the Scheme DTO
   */
  SchemeDTO findBySource(String source);
  
  /**
   * Execute run schemes.
   *
   * @param dto the dto
   * @return true, if successful
   */
  boolean executeRunSchemes(RunSchemeDTO dto);
}
