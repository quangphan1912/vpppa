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
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.gcs.vppa.common.converter.BaseConverter;
import com.gcs.vppa.common.logging.InjectLog;
import com.gcs.vppa.common.service.CryptoService;
import com.gcs.vppa.common.util.CryptoUtils;
import com.gcs.vppa.common.util.DateTimeUtil;
import com.gcs.vppa.common.util.StringUtil;
import com.gcs.vppa.dto.CampaignDTO;
import com.gcs.vppa.dto.CenterDTO;
import com.gcs.vppa.dto.ChannelDTO;
import com.gcs.vppa.dto.DepartmentDTO;
import com.gcs.vppa.dto.DivisionDTO;
import com.gcs.vppa.dto.ParameterDTO;
import com.gcs.vppa.dto.PositionDTO;
import com.gcs.vppa.dto.ProcessDTO;
import com.gcs.vppa.dto.ProductDTO;
import com.gcs.vppa.dto.SchemeDTO;
import com.gcs.vppa.dto.SchemeTypeDTO;
import com.gcs.vppa.entity.Campaign;
import com.gcs.vppa.entity.Center;
import com.gcs.vppa.entity.Channel;
import com.gcs.vppa.entity.Department;
import com.gcs.vppa.entity.Division;
import com.gcs.vppa.entity.Parameter;
import com.gcs.vppa.entity.Position;
import com.gcs.vppa.entity.Process;
import com.gcs.vppa.entity.Product;
import com.gcs.vppa.entity.Scheme;
import com.gcs.vppa.entity.SchemeType;

import lombok.NoArgsConstructor;

/**
 * The Class SchemeConverter.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */

/**
 * Instantiates a new scheme converter.
 */

/**
 * Instantiates a new scheme converter.
 */
@NoArgsConstructor
@Service
@ComponentScan
/** The Constant log. */
public class SchemeConverter extends BaseConverter<Scheme, SchemeDTO> {

  /** The parameter Converter. */
  @Autowired
  private ParameterConverter parameterConverter;

  /** The product Converter. */
  @Autowired
  private ProductConverter productConverter;

  /** The position Converter. */
  @Autowired
  private PositionConverter positionConverter;

  /** The cryptography properties. */
  @Autowired
  private CryptoService cryptoService;

  /**
   * To dto.
   *
   * @param entity
   *          the entity
   * @return the template DTO
   */
  @Override
  @InjectLog(logParams = false)
  public SchemeDTO toDto(Scheme entity) {
    if (entity == null) {
      return null;
    }

    SchemeDTO dto = new SchemeDTO();
    dto.setEncryptedId(
      CryptoUtils.encrypt(entity.getId().toString(), cryptoService.getSchemeKey()));
    dto.setIdScheme(entity.getIdScheme());
    dto.setName(entity.getName());
    dto.setDescription(entity.getDescription());
    dto.setKpi(entity.getKpi());
    dto.setKpiDescription(entity.getKpiDescription());
    dto.setEffectivedDate(entity.getEffectivedDate() == null ? null
      : entity.getEffectivedDate().toLocalDateTime().toString());
    dto.setExpiredDate(entity.getExpiredDate() == null ? null
      : entity.getExpiredDate().toLocalDateTime().toString());
    dto.setActualEffectivedDate(entity.getActualEffectivedDate() == null ? null
      : entity.getActualEffectivedDate().toLocalDateTime().toString());
    dto.setActualExpiredDate(entity.getActualExpiredDate() == null ? null
      : entity.getActualExpiredDate().toLocalDateTime().toString());
    dto.setStatus(entity.getStatus());
    dto.setStatusDate(entity.getStatusDate() == null ? null
        : entity.getStatusDate().toLocalDateTime().toString());
    dto.setSource(entity.getSource());
    dto.setCreatedDate(entity.getCreatedDate() == null ? null
      : entity.getCreatedDate().toLocalDateTime());
    dto.setCreatedBy(entity.getCreatedBy());
    dto.setUpdatedDate(entity.getUpdatedDate() == null ? null
      : entity.getUpdatedDate().toLocalDateTime());
    dto.setUpdatedBy(entity.getUpdatedBy());
    dto.setSchemeReferenceId(entity.getSchemeReferenceId());

    setEncryptIdForReferences(entity, dto);

    // get list param
    Set<Parameter> parameters = entity.getParameters();
    if (parameters != null) {
      List<ParameterDTO> paramDtos = new ArrayList<ParameterDTO>(parameters.size());
      for (Parameter parameter : parameters) {
        ParameterDTO paramDto = new ParameterDTO();
        paramDto = parameterConverter.toDto(parameter);
        paramDtos.add(paramDto);
      }

      dto.setParameters(paramDtos);
    }

    // get list product
    Set<Product> products = entity.getProducts();
    if (products != null) {
      List<ProductDTO> productDtos = new ArrayList<ProductDTO>(products.size());
      for (Product product : products) {
        ProductDTO productDto = new ProductDTO();
        productDto = productConverter.toDto(product);
        productDtos.add(productDto);
      }

      dto.setProducts(productDtos);
    }

    // get list sub product
    Set<Product> subProducts = entity.getSubProducts();
    if (subProducts != null) {
      List<ProductDTO> subProDtos = new ArrayList<ProductDTO>(subProducts.size());
      for (Product subPro : subProducts) {
        ProductDTO subProductDto = new ProductDTO();
        subProductDto = productConverter.toDto(subPro);
        subProDtos.add(subProductDto);
      }

      dto.setSubProducts(subProDtos);
    }

    // get list position
    Set<Position> positions = entity.getPositions();
    if (positions != null) {
      List<PositionDTO> positionDtos = new ArrayList<PositionDTO>(positions.size());
      for (Position position : positions) {
        PositionDTO positionDto = new PositionDTO();
        positionDto = positionConverter.toDto(position);
        positionDtos.add(positionDto);
      }

      dto.setPositions(positionDtos);
    }

    return dto;
  }

