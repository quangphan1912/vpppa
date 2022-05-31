/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 27, 2020     ********           linhnkl.bq            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcs.vppa.annotations.Permitted;
import com.gcs.vppa.common.base.SearchResultDTO;
import com.gcs.vppa.common.constant.Constants;
import com.gcs.vppa.common.constant.ErrorCodes;
import com.gcs.vppa.common.constant.EnumsCommon.Enum.Status;
import com.gcs.vppa.common.controller.BaseController;
import com.gcs.vppa.common.dto.ResponseTemplate;
import com.gcs.vppa.common.repository.SearchCondition;
import com.gcs.vppa.common.util.CryptoUtils;
import com.gcs.vppa.common.util.MapperUtils;
import com.gcs.vppa.core.converter.VppaUserConverter;
import com.gcs.vppa.core.exception.InvalidRequestException;
import com.gcs.vppa.core.exception.LdapLoginFailException;
import com.gcs.vppa.core.exception.NotExistVppaUserException;
import com.gcs.vppa.core.service.LdapUserDataService;
import com.gcs.vppa.core.service.PermissionService;
import com.gcs.vppa.core.service.UserGroupDataService;
import com.gcs.vppa.core.service.UserRoleDataService;
import com.gcs.vppa.core.service.VppaUserDataService;
import com.gcs.vppa.dto.LdapUserDTO;
import com.gcs.vppa.dto.RequestLoginDTO;
import com.gcs.vppa.dto.VppaUserDTO;
import com.gcs.vppa.dto.VppaUserLogginedDTO;
import com.gcs.vppa.entity.VppaUser;
import com.gcs.vppa.repository.VppaUserRepository;
import com.gcs.vppa.util.AuthorizeUtils;

/**
 * @author linhnkl.bq
 *
 */
