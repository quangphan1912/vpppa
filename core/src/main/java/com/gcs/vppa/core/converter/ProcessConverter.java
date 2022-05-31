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

import com.gcs.vppa.common.converter.BaseConverter;
import com.gcs.vppa.common.logging.InjectLog;
import com.gcs.vppa.common.service.CryptoService;
import com.gcs.vppa.common.util.CryptoUtils;
import com.gcs.vppa.dto.ProcessDTO;
import com.gcs.vppa.entity.Process;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

/**
 * The Process UserConverter.
 */

/**
 * Instantiates a new Process converter.
 */
@NoArgsConstructor
@Service
@ComponentScan
public class ProcessConverter extends BaseConverter<Process, ProcessDTO> {
  @Autowired
  private CryptoService cryptoService;

  @Autowired
  private ProcessTypeConverter processTypeConverter;

  @Autowired
  private DivisionConverter divisionConverter;

  @Autowired
  private CenterConverter centerConverter;

  /**
   * To data transfer object.
   *
   * @param entity the entity
   * @return the template DTO
   */
  @Override
  @InjectLog(logParams = false)
  public ProcessDTO toDto(Process entity) {
    if (entity == null) {
      return null;
    }

    ProcessDTO dto = new ProcessDTO();
    dto.setEncryptedId(CryptoUtils.encrypt(entity.getId().toString(), cryptoService.getProcessKey()));
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setKey(entity.getKey());
    dto.setCode(entity.getCode());
    dto.setDescription(entity.getDescription());
    dto.setExpression(entity.getExpression());
    dto.setProcessType(processTypeConverter.toDto(entity.getProcessType()));
    dto.setCenter(centerConverter.toDto(entity.getCenter()));
    dto.setDivision(divisionConverter.toDto(entity.getDivision()));

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
  public Process toEntity(ProcessDTO dto) {
    if (dto == null) {
      return null;
    }

    int id = 0;
    if (dto.getEncryptedId() != null) {
      id = Integer.parseInt(CryptoUtils.decrypt(dto.getEncryptedId(), cryptoService.getProcessKey()));
    } else {
      id = dto.getId();
    }

    Process entity = new Process();

    entity.setId(id);
    entity.setName(dto.getName());
    entity.setCode(dto.getCode());
    entity.setKey(dto.getKey());
    entity.setDescription(dto.getDescription());
    entity.setExpression(dto.getExpression());
    entity.setProcessType(processTypeConverter.toEntity(dto.getProcessType()));
    entity.setCenter(centerConverter.toEntity(dto.getCenter()));
    entity.setDivision(divisionConverter.toEntity(dto.getDivision()));
    return entity;
  }

  /**
   * {@inheritDoc}
   * 
   * @see BaseConverter#toEntity(Object,
   *      Object)
   */
  @Override
  @InjectLog(logParams = false)
  public void toEntity(ProcessDTO dto, Process entity) {
    int id = 0;
    if (dto.getEncryptedId() != null) {
      id = Integer.parseInt(CryptoUtils.decrypt(dto.getEncryptedId(), cryptoService.getProcessKey()));
    } else {
      id = dto.getId();
    }
    entity.setId(id);
    entity.setName(dto.getName());
    entity.setCode(dto.getCode());
    entity.setKey(dto.getKey());
    entity.setDescription(dto.getDescription());
    entity.setExpression(dto.getExpression());
    entity.setProcessType(processTypeConverter.toEntity(dto.getProcessType()));
    entity.setCenter(centerConverter.toEntity(dto.getCenter()));
    entity.setDivision(divisionConverter.toEntity(dto.getDivision()));
  }
}
