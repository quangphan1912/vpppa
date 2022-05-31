/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 13, 2020     ********           hangttran.ht            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.core.converter;

import com.gcs.vppa.dto.*;
import com.gcs.vppa.entity.*;
import com.gcs.vppa.entity.Process;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.gcs.vppa.common.service.CryptoService;
import com.gcs.vppa.common.util.CryptoUtils;

import lombok.NoArgsConstructor;

/**
 * @author hangttran.ht
 *
 */
/**
 * Instantiates a new parameter converter.
 */

@NoArgsConstructor
@Service
@ComponentScan
public class InitDataConverter {
  /** The cryptography properties. */
  @Autowired
  private CryptoService cryptoService;

  /**
   * To dto.
   *
   * @param entity
   *          the entity
   * @return the dto
   */
  public ProductDTO toDto(Product entity) {
    if (entity == null) {
      return null;
    }

    ProductDTO dto = new ProductDTO();
    dto.setId(entity.getId());
    dto.setEncryptedId(
        CryptoUtils.encrypt(entity.getId().toString(), cryptoService.getProductKey()));
    dto.setCode(entity.getCode());
    dto.setName(entity.getName());
    dto.setParentId(entity.getParentId() == null ? null
        : CryptoUtils.encrypt(entity.getParentId().toString(), cryptoService.getProductKey()));

    return dto;

  }

  /**
   * To dto.
   *
   * @param entity
   *          the entity
   * @return the dto
   */
  public DivisionDTO toDto(Division entity) {
    if (entity == null) {
      return null;
    }

    DivisionDTO dto = new DivisionDTO();
    dto.setId(entity.getId());
    dto.setEncryptedId(
        CryptoUtils.encrypt(entity.getId().toString(), cryptoService.getDivisionKey()));
    dto.setCode(entity.getCode());
    dto.setName(entity.getName());
    dto.setChannelId(entity.getChannelId() == null ? null
        : CryptoUtils.encrypt(entity.getChannelId().toString(), cryptoService.getChannelKey()));
    dto.setChannel(this.toDto(entity.getChannel()));

    return dto;

  }

  /**
   * To dto.
   *
   * @param entity
   *          the entity
   * @return the dto
   */
  public CenterDTO toDto(Center entity) {
    if (entity == null) {
      return null;
    }

    CenterDTO dto = new CenterDTO();
    dto.setId(entity.getId());
    dto.setEncryptedId(
        CryptoUtils.encrypt(entity.getId().toString(), cryptoService.getCenterKey()));
    dto.setCode(entity.getCode());
    dto.setName(entity.getName());
    dto.setDivision(entity.getDivision() == null ? null
        : new DivisionDTO(
            CryptoUtils.encrypt(entity.getDivision().getId().toString(),
                cryptoService.getDivisionKey()),
            entity.getDivision().getCode(), entity.getDivision().getName()));

    return dto;
  }

  /**
   * To scheme type dto.
   *
   * @param entity
   *          the entity
   * @return the dto
   */
  public SchemeTypeDTO toDto(SchemeType entity) {
    if (entity == null) {
      return null;
    }

    SchemeTypeDTO dto = new SchemeTypeDTO();
    dto.setId(entity.getId());
    dto.setEncryptedId(
        CryptoUtils.encrypt(entity.getId().toString(), cryptoService.getSchemeTypeKey()));
    dto.setCode(entity.getCode());
    dto.setName(entity.getName());

    return dto;
  }

  /**
   * To scheme type dto.
   *
   * @param entity
   *          the entity
   * @return the dto
   */
  public ProcessTypeDTO toDto(ProcessType entity) {
    if (entity == null) {
      return null;
    }

    ProcessTypeDTO dto = new ProcessTypeDTO();
    dto.setId(entity.getId());
    dto.setEncryptedId(
            CryptoUtils.encrypt(entity.getId().toString(), cryptoService.getProcessTypeKey()));
    dto.setCode(entity.getCode());
    dto.setName(entity.getName());

    return dto;
  }

