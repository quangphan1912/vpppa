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

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.gcs.vppa.common.service.BaseDataServiceImpl;
import com.gcs.vppa.core.converter.SchemeConverter;
import com.gcs.vppa.dto.RunSchemeDTO;
import com.gcs.vppa.dto.SchemeDTO;
import com.gcs.vppa.entity.Scheme;
import com.gcs.vppa.repository.SchemeRepository;

import lombok.NoArgsConstructor;

/**
 * Instantiates a new scheme data service impl.
 */

/**
 * Instantiates a new scheme data service impl.
 */
@NoArgsConstructor
@Service
@ComponentScan
public class SchemeDataServiceImpl
  extends BaseDataServiceImpl<Integer, Scheme, SchemeDTO, SchemeRepository, SchemeConverter>
  implements SchemeDataService {
  /**
   * Gets the entity name.
   *
   * @return the entity name
   */
  @Override
  public String getEntityName() {
    return "Scheme";
  }

  /**
   * Find by name.
   *
   * @param name the name
   * @param encryptedKey the encrypted key
   * @return the scheme DTO
   */
  @Override
  public SchemeDTO findByIdScheme(String idScheme) {
    return this.converter.toDto(this.repository.findByIdScheme(idScheme));
  }

  @Override
  public SchemeDTO findBySource(String source) {
    return this.converter.toDto(this.repository.findBySource(source));
  }

  /**
   * Execute run schemes.
   *
   * @param dto the dto
   * @return true, if successful
   */
  @Override
  public boolean executeRunSchemes(RunSchemeDTO dto) {
    return true;
  }
}