  /**
   * To entity.
   *
   * @param dto
   *          the dto
   * @return the scheme
   */
  @Override
  @InjectLog
  public Scheme toEntity(SchemeDTO dto) {
    if (dto == null) {
      return null;
    }

    Scheme entity = new Scheme();
    int paramId = 0;

    if (dto.getEncryptedId() != null && StringUtils.isEmpty(dto.getEncryptedId())) {
      paramId = CryptoUtils.decryptId(dto.getEncryptedId(), cryptoService.getSchemeKey());
    } else {
      paramId = dto.getId();
    }

    entity.setId(paramId);

    entity.setIdScheme(dto.getIdScheme());
    entity.setName(dto.getName());
    entity.setDescription(dto.getDescription());
    entity.setKpi(dto.getKpi());
    entity.setKpiDescription(dto.getKpiDescription());
    entity.setEffectivedDate(StringUtil.isEmptyString(dto.getEffectivedDate()) ? null
      : Timestamp.valueOf(DateTimeUtil.formatDateTime(dto.getEffectivedDate())));
    entity.setExpiredDate(StringUtil.isEmptyString(dto.getExpiredDate()) ? null
      : Timestamp.valueOf(DateTimeUtil.formatExpiredDate(dto.getExpiredDate())));
    entity.setActualEffectivedDate(StringUtil.isEmptyString(dto.getActualEffectivedDate()) ? null
      : Timestamp.valueOf(DateTimeUtil.formatDateTime(dto.getActualEffectivedDate())));
    entity.setActualExpiredDate(StringUtil.isEmptyString(dto.getActualExpiredDate()) ? null
      : Timestamp.valueOf(DateTimeUtil.formatExpiredDate(dto.getActualExpiredDate())));
    entity.setStatus(dto.getStatus());
    entity.setStatusDate(StringUtil.isEmptyString(dto.getStatusDate()) ? null
        : Timestamp.valueOf(DateTimeUtil.formatDateTime(dto.getStatusDate())));
    entity.setSource(dto.getSource());

    updateReferences(entity, dto);

    entity.setCreatedDate(dto.getCreatedDate() == null ? Timestamp.valueOf(LocalDateTime.now())
      : Timestamp.valueOf(dto.getCreatedDate()));
    entity.setCreatedBy(dto.getCreatedBy());

    entity.setUpdatedDate(dto.getCreatedDate() == null ? null
      : Timestamp.valueOf(dto.getCreatedDate()));
    entity.setUpdatedBy(dto.getUpdatedBy());

    // insert param
    entity.getParameters().clear();
    List<ParameterDTO> paramDtos = dto.getParameters();
    if (paramDtos != null) {
      for (ParameterDTO paramDto : paramDtos) {
        Parameter paramm = parameterConverter.toEntity(paramDto);
        entity.getParameters().add(paramm);
      }
    }

    // insert product
    entity.getProducts().clear();
    List<ProductDTO> productDtos = dto.getProducts();
    if (productDtos != null) {
      for (ProductDTO productDto : productDtos) {
        Product product = productConverter.toEntity(productDto);
        entity.getProducts().add(product);
      }
    }

    // insert subproduct
    entity.getSubProducts().clear();
    List<ProductDTO> subProductDtos = dto.getSubProducts();
    if (subProductDtos != null) {
      for (ProductDTO subProDto : subProductDtos) {
        Product subPro = productConverter.toEntity(subProDto);
        entity.getSubProducts().add(subPro);
      }
    }

    // insert position
    entity.getPositions().clear();
    List<PositionDTO> positionDtos = dto.getPositions();
    if (positionDtos != null) {

      for (PositionDTO positionDto : positionDtos) {
        Position position = positionConverter.toEntity(positionDto);
        entity.getPositions().add(position);
      }
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
  public void toEntity(SchemeDTO dto, Scheme entity) {
    int id = 0;
    if (dto.getEncryptedId() != null) {
      id = CryptoUtils.decryptId(dto.getEncryptedId(), cryptoService.getSchemeKey());
    } else {
      id = dto.getId();
    }

    entity.setId(id);
    entity.setIdScheme(dto.getIdScheme());
    entity.setName(dto.getName());
    entity.setDescription(dto.getDescription());
    entity.setKpi(dto.getKpi());
    entity.setKpiDescription(dto.getKpiDescription());
    entity.setEffectivedDate(StringUtil.isEmptyString(dto.getEffectivedDate()) ? null
        : Timestamp.valueOf(DateTimeUtil.formatDateTime(dto.getEffectivedDate())));
      entity.setExpiredDate(StringUtil.isEmptyString(dto.getExpiredDate()) ? null
        : Timestamp.valueOf(DateTimeUtil.formatExpiredDate(dto.getExpiredDate())));
      entity.setActualEffectivedDate(StringUtil.isEmptyString(dto.getActualEffectivedDate()) ? null
        : Timestamp.valueOf(DateTimeUtil.formatDateTime(dto.getActualEffectivedDate())));
      entity.setActualExpiredDate(StringUtil.isEmptyString(dto.getActualExpiredDate()) ? null
        : Timestamp.valueOf(DateTimeUtil.formatExpiredDate(dto.getActualExpiredDate())));
      entity.setStatus(dto.getStatus());
      entity.setStatusDate(StringUtil.isEmptyString(dto.getStatusDate()) ? null
          : Timestamp.valueOf(DateTimeUtil.formatDateTime(dto.getStatusDate())));
    entity.setSource(dto.getSource());

    updateReferences(entity, dto);

    entity.setCreatedDate(dto.getCreatedDate() == null ? Timestamp.valueOf(LocalDateTime.now())
      : Timestamp.valueOf(dto.getCreatedDate()));
    entity.setCreatedBy(dto.getCreatedBy());

    entity.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));
    entity.setUpdatedBy(dto.getUpdatedBy());

    // update param
    entity.getParameters().clear();
    List<ParameterDTO> paramDtos = dto.getParameters();
    if (paramDtos != null) {

      for (ParameterDTO paramDto : paramDtos) {
        Parameter paramm = parameterConverter.toEntity(paramDto);
        entity.getParameters().add(paramm);
      }
    }

    // update product
    entity.getProducts().clear();
    List<ProductDTO> productDtos = dto.getProducts();
    if (productDtos != null) {

      for (ProductDTO productDto : productDtos) {
        Product product = productConverter.toEntity(productDto);
        entity.getProducts().add(product);
      }
    }

    // update subproduct
    entity.getSubProducts().clear();
    List<ProductDTO> subProductDtos = dto.getSubProducts();

    if (subProductDtos != null) {

      for (ProductDTO subProDto : subProductDtos) {
        Product subPro = productConverter.toEntity(subProDto);
        entity.getSubProducts().add(subPro);
      }
    }

    // update position
    entity.getPositions().clear();
    List<PositionDTO> positionDtos = dto.getPositions();
    if (positionDtos != null) {

      for (PositionDTO positionDto : positionDtos) {
        Position position = positionConverter.toEntity(positionDto);
        entity.getPositions().add(position);
      }
    }

  }

