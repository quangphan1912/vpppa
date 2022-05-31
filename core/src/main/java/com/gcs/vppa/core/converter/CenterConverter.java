/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 20, 2020     ********           hangttran.ht            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.core.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.gcs.vppa.common.converter.BaseConverter;
import com.gcs.vppa.common.service.CryptoService;
import com.gcs.vppa.common.util.CryptoUtils;
import com.gcs.vppa.dto.CenterDTO;
import com.gcs.vppa.entity.Center;

import lombok.NoArgsConstructor;

/**
 * The class PositionConverter.
 * 
 * @author hangttran.ht
 *
 */

/**
 * Instantiates a new Position converter.
 */
@NoArgsConstructor
@Service
@ComponentScan
public class CenterConverter extends BaseConverter<Center, CenterDTO> {

  /** The cryptography properties. */
  @Autowired
  private CryptoService cryptoService;

  /**
   * To dto.
   *
   * @param entity
   *          the entity
   * @return the Product DTO
   */
  @Override
  public CenterDTO toDto(Center entity) {
    if (entity == null) {
      return null;
    }

    CenterDTO dto = new CenterDTO();
    dto.setId(entity.getId());
    dto.setEncryptedId(
        CryptoUtils.encrypt(entity.getId().toString(), cryptoService.getCenterKey()));
    dto.setCode(entity.getCode());
    dto.setName(entity.getName());

    return dto;
  }

  /**
   * To entity.
   *
   * @param dto
   *          the dto
   * @return the Position
   */
  @Override
  public Center toEntity(CenterDTO dto) {
    if (dto == null) {
      return null;
    }

    int positionId = 0;
    if (dto.getEncryptedId() != null) {
      positionId = Integer
          .parseInt(CryptoUtils.decrypt(dto.getEncryptedId(), cryptoService.getCenterKey()));
    } else {
      positionId = dto.getId();
    }

    Center entity = new Center();
    entity.setId(positionId);
    entity.setCode(dto.getCode());
    entity.setName(dto.getName());
    return entity;

  }

  /**
   * To entity.
   *
   * @param dto
   *          the dto
   * @param entity
   *          the entity
   * @return the Position
   */
  @Override
  public void toEntity(CenterDTO dto, Center entity) {
    int positionId = 0;
    if (dto.getEncryptedId() != null) {
      positionId = Integer
              .parseInt(CryptoUtils.decrypt(dto.getEncryptedId(), cryptoService.getCenterKey()));
    } else {
      positionId = dto.getId();
    }

    entity.setId(positionId);
    entity.setCode(dto.getCode());
    entity.setName(dto.getName());
  }

}
