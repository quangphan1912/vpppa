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
package com.gcs.vppa.dto;

import java.util.List;

import com.gcs.vppa.common.base.BaseDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The class MasterDataSchemeDTO.
 * 
 * @author hangttran.ht
 *
 */
/**
 * Get master data scheme.
 *
 * @return the MasterDataSchemeDTO
 */
@Getter

/**
 * Sets the MasterDataSchemeDTO.
 *
 * @param MasterDataSchemeDTO
 *          the new MasterDataSchemeDTO
 */
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(callSuper = true)

/**
 * Instantiates a new campaign DTO.
 */
@NoArgsConstructor
public class MasterDataSchemeDTO extends BaseDTO<Integer> {

  /**
   * The Constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The scheme type. */
  private List<SchemeTypeDTO> schemeTypes;

  /** The scheme. */
  private List<SchemeDTO> schemeRefs;

  /** The process. */
  private List<ProcessDTO> processs;

  /** The product. */
  private List<ProductDTO> products;

  /** The sub product. */
  private List<ProductDTO> subProducts;

  /** The channel. */
  private List<ChannelDTO> channels;

  /** The division. */
  private List<DivisionDTO> divisions;

  /** The center. */
  private List<CenterDTO> centers;

  /** The division proposal. */
  private List<DivisionDTO> divisionProposals;

  /** The department. */
  private List<DepartmentDTO> departments;

  /** The campaign. */
  private List<CampaignDTO> campaigns;

  /** The position. */
  private List<PositionDTO> positions;

  /** The scheme status. */
  private List<SchemeStatusDTO> schemeStatus;

  /**
   * {@inheritDoc}
   * 
   * @see com.gcs.vppa.common.base.BaseDTO#getIdentifier()
   */
  @Override
  public Integer getIdentifier() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   * 
   * @see com.gcs.vppa.common.base.BaseDTO#setIdentifier(java.lang.Object)
   */
  @Override
  public void setIdentifier(Integer id) {
    // TODO Auto-generated method stub

  }

}