@RestController
@RequestMapping("/api/user-management")
public class VppaUserManagementController
  extends
  BaseController<Integer, VppaUser, VppaUserDTO, VppaUserRepository, VppaUserConverter, VppaUserDataService> {
  /** The create request id. */
  private static String CREATE_REQUEST_ID = "0";

  /** The Constant USER_ROLES. */
  private static final String USER_GROUPS = "userGroups";

  /** The Constant USER_ROLES. */
  private static final String USER_ROLES = "userRoles";

  /** The Constant STATUSES. */
  private static final String STATUSES = "statuses";

  /** The Constant INIT_DATA_KEY. */
  private static final String INIT_DATA_KEY = "initData";

  /** The user group data service impl. */
  @Autowired
  private LdapUserDataService ldapUserDataService;

  /** The user role data service. */
  @Autowired
  private UserRoleDataService userRoleDataService;

  /** The user role data service. */
  @Autowired
  private UserGroupDataService userGroupDataService;

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
  @Permitted(features = {PermissionService.WRITE_USER})
  public ResponseEntity<Object> insert(@RequestBody VppaUserDTO dto) {
    return this.insertItem(dto, obj -> this.validateRequest(obj, false));
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
  @Permitted(features = {PermissionService.WRITE_USER})
  public ResponseEntity<Object> update(@RequestBody VppaUserDTO dto, HttpServletRequest request) {
    if (dto != null) {
      dto.setUpdatedBy(this.getUpdatedBy(request));
    }
    
    return new ResponseEntity<>(this.dataService.update(dto), HttpStatus.OK);
  }

  /**
   * Delete an user role.
   *
   * @param id
   *          the id
   * @return the response entity
   */
  @DeleteMapping(value = "/{id}")
  @Permitted(features = {PermissionService.DELETE_USER})
  public ResponseEntity<Object> delete(@PathVariable("id") String encryptId) {
    Integer id = CryptoUtils.decryptId(encryptId, cryptoService.getVppaUserKey());
    VppaUserDTO vppaUserDTO = this.dataService.findById(id);

    if (vppaUserDTO == null) {
      return new ResponseEntity<>(new ResponseTemplate(HttpStatus.BAD_REQUEST,
        i18nMessageService.getMessage(ErrorCodes.UC_USER_ERR_DELETE_UNEXIST)), HttpStatus.BAD_REQUEST);
    }

    return this.deleteItem(CryptoUtils.decryptId(encryptId, cryptoService.getVppaUserKey()),
      i18nMessageService.getMessage(ErrorCodes.UC_USER_DELETE_SUCCESS_INFO),
      i18nMessageService.getMessage(ErrorCodes.uc1_warn_10));
  }

  /**
   * Create/edit initialize.
   *
   * @param encryptId the encrypt id
   * @return the object
   */
  @GetMapping(value = "/edit-init/{id}")
  @Permitted(features = {PermissionService.WRITE_USER})
  public Object crudInit(@PathVariable("id") String encryptId) {
    int id = 0;

    if (!CREATE_REQUEST_ID.equals(encryptId)) {
      id = this.getPlainTextId(encryptId, cryptoService.getVppaUserKey(), 0);
    }

    return this.initItemForEdit(id, this::buildCrudInitData);
  }

  /**
   * Search init.
   *
   * @return the object
   */
  @GetMapping(value = "/search-init")
  @Permitted(features = {PermissionService.READ_USER})
  public Object searchInit() {
    return new ResponseEntity<>(Map.of(STATUSES, buildStatuses()), HttpStatus.OK);
  }

  /**
   * Search param.
   *
   * @param searchCondition
   *          the search condition
   * @return the response entity
   */
  @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
  @Permitted(features = {PermissionService.READ_USER})
  public SearchResultDTO<VppaUserDTO> search(@RequestBody SearchCondition searchCondition) {
    // Default sort by id
    searchCondition.setSortName("id");

    String statusFilter = Status.INACTIVE.getValue();
    this.modifySearchCondition(statusFilter, searchCondition, new String[]{},
        condition -> null);

    return this.dataService.findByCriteria(searchCondition);
  }

  /**
   * Search param.
   *
   * @param searchCondition
   *          the search condition
   * @return the response entity
   */
  @PostMapping(value = "/search-ldap", produces = MediaType.APPLICATION_JSON_VALUE)
  @Permitted(features = {PermissionService.WRITE_USER})
  public SearchResultDTO<LdapUserDTO> searchLdap(@RequestBody SearchCondition searchCondition) {
    // modify condition search
    String statusFilter = null;
    this.modifySearchCondition(statusFilter, searchCondition, new String[]{},
        condition -> null);

    return this.ldapUserDataService.findByCriteria(searchCondition);
  }

  /**
   * Login.
   *
   * @param loginDto the login dto
   * @return the response entity
   */
  @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
  @Permitted(isPublic = true)
  public ResponseEntity<Object> login(@RequestBody RequestLoginDTO loginDto) {
    Object result = null;

    try {
      result = this.dataService.login(loginDto.getEmail(), loginDto.getPassword());
    } catch (NotExistVppaUserException ex) {
      result = new ResponseTemplate(HttpStatus.BAD_REQUEST,
        i18nMessageService.getMessage(ErrorCodes.NOT_EXIST_VPPA_USER));
    } catch (LdapLoginFailException ex) {
      result = new ResponseTemplate(HttpStatus.BAD_REQUEST,
        i18nMessageService.getMessage(ErrorCodes.LDAP_LOGIN_FAIL));
    }

    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  /**
   * Login.
   *
   * @param loginDto the login dto
   * @return the response entity
   */
  @PostMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
  @Permitted(isPublic = true)
  public ResponseEntity<Boolean> logout(@RequestHeader("Authorization") String bearerToken) {
    String token = AuthorizeUtils.getToken(bearerToken);

    if (StringUtils.isEmpty(token)) {
      throw new InvalidRequestException();
    }

    this.dataService.logout(token);
    return new ResponseEntity<>(true, HttpStatus.OK);
  }

  /**
   * Builds the create update init data.
   *
   * @return the map
   */
  private Map<String, Object> buildCrudInitData(VppaUserDTO dto) {
    Map<String, Object> result = new HashMap<>();

    if (dto != null) {
      result.putAll(MapperUtils.objectToMap(dto));
    }

    // roles
    // statuses.
    Map<String, Object> masterMap = new HashMap<>();
    masterMap.put(USER_GROUPS, this.userGroupDataService.findAll());
    masterMap.put(USER_ROLES, this.userRoleDataService.findAll());
    masterMap.put(STATUSES, buildStatuses());

    result.put(INIT_DATA_KEY, masterMap);

    return result;
  }

  /**
   * Validate request.
   *
   * @param dto the dto
   * @return the string
   */
  private String validateRequest(VppaUserDTO dto, boolean isUpdate) {
    if (dto == null) {
      return null;
    }

    if (!isUpdate) {
      if (this.dataService.findByEmail(dto.getEmail()) != null) {
        return i18nMessageService.getMessage(ErrorCodes.UC_USER_DUPLICATE_EMAIL_WARN_001, dto.getEmail());
      }
    } else {
      VppaUserDTO udpatedUser = this.dataService.findById(
        this.getPlainTextId(dto.getEncryptedId(), cryptoService.getVppaUserKey(), 0));

      if (udpatedUser == null) {
        return i18nMessageService.getMessage(ErrorCodes.UC_USER_NOT_EXIST_UPDATE_WARN_001, dto.getFullname());
      } else if (!udpatedUser.getEmail().equals(dto.getEmail())) {
        return i18nMessageService.getMessage(ErrorCodes.UC_USER_EMAIL_NOT_OWNER_UPDATE_WARN_001, dto.getEmail());
      }
    }

    return null;
  }

  /**
   * Builds the statuses.
   *
   * @return the list
   */
  private List<Map<String, String>> buildStatuses() {
    List<Map<String, String>> statuses = null;

    String[] keys = {"Active", "Inactive"};
    statuses = new ArrayList<>();

    for (String key : keys) {
      statuses.add(Map.of(key, i18nMessageService.getMessage(key)));
    }

    return statuses;
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
