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

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.gcs.vppa.common.converter.BaseConverter;
import com.gcs.vppa.common.logging.InjectLog;
import com.gcs.vppa.dto.ProcessStatusDTO;
import com.gcs.vppa.entity.ProcessStatus;

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
public class ProcessStatusConverter extends BaseConverter<ProcessStatus, ProcessStatusDTO> {
  /**
   * To data transfer object.
   *
   * @param entity the entity
   * @return the template DTO
   */
  @Override
  @InjectLog(logParams = false)
  public ProcessStatusDTO toDto(ProcessStatus entity) {
    if (entity == null) {
      return null;
    }

    ProcessStatusDTO dto = new ProcessStatusDTO();
    dto.setId(entity.getId());
    dto.setStatus(entity.getStatus());
    dto.setExecuteBy(entity.getExecuteBy());
    dto.setExecuteDate(entity.getExecuteDate());
    dto.setProcessId(entity.getProcessId());
    dto.setSchemeId(entity.getSchemeId());
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
  public ProcessStatus toEntity(ProcessStatusDTO dto) {
    if (dto == null) {
      return null;
    }

    ProcessStatus entity = new ProcessStatus();

    entity.setId(dto.getId());
    entity.setStatus(dto.getStatus());
    entity.setExecuteBy(dto.getExecuteBy());
    entity.setExecuteDate(dto.getExecuteDate());
    entity.setProcessId(dto.getProcessId());
    entity.setSchemeId(dto.getSchemeId());
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
  public void toEntity(ProcessStatusDTO dto, ProcessStatus entity) {
    entity.setId(dto.getId());
    entity.setStatus(dto.getStatus());
    entity.setExecuteBy(dto.getExecuteBy());
    entity.setExecuteDate(dto.getExecuteDate());
    entity.setProcessId(dto.getProcessId());
    entity.setSchemeId(dto.getSchemeId());
  }
}
