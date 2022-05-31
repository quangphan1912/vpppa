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
package com.gcs.vppa.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gcs.vppa.common.base.BaseDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class SchemeDTO.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */

/**
 * Gets the scheme.
 *
 * @return the scheme
 */
@Getter

/**
 * Sets the scheme
 *
 * @param scheme
 *          the new scheme
 */
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString(callSuper = true)
public class SchemeDTO extends BaseDTO<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  private int id;

  /** The encrypted id. */
  private String encryptedId;

  /** The Scheme id. */
  private String idScheme;

  /** The Scheme name. */
  private String name;

  /** The Scheme description. */
  private String description;

  /** The kpi. */
  private String kpi;

  /** The kpi description. */
  private String kpiDescription;

  /** The Effective date. */
  private String effectivedDate;

  /** The Expired date. */
  private String expiredDate;

  /** The actual Effective date. */
  private String actualEffectivedDate;

  /** The actual Expired date. */
  private String actualExpiredDate;

  /** The status. */
  private String status;

  /** The status date. */
  private String statusDate;

  /** The scheme source. */
  private String source;

  /** The data source. */
  private String dataSource;

  /** The scheme reference id. */
  private String schemeReferenceId;

  /** The scheme type id. */
  private String schemeTypeId;

  /** The scheme type. */
  private SchemeTypeDTO schemeType;

  /** The process id. */
  private String processId;

  /** The process. */
  private ProcessDTO process;

  /** The channel id. */
  private String channelId;

  /** The channel. */
  private ChannelDTO channel;

  /** The division id. */
  private String divisionId;

  /** The division. */
  private DivisionDTO division;

  /** The center id. */
  private String centerId;

  /** The center. */
  private CenterDTO center;

  /** The division proposal id. */
  private String divisionProposalId;

  /** The division proposal. */
  private DivisionDTO divisionProposal;

  /** The department id. */
  private String departmentId;

  /** The department. */
  private DepartmentDTO department;

  /** The campaign id. */
  private String campaignId;

  /** The campaign. */
  private CampaignDTO campaign;

  /** The created date. */
  private LocalDateTime createdDate;

  /** The created by. */
  private String createdBy;

  /** The updated date. */
  private LocalDateTime updatedDate;

  /** The updated by. */
  private String updatedBy;

  /** The list parameter. */
  private List<ParameterDTO> parameters;

  /** The list product. */
  private List<ProductDTO> products;

  /** The list product. */
  private List<ProductDTO> subProducts;

  /** The list position. */
  private List<PositionDTO> positions;

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