  /**
   * Update References Data.
   * 
   * @param entity
   *          the scheme entity
   * @param dto
   *          the scheme dto
   */
  private void updateReferences(Scheme entity, SchemeDTO dto) {

    entity.setSchemeReferenceId(dto.getSchemeReferenceId());

    entity.setSchemeTypeId(StringUtil.isEmptyString(dto.getSchemeTypeId()) ? null
        : CryptoUtils.decryptId(dto.getSchemeTypeId(), cryptoService.getSchemeTypeKey()));
    entity.setSchemeType(StringUtil.isEmptyString(dto.getSchemeTypeId()) ? null
        : new SchemeType(
            CryptoUtils.decryptId(dto.getSchemeTypeId(), cryptoService.getSchemeTypeKey())));

    entity.setProcessId(StringUtil.isEmptyString(dto.getProcessId()) ? null
        : CryptoUtils.decryptId(dto.getProcessId(), cryptoService.getProcessKey()));
    entity.setProcess(StringUtil.isEmptyString(dto.getProcessId()) ? null
        : new Process(
            CryptoUtils.decryptId(dto.getProcessId(), cryptoService.getProcessKey())));

    entity.setChannelId(StringUtil.isEmptyString(dto.getChannelId()) ? null
        : CryptoUtils.decryptId(dto.getChannelId(), cryptoService.getChannelKey()));
    entity.setChannel(StringUtil.isEmptyString(dto.getChannelId()) ? null
        : new Channel(
            CryptoUtils.decryptId(dto.getChannelId(), cryptoService.getChannelKey())));

    entity.setDivisionId(StringUtil.isEmptyString(dto.getDivisionId()) ? null
        : CryptoUtils.decryptId(dto.getDivisionId(), cryptoService.getDivisionKey()));
    entity.setDivision(StringUtil.isEmptyString(dto.getDivisionId()) ? null
        : new Division(
            CryptoUtils.decryptId(dto.getDivisionId(), cryptoService.getDivisionKey())));

    entity.setCenterId(StringUtil.isEmptyString(dto.getCenterId()) ? null
        : CryptoUtils.decryptId(dto.getCenterId(), cryptoService.getCenterKey()));
    entity.setCenter(StringUtil.isEmptyString(dto.getCenterId()) ? null
        : new Center(CryptoUtils.decryptId(dto.getCenterId(), cryptoService.getCenterKey())));

    entity.setDivisionProposalId(StringUtil.isEmptyString(dto.getDivisionProposalId()) ? null
        : CryptoUtils.decryptId(dto.getDivisionProposalId(), cryptoService.getDivisionKey()));
    entity.setDivisionProposal(StringUtil.isEmptyString(dto.getDivisionProposalId()) ? null
        : new Division(CryptoUtils.decryptId(dto.getDivisionProposalId(),
            cryptoService.getDivisionKey())));

    entity.setDepartmentId(StringUtil.isEmptyString(dto.getDepartmentId()) ? null
        : CryptoUtils.decryptId(dto.getDepartmentId(), cryptoService.getDepartmentKey()));
    entity.setDepartment(StringUtil.isEmptyString(dto.getDepartmentId()) ? null
        : new Department(
            CryptoUtils.decryptId(dto.getDepartmentId(), cryptoService.getDepartmentKey())));

    entity.setCampaignId(StringUtil.isEmptyString(dto.getCampaignId()) ? null
        : CryptoUtils.decryptId(dto.getCampaignId(), cryptoService.getCampaignKey()));
    entity.setCampaign(StringUtil.isEmptyString(dto.getCampaignId()) ? null
        : new Campaign(
            CryptoUtils.decryptId(dto.getCampaignId(), cryptoService.getCampaignKey())));
  }

