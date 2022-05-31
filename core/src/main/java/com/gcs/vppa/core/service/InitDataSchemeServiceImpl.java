/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 17, 2020     ********           hangttran.ht            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.gcs.vppa.common.util.CollectionUtil;
import com.gcs.vppa.core.converter.InitDataConverter;
import com.gcs.vppa.core.converter.ParameterConverter;
import com.gcs.vppa.dto.CampaignDTO;
import com.gcs.vppa.dto.CenterDTO;
import com.gcs.vppa.dto.ChannelDTO;
import com.gcs.vppa.dto.DepartmentDTO;
import com.gcs.vppa.dto.DivisionDTO;
import com.gcs.vppa.dto.MasterDataSchemeDTO;
import com.gcs.vppa.dto.ParameterDTO;
import com.gcs.vppa.dto.PositionDTO;
import com.gcs.vppa.dto.ProcessDTO;
import com.gcs.vppa.dto.ProductDTO;
import com.gcs.vppa.dto.SchemeDTO;
import com.gcs.vppa.dto.SchemeStatusDTO;
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
import com.gcs.vppa.entity.SchemeStatus;
import com.gcs.vppa.entity.SchemeType;
import com.gcs.vppa.repository.CampaignRepository;
import com.gcs.vppa.repository.CenterRepository;
import com.gcs.vppa.repository.ChannelRepository;
import com.gcs.vppa.repository.DepartmentRepository;
import com.gcs.vppa.repository.DivisionRepository;
import com.gcs.vppa.repository.ParameterRepository;
import com.gcs.vppa.repository.PositionRepository;
import com.gcs.vppa.repository.ProcessRepository;
import com.gcs.vppa.repository.ProductRepository;
import com.gcs.vppa.repository.SchemeRepository;
import com.gcs.vppa.repository.SchemeStatusRepository;
import com.gcs.vppa.repository.SchemeTypeRepository;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * The class InitDataSchemeServiceImpl.
 * 
 * @author hangttran.ht
 *
 */
/**
 * Instantiates a new parameter data service impl.
 */
@Slf4j
@NoArgsConstructor
@Service
@ComponentScan
public class InitDataSchemeServiceImpl implements InitDataSchemeService {

  /** The product Repository. */
  @Autowired
  private ProductRepository productRepository;

  /** The division Repository. */
  @Autowired
  private DivisionRepository divisionRepository;

  /** The center Repository. */
  @Autowired
  private CenterRepository centerRepository;

  /** The scheme type Repository. */
  @Autowired
  private SchemeTypeRepository schemeTypeRepository;

  /** The process Repository. */
  @Autowired
  private ProcessRepository processRepository;

  /** The scheme Repository. */
  @Autowired
  private SchemeRepository schemeRepository;

  /** The channel Repository. */
  @Autowired
  private ChannelRepository channelRepository;

  /** The department Repository. */
  @Autowired
  private DepartmentRepository departmentRepository;

  /** The position Repository. */
  @Autowired
  private PositionRepository positionRepository;

  /** The campaign Repository. */
  @Autowired
  private CampaignRepository campaignRepository;

  /** The schemeStatus Repository. */
  @Autowired
  private SchemeStatusRepository schemeStatusRepository;

  /** The parameter Repository. */
  @Autowired
  private ParameterRepository parameterRepository;

  /** The InitData Converter. */
  @Autowired
  private InitDataConverter converter;

  /** The parameter Converter. */
  @Autowired
  private ParameterConverter parameterConverter;

  /**
   * Gets the all master data.
   *
   * @return the all master data
   */
  @Override
  public MasterDataSchemeDTO getAllMasterData() {
    MasterDataSchemeDTO masterDataScheme = new MasterDataSchemeDTO();

    // data for scheme type
    List<SchemeTypeDTO> schemeTypeDtos = getDataSchemeType();
    masterDataScheme.setSchemeTypes(schemeTypeDtos);

    // data for process
    List<ProcessDTO> processDtos = getDataProcess();
    masterDataScheme.setProcesss(processDtos);

    // data for scheme ref
    List<SchemeDTO> schemeDtos = getDataScheme();
    masterDataScheme.setSchemeRefs(schemeDtos);

    // data for channel
    List<ChannelDTO> channelDtos = getDataChannel();
    masterDataScheme.setChannels(channelDtos);

    // data for product
    List<ProductDTO> productDtos = getDataProduct();
    masterDataScheme.setProducts(productDtos);

    // data of sub product
    masterDataScheme.setSubProducts(masterDataScheme.getProducts());

    // data division
    List<DivisionDTO> divisionDtos = getDataDivision();
    masterDataScheme.setDivisions(divisionDtos);

    // data division proposal
    masterDataScheme.setDivisionProposals(masterDataScheme.getDivisions());

    // data center
    List<CenterDTO> centerDtos = getDataCenter(0);
    masterDataScheme.setCenters(centerDtos);

    // data department
    List<DepartmentDTO> departmentDtos = getDataDepartment();
    masterDataScheme.setDepartments(departmentDtos);

    // data position
    List<PositionDTO> positionDtos = getDataPosition();
    masterDataScheme.setPositions(positionDtos);

    // data campaign
    List<CampaignDTO> campaignDtos = getDataCampaign();
    masterDataScheme.setCampaigns(campaignDtos);

    // data for scheme status
    List<SchemeStatusDTO> schemeStatusDtos = getDataSchemeStatus();
    masterDataScheme.setSchemeStatus(schemeStatusDtos);

    return masterDataScheme;
  }

