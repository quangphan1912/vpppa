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

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.gcs.vppa.common.converter.BaseConverter;
import com.gcs.vppa.common.logging.InjectLog;
import com.gcs.vppa.dto.DataSourceDTO;
import com.gcs.vppa.entity.DataSource;

import lombok.NoArgsConstructor;

/**
 * The Class ParameterConverter.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */

/**
 * Instantiates a new parameter converter.
 */

/**
 * Instantiates a new parameter converter.
 */
@NoArgsConstructor
@Service
@ComponentScan
public class DataSourceConverter extends BaseConverter<DataSource, DataSourceDTO> {

  /**
   * To dto.
   *
   * @param entity the dataSource
   * @return the template DTO
   */
  @Override
  @InjectLog(logParams = false)
  public DataSourceDTO toDto(DataSource entity) {
    if (entity == null) {
      return null;
    }

    DataSourceDTO dto = new DataSourceDTO();
    dto.setId(entity.getId());
    dto.setSource(entity.getSource());
    dto.setContent(entity.getContent());
    dto.setCreatedBy(entity.getCreatedBy());
    dto.setCreatedDate(
        entity.getCreatedDate() == null ? null : entity.getCreatedDate().toLocalDateTime());
    dto.setUpdatedBy(entity.getUpdatedBy());
    dto.setUpdatedDate(
        entity.getUpdatedDate() == null ? null : entity.getUpdatedDate().toLocalDateTime());

    return dto;
  }

  /**
   * To entity.
   *
   * @param dto the dto
   * @return the parameter
   */
  @Override
  @InjectLog
  public DataSource toEntity(DataSourceDTO dto) {
    if (dto == null) {
      return null;
    }

    DataSource entity = new DataSource();
    entity.setId(dto.getId());
    entity.setSource(dto.getSource());
    entity.setContent(dto.getContent());
    entity.setCreatedBy(dto.getCreatedBy());
    entity.setCreatedDate(dto.getCreatedDate() == null ? Timestamp.valueOf(LocalDateTime.now())
        : Timestamp.valueOf(dto.getCreatedDate()));
    entity.setUpdatedBy(dto.getUpdatedBy());
    entity.setUpdatedDate(dto.getUpdatedDate() == null ? null : Timestamp.valueOf(dto.getUpdatedDate()));

    return entity;
  }

  /**
   * To entity.
   *
   * @param dto the dto
   * @param entity the entity
   */
  @Override
  public void toEntity(DataSourceDTO dto, DataSource entity) {
    entity.setId(dto.getId() == null ? entity.getId() : dto.getId());
    entity.setSource(dto.getSource());
    entity.setContent(dto.getContent());
    entity.setCreatedBy(dto.getCreatedBy());
    entity.setCreatedDate(dto.getCreatedDate() == null ? Timestamp.valueOf(LocalDateTime.now())
        : Timestamp.valueOf(dto.getCreatedDate()));
    entity.setUpdatedBy(dto.getUpdatedBy());
    entity.setUpdatedDate(dto.getUpdatedDate() == null ? Timestamp.valueOf(LocalDateTime.now())
        : Timestamp.valueOf(dto.getUpdatedDate()));
  }

}
