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
package com.gcs.vppa.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcs.vppa.annotations.Permitted;
import com.gcs.vppa.common.base.SearchResultDTO;
import com.gcs.vppa.common.constant.Constants;
import com.gcs.vppa.common.constant.EnumsCommon.Enum.Status;
import com.gcs.vppa.common.constant.ErrorCodes;
import com.gcs.vppa.common.controller.BaseController;
import com.gcs.vppa.common.dto.ResponseTemplate;
import com.gcs.vppa.common.dto.UserType;
import com.gcs.vppa.common.repository.SearchCondition;
import com.gcs.vppa.common.util.CollectionUtil;
import com.gcs.vppa.common.util.CryptoUtils;
import com.gcs.vppa.core.batch.ProcessExecutor;
import com.gcs.vppa.core.converter.SchemeConverter;
import com.gcs.vppa.core.service.DataSourceService;
import com.gcs.vppa.core.service.InitDataSchemeService;
import com.gcs.vppa.core.service.ParameterConditionService;
import com.gcs.vppa.core.service.ParameterDataService;
import com.gcs.vppa.core.service.PermissionService;
import com.gcs.vppa.core.service.SchemeDataService;
import com.gcs.vppa.core.service.SchemeStatusService;
import com.gcs.vppa.core.service.SchemeViewDataService;
import com.gcs.vppa.dto.DataSourceDTO;
import com.gcs.vppa.dto.MasterDataSchemeDTO;
import com.gcs.vppa.dto.ParameterDTO;
import com.gcs.vppa.dto.RunSchemeDTO;
import com.gcs.vppa.dto.SchemeDTO;
import com.gcs.vppa.dto.SchemeExecutorDTO;
import com.gcs.vppa.dto.SchemeStatusDTO;
import com.gcs.vppa.dto.SchemeViewDTO;
import com.gcs.vppa.dto.VppaUserLogginedDTO;
import com.gcs.vppa.entity.Scheme;
import com.gcs.vppa.repository.SchemeRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class SchemeController.
 */
@RestController
@RequestMapping("/scheme")

