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

import com.gcs.vppa.common.base.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
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
 * @param MasterDataSchemeDTO the new MasterDataSchemeDTO
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
public class MasterDataProcessDTO extends BaseDTO<Integer> {
	
  /**
   * The Constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;
  
	 /** The scheme type. */
	  private List<ProcessTypeDTO> processTypes;

	  /** The division. */
	  private List<DivisionDTO> divisions;

	  /** The center. */
	  private List<CenterDTO> centers;


    @Override
    public Integer getIdentifier() {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public void setIdentifier(Integer id) {
      // TODO Auto-generated method stub
      
    }

}