  /**
   * To process dto.
   *
   * @param entity
   *          the entity
   * @return the dto
   */
  public ProcessDTO toDto(Process entity) {
    if (entity == null) {
      return null;
    }

    ProcessDTO dto = new ProcessDTO();
    dto.setId(entity.getId());
    dto.setEncryptedId(
        CryptoUtils.encrypt(entity.getId().toString(), cryptoService.getProcessKey()));
    dto.setCode(entity.getCode());
    dto.setName(entity.getName());

    return dto;
  }

  /**
   * To scheme ref dto.
   *
   * @param entity
   *          the entity
   * @return the dto
   */
  public SchemeDTO toDto(Scheme entity) {
    if (entity == null) {
      return null;
    }

    SchemeDTO dto = new SchemeDTO();
    dto.setEncryptedId(
        CryptoUtils.encrypt(entity.getId().toString(), cryptoService.getSchemeKey()));
    dto.setIdScheme(entity.getIdScheme());
    dto.setName(entity.getName());

    return dto;
  }

  /**
   * To channel dto.
   *
   * @param entity
   *          the entity
   * @return the dto
   */
  public ChannelDTO toDto(Channel entity) {
    if (entity == null) {
      return null;
    }

    ChannelDTO dto = new ChannelDTO();
    dto.setId(entity.getId());
    dto.setEncryptedId(
        CryptoUtils.encrypt(entity.getId().toString(), cryptoService.getChannelKey()));
    dto.setCode(entity.getCode());
    dto.setName(entity.getName());

    return dto;
  }

  /**
   * To department dto.
   *
   * @param entity
   *          the entity
   * @return the dto
   */
  public DepartmentDTO toDto(Department entity) {
    if (entity == null) {
      return null;
    }

    DepartmentDTO dto = new DepartmentDTO();
    dto.setId(entity.getId());
    dto.setEncryptedId(
        CryptoUtils.encrypt(entity.getId().toString(), cryptoService.getDepartmentKey()));
    dto.setCode(entity.getCode());
    dto.setName(entity.getName());
    dto.setCenterId(entity.getCenterId() == null ? null
        : CryptoUtils.encrypt(entity.getCenterId().toString(), cryptoService.getCenterKey()));
    dto.setCenter(this.toDto(entity.getCenter()));

    return dto;
  }

  /**
   * To position dto.
   *
   * @param entity
   *          the entity
   * @return the dto
   */
  public PositionDTO toDto(Position entity) {
    if (entity == null) {
      return null;
    }

    PositionDTO dto = new PositionDTO();
    dto.setId(entity.getId());
    dto.setEncryptedId(
        CryptoUtils.encrypt(entity.getId().toString(), cryptoService.getPositionKey()));
    dto.setCode(entity.getCode());
    dto.setName(entity.getName());

    return dto;
  }

  /**
   * To campaign dto.
   *
   * @param entity
   *          the entity
   * @return the dto
   */
  public CampaignDTO toDto(Campaign entity) {
    if (entity == null) {
      return null;
    }

    CampaignDTO dto = new CampaignDTO();
    dto.setId(entity.getId());
    dto.setEncryptedId(
        CryptoUtils.encrypt(entity.getId().toString(), cryptoService.getCampaignKey()));
    dto.setCode(entity.getCode());
    dto.setName(entity.getName());

    return dto;
  }

  public SchemeStatusDTO toDto(SchemeStatus entity) {
    if (entity == null) {
      return null;
    }

    SchemeStatusDTO dto = new SchemeStatusDTO();
    dto.setId(entity.getId());
    dto.setEncryptedId(
        CryptoUtils.encrypt(entity.getId().toString(), cryptoService.getSchemeStatusKey()));
    dto.setSchemeId(entity.getSchemeId() == null ? null
        : CryptoUtils.encrypt(entity.getSchemeId().toString(), cryptoService.getSchemeKey()));
    dto.setStatus(entity.getStatus());
    dto.setUpdatedBy(entity.getUpdatedBy());
    dto.setUpdatedDate(
        entity.getUpdatedDate() == null ? null : entity.getUpdatedDate().toLocalDateTime().toString());

    return dto;
  }
}
