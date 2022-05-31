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
package com.gcs.vppa.core.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.gcs.vppa.common.converter.BaseConverter;
import com.gcs.vppa.common.service.CryptoService;
import com.gcs.vppa.common.util.CryptoUtils;
import com.gcs.vppa.dto.SchemeTypeDTO;
import com.gcs.vppa.entity.SchemeType;

import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * The Class SchemeTypeConverter.
 * 
 * @author hangttran.ht
 *
 */

/**
 * Instantiates a new vehicle converter.
 */
@NoArgsConstructor
@Service
@ComponentScan
public class SchemeTypeConverter extends BaseConverter<SchemeType, SchemeTypeDTO> {

  /** The cryptography properties. */
  @Autowired
  private CryptoService cryptoService;

  /**
   * To dto.
   *
   * @param entity the entity
   * @return the SchemeTypeDTO
   */
  @Override
  public SchemeTypeDTO toDto(SchemeType entity) {
    if (entity == null) {
      return null;
    }

    SchemeTypeDTO dto = new SchemeTypeDTO();
    dto.setEncryptedId(CryptoUtils.encrypt(entity.getId().toString(), cryptoService.getSchemeTypeKey()));
    dto.setCode(entity.getCode());
    dto.setName(entity.getName());

    return dto;
  }

  /**
   * To entity.
   *
   * @param dto the dto
   * @return the SchemeType
   */
  @Override
  public SchemeType toEntity(SchemeTypeDTO dto) {
    if (dto == null) {
      return null;
    }

    SchemeType entity = new SchemeType();
    int paramId = 0;
    if (dto.getEncryptedId() != null && StringUtils.isEmpty(dto.getEncryptedId())) {
      paramId = CryptoUtils.decryptId(dto.getEncryptedId(), cryptoService.getSchemeTypeKey());
    } else {
      paramId = dto.getId();
    }
    entity.setId(paramId);
    entity.setCode(dto.getCode());
    entity.setName(dto.getName());

    return entity;
  }

  /**
   * To entity.
   *
   * @param dto the dto
   * @return the SchemeType
   */
  @Override
  public void toEntity(SchemeTypeDTO dto, SchemeType entity) {
    int paramId = 0;
    if (dto.getEncryptedId() != null && StringUtils.isEmpty(dto.getEncryptedId())) {
      paramId = CryptoUtils.decryptId(dto.getEncryptedId(), cryptoService.getSchemeTypeKey());
    } else {
      paramId = dto.getId();
    }
    entity.setId(paramId);
    entity.setCode(dto.getCode());
    entity.setName(dto.getName());
  }
}
