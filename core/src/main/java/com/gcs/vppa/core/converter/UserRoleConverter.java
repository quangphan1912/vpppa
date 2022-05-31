/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 25, 2020     ********           linhnkl.bq            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.core.converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.gcs.vppa.common.converter.BaseConverter;
import com.gcs.vppa.common.logging.InjectLog;
import com.gcs.vppa.common.service.CryptoService;
import com.gcs.vppa.common.util.CryptoUtils;
import com.gcs.vppa.common.util.DateTimeUtil;
import com.gcs.vppa.core.service.PermissionService;
import com.gcs.vppa.dto.PermissionDTO;
import com.gcs.vppa.dto.UserGroupDTO;
import com.gcs.vppa.dto.UserRoleDTO;
import com.gcs.vppa.entity.UserRole;

import lombok.NoArgsConstructor;

/**
 * The Class UserRoleConverter.
 *
 * @author linhnkl.bq
 */

/**
 * Instantiates a new user role converter.
 */
@NoArgsConstructor
@Service
@ComponentScan
public class UserRoleConverter extends BaseConverter<UserRole, UserRoleDTO> {

  /** The cryptography properties. */
  @Autowired
  private CryptoService cryptoService;

  /** The user group converter. */
  @Autowired
  private UserGroupConverter userGroupConverter;

  /** The permission service. */
  @Autowired
  private PermissionService permissionService;

  /**
   * To entity.
   *
   * @param dto the dto
   * @return the user role
   */
  @Override
  public UserRole toEntity(UserRoleDTO dto) {
    if (dto == null) {
      return null;
    }

    int id = 0;
    if (dto.getEncryptedId() != null) {
      id = CryptoUtils.decryptId(dto.getEncryptedId(), cryptoService.getUserRoleKey());
    } else {
      id = dto.getId();
    }

    UserRole entity = new UserRole();
    entity.setId(id);
    entity.setName(dto.getName());
    entity.setDescription(dto.getDescription());

    if (dto.getPermissionItems() != null && dto.getPermissionItems().length > 0) {
      int[] permissionIds = new int[dto.getPermissionItems().length];
      int idx = 0;

      for (PermissionDTO item : dto.getPermissionItems()) {
        permissionIds[idx++] = CryptoUtils.decryptId(item.getEncryptedId(), cryptoService.getPermissionKey());
      }

      entity.setPermission(permissionIds);
    }

    entity.setUserGroupId(dto.getUserGroupId());
    entity.setUserGroup(userGroupConverter.toEntity(dto.getUserGroup()));

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
  public void toEntity(UserRoleDTO dto, UserRole entity) {
    int id = 0;

    if (dto.getEncryptedId() != null) {
      id = CryptoUtils.decryptId(dto.getEncryptedId(), cryptoService.getUserRoleKey());
    } else {
      id = dto.getId();
    }

    entity.setId(id);
    entity.setName(dto.getName());
    entity.setDescription(dto.getDescription());

    if (dto.getPermissionItems() != null) {
      int[] permissionIds = new int[dto.getPermissionItems().length];
      int idx = 0;

      for (PermissionDTO item : dto.getPermissionItems()) {
        permissionIds[idx++] = CryptoUtils.decryptId(item.getEncryptedId(), cryptoService.getPermissionKey());
      }

      entity.setPermission(permissionIds);
    } else {
      entity.setPermission(new int[] {});
    }

    entity.setUserGroupId(dto.getUserGroupId());
    entity.setUserGroup(userGroupConverter.toEntity(dto.getUserGroup()));

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
  public UserRoleDTO toDto(UserRole entity) {
    if (entity == null) {
      return null;
    }

    UserRoleDTO dto = new UserRoleDTO();

    dto.setId(0);
    dto.setEncryptedId(
      CryptoUtils.encrypt(
        String.valueOf(entity.getId()), cryptoService.getUserRoleKey()));

    dto.setName(entity.getName());
    dto.setDescription(entity.getDescription());
    dto.setPermission(entity.getPermission());

    if (entity.getPermission() != null && entity.getPermission().length > 0) {
      PermissionDTO[] permissionItems = new PermissionDTO[entity.getPermission().length];

      int permissionIdx = 0;
      for (int item : entity.getPermission()) {
        permissionItems[permissionIdx++] = permissionService.getPermissionEntry(item);
      }

      dto.setPermissionItems(permissionItems);
    }

    dto.setUserGroupId(entity.getUserGroupId());

    if (entity.getUserGroup() == null) {
      dto.setUserGroup(new UserGroupDTO());
    } else {
      dto.setUserGroup(userGroupConverter.toDto(entity.getUserGroup()));
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
   * @return the user role DTO
   */
  public UserRoleDTO toDto(UserRole entity, boolean ignoreSensitiveData) {
    if (entity == null) {
      return null;
    }

    UserRoleDTO dto = new UserRoleDTO();

    if (!ignoreSensitiveData) {
      dto.setId(0);
      dto.setEncryptedId(
        CryptoUtils.encrypt(
          String.valueOf(entity.getId()), cryptoService.getUserRoleKey()));

      dto.setName(entity.getName());
      dto.setDescription(entity.getDescription());
      dto.setPermission(entity.getPermission());

      dto.setUserGroupId(entity.getUserGroupId());

      if (entity.getUserGroup() == null) {
        dto.setUserGroup(new UserGroupDTO());
      } else {
        dto.setUserGroup(userGroupConverter.toDto(entity.getUserGroup()));
      }

      dto.setCreatedBy(entity.getCreatedBy());
      dto.setCreatedDate(DateTimeUtil.toLocalDateTime(entity.getCreatedDate()));

      dto.setUpdatedBy(entity.getUpdatedBy());
      dto.setUpdatedDate(DateTimeUtil.toLocalDateTime(entity.getUpdatedDate()));
    }

    if (entity.getPermission() != null && entity.getPermission().length > 0) {
      PermissionDTO[] permissionItems = new PermissionDTO[entity.getPermission().length];

      int permissionIdx = 0;
      for (int item : entity.getPermission()) {
        PermissionDTO permission = this.clonePermissionDTO(permissionService.getPermissionEntry(item));

        if (ignoreSensitiveData) {
          permission.setEncryptedId(null);
          permission.setId(0);
        }

        permissionItems[permissionIdx++] = permission;
      }

      dto.setPermissionItems(permissionItems);
    }

    return dto;
  }

  /**
   * Clone permission DTO.
   *
   * @param permission the permission
   * @return the permission DTO
   */
  private PermissionDTO clonePermissionDTO(PermissionDTO permission) {
    PermissionDTO clone = new PermissionDTO();

    clone.setId(permission.getId());
    clone.setEncryptedId(permission.getEncryptedId());
    clone.setCode(permission.getCode());
    clone.setDisplayText(permission.getDisplayText());
    clone.setDescription(permission.getDescription());

    return clone;
  }
}
