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
import com.gcs.vppa.dto.PositionDTO;
import com.gcs.vppa.entity.Position;

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
public class PositionConverter extends BaseConverter<Position, PositionDTO> {
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
  public PositionDTO toDto(Position entity) {
    if (entity == null) {
      return null;
    }

    PositionDTO dto = new PositionDTO();
    dto.setId(entity.getId());
    dto.setEncryptedId(
        CryptoUtils.encrypt(entity.getId().toString(), cryptoService.getPositionKey()));
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
  public Position toEntity(PositionDTO dto) {
    if (dto == null) {
      return null;
    }

    int positionId = 0;
    if (dto.getEncryptedId() != null) {
      positionId = Integer
          .parseInt(CryptoUtils.decrypt(dto.getEncryptedId(), cryptoService.getPositionKey()));
    } else {
      positionId = dto.getId();
    }

    Position entity = new Position();
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
  public void toEntity(PositionDTO dto, Position entity) {
    // TODO Auto-generated method stub
  }

}
