/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 29, 2020     ********           hangttran.ht            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gcs.vppa.annotations.Permitted;
import com.gcs.vppa.common.base.SearchResultDTO;
import com.gcs.vppa.common.constant.Constants;
import com.gcs.vppa.common.constant.ErrorCodes;
import com.gcs.vppa.common.constant.EnumsCommon.Enum.Status;
import com.gcs.vppa.common.controller.BaseController;
import com.gcs.vppa.common.dto.ResponseTemplate;
import com.gcs.vppa.common.repository.Condition;
import com.gcs.vppa.common.repository.DataType;
import com.gcs.vppa.common.repository.Operator;
import com.gcs.vppa.common.repository.SearchCondition;
import com.gcs.vppa.common.util.CryptoUtils;
import com.gcs.vppa.core.converter.SchemeExecutorViewConverter;
import com.gcs.vppa.core.service.PermissionService;
import com.gcs.vppa.core.service.SchemeExecutorService;
import com.gcs.vppa.core.service.SchemeExecutorViewService;
import com.gcs.vppa.dto.RunSchemeDTO;
import com.gcs.vppa.dto.SchemeExecutorDTO;
import com.gcs.vppa.dto.SchemeExecutorViewDTO;
import com.gcs.vppa.entity.SchemeExecutorView;
import com.gcs.vppa.repository.SchemeExecutorViewRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author hangttran.ht
 */
@RestController
@RequestMapping("/scheme-tracking")

/** The Constant log. */
@Slf4j
public class ViewHistorySchemeController extends
    BaseController<Integer, SchemeExecutorView, SchemeExecutorViewDTO, SchemeExecutorViewRepository, SchemeExecutorViewConverter, SchemeExecutorViewService> {

  /** The SchemeExecutor service. */
  @Autowired
  private SchemeExecutorService schemeExecutorService;

  /**
   * Gets the all scheme view.
   *
   * @return the all scheme view
   */
  @GetMapping(value = "")
  public ResponseEntity<Object> getAllParam() {
    return this.getAllItems();
  }

  /**
   * Get list detail run scheme.
   *
   * @param schemeId the schemeId
   * @return the response dto
   */
  @GetMapping(value = "/detail")
  @Permitted(features = {PermissionService.READ_PROCESS})
  @ResponseBody
  public ResponseEntity<Object> getDetail(@RequestParam(required = true) String schemeId, String processId, String type) {
    if (schemeId == null || processId == null || type == null) {
      return new ResponseEntity<>(new ResponseTemplate(HttpStatus.BAD_REQUEST,
          i18nMessageService.getMessage(ErrorCodes.uc3_warn_07)), HttpStatus.BAD_REQUEST);
    }

    List<SchemeExecutorDTO> searchResult = new ArrayList<>();
    List<SchemeExecutorDTO> objectDtos = schemeExecutorService
        .findBySchemeId(CryptoUtils.decryptId(schemeId, cryptoService.getSchemeKey()));

    objectDtos.forEach(object -> {
        if (object.getProcessId().equalsIgnoreCase(processId) && object.getType().equalsIgnoreCase(type)) {
          searchResult.add(object);
        }
    });

    //Should sort from latest to oldest
    searchResult.sort((o1,o2) -> o2.getExecuteDate().compareTo(o1.getExecuteDate()));

    return new ResponseEntity<>(searchResult, HttpStatus.OK);
  }

  /**
   * Search scheme view.
   *
   * @param searchCondition
   *          the search condition
   * @return the response entity
   */
  @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
  @Permitted(features = {PermissionService.READ_PROCESS, PermissionService.WRITE_PROCESS})
  public ResponseEntity<Object> searchParam(@RequestBody SearchCondition searchCondition) {

    // add condition filter status scheme
    String statusSchemeFilter = Status.DISABLED.getValue();
    Condition conditionStatusScheme = setFilterStatusScheme(statusSchemeFilter);
    searchCondition.getConditions().add(conditionStatusScheme);

    String statusFilter = null;
    this.modifySearchCondition(statusFilter, searchCondition, new String[]{},
        condition -> null);

    SearchResultDTO<SchemeExecutorViewDTO> searchResult =
        this.dataService.findByCriteria(searchCondition);

    return new ResponseEntity<>(searchResult, HttpStatus.OK);
  }

  /**
   * Run schemes.
   *
   * @param dto the dto
   * @return the response entity
   */
  @PostMapping(value = "/run/execute-status", produces = MediaType.APPLICATION_JSON_VALUE)
  @Permitted(features = {PermissionService.RERUN_SCHEME, PermissionService.REVISE_SCHEME})
  public ResponseEntity<Object> runSchemesStatuss(@RequestBody RunSchemeDTO dto) {
    String schemeIdList = "";
    if (dto.getSelectedSchemes() != null) {
      schemeIdList = formatSchemeId(dto.getSelectedSchemes(), cryptoService.getSchemeKey());
    }

    SearchCondition searchCondition = new SearchCondition();
    searchCondition.setPage(1);
    searchCondition.setSize(20);
    searchCondition.setSortDirection(false);
    searchCondition.setSortName("id");
    List<Condition> conditions = new ArrayList<>();

    // set condition for process id
    Condition condProcess = new Condition();
    condProcess = setCondition("processId", String.valueOf(CryptoUtils.decryptId(dto.getProcessId(), cryptoService.getProcessKey())));
    conditions.add(condProcess);

    // set condition for list scheme id
    Condition condScheme = new Condition();
   
    condScheme = setConditionScheme("sid", schemeIdList);
    conditions.add(condScheme);

    searchCondition.getConditions().addAll(conditions);

    SearchResultDTO<SchemeExecutorViewDTO> searchResult =
        this.dataService.findByCriteria(searchCondition);

    return new ResponseEntity<>(searchResult, HttpStatus.OK);
  }
  
  /**
   * Set FilterStatus.
   * 
   * @return condition
   */
  private Condition setCondition(String keySearch, String value) {
    Condition condition = new Condition();

    condition.setKey(keySearch);
    condition.setValue(value);
    condition.setType(DataType.NUMBER);
    condition.setOperator(Operator.EQUAL);
    condition.setJoinType(Constants.JOIN_TYPE_INNER);

    return condition;
  }
  
  /**
   * Set FilterStatus.
   * 
   * @return condition
   */
  private Condition setConditionScheme(String keySearch, String schemeIdList) {
    Condition condition = new Condition();

    condition.setKey(keySearch);
    condition.setValue(schemeIdList);
    condition.setType(DataType.NUMBER);
    condition.setOperator(Operator.EQUAL_IN);
    condition.setJoinType(Constants.JOIN_TYPE_INNER);

    return condition;
  }
  

  /**
   * Set Filter Status scheme.
   * 
   * @return condition
   */
  private Condition setFilterStatusScheme(String statusFilter) {
    Condition filterStatus = new Condition();

    filterStatus.setKey(Constants.KEY_STATUS_SCHEME);
    filterStatus.setValue(statusFilter);
    filterStatus.setType(Constants.TYPE_STRING);
    filterStatus.setOperator(Constants.OPERATOR_NOT_EQ_IN);
    filterStatus.setJoinType(Constants.JOIN_TYPE_INNER);

    return filterStatus;
  }
}
