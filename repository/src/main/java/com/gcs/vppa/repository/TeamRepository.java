/**
 * 
 */
package com.gcs.vppa.repository;

import org.springframework.stereotype.Repository;

import com.gcs.vppa.common.repository.BaseRepository;
import com.gcs.vppa.entity.Department;

/**
 * @author hangttran.ht
 *
 */
@Repository
public interface TeamRepository extends BaseRepository<Department, Integer> {

}
