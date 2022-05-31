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
package com.gcs.vppa.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gcs.vppa.common.base.BaseDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The class SchemeViewDTO.
 * 
 * @author hangttran.ht
 *
 */
/**
 * Gets the scheme name.
 *
 * @return the scheme name
 */
@Getter

/**
 * Sets the scheme name.
 *
 * @param schemeName
 *          the new scheme name
 */
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(callSuper = true)
public class SchemeViewDTO extends BaseDTO<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  private int id;

  /** The Scheme id. */
  private String schemeId;

  /** The Scheme name. */
  private String schemeName;

  /** The product id */
  private String productId;

  /** The product name. */
  private String productName;

  /** The subproduct id */
  private String subProductId;

  /** The subproduct name. */
  private String subProductName;

  /** The division id. */
  private String divisionId;

  /** The division name. */
  private String divisionName;

  /** The division id. */
  private String divisionProposalId;

  /** The division name. */
  private String divisionProposalName;

  /** The center id. */
  private String centerId;

  /** The center name. */
  private String centerName;

  /** The process id. */
  private String processId;

  /** The process name. */
  private String processName;

  /** The channel id. */
  private String channelId;

  /** The channel name. */
  private String channelName;

  /** The department id. */
  private String departmentId;

  /** The department name. */
  private String departmentName;

  /** The campaign id. */
  private String campaignId;

  /** The campaign name. */
  private String campaignName;

  /** The position id. */
  private String positionId;

  /** The position name. */
  private String positionName;

  /** The schemeType id. */
  private String schemeTypeId;

  /** The schemeType name. */
  private String schemeTypeName;

  /** The Effective date. */
  private LocalDateTime effectivedDate;

  /** The Expired date. */
  private LocalDateTime expiredDate;

  /** The actual Effective date. */
  private LocalDateTime actualEffectivedDate;

  /** The actual Expired date. */
  private LocalDateTime actualExpiredDate;

  /** The status. */
  private String status;

  /** The status date. */
  private LocalDateTime statusDate;
  
  /** The last run time. */
  private LocalDateTime lastRunTime;
  
  /** The last run time. */
  private String resultFile;

  /** The created date. */
  private LocalDateTime createdDate;

  /** The updated date. */
  private LocalDateTime updatedDate;

  /**
   * {@inheritDoc}
   * 
   * @see com.gcs.vppa.common.base.BaseDTO#getIdentifier()
   */
  @Override
  @JsonIgnore
  public Integer getIdentifier() {
    return this.id;
  }

  /**
   * {@inheritDoc}
   * 
   * @see com.gcs.vppa.common.base.BaseDTO#setIdentifier(java.lang.Object)
   */
  @Override
  @JsonIgnore
  public void setIdentifier(Integer id) {
    this.id = id;
  }
}
