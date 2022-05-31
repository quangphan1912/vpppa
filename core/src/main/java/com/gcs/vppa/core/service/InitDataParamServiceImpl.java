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

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.gcs.vppa.common.util.CollectionUtil;
import com.gcs.vppa.core.converter.InitDataConverter;
import com.gcs.vppa.dto.CenterDTO;
import com.gcs.vppa.dto.DivisionDTO;
import com.gcs.vppa.dto.MasterDataParameterDTO;
import com.gcs.vppa.dto.ProductDTO;
import com.gcs.vppa.entity.Center;
import com.gcs.vppa.entity.Division;
import com.gcs.vppa.entity.Product;
import com.gcs.vppa.repository.CenterRepository;
import com.gcs.vppa.repository.DivisionRepository;
import com.gcs.vppa.repository.ProductRepository;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
public class InitDataParamServiceImpl implements InitDataParamService {

  /** The product Repository. */
  @Autowired
  private ProductRepository productRepository;

  /** The division Repository. */
  @Autowired
  private DivisionRepository divisionRepository;

  /** The center Repository. */
  @Autowired
  private CenterRepository centerRepository;

  /** The InitData Converter. */
  @Autowired
  private InitDataConverter converter;

  /**
   * Gets the all master data.
   *
   * @return the all master data
   */
  @Override
  public MasterDataParameterDTO getAllMasterData() {
    MasterDataParameterDTO masterData = new MasterDataParameterDTO();

    // data for product
    List<Product> productEntitys = this.productRepository.findAll();

    if (!CollectionUtil.isNullOrEmpty(productEntitys)) {
      List<ProductDTO> productDtos = new ArrayList<>(productEntitys.size());
      log.debug("findAll - num_entities=[{}]", productEntitys.size());

      for (Product entity : productEntitys) {
        productDtos.add(this.converter.toDto(entity));
      }

      masterData.setProducts(productDtos);
    }

    // data division
    List<Division> divisionEntitys = this.divisionRepository.findAll();

    if (!CollectionUtil.isNullOrEmpty(divisionEntitys)) {
      List<DivisionDTO> divisionDtos = new ArrayList<>(divisionEntitys.size());
      log.debug("findAll - num_entities=[{}]", divisionEntitys.size());

      for (Division entity : divisionEntitys) {
        divisionDtos.add(this.converter.toDto(entity));
      }

      masterData.setDivisions(divisionDtos);
    }

    // data center
    List<CenterDTO> centerDtos = null;
    List<Center> centerEntitys = this.centerRepository.findAll();

    if (!CollectionUtil.isNullOrEmpty(centerEntitys)) {
      centerDtos = new ArrayList<>(centerEntitys.size());
      log.debug("findAll - centerEntitys=[{}]", centerEntitys.size());

      for (Center entity : centerEntitys) {
        centerDtos.add(this.converter.toDto(entity));
      }

      masterData.setCenters(centerDtos);
    }

    return masterData;

  }

}
