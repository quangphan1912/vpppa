/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 26, 2020     ********           linhnkl.bq            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.core.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.gcs.vppa.common.constant.ErrorCodes;
import com.gcs.vppa.common.constant.Constants;
import com.gcs.vppa.common.service.BaseDataServiceImpl;
import com.gcs.vppa.common.util.DateTimeUtil;
import com.gcs.vppa.core.converter.UserRoleConverter;
import com.gcs.vppa.dto.UserRoleDTO;
import com.gcs.vppa.entity.UserRole;
import com.gcs.vppa.entity.VppaUser;
import com.gcs.vppa.entity.VppaUserLoggined;
import com.gcs.vppa.repository.UserRoleReponsitory;
import com.gcs.vppa.repository.VppaUserRepository;
import com.gcs.vppa.repository.VppaUserLogginedRepository;

import lombok.NoArgsConstructor;

/**
 * @author linhnkl.bq
 *
 */
@NoArgsConstructor
@Service
@ComponentScan
public class UserRoleDataServiceImpl
  extends BaseDataServiceImpl<Integer, UserRole, UserRoleDTO, UserRoleReponsitory, UserRoleConverter>
  implements UserRoleDataService {
	
  @Autowired
  VppaUserRepository vppaUserRepository;

  /** The vppa user loggined repository. */
  @Autowired
  private VppaUserLogginedRepository vppaUserLogginedRepository;

  /**
   * Gets the entity name.
   *
   * @return the entity name
   */
  @Override
  public String getEntityName() {
    return UserRole.class.getSimpleName();
  }

  /**
   * Find by name.
   *
   * @param name the name
   * @return the user role DTO
   */
  @Override
  public UserRoleDTO findByName(String name) {
    return this.converter.toDto(this.repository.findByName(name));
  }

  /**
   * Find by user group.
   *
   * @param userRole the user role
   * @return the user role DTO
   */
  @Override
  public UserRoleDTO findByUserGroup(String userRole) {
    return null;
  }
  
  public String checkIdAndDelete(Integer id) {
	  if (!this.repository.existsById(id)) {
		  return ErrorCodes.UC_USER_ROLE_ERR_DELETE_UNEXIST;
	  }
	  
	  if (this.vppaUserRepository.findAll().stream().anyMatch(x -> x.getUserRoleId().equals(id))) {
		  return ErrorCodes.UC_USER_ROLE_ERR_DELETE_USED_BY_USER;
	  }
	  
	  this.repository.deleteById(id);
	  return null;
  }


  /**
   * Update.
   *
   * @param id the id
   * @param dto the dto
   * @param updatedBy the updated by
   * @return the user role DTO
   */
  @Override
  public UserRoleDTO update(Integer id, UserRoleDTO dto, Integer updatedBy) {
    // update user token related to this user role.
    UserRole oldEntity = this.repository.findByIdentifier(id);

    if (!Arrays.equals(oldEntity.getPermission(), dto.getPermission())) {
      // update user login.
      this.updateUserLoggined(oldEntity, updatedBy);
    }

    return super.update(id, dto);
  }

  /**
   * Update user loggined.
   *
   * @param entity the entity
   * @param updatedBy the updated by
   */
  @Override
  public void updateUserLoggined(UserRole entity, Integer updatedBy) {
    if (entity.getUsers() != null) {
      for (VppaUser vppaUserEntity : entity.getUsers()) {
        if (Constants.ADMIN_TYPE != vppaUserEntity.getUserType()) {
          this.updateUserLogginedByUser(vppaUserEntity.getUserLoggineds(), updatedBy);
        }
      }
    }
  }

  /**
   * Update user loggined by user.
   *
   * @param userLoggineds the user loggineds
   * @param updatedBy the updated by
   */
  @Override
  public void updateUserLogginedByUser(List<VppaUserLoggined> userLoggineds, Integer updatedBy) {
    if (userLoggineds == null) {
      return;
    }

    for (VppaUserLoggined item : userLoggineds) {
      item.setExpired(true);
      item.setUpdatedDate(DateTimeUtil.toTimestamp(LocalDateTime.now()));
      item.setUpdatedBy(updatedBy);
    }

    vppaUserLogginedRepository.saveAll(userLoggineds);
  }
}
