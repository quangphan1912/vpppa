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
import com.gcs.vppa.dto.ProcessStatusViewDTO;
import com.gcs.vppa.entity.ProcessStatusView;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

/**
 * The ProcessStatusViewConverter.
 */

/**
 * Instantiates a new ProcessStatusViewConverter.
 */
@NoArgsConstructor
@Service
@ComponentScan
public class ProcessStatusViewConverter extends BaseConverter<ProcessStatusView, ProcessStatusViewDTO> {
  /**
   * To data transfer object.
   *
   * @param entity the entity
   * @return the template DTO
   */
  @Override
  @InjectLog(logParams = false)
  public ProcessStatusViewDTO toDto(ProcessStatusView entity) {
    if (entity == null) {
      return null;
    }

    ProcessStatusViewDTO dto = new ProcessStatusViewDTO();
    dto.setId(entity.getId());
    dto.setStatus(entity.getStatus());
    dto.setExecuteBy(entity.getExecuteBy());
    dto.setExecuteDate(entity.getExecuteDate());
    dto.setProcessId(entity.getProcessId());
    dto.setProcessName(entity.getProcessName());
    dto.setSchemeId(entity.getSchemeId());
    dto.setSchemeName(entity.getSchemeName());
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
  public ProcessStatusView toEntity(ProcessStatusViewDTO dto) {
    if (dto == null) {
      return null;
    }

    ProcessStatusView entity = new ProcessStatusView();

    entity.setId(dto.getId());
    entity.setStatus(dto.getStatus());
    entity.setExecuteBy(dto.getExecuteBy());
    entity.setExecuteDate(dto.getExecuteDate());
    entity.setProcessId(dto.getProcessId());
    entity.setProcessName(dto.getProcessName());
    entity.setSchemeId(dto.getSchemeId());
    entity.setSchemeName(dto.getSchemeName());
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
  public void toEntity(ProcessStatusViewDTO dto, ProcessStatusView entity) {
    entity.setId(dto.getId());
    entity.setStatus(dto.getStatus());
    entity.setExecuteBy(dto.getExecuteBy());
    entity.setExecuteDate(dto.getExecuteDate());
    entity.setProcessId(dto.getProcessId());
    entity.setProcessName(dto.getProcessName());
    entity.setSchemeId(dto.getSchemeId());
    entity.setSchemeName(dto.getSchemeName());
  }
}