  /**
   * Get data DivisionDTO.
   * 
   * @return list DivisionDTO
   */
  @Override
  public List<DivisionDTO> getDataDivision() {
    List<DivisionDTO> divisionDtos = null;
    List<Division> divisionEntitys = this.divisionRepository.findAll();

    if (!CollectionUtil.isNullOrEmpty(divisionEntitys)) {
      divisionDtos = new ArrayList<>(divisionEntitys.size());
      log.debug("findAll - num_entities=[{}]", divisionEntitys.size());
      for (Division entity : divisionEntitys) {
        divisionDtos.add(this.converter.toDto(entity));
      }
    }

    return divisionDtos;
  }
  
  /**
   * Get Data SchemeType.
   * 
   * @return SchemeType
   */
  public List<SchemeTypeDTO> getDataSchemeType() {
    List<SchemeTypeDTO> schemeTypeDtos = null;
    List<SchemeType> schemeTypeEntitys = this.schemeTypeRepository.findAll();

    if (!CollectionUtil.isNullOrEmpty(schemeTypeEntitys)) {
      schemeTypeDtos = new ArrayList<>(schemeTypeEntitys.size());
      log.debug("findAll - num_entities=[{}]", schemeTypeEntitys.size());
      for (SchemeType entity : schemeTypeEntitys) {
        schemeTypeDtos.add(this.converter.toDto(entity));
      }
    }
    
    return schemeTypeDtos;
  }
  
  /**
   * Get data CenterDTO.
   * 
   * @return list CenterDTO
   */
  public List<CenterDTO> getDataCenter(Integer divisionId) {
    List<CenterDTO> centerDtos = null;
    List<Center> centerEntitys = null;
    
    if (divisionId > 0) {
      centerEntitys = this.centerRepository.findByDivisionId(divisionId);
    } else {
      centerEntitys = this.centerRepository.findAll();
    }

    if (!CollectionUtil.isNullOrEmpty(centerEntitys)) {
      centerDtos = new ArrayList<>(centerEntitys.size());
      log.debug("findAll - centerEntitys=[{}]", centerEntitys.size());
      for (Center entity : centerEntitys) {
        centerDtos.add(this.converter.toDto(entity));
      }
    }

    return centerDtos;
  }
  
  /**
   * Get data scheme status.
   * 
   * @return list SchemeStatusDTO
   */
  private List<SchemeStatusDTO> getDataSchemeStatus() {
    List<SchemeStatusDTO> schemeStatusDtos = null;
    List<SchemeStatus> schemeStatusEntitys = this.schemeStatusRepository.findAll();

    if (!CollectionUtil.isNullOrEmpty(schemeStatusEntitys)) {
      schemeStatusDtos = new ArrayList<>(schemeStatusEntitys.size());
      log.debug("findAll - positionEntitys=[{}]", schemeStatusEntitys.size());
      for (SchemeStatus entity : schemeStatusEntitys) {
        schemeStatusDtos.add(this.converter.toDto(entity));
      }
    }

    return schemeStatusDtos;
  }

  /**
   * Get data campaign.
   * 
   * @return list CampaignDTO
   */
  private List<CampaignDTO> getDataCampaign() {
    List<CampaignDTO> campaignDtos = null;
    List<Campaign> campaignEntitys = this.campaignRepository.findAll();

    if (!CollectionUtil.isNullOrEmpty(campaignEntitys)) {
      campaignDtos = new ArrayList<>(campaignEntitys.size());
      log.debug("findAll - positionEntitys=[{}]", campaignEntitys.size());
      for (Campaign entity : campaignEntitys) {
        campaignDtos.add(this.converter.toDto(entity));
      }
    }

    return campaignDtos;
  }

