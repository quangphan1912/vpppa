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
package com.gcs.vppa.core.service;

import com.gcs.vppa.common.util.CollectionUtil;
import com.gcs.vppa.core.converter.InitDataConverter;
import com.gcs.vppa.dto.CenterDTO;
import com.gcs.vppa.dto.DivisionDTO;
import com.gcs.vppa.dto.MasterDataProcessDTO;
import com.gcs.vppa.dto.ProcessTypeDTO;
import com.gcs.vppa.entity.Center;
import com.gcs.vppa.entity.Division;
import com.gcs.vppa.entity.ProcessType;
import com.gcs.vppa.repository.CenterRepository;
import com.gcs.vppa.repository.DivisionRepository;
import com.gcs.vppa.repository.ProcessTypeRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
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
public class InitDataProcessServiceImpl implements InitDataProcessService {
  /** The division Repository. */
  @Autowired
  private DivisionRepository divisionRepository;

  /** The center Repository. */
  @Autowired
  private CenterRepository centerRepository;

  /** The scheme type Repository. */
  @Autowired
  private ProcessTypeRepository processTypeRepository;

  /** The InitData Converter. */
  @Autowired
  private InitDataConverter converter;

  /**
   * Gets the all master data.
   *
   * @return the all master data
   */
  @Override
  public MasterDataProcessDTO getAllMasterData() {
    MasterDataProcessDTO masterData = new MasterDataProcessDTO();

    // data center
    List<CenterDTO> centerDtos = getDataCenter();
    masterData.setCenters(centerDtos);

    // data division
    List<DivisionDTO> divisionDtos = getDataDivision();
    masterData.setDivisions(divisionDtos);

    // data for scheme type
    List<ProcessTypeDTO> processTypeDTOS = getDataProcessType();
    masterData.setProcessTypes(processTypeDTOS);

    return masterData;

  }

  /**
   * Get Data SchemeType.
   *
   * @return SchemeType
   */
  private List<ProcessTypeDTO> getDataProcessType() {
    List<ProcessTypeDTO> processTypeDTOS = null;
    List<ProcessType> processTypeEntitys = this.processTypeRepository.findAll();

    if (!CollectionUtil.isNullOrEmpty(processTypeEntitys)) {
      processTypeDTOS = new ArrayList<>(processTypeEntitys.size());
      log.debug("findAll - num_entities=[{}]", processTypeEntitys.size());
      for (ProcessType entity : processTypeEntitys) {
        processTypeDTOS.add(this.converter.toDto(entity));
      }

    }
    return processTypeDTOS;

  }

  private List<DivisionDTO> getDataDivision() {
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

  private List<CenterDTO> getDataCenter() {
    List<CenterDTO> centerDtos = null;
    List<Center> centerEntitys = this.centerRepository.findAll();

    if (!CollectionUtil.isNullOrEmpty(centerEntitys)) {
      centerDtos = new ArrayList<>(centerEntitys.size());
      log.debug("findAll - centerEntitys=[{}]", centerEntitys.size());
      for (Center entity : centerEntitys) {
        centerDtos.add(this.converter.toDto(entity));
      }
    }

    return centerDtos;
  }

}
