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
package com.gcs.vppa.repository;

import javax.persistence.criteria.AbstractQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.gcs.vppa.common.repository.BaseRepository;
import com.gcs.vppa.entity.VppaUser;

/**
 * The Interface UserRepository.
 */
@Repository
public interface VppaUserRepository extends BaseRepository<VppaUser, Integer> {
  /**
   * Find by user name.
   *
   * @param userName the user name
   * @return the user
   */
  VppaUser findByEmail(String email);

  /**
   * Find by user name.
   *
   * @param userName the user name
   * @return the user
   */
  VppaUser findByFullname(String fullName);

  /**
   * {@inheritDoc}
   * @see com.gcs.vppa.common.repository.BaseRepository#useDistinct(javax.persistence.criteria.Root, javax.persistence.criteria.CriteriaBuilder, javax.persistence.criteria.CriteriaQuery)
   */
  @Override
  default void useDistinct(Root<VppaUser> root, CriteriaBuilder criteriaBuilder, AbstractQuery<?> criteriaQuery) {
    criteriaQuery.distinct(true);
  }
}
