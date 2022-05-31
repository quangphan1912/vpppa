/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 28, 2020     ********           Administrator            Initialize                  
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
import com.gcs.vppa.dto.LdapUserDTO;
import com.gcs.vppa.entity.LdapUser;

import lombok.NoArgsConstructor;

/**
 * @author Administrator
 *
 */
@NoArgsConstructor
@Service
@ComponentScan
public class LdapUserConverter extends BaseConverter<LdapUser, LdapUserDTO> {

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
  public LdapUser toEntity(LdapUserDTO dto) {
    if (dto == null) {
      return null;
    }

    int id = 0;

    if (dto.getEncryptedId() != null) {
      id = CryptoUtils.decryptId(dto.getEncryptedId(), cryptoService.getLdapUserKey());
    } else {
      id = dto.getId();
    }

    LdapUser entity = new LdapUser();
    entity.setId(id);
    entity.setEmail(dto.getEmail());
    entity.setFullname(dto.getFullname());
    entity.setTitle(dto.getTitle());

    return entity;
  }

  /**
   * To entity.
   *
   * @param dto the dto
   * @param entity the entity
   */
  @Override
  public void toEntity(LdapUserDTO dto, LdapUser entity) {
    int id = 0;

    if (dto.getEncryptedId() != null) {
      id = CryptoUtils.decryptId(dto.getEncryptedId(), cryptoService.getUserGroupKey());
    } else {
      id = dto.getId();
    }

    entity.setId(id);
    entity.setEmail(dto.getEmail());
    entity.setFullname(dto.getFullname());
    entity.setTitle(dto.getTitle());
  }

  /**
   * To dto.
   *
   * @param entity the entity
   * @return the template DTO
   */
  @Override
  @InjectLog(logParams = false)
  public LdapUserDTO toDto(LdapUser entity) {
    if (entity == null) {
      return null;
    }

    LdapUserDTO dto = new LdapUserDTO();

    dto.setId(0);

    if (entity.getId() > 0) {
      dto.setEncryptedId(CryptoUtils.encrypt(
        String.valueOf(entity.getId()), cryptoService.getUserGroupKey()));
    } else {
      dto.setEncryptedId(null);
    }

    dto.setId(0);
    dto.setEmail(entity.getEmail());
    dto.setFullname(entity.getFullname());
    dto.setTitle(entity.getTitle());

    return dto;
  }
}
