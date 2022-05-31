/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 26, 2020     ********           Administrator            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.core.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.gcs.vppa.common.converter.BaseConverter;
import com.gcs.vppa.common.logging.InjectLog;
import com.gcs.vppa.common.service.CryptoService;
import com.gcs.vppa.common.util.CryptoUtils;
import com.gcs.vppa.dto.UserGroupDTO;
import com.gcs.vppa.entity.UserGroup;

import lombok.NoArgsConstructor;

/**
 * @author Administrator
 *
 */
@NoArgsConstructor
@Service
@ComponentScan
public class UserGroupConverter extends BaseConverter<UserGroup, UserGroupDTO> {

  /** The cryptography properties. */
  @Autowired
  private CryptoService cryptoService;

  /**
   * To entity.
   *
   * @param dto the dto
   * @return the user role
   */
  @Override
  public UserGroup toEntity(UserGroupDTO dto) {
    if (dto == null) {
      return null;
    }

    int id = 0;
    if (dto.getEncryptedId() != null) {
      id = CryptoUtils.decryptId(dto.getEncryptedId(), cryptoService.getUserGroupKey());
    } else {
      id = dto.getId();
    }

    UserGroup entity = new UserGroup();
    entity.setId(id);
    entity.setName(dto.getName());
    entity.setCode(dto.getCode());
    entity.setDescription(dto.getDescription());

    return entity;
  }

  /**
   * To entity.
   *
   * @param dto the dto
   * @param entity the entity
   */
  @Override
  public void toEntity(UserGroupDTO dto, UserGroup entity) {
    int id = 0;

    if (dto.getEncryptedId() != null) {
      id = CryptoUtils.decryptId(dto.getEncryptedId(), cryptoService.getUserGroupKey());
    } else {
      id = dto.getId();
    }

    entity.setId(id);
    entity.setName(dto.getName());
    entity.setCode(dto.getCode());
    entity.setDescription(dto.getDescription());
  }

  /**
   * To dto.
   *
   * @param entity the entity
   * @return the template DTO
   */
  @Override
  @InjectLog(logParams = false)
  public UserGroupDTO toDto(UserGroup entity) {
    if (entity == null) {
      return null;
    }

    UserGroupDTO dto = new UserGroupDTO();
    dto.setId(0);

    if (entity.getId() > 0) {
      dto.setEncryptedId(CryptoUtils.encrypt(
        String.valueOf(entity.getId()), cryptoService.getUserGroupKey()));
    } else {
      dto.setEncryptedId(null);
    }

    dto.setName(entity.getName());
    dto.setCode(entity.getCode());
    dto.setDescription(entity.getDescription());

    return dto;
  }
}