  /**
   * Get data PositionDTO.
   * 
   * @return list PositionDTO
   */
  private List<PositionDTO> getDataPosition() {
    List<PositionDTO> positionDtos = null;
    List<Position> positionEntitys = this.positionRepository.findAll();

    if (!CollectionUtil.isNullOrEmpty(positionEntitys)) {
      positionDtos = new ArrayList<>(positionEntitys.size());
      log.debug("findAll - positionEntitys=[{}]", positionEntitys.size());
      for (Position entity : positionEntitys) {
        positionDtos.add(this.converter.toDto(entity));
      }
    }
    return positionDtos;

  }

  /**
   * Get data DepartmentDTO.
   * 
   * @return list DepartmentDTO
   */
  private List<DepartmentDTO> getDataDepartment() {
    List<DepartmentDTO> departmentDtos = null;
    List<Department> departmentEntitys = this.departmentRepository.findAll();

    if (!CollectionUtil.isNullOrEmpty(departmentEntitys)) {
      departmentDtos = new ArrayList<>(departmentEntitys.size());
      log.debug("findAll - departmentEntitys=[{}]", departmentEntitys.size());
      for (Department entity : departmentEntitys) {
        departmentDtos.add(this.converter.toDto(entity));
      }
    }

    return departmentDtos;
  }

  /**
   * Get data ProductDTO.
   * 
   * @return list ProductDTO
   */
  private List<ProductDTO> getDataProduct() {
    List<ProductDTO> productDtos = null;
    List<Product> productEntitys = this.productRepository.findAll();

    if (!CollectionUtil.isNullOrEmpty(productEntitys)) {
      productDtos = new ArrayList<>(productEntitys.size());
      log.debug("findAll - num_entities=[{}]", productEntitys.size());
      for (Product entity : productEntitys) {
        productDtos.add(this.converter.toDto(entity));
      }
    }
    return productDtos;
  }

  /**
   * Get data ChannelDTO.
   * 
   * @return list ChannelDTO
   */
  private List<ChannelDTO> getDataChannel() {
    List<ChannelDTO> channelDtos = null;
    List<Channel> channelEntitys = this.channelRepository.findAll();

    if (!CollectionUtil.isNullOrEmpty(channelEntitys)) {
      channelDtos = new ArrayList<>(channelEntitys.size());
      log.debug("findAll - num_entities=[{}]", channelEntitys.size());
      for (Channel entity : channelEntitys) {
        channelDtos.add(this.converter.toDto(entity));
      }
    }
    return channelDtos;
  }

  /**
   * Get Data Process.
   * 
   * @return processDtos
   */
  private List<ProcessDTO> getDataProcess() {
    List<ProcessDTO> processDtos = null;
    List<Process> processEntitys = this.processRepository.findAll();

    if (!CollectionUtil.isNullOrEmpty(processEntitys)) {
      processDtos = new ArrayList<>(processEntitys.size());
      log.debug("findAll - num_entities=[{}]", processEntitys.size());
      for (Process entity : processEntitys) {
        processDtos.add(this.converter.toDto(entity));
      }
    }
    return processDtos;
  }

  /**
   * Get Data scheme.
   * 
   * @return scheme dtos
   */
  private List<SchemeDTO> getDataScheme() {
    List<SchemeDTO> schemeDtos = null;
    List<Scheme> schemeEntitys = this.schemeRepository.findAll();

    if (!CollectionUtil.isNullOrEmpty(schemeEntitys)) {
      schemeDtos = new ArrayList<>(schemeEntitys.size());
      log.debug("findAll - num_entities=[{}]", schemeEntitys.size());
      for (Scheme entity : schemeEntitys) {
        schemeDtos.add(this.converter.toDto(entity));
      }
    }

    return schemeDtos;
  }

  /**
   * Gets the list parameter.
   *
   * @param id the id
   * @return the list parameter
   */
  @Override
  public List<ParameterDTO> getListParameter(Integer id) {
    List<Parameter> parameters = parameterRepository.findAll();
    List<ParameterDTO> objectDtos = new ArrayList<>(parameters.size());

    Scheme schemeEntity = this.schemeRepository.findByIdentifier(id);
    Set<Parameter> paramScheme = schemeEntity.getParameters();

    // filter parameter not use
    List<Parameter> objectTemps = new ArrayList<>(parameters.size());
    for (Parameter param : parameters) {
      if (!paramScheme.contains(param)) {
        objectTemps.add(param);
      }
    }

    for (Parameter object : objectTemps) {
      objectDtos.add(parameterConverter.toDto(object));
    }

    return objectDtos;
  }

}
