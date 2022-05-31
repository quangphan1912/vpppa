/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 18, 2020     ********           hangttran.ht            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.core.converter;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.gcs.vppa.common.converter.BaseConverter;
import com.gcs.vppa.common.service.CryptoService;
import com.gcs.vppa.common.util.CryptoUtils;
import com.gcs.vppa.common.util.DateTimeUtil;
import com.gcs.vppa.dto.SchemeStatusDTO;
import com.gcs.vppa.entity.Scheme;
import com.gcs.vppa.entity.SchemeStatus;

import lombok.NoArgsConstructor;

/**
 * @author hangttran.ht
 *
 */
/**
 * Instantiates a new SchemeStatus converter.
 */
@NoArgsConstructor
@Service
@ComponentScan
public class SchemeStatusConverter extends BaseConverter<SchemeStatus, SchemeStatusDTO> {

  /** The cryptography properties. */
  @Autowired
  private CryptoService cryptoService;

  /**
   * To dto.
   *
   * @param entity the entity
   * @return the SchemeStatus DTO
   */
  @Override
  public SchemeStatusDTO toDto(SchemeStatus entity) {
    if (entity == null) {
      return null;
    }

    SchemeStatusDTO dto = new SchemeStatusDTO();
    dto.setEncryptedId(
      CryptoUtils.encrypt(entity.getId().toString(), cryptoService.getSchemeStatusKey()));
    dto.setSchemeId(CryptoUtils.encrypt(entity.getSchemeId().toString(), cryptoService.getSchemeKey()));
    dto.setStatus(entity.getStatus());
    dto.setUpdatedBy(entity.getUpdatedBy());
    dto.setUpdatedDate(entity.getUpdatedDate() == null ? null
      : entity.getUpdatedDate().toLocalDateTime().toString());
    return dto;

  }

  /**
   * To entity.
   *
   * @param dto the dto
   * @return the scheme
   */
  @Override
  public SchemeStatus toEntity(SchemeStatusDTO dto) {
    if (dto == null) {
      return null;
    }

    SchemeStatus entity = new SchemeStatus();
    entity.setId(dto.getId());
    entity.setSchemeId(CryptoUtils.decryptId(dto.getSchemeId(), cryptoService.getSchemeKey()));
    entity.setScheme(new Scheme(CryptoUtils.decryptId(dto.getSchemeId(), cryptoService.getSchemeKey())));
    entity.setStatus(dto.getStatus());
    entity.setUpdatedBy(dto.getUpdatedBy());
    entity.setUpdatedDate(dto.getUpdatedDate() == null ? null
      : Timestamp.valueOf(DateTimeUtil.formatDateTime(dto.getUpdatedDate())));
    return entity;
  }

  /**
   * To entity.
   *
   * @param dto the dto
   * @param entity the entity
   * @return the scheme
   */
  @Override
  public void toEntity(SchemeStatusDTO dto, SchemeStatus entity) {
    // TODO Auto-generated method stub

  }

}
