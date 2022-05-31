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
package com.gcs.vppa.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcs.vppa.annotations.Permitted;
import com.gcs.vppa.common.base.SearchResultDTO;
import com.gcs.vppa.common.constant.Constants;
import com.gcs.vppa.common.constant.ErrorCodes;
import com.gcs.vppa.common.controller.BaseController;
import com.gcs.vppa.common.dto.ResponseTemplate;
import com.gcs.vppa.common.repository.SearchCondition;
import com.gcs.vppa.common.util.CryptoUtils;
import com.gcs.vppa.common.util.MapperUtils;
import com.gcs.vppa.core.converter.UserRoleConverter;
import com.gcs.vppa.core.service.PermissionService;
import com.gcs.vppa.core.service.UserGroupDataService;
import com.gcs.vppa.core.service.UserRoleDataService;
import com.gcs.vppa.dto.UserRoleDTO;
import com.gcs.vppa.dto.VppaUserLogginedDTO;
import com.gcs.vppa.entity.UserRole;
import com.gcs.vppa.repository.UserRoleReponsitory;

/**
 * @author hieudinh
 *
 */

@RestController
@RequestMapping("/api/user-role")
public class UserRoleController extends
  BaseController<Integer, UserRole, UserRoleDTO, UserRoleReponsitory, UserRoleConverter, UserRoleDataService> {

  /** The user group id key. */
  private static String USER_GROUP_ID_KEY = "userGroupId";

  /** The create request id. */
  private static String CREATE_REQUEST_ID = "0";

  /** The user group data service impl. */
  @Autowired
  private UserGroupDataService userGroupDataService;

  /** The permision service. */
  @Autowired
  private PermissionService permissionService;

  /**
   * Insert user role..
   *
   * @param dto
   *          the dto
   * @return the response entity
   */
  @PostMapping(value = "",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  @Permitted(features = {PermissionService.WRITE_USERROLE})
  public ResponseEntity<Object> insert(@RequestBody UserRoleDTO dto) {
    return this.insertItem(dto, obj -> this.validateRequest(obj, 0));
  }

  /**
   * Update param.
   *
   * @param dto
   *          the dto
   * @return the response entity
   */
  @PutMapping(value = "",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  @Permitted(features = {PermissionService.WRITE_USERROLE})
  public ResponseEntity<Object> update(@RequestBody UserRoleDTO dto, HttpServletRequest request) {
    int id = this.getPlainTextId(dto.getEncryptedId(), cryptoService.getUserRoleKey(), dto.getId());
    String errorMsg = this.validateRequest(dto, id);

    if (errorMsg != null) {
      return new ResponseEntity<>(
        new ResponseTemplate(HttpStatus.BAD_REQUEST, errorMsg),
        HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(this.dataService.update(
      this.getPlainTextId(dto.getEncryptedId(), cryptoService.getUserRoleKey(), dto.getId()),
      dto, this.getUpdatedBy(request)), HttpStatus.OK);
  }

  /**
   * Delete an user role.
   *
   * @param id
   *          the id
   * @return the response entity
   */
  @DeleteMapping(value = "/{id}")
  @Permitted(features = {PermissionService.DELETE_USERROLE})
  public ResponseEntity<Object> delete(@PathVariable("id") String encryptId) {
    ResponseTemplate response = null;
    
	if (StringUtils.isEmpty(encryptId)) {
		response = new ResponseTemplate(HttpStatus.BAD_REQUEST, i18nMessageService.getMessage(ErrorCodes.UC_COMMON_DELETE_WARN_001));
	}
	else {
		// Check the user role by the give id before delete, if error return is null -> OK, otherwise return error
		String errorCode = this.dataService.checkIdAndDelete(CryptoUtils.decryptId(encryptId, cryptoService.getUserRoleKey()));
		if (errorCode == null) {
			response = new ResponseTemplate(HttpStatus.OK, i18nMessageService.getMessage(ErrorCodes.UC_USER_ROLE_INFO_001));
		}
		else {
			response = new ResponseTemplate(HttpStatus.BAD_REQUEST, i18nMessageService.getMessage(errorCode));
		}
	}
    
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Initialize user role screen.
   *
   * @param encryptId the encrypt id
   * @return the response dto
   */
  @GetMapping(value = "/edit-init/{id}")
  @Permitted(features = {PermissionService.READ_USERROLE})
  public Object cruInit(@PathVariable("id") String encryptId) {
    return this.initItemForEdit(
      !CREATE_REQUEST_ID.equals(encryptId) ? this.getPlainTextId(encryptId, cryptoService.getUserRoleKey(), 0) : 0,
      dto -> this.buildInit(false, dto));
  }

  /**
   * Search init.
   *
   * @return the object
   */
  @GetMapping(value = "/search-init")
  @Permitted(features = {PermissionService.READ_USERROLE})
  public Object searchInit() {
    return new ResponseEntity<>(this.buildInit(true, null), HttpStatus.OK);
  }

  /**
   * Builds the initialize.
   *
   * @param isSearch the is search
   * @param dto the user role dto.
   * @return the map
   */
  private Map<String, Object> buildInit(boolean isSearch, UserRoleDTO dto) {
    final String USER_GROUP_KEY = "userGroups";
    final String PERMISSION_KEY = "permission";
    final String INIT_DATA_KEY = "initData";

    Map<String, Object> result = null;

    if (dto != null) {
      result = MapperUtils.objectToMap(dto);
    } else {
      result = new HashMap<>();
    }

    Map<String, Object> initMap = new HashMap<>();
    initMap.put(USER_GROUP_KEY, userGroupDataService.findAll());

    if (!isSearch) {
      initMap.put(PERMISSION_KEY, permissionService.getAllPermission());
    } else {
      return initMap;
    }

    result.put(INIT_DATA_KEY, initMap);

    return result;
  }

  /**
   * Search param.
   *
   * @param searchCondition
   *          the search condition
   * @return the response entity
   */
  @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
  @Permitted(features = {PermissionService.READ_USERROLE})
  public SearchResultDTO<UserRoleDTO> search(@RequestBody SearchCondition searchCondition) {
    this.modifySearchCondition(null, searchCondition, null, condition -> {
      String key = condition.getKey();

      if (USER_GROUP_ID_KEY.equals(key)) {
        String value = String.valueOf(condition.getValue());

        if (StringUtils.isNotEmpty(value)) {
          return this.getPlainTextId(value, cryptoService.getUserGroupKey(), null);
        }
      }

      return null;
    });
	// Default sort by id
	searchCondition.setSortName("id");

    return this.dataService.findByCriteria(searchCondition);
  }

  /**
   * Validate request.
   *
   * @param dto the dto
   * @param id the id
   * @return the string
   */
  private String validateRequest(UserRoleDTO dto, int id) {
    if (this.isDuplicateName(dto.getName().trim(), id)) {
      return String.format(this.i18nMessageService.getMessage(ErrorCodes.UC_USER_ROLE_DUPLICATE_NAME_WARN_001),
        dto.getName());
    }
    return null;
  }

  /**
   * Checks if is duplicate name.
   *
   * @param roleName the role name
   * @param id the id
   * @return true, if is duplicate name
   */
  private boolean isDuplicateName(String roleName, int id) {
    UserRoleDTO role = this.dataService.findByName(roleName);
    
    // id == 0 => insert
    // id == role.getEncryptedId() => update.
    return role != null
        && (CryptoUtils.decryptId(role.getEncryptedId(), cryptoService.getUserRoleKey()) != id
            || id == 0);
  }

  /**
   * Gets the user loggined.
   *
   * @param request the request
   * @return the user loggined
   */
  private VppaUserLogginedDTO getUserLoggined(HttpServletRequest request) {
    Object attribute = request.getAttribute(Constants.ACCESS_USER);

    if (attribute instanceof VppaUserLogginedDTO) {
      return (VppaUserLogginedDTO) attribute;
    }

    return null;
  }

  /**
   * Gets the updated by.
   *
   * @param request the request
   * @return the updated by
   */
  private int getUpdatedBy(HttpServletRequest request) {
    VppaUserLogginedDTO userLoggined = this.getUserLoggined(request);
    return userLoggined != null ? userLoggined.getUserId() : 0;
  }
}
