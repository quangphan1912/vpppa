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
import org.springframework.util.StringUtils;

import com.gcs.vppa.common.converter.BaseConverter;
import com.gcs.vppa.common.service.CryptoService;
import com.gcs.vppa.common.util.CryptoUtils;
import com.gcs.vppa.dto.ProcessTypeDTO;
import com.gcs.vppa.entity.ProcessType;

import lombok.NoArgsConstructor;

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
public class ProcessTypeConverter extends BaseConverter<ProcessType, ProcessTypeDTO> {

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
  public ProcessTypeDTO toDto(ProcessType entity) {
    if (entity == null) {
      return null;
    }

    ProcessTypeDTO dto = new ProcessTypeDTO();
    dto.setEncryptedId(CryptoUtils.encrypt(entity.getId().toString(), cryptoService.getProcessTypeKey()));
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
  public ProcessType toEntity(ProcessTypeDTO dto) {
    if (dto == null) {
      return null;
    }

    ProcessType entity = new ProcessType();
    int paramId = 0;
    if (dto.getEncryptedId() != null && StringUtils.isEmpty(dto.getEncryptedId())) {
      paramId = CryptoUtils.decryptId(dto.getEncryptedId(), cryptoService.getProcessTypeKey());
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
  public void toEntity(ProcessTypeDTO dto, ProcessType entity) {
    int paramId = 0;
    if (dto.getEncryptedId() != null && StringUtils.isEmpty(dto.getEncryptedId())) {
      paramId = CryptoUtils.decryptId(dto.getEncryptedId(), cryptoService.getProcessTypeKey());
    } else {
      paramId = dto.getId();
    }
    entity.setId(paramId);
    entity.setCode(dto.getCode());
    entity.setName(dto.getName());
  }
}
