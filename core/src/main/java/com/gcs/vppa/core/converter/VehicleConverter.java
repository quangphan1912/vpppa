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
import com.gcs.vppa.dto.VehicleDTO;
import com.gcs.vppa.entity.Vehicle;

import lombok.NoArgsConstructor;

/**
 * The Class UserConverter.
 */

/**
 * Instantiates a new vehicle converter.
 */
@NoArgsConstructor
@Service
@ComponentScan
public class VehicleConverter extends BaseConverter<Vehicle, VehicleDTO> {
  /**
   * To data transfer object.
   *
   * @param entity the entity
   * @return the template DTO
   */
  @Override
  @InjectLog(logParams = false)
  public VehicleDTO toDto(Vehicle entity) {
    if (entity == null) {
      return null;
    }

    VehicleDTO dto = new VehicleDTO();
    dto.setVehicleId(entity.getVehicleId());
    dto.setName(entity.getName());
    dto.setOwnerName(entity.getOwnerName());

    dto.setCreateOn(entity.getCreateOn() == null ? null : entity.getCreateOn().toLocalDateTime());
    dto.setRegisterNumber(entity.getRegisterNumber());

    dto.setLastUpdated(entity.getLastUpdated() == null ? null : entity.getLastUpdated().toLocalDateTime());

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
  public Vehicle toEntity(VehicleDTO dto) {
    if (dto == null) {
      return null;
    }

    Vehicle entity = new Vehicle();

    entity.setVehicleId(dto.getVehicleId());
    entity.setName(dto.getName());
    entity.setOwnerName(dto.getOwnerName());
    entity.setRegisterNumber(dto.getRegisterNumber());

    Timestamp createdOn = dto.getCreateOn() == null ? Timestamp.valueOf(LocalDateTime.now())
      : Timestamp.valueOf(dto.getCreateOn());
    entity.setCreateOn(createdOn);

    entity.setName(dto.getName());

    Timestamp lastUpdated = dto.getLastUpdated() == null ? null
      : Timestamp.valueOf(dto.getLastUpdated());
    entity.setLastUpdated(lastUpdated);

    return entity;
  }

  /**
   * {@inheritDoc}
   * 
   * @see com.gcs.vppa.common.converter.BaseConverter#toEntity(java.lang.Object,
   *      java.lang.Object)
   */
  @Override
  @InjectLog(logParams = false)
  public void toEntity(VehicleDTO dto, Vehicle entity) {
    entity.setName(dto.getName());
    entity.setRegisterNumber(dto.getRegisterNumber());
    entity.setOwnerName(dto.getOwnerName());

    entity.setCreateOn(dto.getCreateOn() == null ? Timestamp.valueOf(LocalDateTime.now())
      : Timestamp.valueOf(dto.getCreateOn()));

    entity.setLastUpdated(dto.getLastUpdated() == null ? null
      : Timestamp.valueOf(dto.getLastUpdated()));
  }
}
