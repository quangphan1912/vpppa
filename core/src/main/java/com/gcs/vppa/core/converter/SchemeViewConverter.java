/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 19, 2020     ********           hangttran.ht            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.core.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.gcs.vppa.common.converter.BaseConverter;
import com.gcs.vppa.common.service.CryptoService;
import com.gcs.vppa.common.util.CryptoUtils;
import com.gcs.vppa.dto.SchemeViewDTO;
import com.gcs.vppa.entity.SchemeView;

import lombok.NoArgsConstructor;

/**
 * The class SchemeViewConverter.
 * 
 * @author hangttran.ht
 *
 */
/**
 * Instantiates a new SchemeView converter.
 */
@NoArgsConstructor
@Service
@ComponentScan
public class SchemeViewConverter extends BaseConverter<SchemeView, SchemeViewDTO> {

  /** The cryptography properties. */
  @Autowired
  private CryptoService cryptoService;

  /**
   * Todo Scheme dto.
   *
   * @param entity the entity
   * @return the scheme view DTO
   */
  @Override
  public SchemeViewDTO toDto(SchemeView entity) {
    if (entity == null) {
      return null;
    }

    SchemeViewDTO dto = new SchemeViewDTO();

    dto.setEncryptedId(
        CryptoUtils.encrypt(entity.getId().toString(), cryptoService.getSchemeKey()));
    dto.setSchemeId(entity.getSchemeId());
    dto.setSchemeName(entity.getSchemeName());

    dto.setProductId(entity.getProductId() == null ? null
        : CryptoUtils.encryptArrayId(entity.getProductId(),
            cryptoService.getProductKey()));
    dto.setProductName(entity.getProductName());
    dto.setSubProductId(entity.getSubProductId() == null ? null
        : CryptoUtils.encryptArrayId(entity.getSubProductId(),
            cryptoService.getProductKey()));
    dto.setSubProductName(entity.getSubProductName());
    dto.setDivisionId(entity.getDivisionId() == null ? null
        : CryptoUtils.encrypt(entity.getDivisionId().toString(),
            cryptoService.getDivisionKey()));
    dto.setDivisionName(entity.getDivisionName());
    dto.setDivisionProposalId(entity.getDivisionProposalId() == null ? null
        : CryptoUtils.encrypt(entity.getDivisionProposalId().toString(),
            cryptoService.getDivisionKey()));
    dto.setDivisionProposalName(entity.getDivisionProposalName());
    dto.setCenterId(entity.getCenterId() == null ? null
        : CryptoUtils.encrypt(entity.getCenterId().toString(), cryptoService.getCenterKey()));
    dto.setCenterName(entity.getCenterName());
    dto.setProcessId(entity.getProcessId() == null ? null
        : CryptoUtils.encrypt(entity.getProcessId().toString(),
            cryptoService.getProcessKey()));
    dto.setProcessName(entity.getProcessName());
    dto.setChannelId(entity.getChannelId() == null ? null
        : CryptoUtils.encrypt(entity.getChannelId().toString(),
            cryptoService.getChannelKey()));
    dto.setChannelName(entity.getChannelName());
    dto.setDepartmentId(entity.getDepartmentId() == null ? null
        : CryptoUtils.encrypt(entity.getDepartmentId().toString(),
            cryptoService.getDepartmentKey()));
    dto.setDepartmentName(entity.getDepartmentName());
    dto.setCampaignId(entity.getCampaignId() == null ? null
        : CryptoUtils.encrypt(entity.getCampaignId().toString(),
            cryptoService.getCampaignKey()));
    dto.setCampaignName(entity.getCampaignName());
    dto.setPositionId(entity.getPositionId() == null ? null
        : CryptoUtils.encryptArrayId(entity.getPositionId(),
            cryptoService.getPositionKey()));
    dto.setPositionName(entity.getPositionName());
    dto.setSchemeTypeId(entity.getSchemeTypeId() == null ? null
        : CryptoUtils.encrypt(entity.getSchemeTypeId().toString(),
            cryptoService.getSchemeTypeKey()));
    dto.setSchemeTypeName(entity.getSchemeTypeName());
    
    dto.setEffectivedDate(entity.getEffectivedDate() == null ? null
        : entity.getEffectivedDate().toLocalDateTime());
    dto.setExpiredDate(
        entity.getExpiredDate() == null ? null : entity.getExpiredDate().toLocalDateTime());
    dto.setActualEffectivedDate(entity.getActualEffectivedDate() == null ? null
        : entity.getActualEffectivedDate().toLocalDateTime());
    dto.setActualExpiredDate(entity.getActualExpiredDate() == null ? null
        : entity.getActualExpiredDate().toLocalDateTime());
    dto.setStatus(entity.getStatus());
    dto.setStatusDate(
        entity.getStatusDate() == null ? null : entity.getStatusDate().toLocalDateTime());
    
    dto.setLastRunTime(
      entity.getLastRunTime() == null ? null : entity.getLastRunTime().toLocalDateTime());
    
    dto.setResultFile(entity.getResultFile());
    
    dto.setCreatedDate(
        entity.getCreatedDate() == null ? null : entity.getCreatedDate().toLocalDateTime());
    dto.setUpdatedDate(
        entity.getUpdatedDate() == null ? null : entity.getUpdatedDate().toLocalDateTime());

    return dto;
  }

  /**
   * To entity.
   *
   * @param dto the dto
   * @return the scheme view
   */
  @Override
  public SchemeView toEntity(SchemeViewDTO dto) {
    return null;
  }

  /**
   * To entity.
   *
   * @param dto the dto
   * @param entity the entity
   */
  @Override
  public void toEntity(SchemeViewDTO dto, SchemeView entity) {
  }

}
