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
 * The Class Vehicle.
 */
@Entity
@Table(name = "VEHICLE")

/**
 * Gets the last updated.
 *
 * @return the last updated
 */
@Getter

/**
 * Sets the last updated.
 *
 * @param lastUpdated the new last updated
 */
@Setter

/**
 * Instantiates a new vehicle.
 */
@NoArgsConstructor
public class Vehicle extends BaseEntity<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 8107092578377879894L;

  /** The vehicle id. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "VEHICLE_ID", unique = true)
  private Integer vehicleId;

  /** The name. */
  @Column(name = "NAME", length = 64)
  private String name;

  /** The register number. */
  @Column(name = "REGISTER_NUMBER", length = 64)
  private String registerNumber;

  /** The owner name. */
  @Column(name = "OWNER_NAME")
  private String ownerName;

  /** The create on. */
  @Column(name = "CREATE_ON")
  private Timestamp createOn;

  /** The last updated. */
  @Column(name = "LAST_UPDATED")
  private Timestamp lastUpdated;

  /**
   * Instantiates a new vehicle.
   *
   * @param vehicleId the vehicle id
   */
  public Vehicle(Integer vehicleId) {
    this.vehicleId = vehicleId;
  }
  
  /**
   * {@inheritDoc}
   * 
   * @see com.gcs.vppa.common.base.BaseEntity#getIdentifier()
   */
  @Override
  public Integer getIdentifier() {
    return this.vehicleId;
  }
}
