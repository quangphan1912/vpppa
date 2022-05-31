/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 29, 2020     ********           hangttran.ht            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.core.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.gcs.vppa.common.converter.BaseConverter;
import com.gcs.vppa.common.service.CryptoService;
import com.gcs.vppa.common.util.CryptoUtils;
import com.gcs.vppa.dto.SchemeExecutorViewDTO;
import com.gcs.vppa.entity.SchemeExecutorView;

import lombok.NoArgsConstructor;

/**
 * @author hangttran.ht
 *
 */
/**
 * Instantiates a new SchemeExecutor converter.
 */
@NoArgsConstructor
@Service
@ComponentScan
public class SchemeExecutorViewConverter  extends BaseConverter<SchemeExecutorView, SchemeExecutorViewDTO> {

  /** The cryptography properties. */
  @Autowired
  private CryptoService cryptoService;


  /**
   * To dto.
   *
   * @param entity the entity
   * @return the SchemeExecutorViewDTO DTO
   */
  @Override
  public SchemeExecutorViewDTO toDto(SchemeExecutorView entity) {
    if (entity == null) {
      return null;
    }

    SchemeExecutorViewDTO dto = new SchemeExecutorViewDTO();

    dto.setId(entity.getId());
    dto.setExecutorId(entity.getExecutorId());
    dto.setResultFile(entity.getResultFile());
    dto.setStatus(entity.getStatus());
    dto.setType(entity.getType());
    dto.setSid(CryptoUtils.encrypt(entity.getSid().toString(), cryptoService.getSchemeKey()));
    dto.setSchemeId(entity.getSchemeId());
    dto.setSchemeName(entity.getSchemeName());
    dto.setProcessId(CryptoUtils.encrypt(entity.getProcessId().toString(), cryptoService.getProcessKey()));
    dto.setProcessName(entity.getProcessName());
    dto.setExecuteDate(
        entity.getExecuteDate() == null ? null : entity.getExecuteDate().toLocalDateTime());
    dto.setExecuteBy(entity.getExecuteBy());
    dto.setStatusScheme(entity.getStatusScheme());

    return dto;
  }

  /**
   * To entity.
   *
   * @param dto the dto
   * @return the Product
   */
  @Override
  public SchemeExecutorView toEntity(SchemeExecutorViewDTO dto) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * To entity.
   *
   * @param dto the dto
   * @param entity the entity
   */
  @Override
  public void toEntity(SchemeExecutorViewDTO dto, SchemeExecutorView entity) {
    // TODO Auto-generated method stub
    
  }

}
