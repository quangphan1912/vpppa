/*
 * (C) Copyright Global Cybersoft (GCS) 2020. All rights reserved. Proprietary and confidential.
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
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */
@Entity
@Table(name = "TBL_CENTER")

/**
 * Gets the center
 *
 * @return the center
 */
@Getter

/**
 * Sets the center.
 *
 * @param center the new center
 */
@Setter

/**
 * Instantiates a new center
 */
@NoArgsConstructor
public class Center extends BaseEntity<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 8107092578377879894L;

  /** The center id. */
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

  /** The divition id. */
  @Column(name = "DIVISION_ID", updatable = false, insertable = false)
  private Integer divitionId;

  /** The division. */
  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "DIVISION_ID")
  private Division division;

  /** The schemes. */
  @OneToMany(mappedBy = "center", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Scheme> schemes;

  /** The centers. */
  @OneToMany(mappedBy = "center", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Department> teams;

  /**
   * Instantiates a new team.
   *
   * @param id
   *          the team id
   */
  public Center(Integer id) {
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
