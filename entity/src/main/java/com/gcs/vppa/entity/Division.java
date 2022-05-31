/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 10, 2020     ********           hangttran.ht            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.gcs.vppa.common.base.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author hangttran.ht
 */
@Entity
@Table(name = "TBL_DIVISION")

/**
 * Gets the division
 *
 * @return the division
 */
@Getter

/**
 * Sets the division.
 *
 * @param division the new division
 */
@Setter

/**
 * Instantiates a new division
 */
@NoArgsConstructor
public class Division extends BaseEntity<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 8107092578377879894L;

  /** The division id. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", unique = true)
  private Integer id;

  /** The code. */
  @Column(name = "CODE", length = 128)
  private String code;

  /** The name. */
  @Column(name = "NAME", length = 128)
  private String name;

  /** The channel id. */
  @Column(name = "CHANNEL_ID", updatable = false, insertable = false)
  private Integer channelId;

  /** The channel. */
  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "CHANNEL_ID")
  private Channel channel;

  /** The schemes. */
  @OneToMany(mappedBy = "division", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Scheme> schemes;

  /** The centers. */
  @OneToMany(mappedBy = "division", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Center> centers;

  /**
   * Instantiates a new division.
   *
   * @param id
   *          the division id
   */
  public Division(Integer id) {
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
