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
package com.gcs.vppa.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.gcs.vppa.common.base.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class Scheme.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */
@Entity
@Table(name = "TBL_SCHEME")

/**
 * Gets the scheme.
 *
 * @return the scheme
 */
@Getter

/**
 * Sets the scheme.
 *
 * @param Scheme the new scheme
 */
@Setter

/**
 * Instantiates a new scheme.
 */
@NoArgsConstructor
public class Scheme extends BaseEntity<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 8107092578377879894L;

  /** The id. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", unique = true)
  private Integer id;

  /** The Scheme id. */
  @Column(name = "IDENTIFIER", length = 200)
  private String idScheme;

  /** The Scheme name. */
  @Column(name = "NAME", length = 200)
  private String name;

  /** The Scheme description. */
  @Column(name = "DESCRIPTION", length = 4000)
  private String description;

  /** The kpi. */
  @Column(name = "KPI", length = 4000)
  private String kpi;

  /** The kpi description. */
  @Column(name = "KPI_DESCRIPTION", length = 4000)
  private String kpiDescription;

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

  /** The scheme source. */
  @Column(name = "SOURCE")
  private String source;

  /** The scheme reference id. */
  @Column(name = "SCHEME_REFERENCE_ID")
  private String schemeReferenceId;

  /** The scheme type id. */
  @Column(name = "SCHEME_TYPE_ID", updatable = false, insertable = false)
  private Integer schemeTypeId;

  /** The scheme type. */
  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "SCHEME_TYPE_ID")
  private SchemeType schemeType;

  /** The process id. */
  @Column(name = "PROCESS_ID", updatable = false, insertable = false)
  private Integer processId;

  /** The process. */
  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "PROCESS_ID")
  private Process process;

  /** The channel id. */
  @Column(name = "CHANNEL_ID", updatable = false, insertable = false)
  private Integer channelId;

  /** The channel. */
  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "CHANNEL_ID")
  private Channel channel;

  /** The division id. */
  @Column(name = "DIVISION_ID", updatable = false, insertable = false)
  private Integer divisionId;

  /** The division. */
  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "DIVISION_ID")
  private Division division;

  /** The center id. */
  @Column(name = "CENTER_ID", updatable = false, insertable = false)
  private Integer centerId;

  /** The center. */
  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "CENTER_ID")
  private Center center;

  /** The division proposal id. */
  @Column(name = "DIVISION_PROPOSAL_ID", updatable = false, insertable = false)
  private Integer divisionProposalId;

  /** The division proposal. */
  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "DIVISION_PROPOSAL_ID")
  private Division divisionProposal;

  /** The team id. */
  @Column(name = "DEPARTMENT_ID", updatable = false, insertable = false)
  private Integer departmentId;

  /** The department. */
  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "DEPARTMENT_ID")
  private Department department;

  /** The campaign id. */
  @Column(name = "CAMPAIGN_ID", updatable = false, insertable = false)
  private Integer campaignId;

  /** The campaign. */
  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "CAMPAIGN_ID")
  private Campaign campaign;

  /** The created date. */
  @Column(name = "CREATED_DATE")
  private Timestamp createdDate;

  /** The created by. */
  @Column(name = "CREATED_BY")
  private String createdBy;

  /** The updated date. */
  @Column(name = "UPDATED_DATE")
  private Timestamp updatedDate;

  /** The updated by. */
  @Column(name = "UPDATED_BY")
  private String updatedBy;

  /** The products */
  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinTable(name = "TBL_SCHEME_PRODUCT", joinColumns = {
      @JoinColumn(name = "SCHEME_ID") }, inverseJoinColumns = {
          @JoinColumn(name = "PRODUCT_ID") })
  private Set<Product> products = new HashSet<Product>();
  
  /** The sub products */
  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinTable(name = "TBL_SCHEME_SUBPRODUCT", joinColumns = {
      @JoinColumn(name = "SCHEME_ID") }, inverseJoinColumns = {
          @JoinColumn(name = "PRODUCT_ID") })
  private Set<Product> subProducts = new HashSet<Product>();
  
  /** The positions */
  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinTable(name = "TBL_SCHEME_POSITION", joinColumns = {
      @JoinColumn(name = "SCHEME_ID") }, inverseJoinColumns = {
          @JoinColumn(name = "POSITION_ID") })
  private Set<Position> positions = new HashSet<Position>();

  /** The parameters */
  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinTable(name = "TBL_SCHEME_PARAMETER", joinColumns = {
      @JoinColumn(name = "SCHEME_ID") }, inverseJoinColumns = {
          @JoinColumn(name = "PARAMETER_ID") })
  private Set<Parameter> parameters = new HashSet<Parameter>();

  /** The schemeStatus. */
  @OneToMany(mappedBy = "scheme", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<SchemeStatus> schemeStatus;

  /**
   * {@inheritDoc}
   *
   * @see com.gcs.vppa.common.base.BaseEntity#getIdentifier()
   */
  @Override
  public Integer getIdentifier() {
    return this.id;
  }

  /**
   * Instantiates a new vehicle.
   *
   * @param paramId
   *          the param id
   */
  public Scheme(Integer paramId) {
    this.id = paramId;
  }

}
