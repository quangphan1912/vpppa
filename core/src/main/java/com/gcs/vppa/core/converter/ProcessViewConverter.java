/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * July 27, 2020     ********           PhoVo            Initialize                  
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
import com.gcs.vppa.dto.ProcessViewDTO;
import com.gcs.vppa.entity.ProcessView;

import lombok.NoArgsConstructor;

/**
 * The Process UserConverter.
 */

/**
 * Instantiates a new Process converter.
 */
@NoArgsConstructor
@Service
@ComponentScan
public class ProcessViewConverter extends BaseConverter<ProcessView, ProcessViewDTO> {
  @Autowired
  private CryptoService cryptoService;

  /**
   * To data transfer object.
   *
   * @param entity the entity
   * @return the template DTO
   */
  @Override
  @InjectLog(logParams = false)
  public ProcessViewDTO toDto(ProcessView entity) {
    if (entity == null) {
      return null;
    }

    ProcessViewDTO dto = new ProcessViewDTO();
    dto.setEncryptedId(CryptoUtils.encrypt(entity.getId().toString(), cryptoService.getProcessKey()));
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setKey(entity.getKey());
    dto.setDescription(entity.getDescription());
    dto.setExpression(entity.getExpression());
    dto.setCenterId(entity.getCenterId() == null ? null : CryptoUtils.encrypt(entity.getCenterId(), cryptoService.getCenterKey()));
    dto.setCenterName(entity.getCenterName());
    dto.setDivisionId(entity.getDivisionId() == null ? null : CryptoUtils.encrypt(entity.getDivisionId(), cryptoService.getDivisionKey()));
    dto.setDivisionName(entity.getDivisionName());
    dto.setProcessTypeId(entity.getProcessTypeId() == null ? null : CryptoUtils.encrypt(entity.getProcessTypeId(), cryptoService.getProcessTypeKey()));
    dto.setProcessTypeName(entity.getProcessTypeName());

    return dto;
  }

  /**
   * To entity.
   *
   * @param dto the dto
   * @return the t entity
   */
  @Override
  @InjectLog
  public ProcessView toEntity(ProcessViewDTO dto) {
    // nop
    return null;
  }

  /**
   * {@inheritDoc}
   * 
   * @see BaseConverter#toEntity(Object,
   *      Object)
   */
  @Override
  @InjectLog(logParams = false)
  public void toEntity(ProcessViewDTO dto, ProcessView entity) {
    // nop
  }
}
