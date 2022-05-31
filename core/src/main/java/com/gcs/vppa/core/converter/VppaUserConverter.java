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

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.gcs.vppa.common.converter.BaseConverter;
import com.gcs.vppa.common.dto.UserType;
import com.gcs.vppa.common.logging.InjectLog;
import com.gcs.vppa.common.service.CryptoService;
import com.gcs.vppa.common.util.CryptoUtils;
import com.gcs.vppa.common.util.DateTimeUtil;
import com.gcs.vppa.dto.VppaUserDTO;
import com.gcs.vppa.entity.VppaUser;

import lombok.NoArgsConstructor;

/**
 * @author Administrator
 *
 */
@NoArgsConstructor
@Service
@ComponentScan
public class VppaUserConverter extends BaseConverter<VppaUser, VppaUserDTO> {

  /** The cryptography properties. */
  @Autowired
  private CryptoService cryptoService;

  /** The user role converter. */
  @Autowired
  private UserRoleConverter userRoleConverter;

  /**
   * To entity.
   *
   * @param dto the dto
   * @return the user role
   */
  @Override
  public VppaUser toEntity(VppaUserDTO dto) {
    if (dto == null) {
      return null;
    }

    int id = 0;
    if (dto.getEncryptedId() != null) {
      id = CryptoUtils.decryptId(dto.getEncryptedId(), cryptoService.getVppaUserKey());
    } else {
      id = dto.getId();
    }

    VppaUser entity = new VppaUser();
    entity.setId(id);
    entity.setEmail(dto.getEmail());
    entity.setFullname(dto.getFullname());
    entity.setTitle(dto.getTitle());
    entity.setUserRole(userRoleConverter.toEntity(dto.getUserRole()));
    entity.setStatus(dto.getStatus());
    entity.setRemark(dto.getRemark());
    entity.setUserType(dto.getUserType().getValue());

    entity.setCreatedBy(dto.getCreatedBy());
    entity.setCreatedDate(dto.getCreatedDate() == null ? Timestamp.valueOf(LocalDateTime.now())
      : Timestamp.valueOf(dto.getCreatedDate()));

    entity.setUpdatedBy(dto.getUpdatedBy());
    entity.setUpdatedDate(dto.getUpdatedDate() == null ? Timestamp.valueOf(LocalDateTime.now())
      : Timestamp.valueOf(dto.getUpdatedDate()));

    return entity;
  }

  /**
   * To entity.
   *
   * @param dto the dto
   * @param entity the entity
   */
  @Override
  public void toEntity(VppaUserDTO dto, VppaUser entity) {
    int id = 0;

    if (dto.getEncryptedId() != null) {
      id = CryptoUtils.decryptId(dto.getEncryptedId(), cryptoService.getVppaUserKey());
    } else {
      id = dto.getId();
    }

    entity.setId(id);
    entity.setEmail(dto.getEmail());
    entity.setFullname(dto.getFullname());
    entity.setTitle(dto.getTitle());
    entity.setUserRole(userRoleConverter.toEntity(dto.getUserRole()));
    entity.setStatus(dto.getStatus());
    entity.setRemark(dto.getRemark());
    entity.setUserType(dto.getUserType().getValue());

    entity.setCreatedBy(dto.getCreatedBy());
    entity.setCreatedDate(dto.getCreatedDate() == null ? Timestamp.valueOf(LocalDateTime.now())
      : Timestamp.valueOf(dto.getCreatedDate()));

    entity.setUpdatedBy(dto.getUpdatedBy());
    entity.setUpdatedDate(dto.getUpdatedDate() == null ? Timestamp.valueOf(LocalDateTime.now())
      : Timestamp.valueOf(dto.getUpdatedDate()));
  }

  /**
   * To dto.
   *
   * @param entity the entity
   * @return the template DTO
   */
  @Override
  @InjectLog(logParams = false)
  public VppaUserDTO toDto(VppaUser entity) {
    if (entity == null) {
      return null;
    }

    VppaUserDTO dto = new VppaUserDTO();
    dto.setId(0);

    if (entity.getId() > 0) {
      dto.setEncryptedId(CryptoUtils.encrypt(
        String.valueOf(entity.getId()), cryptoService.getVppaUserKey()));
    } else {
      dto.setEncryptedId(null);
    }

    dto.setId(0);
    dto.setEmail(entity.getEmail());
    dto.setFullname(entity.getFullname());
    dto.setTitle(entity.getTitle());
    dto.setUserRole(userRoleConverter.toDto(entity.getUserRole()));
    dto.setStatus(entity.getStatus());
    dto.setRemark(entity.getRemark());
    if (entity.getUserType() == null) {
      dto.setUserType(UserType.NORMAL);
    } else {
      dto.setUserType(entity.getUserType() == UserType.ADMIN.getValue() ? UserType.ADMIN : UserType.NORMAL);
    }
    
    dto.setCreatedBy(entity.getCreatedBy());
    dto.setCreatedDate(DateTimeUtil.toLocalDateTime(entity.getCreatedDate()));

    dto.setUpdatedBy(entity.getUpdatedBy());
    dto.setUpdatedDate(DateTimeUtil.toLocalDateTime(entity.getUpdatedDate()));

    return dto;
  }
  
  /**
   * To dto.
   *
   * @param entity the entity
   * @param ignoreSensitiveData the ignore sensitive data
   * @return the vppa user DTO
   */
  public VppaUserDTO toDto(VppaUser entity, boolean ignoreSensitiveData) {
    if (entity == null) {
      return null;
    }

    VppaUserDTO dto = new VppaUserDTO();
    

    if (!ignoreSensitiveData) {
      if (entity.getId() > 0) {
        dto.setEncryptedId(CryptoUtils.encrypt(
          String.valueOf(entity.getId()), cryptoService.getVppaUserKey()));
      } else {
        dto.setEncryptedId(null);
      }
      
      dto.setId(0);
      dto.setEmail(entity.getEmail());
      dto.setStatus(entity.getStatus());
    }
    
    dto.setFullname(entity.getFullname());
    dto.setTitle(entity.getTitle());
    dto.setUserRole(userRoleConverter.toDto(entity.getUserRole(), ignoreSensitiveData));
    dto.setRemark(entity.getRemark());
    if (entity.getUserType() == null) {
      dto.setUserType(UserType.NORMAL);
    } else {
      dto.setUserType(entity.getUserType() == UserType.ADMIN.getValue() ? UserType.ADMIN : UserType.NORMAL);
    }

    dto.setCreatedBy(entity.getCreatedBy());
    dto.setCreatedDate(DateTimeUtil.toLocalDateTime(entity.getCreatedDate()));

    dto.setUpdatedBy(entity.getUpdatedBy());
    dto.setUpdatedDate(DateTimeUtil.toLocalDateTime(entity.getUpdatedDate()));

    return dto;
  }
}
