/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 5, 2020     ********           hangttran.ht            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.entity;

import com.gcs.vppa.common.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * The class schemetype
 * 
 * @author hangttran.ht
 *
 */
@Entity
@Table(name = "TBL_PROCESS_TYPE")

/**
 * Gets the scheme type.
 *
 * @return the scheme type
 */
@Getter

/**
 * Sets the scheme type.
 *
 * @param ProcessType the new Process Type
 */
@Setter

/**
 * Instantiates a new scheme type.
 */
@NoArgsConstructor
public class ProcessType extends BaseEntity<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 8107092578377879894L;

  /** The type id. */
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

  /** The params. */
  @OneToMany(mappedBy = "processType", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Process> processes;

  /**
   * Instantiates a new type.
   *
   * @param id
   *          the type id
   */
  public ProcessType(Integer id) {
    this.id = id;
  }

  /**
   * {@inheritDoc}
   * @see BaseEntity#getIdentifier()
   */
  @Override
  public Integer getIdentifier() {
    return this.id;
  }

}
