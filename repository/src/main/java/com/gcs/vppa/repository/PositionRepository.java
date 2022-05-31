/**
 * 
 */
package com.gcs.vppa.repository;

import org.springframework.stereotype.Repository;

import com.gcs.vppa.common.repository.BaseRepository;
import com.gcs.vppa.entity.Position;

/**
 * @author hangttran.ht
 */
@Repository
public interface PositionRepository extends BaseRepository<Position, Integer> {

}
