/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 27, 2020     ********           hangttran.ht            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.core.converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.gcs.vppa.common.converter.BaseConverter;
import com.gcs.vppa.common.service.CryptoService;
import com.gcs.vppa.common.util.CryptoUtils;
import com.gcs.vppa.dto.ParameterConditionDTO;
import com.gcs.vppa.entity.Parameter;
import com.gcs.vppa.entity.ParameterCondition;

import lombok.NoArgsConstructor;

/**
 * @author hangttran.ht
 *
 */
/**
 * Instantiates a new ParameterCondition converter.
 */
@NoArgsConstructor
@Service
@ComponentScan
public class ParameterConditionConverter
    extends BaseConverter<ParameterCondition, ParameterConditionDTO> {

  /** The cryptography properties. */
  @Autowired
  private CryptoService cryptoService;

  /**
   * To dto.
   *
   * @param entity
   *          the entity
   * @return the ParameterCondition DTO
   */
  @Override
  public ParameterConditionDTO toDto(ParameterCondition entity) {
    if (entity == null) {
      return null;
    }

    ParameterConditionDTO dto = new ParameterConditionDTO();
    dto.setEncryptedId(CryptoUtils.encrypt(entity.getId().toString(),
        cryptoService.getParameterConditionKey()));
    dto.setParameterIdEncrypt(entity.getParameterId() == null ? null
        : CryptoUtils.encrypt(entity.getParameterId().toString(),
            cryptoService.getParameterKey()));
    dto.setName(entity.getName());
    dto.setValue(entity.getValue());
    dto.setUpdatedBy(entity.getUpdatedBy());
    dto.setUpdatedDate(
        entity.getUpdatedDate() == null ? null : entity.getUpdatedDate().toLocalDateTime());
    return dto;

  }

  /**
   * To entity.
   *
   * @param dto
   *          the dto
   * @return the scheme
   */
  @Override
  public ParameterCondition toEntity(ParameterConditionDTO dto) {
    if (dto == null) {
      return null;
    }

    int conditionId = 0;
    if (dto.getEncryptedId() != null) {
      conditionId = CryptoUtils.decryptId(dto.getEncryptedId(), cryptoService.getParameterConditionKey());
    } else {
      conditionId = dto.getId();
    }
    ParameterCondition entity = new ParameterCondition();
    entity.setId(conditionId);
    entity.setParameterId(dto.getParameterIdEncrypt() == null ? null
        : CryptoUtils.decryptId(dto.getParameterIdEncrypt(), cryptoService.getParameterKey()));
    entity.setParameter(dto.getParameterIdEncrypt() == null ? null
        : new Parameter(CryptoUtils.decryptId(dto.getParameterIdEncrypt(), cryptoService.getParameterKey())));
    entity.setName(dto.getName());
    entity.setValue(dto.getValue());
    entity.setUpdatedBy(dto.getUpdatedBy());
    entity.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));
    return entity;
  }

  /**
   * To entity.
   *
   * @param dto
   *          the dto
   * @param entity
   *          the entity
   * @return the scheme
   */
  @Override
  public void toEntity(ParameterConditionDTO dto, ParameterCondition entity) {
    int conditionId = 0;
    if (dto.getEncryptedId() != null) {
      conditionId = CryptoUtils.decryptId(dto.getEncryptedId(), cryptoService.getParameterConditionKey());
    } else {
      conditionId = dto.getId();
    }
    entity.setId(conditionId);
    entity.setParameterId(dto.getParameterIdEncrypt() == null ? null
        : CryptoUtils.decryptId(dto.getParameterIdEncrypt(), cryptoService.getParameterKey()));
    entity.setParameter(dto.getParameterIdEncrypt() == null ? null
        : new Parameter(CryptoUtils.decryptId(dto.getParameterIdEncrypt(), cryptoService.getParameterKey())));
    entity.setName(dto.getName());
    entity.setValue(dto.getValue());
    entity.setUpdatedBy(dto.getUpdatedBy());
    entity.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));

  }

}
