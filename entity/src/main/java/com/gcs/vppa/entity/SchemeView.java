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
package com.gcs.vppa.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gcs.vppa.common.base.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author hangttran.ht
 */
@Entity
@Table(name = "VW_SCHEME")
/**
 * Gets the scheme.
 *
 * @return the scheme
 */
@Getter

/**
 * Sets the scheme.
 *
 * @param scheme
 *          the new scheme
 */
@Setter

/**
 * Instantiates a new scheme.
 */
@NoArgsConstructor
public class SchemeView extends BaseEntity<Integer> implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 8107092578377879894L;

  /** The id. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", unique = true)
  private Integer id;

  /** The Scheme id. */
  @Column(name = "SCHEME_ID")
  private String schemeId;

  /** The Scheme name. */
  @Column(name = "SCHEME_NAME")
  private String schemeName;

  /** The product id */
  @Column(name = "PRODUCT_ID")
  private String productId;

  /** The product name. */
  @Column(name = "PRODUCT_NAME")
  private String productName;
  
  /** The subproduct id */
  @Column(name = "SUBPRODUCT_ID")
  private String subProductId;

  /** The subproduct name. */
  @Column(name = "SUBPRODUCT_NAME")
  private String subProductName;

  /** The division id. */
  @Column(name = "DIVISION_ID")
  private Integer divisionId;

  /** The division name. */
  @Column(name = "DIVISION_NAME")
  private String divisionName;

  /** The division id. */
  @Column(name = "DIVISION_PROPOSAL_ID")
  private Integer divisionProposalId;

  /** The division name. */
  @Column(name = "DIVISION_PROPOSAL_NAME")
  private String divisionProposalName;

  /** The center id. */
  @Column(name = "CENTER_ID")
  private Integer centerId;

  /** The center name. */
  @Column(name = "CENTER_NAME")
  private String centerName;

  /** The process id. */
  @Column(name = "PROCESS_ID")
  private Integer processId;

  /** The process name. */
  @Column(name = "PROCESS_NAME")
  private String processName;

  /** The channel id. */
  @Column(name = "CHANNEL_ID")
  private Integer channelId;

  /** The channel name. */
  @Column(name = "CHANNEL_NAME")
  private String channelName;

  /** The department id. */
  @Column(name = "DEPARTMENT_ID")
  private Integer departmentId;

  /** The department name. */
  @Column(name = "DEPARTMENT_NAME")
  private String departmentName;

  /** The campaign id. */
  @Column(name = "CAMPAIGN_ID")
  private Integer campaignId;

  /** The campaign name. */
  @Column(name = "CAMPAIGN_NAME")
  private String campaignName;

  /** The position id. */
  @Column(name = "POSITION_ID")
  private String positionId;

  /** The position name. */
  @Column(name = "POSITION_NAME")
  private String positionName;

  /** The schemeType id. */
  @Column(name = "SCHEME_TYPE_ID")
  private Integer schemeTypeId;

  /** The schemeType name. */
  @Column(name = "SCHEME_TYPE_NAME")
  private String schemeTypeName;

  /** The Effective date. */
  @Column(name = "EFFECTIVED_DATE")
  private Timestamp effectivedDate;

  /** The Expired date. */
  @Column(name = "EXPIRED_DATE")
  private Timestamp expiredDate;

  /** The actual Effective date. */
  @Column(name = "ACTUAL_EFFECTIVED_DATE")
  private Timestamp actualEffectivedDate;

  /** The actual Expired date. */
  @Column(name = "ACTUAL_EXPIRED_DATE")
  private Timestamp actualExpiredDate;

  /** The status. */
  @Column(name = "STATUS")
  private String status;

  /** The status date. */
  @Column(name = "STATUS_DATE")
  private Timestamp statusDate;
  
  /** The last run time. */
  @Column(name = "LAST_RUN_TIME")
  private Timestamp lastRunTime;
  
  /** The last run time. */
  @Column(name = "RESULT_FILE")
  private String resultFile;

  /** The created date. */
  @Column(name = "CREATED_DATE")
  private Timestamp createdDate;

  /** The updated date. */
  @Column(name = "UPDATED_DATE")
  private Timestamp updatedDate;

  /**
   * Instantiates a new vehicle.
   *
   * @param paramId
   *          the param id
   */
  public SchemeView(Integer id) {
    this.id = id;
  }

  /**
   * {@inheritDoc}
   *
   * @see com.gcs.vppa.common.base.BaseEntity#getIdentifier()
   */
  @Override
  public Integer getIdentifier() {
    return this.id;
  }
}
