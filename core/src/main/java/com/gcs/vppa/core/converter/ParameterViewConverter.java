/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 15, 2020     ********           hangttran.ht            Initialize                  
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
import com.gcs.vppa.dto.ParameterViewDTO;
import com.gcs.vppa.entity.ParameterView;

import lombok.NoArgsConstructor;

/**
 * @author hangttran.ht
 *
 */
/**
 * Instantiates a new ParameterView converter.
 */
@NoArgsConstructor
@Service
@ComponentScan
public class ParameterViewConverter extends BaseConverter<ParameterView, ParameterViewDTO> {

  /** The cryptography properties. */
  @Autowired
  private CryptoService cryptoService;

  /**
   * To dto.
   *
   * @param entity
   *          the entity
   * @return the ParameterView DTO
   */
  @Override
  @InjectLog(logParams = false)
  public ParameterViewDTO toDto(ParameterView entity) {
    if (entity == null) {
      return null;
    }

    ParameterViewDTO dto = new ParameterViewDTO();

    dto.setEncryptedId(CryptoUtils.encrypt(entity.getId().toString(), cryptoService.getParameterKey()));
    dto.setParamName(entity.getParamName());
    dto.setDescription(entity.getDescription());
    dto.setNote(entity.getNote());
    dto.setStatus(entity.getStatus());
    dto.setSchemeId(entity.getSchemeId());
    dto.setSchemeName(entity.getSchemeName());
    dto.setDivisionId(entity.getDivisionId() == null ? null
        : CryptoUtils.encrypt(entity.getDivisionId(),
            cryptoService.getDivisionKey()));
    dto.setDivisionName(entity.getDivisionName());
    dto.setProductId(entity.getProductId() == null ? null
        : CryptoUtils.encryptArrayId(entity.getProductId(),
            cryptoService.getProductKey()));
    dto.setProductName(entity.getProductName());
    dto.setCenterId(entity.getCenterId() == null ? null
        : CryptoUtils.encrypt(entity.getCenterId(), cryptoService.getCenterKey()));
    dto.setCenterName(entity.getCenterName());
    dto.setCreatedDate(
        entity.getCreatedDate() == null ? null : entity.getCreatedDate().toLocalDateTime());
    dto.setUpdatedDate(
        entity.getUpdatedDate() == null ? null : entity.getUpdatedDate().toLocalDateTime());

    return dto;
  }

  @Override
  public ParameterView toEntity(ParameterViewDTO dto) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void toEntity(ParameterViewDTO dto, ParameterView entity) {
    // TODO Auto-generated method stub

  }

}
