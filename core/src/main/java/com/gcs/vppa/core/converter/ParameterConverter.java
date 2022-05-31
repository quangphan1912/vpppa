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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.gcs.vppa.common.converter.BaseConverter;
import com.gcs.vppa.common.logging.InjectLog;
import com.gcs.vppa.common.service.CryptoService;
import com.gcs.vppa.common.util.CryptoUtils;
import com.gcs.vppa.dto.ParameterConditionDTO;
import com.gcs.vppa.dto.ParameterDTO;
import com.gcs.vppa.dto.SchemeDTO;
import com.gcs.vppa.entity.Parameter;
import com.gcs.vppa.entity.ParameterCondition;
import com.gcs.vppa.entity.Scheme;

import lombok.NoArgsConstructor;

/**
 * The Class ParameterConverter.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */
/**
 * Instantiates a new parameter converter.
 */
@NoArgsConstructor
@Service
@ComponentScan
public class ParameterConverter extends BaseConverter<Parameter, ParameterDTO> {
  /** The cryptography properties. */
  @Autowired
  private CryptoService cryptoService;

  /** The parameter Condition Converter. */
  @Autowired
  private ParameterConditionConverter parameterConditionConverter;

  /**
   * To dto.
   *
   * @param entity the entity
   * @param encryptedKey the encrypted key
   * @return the template DTO
   */
  @Override
  @InjectLog(logParams = false)
  public ParameterDTO toDto(Parameter entity) {
    if (entity == null) {
      return null;
    }

    ParameterDTO dto = new ParameterDTO();

    dto.setEncryptedId(
        CryptoUtils.encrypt(entity.getId().toString(), cryptoService.getParameterKey()));
    dto.setParamName(entity.getParamName());
    dto.setSource(entity.getSource());
    dto.setDescription(entity.getDescription());
    dto.setNote(entity.getNote());
    dto.setStatus(entity.getStatus());
    dto.setCreatedDate(
        entity.getCreatedDate() == null ? null : entity.getCreatedDate().toLocalDateTime());
    dto.setCreatedBy(entity.getCreatedBy());
    dto.setUpdatedDate(
        entity.getUpdatedDate() == null ? null : entity.getUpdatedDate().toLocalDateTime());
    dto.setUpdatedBy(entity.getUpdatedBy());

    // get list scheme
    Set<Scheme> schemes = entity.getSchemes();
    if (schemes != null) {
      List<SchemeDTO> schemeDtos = new ArrayList<SchemeDTO>(schemes.size());
        for (Scheme scheme : schemes) {
          SchemeDTO schemeDto = new SchemeDTO();
          schemeDto = schemeConvertertoDto(scheme);
          schemeDtos.add(schemeDto);
      }
      dto.setSchemes(schemeDtos);
    }

    // get list parameter condition
    Set<ParameterCondition> paramConditions = entity.getParameterConditions();
    if (paramConditions != null) {
      List<ParameterConditionDTO> paramConditionsDtos = new ArrayList<ParameterConditionDTO>(paramConditions.size());
        for (ParameterCondition condition : paramConditions) {
          paramConditionsDtos.add(parameterConditionConverter.toDto(condition));
        }
      dto.setParameterConditions(paramConditionsDtos);
    }

    return dto;
  }

  /**
   * To entity.
   *
   * @param dto
   *          the dto
   * @return the parameter
   */
  @Override
  @InjectLog
  public Parameter toEntity(ParameterDTO dto) {
    if (dto == null) {
      return null;
    }

    int paramId = 0;
    if (dto.getEncryptedId() != null) {
      paramId = CryptoUtils.decryptId(dto.getEncryptedId(), cryptoService.getParameterKey());
    } else {
      paramId = dto.getId();
    }

    Parameter entity = new Parameter();
    entity.setId(paramId);
    entity.setParamName(dto.getParamName());
    entity.setSource(dto.getSource());
    entity.setDescription(dto.getDescription());
    entity.setNote(dto.getNote());
    entity.setStatus(dto.getStatus());

    entity.setCreatedDate(dto.getCreatedDate() == null || dto.getCloneParamId() != null ? Timestamp.valueOf(LocalDateTime.now())
        : Timestamp.valueOf(dto.getCreatedDate()));
    entity.setCreatedBy(dto.getCreatedBy());

    // get list parameter condition
    List<ParameterConditionDTO> paramConditions = dto.getParameterConditions();
    if (paramConditions != null) {
      Set<ParameterCondition> paramConditionsEntities = new HashSet<>(paramConditions.size());
      for (ParameterConditionDTO condition : paramConditions) {
        paramConditionsEntities.add(parameterConditionConverter.toEntity(condition));
      }
      entity.setParameterConditions(paramConditionsEntities);
    }

    return entity;
  }

  /**
   * To entity.
   *
   * @param dto
   *          the dto
   * @param entity
   *          the entity
   */
  @Override
  public void toEntity(ParameterDTO dto, Parameter entity) {
    int paramId = 0;
    if (dto.getEncryptedId() != null) {
      paramId = CryptoUtils.decryptId(dto.getEncryptedId(), cryptoService.getParameterKey());
    } else {
      paramId = dto.getId();
    }

    entity.setId(paramId);
    entity.setParamName(dto.getParamName());
    entity.setSource(dto.getSource());
    entity.setDescription(dto.getDescription());
    entity.setNote(dto.getNote());
    entity.setStatus(dto.getStatus());

    entity.setCreatedDate(dto.getCreatedDate() == null ? Timestamp.valueOf(LocalDateTime.now())
        : Timestamp.valueOf(dto.getCreatedDate()));
    entity.setCreatedBy(dto.getCreatedBy());

    entity.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));
    entity.setUpdatedBy(dto.getUpdatedBy());

    // get list parameter condition
    List<ParameterConditionDTO> paramConditions = dto.getParameterConditions();
    if (paramConditions != null) {
      Set<ParameterCondition> paramConditionsEntities = new HashSet<>(paramConditions.size());
      for (ParameterConditionDTO condition : paramConditions) {
        paramConditionsEntities.add(parameterConditionConverter.toEntity(condition));
      }
      entity.setParameterConditions(paramConditionsEntities);
    }

  }

  /**
   * Convert to Dto scheme in parameter.
   * 
   * @param scheme
   *          entity
   * @return Dto
   */
  private SchemeDTO schemeConvertertoDto(Scheme scheme) {
    if (scheme == null) {
      return null;
    }
    SchemeDTO dto = new SchemeDTO();
    dto.setEncryptedId(
        CryptoUtils.encrypt(scheme.getId().toString(), cryptoService.getSchemeKey()));
    dto.setIdScheme(scheme.getIdScheme());
    dto.setName(scheme.getName());
    dto.setStatus(scheme.getStatus());
    return dto;
  }

}
