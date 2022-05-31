/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Sep 3, 2020     ********           Administrator            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.core.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.gcs.vppa.common.converter.BaseConverter;
import com.gcs.vppa.common.logging.InjectLog;
import com.gcs.vppa.common.util.DateTimeUtil;
import com.gcs.vppa.dto.VppaUserLogginedDTO;
import com.gcs.vppa.entity.VppaUserLoggined;

import lombok.NoArgsConstructor;

/**
 * The Class VppaUserLogginedConverter.
 *
 * @author Administrator
 */
@NoArgsConstructor
@Service
@ComponentScan
public class VppaUserLogginedConverter extends BaseConverter<VppaUserLoggined, VppaUserLogginedDTO> {
  /** The user converter. */
  @Autowired
  private VppaUserConverter vppaUserConverter;

  /**
   * To entity.
   *
   * @param dto the VppaUserLogginedDTO
   * @return the user
   */
  @Override
  public VppaUserLoggined toEntity(VppaUserLogginedDTO dto) {
    if (dto == null) {
      return null;
    }

    VppaUserLoggined entity = new VppaUserLoggined();

    entity.setId(dto.getId());
    entity.setToken(dto.getToken());
    entity.setUserId(dto.getUserId());
    entity.setUser(this.vppaUserConverter.toEntity(dto.getUser()));
    entity.setLastAccessedTime(DateTimeUtil.toTimestamp(dto.getLastAccessedTime()));
    entity.setExpired(dto.isExpired());

    entity.setCreatedBy(dto.getCreatedBy());
    entity.setCreatedDate(DateTimeUtil.toTimestamp(dto.getCreatedDate()));

    entity.setUpdatedBy(dto.getUpdatedBy());
    entity.setUpdatedDate(DateTimeUtil.toTimestamp(dto.getUpdatedDate()));

    return entity;
  }

  /**
   * To entity.
   *
   * @param dto the dto
   * @param entity the entity
   */
  @Override
  public void toEntity(VppaUserLogginedDTO dto, VppaUserLoggined entity) {
    
    entity.setId(dto.getId());
    entity.setToken(dto.getToken());
    entity.setUserId(dto.getUserId());
    entity.setUser(this.vppaUserConverter.toEntity(dto.getUser()));
    entity.setLastAccessedTime(DateTimeUtil.toTimestamp(dto.getLastAccessedTime()));
    entity.setExpired(dto.isExpired());

    entity.setCreatedBy(dto.getCreatedBy());
    entity.setCreatedDate(DateTimeUtil.toTimestamp(dto.getCreatedDate()));

    entity.setUpdatedBy(dto.getUpdatedBy());
    entity.setUpdatedDate(DateTimeUtil.toTimestamp(dto.getUpdatedDate()));
  }

  /**
   * To VppaUserLogginedDTO.
   *
   * @param entity the entity
   * @return the template DTO
   */
  @Override
  @InjectLog(logParams = false)
  public VppaUserLogginedDTO toDto(VppaUserLoggined entity) {
    if (entity == null) {
      return null;
    }

    VppaUserLogginedDTO dto = new VppaUserLogginedDTO();

    dto.setId(entity.getId());
    dto.setUserId(entity.getUserId());
    dto.setUser(this.vppaUserConverter.toDto(entity.getUser()));
    dto.setLastAccessedTime(DateTimeUtil.toLocalDateTime(entity.getLastAccessedTime()));
    dto.setToken(entity.getToken());
    dto.setExpired(entity.isExpired());
    
    dto.setCreatedBy(entity.getCreatedBy());
    dto.setCreatedDate(DateTimeUtil.toLocalDateTime(entity.getCreatedDate()));

    dto.setUpdatedBy(entity.getUpdatedBy());
    dto.setUpdatedDate(DateTimeUtil.toLocalDateTime(entity.getUpdatedDate()));

    return dto;
  }
  
  /**
   * To VppaUserLogginedDTO.
   *
   * @param entity the entity
   * @param ignoreSensitiveData the ignore sensitve data
   * @return the vppa user loggined DTO
   */
  public VppaUserLogginedDTO toDto(VppaUserLoggined entity, boolean ignoreSensitiveData) {
    if (entity == null) {
      return null;
    }

    VppaUserLogginedDTO dto = new VppaUserLogginedDTO();

    dto.setToken(entity.getToken());
    dto.setLastAccessedTime(DateTimeUtil.toLocalDateTime(entity.getLastAccessedTime()));
    dto.setUser(this.vppaUserConverter.toDto(entity.getUser(), ignoreSensitiveData));
    dto.setExpired(entity.isExpired());
    
    if (!ignoreSensitiveData) {
      dto.setUserId(entity.getUserId());
    }
    
    dto.setCreatedBy(entity.getCreatedBy());
    dto.setCreatedDate(DateTimeUtil.toLocalDateTime(entity.getCreatedDate()));
    dto.setUpdatedBy(entity.getUpdatedBy());
    dto.setUpdatedDate(DateTimeUtil.toLocalDateTime(entity.getUpdatedDate()));
    
    return dto;
  }
}