/** The Constant log. */
@Slf4j
public class SchemeController extends
  BaseController<Integer, Scheme, SchemeDTO, SchemeRepository, SchemeConverter, SchemeDataService> {

  /** The Constant RERUN. */
  private static final String RERUN = "Re-run";

  /** The Constant REVISE. */
  private static final String REVISE = "Revise";

  /** The init Data Service. */
  @Autowired
  private InitDataSchemeService initDataService;

  /** The schemeStatus service. */
  @Autowired
  private SchemeStatusService schemeStatusService;

  /** The schemeView service. */
  @Autowired
  private SchemeViewDataService schemeViewDataService;

  @Autowired
  private ParameterConditionService parameterConditionService;

  /** The Data source Service. */
  @Autowired
  private DataSourceService dataSourceService;

  /** The process Executor Service. */
  @Autowired
  private ProcessExecutor processExecutor;

  @Autowired
  private ParameterDataService parameterDataService;

  /**
   * Gets the all Scheme.
   *
   * @return the all Scheme
   */
  @GetMapping(value = "/init")
  @Permitted(features = {PermissionService.WRITE_SCHEME, PermissionService.READ_SCHEME})
  public MasterDataSchemeDTO initData() {
    return this.initDataService.getAllMasterData();
  }

  /**
   * Gets the all Scheme.
   *
   * @return the all Scheme
   */
  @GetMapping(value = "")
  @Permitted(features = {PermissionService.WRITE_SCHEME, PermissionService.READ_SCHEME})
  public ResponseEntity<Object> getAllScheme() {
    return this.getAllItems();
  }

  /**
   * Search scheme.
   *
   * @param searchCondition
   *          the search condition
   * @return the response entity
   */
  @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
  @Permitted(features = {
    PermissionService.READ_SCHEME,
    PermissionService.RERUN_SCHEME,
    PermissionService.REVISE_SCHEME,
    PermissionService.WRITE_PROCESS})
  public SearchResultDTO<SchemeViewDTO> searchScheme(
    @RequestBody SearchCondition searchCondition) {

    // modify search condition with encrypt id.
    String statusFilter = Status.DISABLED.getValue();
    this.modifySearchCondition(statusFilter, searchCondition, new String[] {"schemeId"}, condition -> {
      String key = condition.getKey();

      if (key == null) {
        return null;
      }

      if (key.equals("effectivedDate") || key.equals("actualEffectivedDate")) {
        return LocalDate.parse(String.valueOf(condition.getValue()),
          DateTimeFormatter.ofPattern("dd/MM/yyyy")).atTime(0, 0, 0).format(
            DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMAT_2));
      } else if (key.equals("expiredDate") || key.equals("actualExpiredDate")) {
        return LocalDate.parse(String.valueOf(condition.getValue()),
          DateTimeFormatter.ofPattern("dd/MM/yyyy")).atTime(23, 59, 59).format(
            DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMAT_2));
      }

      return null;
    });

    return this.schemeViewDataService.findByCriteria(searchCondition);
  }

  /**
   * Insert Scheme.
   *
   * @param dto
   *          the dto
   * @return the response entity
   */
  @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  @Permitted(features = {PermissionService.WRITE_SCHEME})
  public ResponseEntity<Object> insertScheme(@RequestBody SchemeDTO dto, HttpServletRequest request) {
    if (dto == null) {
      return new ResponseEntity<>(new ResponseTemplate(HttpStatus.BAD_REQUEST,
        i18nMessageService.getMessage(ErrorCodes.uc3_warn_07)), HttpStatus.BAD_REQUEST);
    }

    VppaUserLogginedDTO userLoggined = this.getUserLoggined(request);
    if (userLoggined == null || userLoggined.getUser() == null) {
      return new ResponseEntity<>(new ResponseTemplate(HttpStatus.BAD_REQUEST,
        i18nMessageService.getMessage(ErrorCodes.UNAUTHORIZED)), HttpStatus.BAD_REQUEST);
    }

    dto.setCreatedBy(userLoggined.getUser().getFullname());

    // insert datasource
    ResponseEntity<Object> responseInsert = insertDataSource(dto);
    if (responseInsert != null) {
      return responseInsert;
    }

    if (!CollectionUtil.isNullOrEmpty(dto.getParameters())) {
      dto.getParameters().forEach(parameterDTO -> {
        parameterDTO.setParameterConditions(parameterConditionService
          .findByParameterId(CryptoUtils.decryptId(parameterDTO.getEncryptedId(), cryptoService.getParameterKey())));
        parameterDTO.setSource(parameterDataService
          .findById(CryptoUtils.decryptId(parameterDTO.getEncryptedId(), cryptoService.getParameterKey())).getSource());
      });
    }

    // insert scheme
    SchemeDTO schemeDto = this.dataService.insert(dto);

    ResponseTemplate response = new ResponseTemplate(HttpStatus.OK,
      i18nMessageService.getMessage(ErrorCodes.uc1_info_002));

    // insert for scheme_status
    SchemeStatusDTO schemeStatus = new SchemeStatusDTO(schemeDto.getEncryptedId(), dto.getStatus(),
      dto.getStatusDate(), userLoggined.getUser().getFullname());
    schemeStatusService.insert(schemeStatus);
    log.debug("update schemeStatus - The schemeStatus created successfully.!");

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Init for Update Scheme.
   *
   * @param encryptId
   *          the id
   * @return the response dto
   */
  @GetMapping(value = "/{id}")
  @Permitted(features = {PermissionService.WRITE_SCHEME, PermissionService.READ_SCHEME})
  public Object initScheme(@PathVariable("id") String encryptId) {
    Integer id = CryptoUtils.decryptId(encryptId, cryptoService.getSchemeKey());
    SchemeDTO schemeDto = this.dataService.findById(id);

    if (schemeDto == null) {
      log.debug("The action is invalid, ID=[{}] does not exist!", id);
      return new ResponseEntity<>(new ResponseTemplate(HttpStatus.BAD_REQUEST,
        i18nMessageService.getMessage(ErrorCodes.uc1_warn_017)), HttpStatus.BAD_REQUEST);
    }

    return schemeDto;
  }

  /**
   * Update Scheme.
   *
   * @param dto
   *          the dto
   * @return the response entity
   */
  @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Permitted(features = {PermissionService.WRITE_SCHEME})
  public ResponseEntity<Object> updateScheme(@RequestBody SchemeDTO dto, HttpServletRequest request) {
    if (dto == null) {
      return new ResponseEntity<>(new ResponseTemplate(HttpStatus.BAD_REQUEST,
        i18nMessageService.getMessage(ErrorCodes.uc3_warn_07)), HttpStatus.BAD_REQUEST);
    }

    VppaUserLogginedDTO userLoggined = this.getUserLoggined(request);
    if (userLoggined == null || userLoggined.getUser() == null) {
      return new ResponseEntity<>(new ResponseTemplate(HttpStatus.BAD_REQUEST,
        i18nMessageService.getMessage(ErrorCodes.UNAUTHORIZED)), HttpStatus.BAD_REQUEST);
    }

    dto.setUpdatedBy(userLoggined.getUser().getFullname());

    // check exist scheme
    Integer schemeId = 0;
    if (dto.getEncryptedId() != null) {
      schemeId = CryptoUtils.decryptId(dto.getEncryptedId(), cryptoService.getSchemeKey());
    } else {
      schemeId = dto.getId();
    }

    SchemeDTO schemeObject = this.dataService.findById(schemeId);

    ResponseEntity<Object> returnObject = validateUpdateScheme(schemeObject, dto);

    if (returnObject != null) {
      return returnObject;
    }

    if (!CollectionUtil.isNullOrEmpty(dto.getParameters())) {
      dto.getParameters().forEach(parameterDTO -> {
        parameterDTO.setParameterConditions(parameterConditionService
          .findByParameterId(CryptoUtils.decryptId(parameterDTO.getEncryptedId(), cryptoService.getParameterKey())));
        parameterDTO.setSource(parameterDataService
          .findById(CryptoUtils.decryptId(parameterDTO.getEncryptedId(), cryptoService.getParameterKey())).getSource());
      });
    }

    // update for scheme_status
    if (!schemeObject.getStatus().equalsIgnoreCase(dto.getStatus())) {
      SchemeStatusDTO schemeStatus = new SchemeStatusDTO(dto.getEncryptedId(), dto.getStatus(),
          dto.getStatusDate(), userLoggined.getUser().getFullname());
      schemeStatusService.insert(schemeStatus);
      log.debug("update schemeStatus - The schemeStatus created successfully.!");
    }

    // update data source
    ResponseEntity<Object> responseUpdate = updateDataSource(dto, schemeObject);
    if (responseUpdate != null) {
      return responseUpdate;
    }

    return this.updateItem(schemeId, dto, null);
  }

  /**
   * Delete Scheme.
   *
   * @param encryptId
   *          the id
   * @return the response entity
   */
  @DeleteMapping(value = "/{id}")
  @Permitted(features = {PermissionService.DELETE_SCHEME})
  public ResponseEntity<Object> disableScheme(@PathVariable("id") String encryptId, HttpServletRequest request) {
    Integer id = CryptoUtils.decryptId(encryptId, cryptoService.getSchemeKey());
    SchemeDTO schemeObject = this.dataService.findById(id);

    if (schemeObject == null) {
      log.debug("The action is invalid, ID=[{}] does not exist!", id);
      return new ResponseEntity<>(new ResponseTemplate(HttpStatus.BAD_REQUEST,
        i18nMessageService.getMessage(ErrorCodes.uc1_warn_06)), HttpStatus.BAD_REQUEST);
    }

    VppaUserLogginedDTO userLoggined = this.getUserLoggined(request);
    if (userLoggined == null || userLoggined.getUser() == null) {
      return new ResponseEntity<>(new ResponseTemplate(HttpStatus.BAD_REQUEST,
        i18nMessageService.getMessage(ErrorCodes.UNAUTHORIZED)), HttpStatus.BAD_REQUEST);
    }

    schemeObject.setUpdatedBy(userLoggined.getUser().getFullname());

    // disable scheme
    schemeObject.setStatus(Status.DISABLED.getValue());

    // update history scheme status
    SchemeStatusDTO schemeStatus = new SchemeStatusDTO(encryptId, schemeObject.getStatus(),
      LocalDateTime.now().toString(), userLoggined.getUser().getFullname());
    schemeStatusService.insert(schemeStatus);
    log.debug("update schemeStatus - The schemeStatus created successfully.!");

    // update scheme
    this.dataService.update(id, schemeObject);
    ResponseTemplate response = new ResponseTemplate(HttpStatus.OK,
      i18nMessageService.getMessage(ErrorCodes.uc3_info_001));

    return new ResponseEntity<>(response, HttpStatus.OK);

  }

  /**
   * Init for Update Scheme.
   *
   * @param encryptId
   *          the id
   * @return the response dto
   */
  @GetMapping(value = "/parameter-list/{id}")
  @Permitted(features = {PermissionService.WRITE_SCHEME, PermissionService.READ_SCHEME})
  public List<ParameterDTO> initParameterOfScheme(@PathVariable("id") String encryptId) {
    return this.initDataService
      .getListParameter(CryptoUtils.decryptId(encryptId, cryptoService.getSchemeKey()));
  }

  /**
   * Insert Data Source for scheme.
   * 
   * @param dto
   * @return
   */
  private ResponseEntity<Object> insertDataSource(SchemeDTO dto) {
    DataSourceDTO dataSourceDto = null;
    // check used source
    if (dto.getSource() != null && !dto.getSource().trim().isEmpty()
      && this.dataService.findBySource(dto.getSource()) != null) {
      log.debug("The data source file is already in use. Please update the new source name!");
      return new ResponseEntity<>(new ResponseTemplate(HttpStatus.BAD_REQUEST,
        i18nMessageService.getMessage(ErrorCodes.uc1_warn_11)), HttpStatus.BAD_REQUEST);
    }

    // insert data source
    if (dto.getDataSource() != null && !dto.getDataSource().trim().isEmpty()) {
      dataSourceDto = new DataSourceDTO(dto.getSource(), dto.getDataSource());
      // check exist source in table data-source
      DataSourceDTO dataSourceDB = dataSourceService.findBySource(dto.getSource());
      if (dataSourceDB != null) {
        dataSourceService.update(dataSourceDB.getId(), dataSourceDto);
        log.debug("updateDataSource - The data source updated successfully.!");
      } else {
        dataSourceService.insert(dataSourceDto);
        log.debug("insertDataSource - The data source created successfully.!");
      }

    }

    return null;
  }

  /**
   * Update data source.
   * 
   * @param dto
   * @param schemeObject
   * @return
   */
  private ResponseEntity<Object> updateDataSource(SchemeDTO dto, SchemeDTO schemeObject) {
    // check used source
    if (dto.getSource() != null && !dto.getSource().trim().isEmpty()
      && !dto.getSource().equalsIgnoreCase(schemeObject.getSource())) {
      SchemeDTO schemeSource = this.dataService.findBySource(dto.getSource());
      if (schemeSource != null) {
        log.debug("The data source file is already in use. Please update the new source name!");
        return new ResponseEntity<>(new ResponseTemplate(HttpStatus.BAD_REQUEST,
          i18nMessageService.getMessage(ErrorCodes.uc1_warn_11)),
          HttpStatus.BAD_REQUEST);
      }

      // update
      DataSourceDTO dataSourceDto = null;
      if (dto.getDataSource() != null && !dto.getDataSource().trim().isEmpty()) {
        dataSourceDto = new DataSourceDTO(dto.getSource(), dto.getDataSource());
        DataSourceDTO dsObjectDB = dataSourceService.findBySource(dto.getSource());

        if (dsObjectDB == null) {
          dataSourceService.insert(dataSourceDto);
          log.debug("insertDataSource - The data source created successfully.!");
          dto.setSource(dataSourceDto.getSource());

        } else {
          dataSourceDto = new DataSourceDTO(dto.getSource(), dto.getDataSource());
          dataSourceService.update(dsObjectDB.getId(), dataSourceDto);
          log.debug("updateDataSource - The data source updated successfully.!");
        }

      }
    }

    return null;
  }

  /**
   * Validate Update.
   * 
   * @return
   */
  private ResponseEntity<Object> validateUpdateScheme(SchemeDTO schemeObject, SchemeDTO dto) {
    // check exist scheme
    if (schemeObject == null) {
      log.debug("The action is invalid, ID Scheme=[{}] does not exist!", dto.getIdScheme());
      return new ResponseEntity<>(new ResponseTemplate(HttpStatus.BAD_REQUEST,
        i18nMessageService.getMessage(ErrorCodes.uc1_warn_017)), HttpStatus.BAD_REQUEST);
    }

    // check status "Draft", only change to "Active"
    if (schemeObject.getStatus().equalsIgnoreCase(Status.DRAFT.getValue())) {
      if (!dto.getStatus().equalsIgnoreCase(Status.DRAFT.getValue())
        && !dto.getStatus().equalsIgnoreCase(Status.ACTIVE.getValue())) {
        log.warn("The action is invalid, only change it status from Draft to Active!");
        return new ResponseEntity<>(
          new ResponseTemplate(HttpStatus.BAD_REQUEST,
            i18nMessageService.getMessage(ErrorCodes.uc1_warn_07)),
          HttpStatus.BAD_REQUEST);
      }
    }

    // check status of a scheme is "Active"
    if (schemeObject.getStatus().equalsIgnoreCase(Status.ACTIVE.getValue())) {
      /*if (!dto.getIdScheme().equalsIgnoreCase(schemeObject.getIdScheme())) {
        log.warn("The action is invalid, can NOT change the ID Scheme!");
        return new ResponseEntity<>(
          new ResponseTemplate(HttpStatus.BAD_REQUEST,
            i18nMessageService.getMessage(ErrorCodes.uc1_warn_018)),
          HttpStatus.BAD_REQUEST);
      }*/

      if (!dto.getStatus().equalsIgnoreCase(Status.ACTIVE.getValue())
        && !dto.getStatus().equalsIgnoreCase(Status.DISABLED.getValue())) {
        log.warn("The action is invalid, can only change its status from Active to Disable!");
        return new ResponseEntity<>(
          new ResponseTemplate(HttpStatus.BAD_REQUEST,
            i18nMessageService.getMessage(ErrorCodes.uc1_warn_019)),
          HttpStatus.BAD_REQUEST);
      }
    }

    // check status of a scheme is "DISABLED"
    if (schemeObject.getStatus().equalsIgnoreCase(Status.DISABLED.getValue())) {
      log.warn("The action is invalid, you do not  have permission on this feature!");
      return new ResponseEntity<>(new ResponseTemplate(HttpStatus.BAD_REQUEST,
        i18nMessageService.getMessage(ErrorCodes.uc1_warn_015)), HttpStatus.BAD_REQUEST);
    }

    return null;
  }

  /**
   * Gets the all Scheme.
   *
   * @return the all Scheme
   */
  @GetMapping(value = "/run/init/{division-id}")
  @Permitted(features = {PermissionService.RERUN_SCHEME, PermissionService.REVISE_SCHEME})
  public ResponseEntity<Object> initRunScheme(@PathVariable("division-id") String divisionId) {
    final String DivisionIdEmpty = "0";
    final String SchemeTypes = "schemeTypes";
    final String DivisionBeneficiaries = "divisionBeneficiaries";
    final String DenterBeneficiaries = "centerBeneficiaries";

    Map<String, Object> result = new HashMap<>();

    if (StringUtils.isEmpty(divisionId) || DivisionIdEmpty.equals(divisionId)) {
      // first request.
      result.put(SchemeTypes, this.initDataService.getDataSchemeType());
      result.put(DivisionBeneficiaries, this.initDataService.getDataDivision());
    } else {
      // after the user selected a division.
      result.put(DenterBeneficiaries,
        this.initDataService.getDataCenter(
          CryptoUtils.decryptId(divisionId, cryptoService.getDivisionKey())));
    }

    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  /**
   * Run schemes.
   *
   * @param dto the dto
   * @return the response entity
   */
  @PostMapping(value = "/run/execute")
  @Permitted(features = {PermissionService.RERUN_SCHEME, PermissionService.REVISE_SCHEME})
  public ResponseEntity<Object> runSchemes(@RequestBody RunSchemeDTO dto, HttpServletRequest request) {
    if (dto.getProcessId() == null) {
      dto.setProcessId("2f636639567a6754362f6f61726a51676d674b4167773d3d");
    }

    VppaUserLogginedDTO userLoggined = this.getUserLoggined(request);
    if (userLoggined == null || userLoggined.getUser() == null) {
      return new ResponseEntity<>(new ResponseTemplate(HttpStatus.BAD_REQUEST,
        i18nMessageService.getMessage(ErrorCodes.UNAUTHORIZED)), HttpStatus.BAD_REQUEST);
    }

    if (userLoggined.getUser().getUserType() != UserType.ADMIN
      && !this.hasRerunRevisePermission(
        userLoggined.getUser().getUserRole().getPermission(), dto.getRunType())) {
    }

    List<String> schemeIdList = new ArrayList<>(dto.getSelectedSchemes().length);
    if (dto.getSelectedSchemes() != null) {
      schemeIdList = getListSchemeId(dto.getSelectedSchemes(), cryptoService.getSchemeKey());
    }

    Integer processId = CryptoUtils.decryptId(dto.getProcessId(), cryptoService.getProcessKey());

    SchemeExecutorDTO schemeExecutor = new SchemeExecutorDTO();
    schemeExecutor.setType(dto.getRunType());
    schemeExecutor.setExecuteBy(userLoggined.getUser().getFullname());
    schemeExecutor.setProcessId(processId.toString());

    if (dto.getReviseMonth() != null) {
      schemeExecutor.setExecuteMonth(dto.getReviseMonth());
    }

    processExecutor.scheduleCamundaProcess(schemeExecutor, schemeIdList);
    return new ResponseEntity<>(true, HttpStatus.OK);
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
   * Checks for rerun revise permission.
   *
   * @param permission the permission
   * @param runType the run type
   * @return true, if successful
   */
  private boolean hasRerunRevisePermission(int[] permission, String runType) {
    if (permission == null || permission.length == 0) {
      return false;
    }

    if (RERUN.equals(runType)) {
      return Arrays.stream(permission).anyMatch(i -> i == PermissionService.RERUN_SCHEME);
    } else if (REVISE.equals(runType)) {
      return Arrays.stream(permission).anyMatch(i -> i == PermissionService.REVISE_SCHEME);
    }

    return false;
  }
}