  /**
   * Set EncryptId For References.
   * 
   * @param entity
   * @param dto
   */
  private void setEncryptIdForReferences(Scheme entity, SchemeDTO dto) {

    if (entity.getSchemeTypeId() != null) {
      dto.setSchemeTypeId(CryptoUtils.encrypt(entity.getSchemeTypeId().toString(),
        cryptoService.getSchemeTypeKey()));
    }

    if (entity.getSchemeType() != null) {
      dto.setSchemeType(new SchemeTypeDTO(
        CryptoUtils.encrypt(entity.getSchemeType().getId().toString(),
          cryptoService.getSchemeTypeKey()),
        entity.getSchemeType().getCode(), entity.getSchemeType().getName()));
    }

    if (entity.getProcessId() != null) {
      dto.setProcessId(CryptoUtils.encrypt(entity.getProcessId().toString(),
        cryptoService.getProcessKey()));
    }

    if (entity.getProcess() != null) {
      dto.setProcess(new ProcessDTO(
        CryptoUtils.encrypt(entity.getProcess().getId().toString(),
          cryptoService.getProcessKey()),
        entity.getProcess().getCode(), entity.getProcess().getName()));
    }

    if (entity.getChannelId() != null) {
      dto.setChannelId(CryptoUtils.encrypt(entity.getChannelId().toString(),
        cryptoService.getChannelKey()));
    }

    if (entity.getChannel() != null) {
      dto.setChannel(new ChannelDTO(
        CryptoUtils.encrypt(entity.getChannel().getId().toString(),
          cryptoService.getChannelKey()),
        entity.getChannel().getCode(), entity.getChannel().getName()));
    }

    if (entity.getDivisionId() != null) {
      dto.setDivisionId(CryptoUtils.encrypt(entity.getDivisionId().toString(),
        cryptoService.getDivisionKey()));
    }

    if (entity.getDivision() != null) {
      dto.setDivision(new DivisionDTO(
        CryptoUtils.encrypt(entity.getDivision().getId().toString(),
          cryptoService.getDivisionKey()),
        entity.getDivision().getCode(), entity.getDivision().getName()));
    }

    if (entity.getCenterId() != null) {
      dto.setCenterId(
        CryptoUtils.encrypt(entity.getCenterId().toString(), cryptoService.getCenterKey()));
    }

    if (entity.getCenter() != null) {
      DivisionDTO disionDtoCenter = null;
      if (entity.getCenter().getDivitionId() != null
        && entity.getCenter().getDivision() != null) {
        disionDtoCenter = new DivisionDTO(
          CryptoUtils.encrypt(entity.getCenter().getDivitionId().toString(),
            cryptoService.getDivisionKey()),
          entity.getCenter().getDivision().getCode(),
          entity.getCenter().getDivision().getName());
      }

      dto.setCenter(new CenterDTO(
        CryptoUtils.encrypt(entity.getCenterId().toString(), cryptoService.getCenterKey()),
        entity.getCenter().getCode(), entity.getCenter().getName(),
        entity.getCenter().getDivitionId(), disionDtoCenter));
    }

    if (entity.getDivisionProposalId() != null) {
      dto.setDivisionProposalId(CryptoUtils.encrypt(entity.getDivisionProposalId().toString(),
        cryptoService.getDivisionKey()));
    }

    if (entity.getDivisionProposal() != null) {
      dto.setDivisionProposal(new DivisionDTO(
        CryptoUtils.encrypt(entity.getDivisionProposal().getId().toString(),
          cryptoService.getDivisionKey()),
        entity.getDivisionProposal().getCode(), entity.getDivisionProposal().getName()));
    }

    if (entity.getDepartmentId() != null) {
      dto.setDepartmentId(CryptoUtils.encrypt(entity.getDepartmentId().toString(),
        cryptoService.getDepartmentKey()));
    }

    if (entity.getDepartment() != null) {
      CenterDTO center = null;
      Center centerEntity = entity.getDepartment().getCenter();
      if (centerEntity != null) {
        DivisionDTO divisionCenter = null;
        if (centerEntity.getDivision() != null) {
          divisionCenter = new DivisionDTO(
            CryptoUtils.encrypt(centerEntity.getDivision().getId().toString(),
              cryptoService.getDivisionKey()),
            centerEntity.getDivision().getCode(), centerEntity.getDivision().getName());
        }
        center = new CenterDTO(
          CryptoUtils.encrypt(centerEntity.getId().toString(),
            cryptoService.getCenterKey()),
          centerEntity.getCode(), centerEntity.getName(), centerEntity.getDivitionId(),
          divisionCenter);
      }
      dto.setDepartment(new DepartmentDTO(
        CryptoUtils.encrypt(entity.getDepartment().getId().toString(),
          cryptoService.getDepartmentKey()),
        entity.getDepartment().getCode(), entity.getDepartment().getName(), center));
    }

    if (entity.getCampaignId() != null) {
      dto.setCampaignId(CryptoUtils.encrypt(entity.getCampaignId().toString(),
        cryptoService.getCampaignKey()));
    }

    if (entity.getCampaign() != null) {
      dto.setCampaign(new CampaignDTO(
        CryptoUtils.encrypt(entity.getCampaign().getId().toString(),
          cryptoService.getCampaignKey()),
        entity.getCampaign().getCode(), entity.getCampaign().getName()));
    }

  }

}
