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
import com.gcs.vppa.dto.DivisionDTO;
import com.gcs.vppa.entity.Division;

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
public class DivisionConverter extends BaseConverter<Division, DivisionDTO> {

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
  public DivisionDTO toDto(Division entity) {
    if (entity == null) {
      return null;
    }

    DivisionDTO dto = new DivisionDTO();
    dto.setId(entity.getId());
    dto.setEncryptedId(
        CryptoUtils.encrypt(entity.getId().toString(), cryptoService.getDivisionKey()));
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
  public Division toEntity(DivisionDTO dto) {
    if (dto == null) {
      return null;
    }

    int id = 0;
    if (dto.getEncryptedId() != null) {
      id = Integer
          .parseInt(CryptoUtils.decrypt(dto.getEncryptedId(), cryptoService.getDivisionKey()));
    } else {
      id = dto.getId();
    }

    Division entity = new Division();
    entity.setId(id);
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
  public void toEntity(DivisionDTO dto, Division entity) {
    int id = 0;
    if (dto.getEncryptedId() != null) {
      id = Integer
              .parseInt(CryptoUtils.decrypt(dto.getEncryptedId(), cryptoService.getDivisionKey()));
    } else {
      id = dto.getId();
    }

    entity.setId(id);
    entity.setCode(dto.getCode());
    entity.setName(dto.getName());
  }

}
