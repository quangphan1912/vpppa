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

import java.util.List;

import com.gcs.vppa.dto.CenterDTO;
import com.gcs.vppa.dto.DivisionDTO;
import com.gcs.vppa.dto.MasterDataSchemeDTO;
import com.gcs.vppa.dto.ParameterDTO;
import com.gcs.vppa.dto.SchemeTypeDTO;

/**
 * The Interface InitDataSchemeService.
 * 
 * @author hangttran.ht
 *
 */
public interface InitDataSchemeService {
  /**
   * Gets the all master data.
   *
   * @return MasterDataParameter DTO
   */
  MasterDataSchemeDTO getAllMasterData();

  /**
   * Gets the list parameter.
   *
   * @param id the id
   * @return the list parameter
   */
  List<ParameterDTO> getListParameter(Integer id);

  /**
   * Gets the data scheme type.
   *
   * @return the data scheme type
   */
  List<SchemeTypeDTO> getDataSchemeType();

  /**
   * Gets the data division.
   *
   * @return the data division
   */
  List<DivisionDTO> getDataDivision();

  /**
   * Gets the data center.
   *
   * @param divisionId the division id
   * @return the data center
   */
  List<CenterDTO> getDataCenter(Integer divisionId);
}
