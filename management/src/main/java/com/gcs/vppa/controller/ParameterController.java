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

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gcs.vppa.annotations.Permitted;
import com.gcs.vppa.common.base.SearchResultDTO;
import com.gcs.vppa.common.constant.EnumsCommon.Enum.Status;
import com.gcs.vppa.common.constant.ErrorCodes;
import com.gcs.vppa.common.controller.BaseController;
import com.gcs.vppa.common.dto.ResponseTemplate;
import com.gcs.vppa.common.repository.SearchCondition;
import com.gcs.vppa.common.service.CryptoService;
import com.gcs.vppa.common.service.I18nMessageService;
import com.gcs.vppa.common.util.CollectionUtil;
import com.gcs.vppa.common.util.CryptoUtils;
import com.gcs.vppa.core.converter.ParameterConverter;
import com.gcs.vppa.core.service.DataSourceService;
import com.gcs.vppa.core.service.InitDataParamService;
import com.gcs.vppa.core.service.ParameterConditionService;
import com.gcs.vppa.core.service.ParameterDataService;
import com.gcs.vppa.core.service.ParameterViewService;
import com.gcs.vppa.core.service.PermissionService;
import com.gcs.vppa.dto.DataSourceDTO;
import com.gcs.vppa.dto.MasterDataParameterDTO;
import com.gcs.vppa.dto.ParameterConditionDTO;
import com.gcs.vppa.dto.ParameterDTO;
import com.gcs.vppa.dto.ParameterViewDTO;
import com.gcs.vppa.dto.SchemeDTO;
import com.gcs.vppa.entity.Parameter;
import com.gcs.vppa.repository.ParameterRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class ParameterController.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */
@RestController
@RequestMapping("/params")

/** The Constant log. */

/** The Constant log. */
@Slf4j
public class ParameterController extends
    BaseController<Integer, Parameter, ParameterDTO, ParameterRepository, ParameterConverter, ParameterDataService> {

  /** The i 18 n message service. */
  @Autowired
  private I18nMessageService i18nMessageService;

  /** The crypt service. */
  @Autowired
  private CryptoService cryptoService;

  /** The init Data Service. */
  @Autowired
  private InitDataParamService initDataService;

  /** The Data source Service. */
  @Autowired
  private DataSourceService dataSourceService;

  /** The search parameter Service. */
  @Autowired
  private ParameterViewService parameterViewService;

  /** The ParameterCondition service. */
  @Autowired
  private ParameterConditionService parameterConditionService;

  /**
   * Gets the all Params.
   *
   * @return the all Params
   */
  @GetMapping(value = "/init")
  @Permitted(features = {PermissionService.WRITE_PARAMETER, PermissionService.READ_PARAMETER})
  public MasterDataParameterDTO initData() {
    return this.initDataService.getAllMasterData();

  }

  /**
   * Gets the all Params.
   *
   * @return the all Params
   */
  @GetMapping(value = "")
  @Permitted(features = {PermissionService.READ_PARAMETER, PermissionService.WRITE_PARAMETER})
  public ResponseEntity<Object> getAllParam() {
    return this.getAllItems();
  }

  /**
   * Search param.
   *
   * @param searchCondition
   *          the search condition
   * @return the response entity
   */
  @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
  @Permitted(features = {PermissionService.READ_PARAMETER, PermissionService.WRITE_SCHEME})
  public SearchResultDTO<ParameterViewDTO> searchParam(
      @RequestBody SearchCondition searchCondition) {

    String statusFilter = Status.INACTIVE.getValue();
    this.modifySearchCondition(statusFilter, searchCondition, new String[]{"schemeId" },
        condition -> {
          String key = condition.getKey().trim();

          if (key != null && key.contains("ignoreParameterIds")) {
            String strVal = condition.getValue().toString().trim();

            String decryptedKey = cryptoService.getParameterKey();

            // update condition key.
            condition.setKey("id");
            if (strVal.length() >= 2) {
              if (strVal.charAt(0) == '[' && strVal.charAt(strVal.length() - 1) == ']') {
                return this.getArrayValues(strVal, decryptedKey);
              } else {
                return String.valueOf(CryptoUtils.decryptId(strVal, decryptedKey));
              }
            }
          }

          return null;
        });
    return this.parameterViewService.findByCriteria(searchCondition);
  }

  /**
   * Insert Param.
   *
   * @param dto
   *          the dto
   * @return the response entity
   */
  @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Permitted(features = {PermissionService.WRITE_PARAMETER})
  public ResponseEntity<Object> insertParam(@RequestBody ParameterDTO dto) {
    if (dto == null) {
      return new ResponseEntity<>(new ResponseTemplate(HttpStatus.BAD_REQUEST,
          i18nMessageService.getMessage(ErrorCodes.uc3_warn_07)), HttpStatus.BAD_REQUEST);
    }

    if (this.dataService.findByParamName(dto.getParamName().trim()) != null) {
      log.warn("insertParam - The parameter name has existed in the system!");
      return new ResponseEntity<>(new ResponseTemplate(HttpStatus.BAD_REQUEST,
          i18nMessageService.getMessage(ErrorCodes.uc3_err_003)), HttpStatus.BAD_REQUEST);
    }

    // insert datasource
    ResponseEntity<Object> responseInsert = insertDataSource(dto);
    if (responseInsert != null) {
      return responseInsert;
    }

    // get list parameterConditions
    List<ParameterConditionDTO> conditionDtos = dto.getParameterConditions();
    // insert parameter
    dto.setParameterConditions(null);
    this.insertItemDB(dto);
    ResponseTemplate response = new ResponseTemplate(HttpStatus.OK,
        i18nMessageService.getMessage(ErrorCodes.uc1_info_001));

    // insert param condition.
    ParameterDTO paramDto = this.dataService.findByParamName(dto.getParamName());
    if (CollectionUtils.isNotEmpty(conditionDtos) && conditionDtos.size() >= 1) {

      for (ParameterConditionDTO conditionDto : conditionDtos) {
        conditionDto.setParameterIdEncrypt(paramDto.getEncryptedId());
        conditionDto.setEncryptedId(null);
        parameterConditionService.insert(conditionDto);
        log.debug("insertParameterCondition - The parameter condition created successfully.!");
      }

    }

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Init for Update param.
   *
   * @param encryptId
   *          the id
   * @return the response dto
   */
  @GetMapping(value = "/{id}")
  @Permitted(features = {PermissionService.WRITE_PARAMETER, PermissionService.READ_PARAMETER})
  public Object initParam(@PathVariable("id") String encryptId) {
    Integer id = CryptoUtils.decryptId(encryptId, cryptoService.getParameterKey());
    ParameterDTO paramDto = this.dataService.findById(id);

    if (paramDto == null) {
      log.debug("The action is invalid, ID=[{}] does not exist!", id);
      return new ResponseEntity<>(new ResponseTemplate(HttpStatus.BAD_REQUEST,
          i18nMessageService.getMessage(ErrorCodes.uc1_warn_06)), HttpStatus.BAD_REQUEST);
    }

    return paramDto;
  }

  /**
   * Update param.
   *
   * @param dto
   *          the dto
   * @return the response entity
   */
  @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Permitted(features = {PermissionService.WRITE_PARAMETER})
  public ResponseEntity<Object> updateParam(@RequestBody ParameterDTO dto) {

    if (dto == null) {
      return new ResponseEntity<>(new ResponseTemplate(HttpStatus.BAD_REQUEST,
          i18nMessageService.getMessage(ErrorCodes.uc3_warn_07)), HttpStatus.BAD_REQUEST);
    }

    Integer paramId = 0;
    if (dto.getEncryptedId() != null) {
      paramId = CryptoUtils.decryptId(dto.getEncryptedId(), cryptoService.getParameterKey());
    } else {
      paramId = dto.getId();
    }

    // update param condition
    ResponseEntity<Object> respCondition = updateParameterCondition(dto, paramId);
    if (respCondition != null) {
      return respCondition;
    }

    // update data source
    ResponseEntity<Object> respDataSource = updateDataSource(dto, paramId);
    if (respDataSource != null) {
      return respDataSource;
    }

    dto.setParameterConditions(null);
    return this.updateItem(paramId, dto, null);
  }

  /**
   * Delete param.
   *
   * @param id
   *          the id
   * @return the response entity
   */
  @DeleteMapping(value = "/{id}")
  @Permitted(features = {PermissionService.DELETE_PARAMETER})
  public ResponseEntity<Object> deleteParam(@PathVariable("id") String encryptId) {
    Integer id = CryptoUtils.decryptId(encryptId, cryptoService.getParameterKey());
    ParameterDTO paramObject = this.dataService.findById(id);

    if (paramObject == null) {
      log.debug("The action is invalid, ID=[{}] does not exist!", id);
      return new ResponseEntity<>(new ResponseTemplate(HttpStatus.BAD_REQUEST,
          i18nMessageService.getMessage(ErrorCodes.uc1_warn_06)), HttpStatus.BAD_REQUEST);
    }

    // check param applied in schemes
    if (paramObject.getSchemes().size() >= 1) {
      log.debug("You cannot delete the Parameter because it is being used in the scheme(s)");
      return new ResponseEntity<>(new ResponseTemplate(HttpStatus.BAD_REQUEST,
          i18nMessageService.getMessage(ErrorCodes.uc4_err_004)), HttpStatus.BAD_REQUEST);
    }

    // delete param
    try {
      this.dataService.delete(id);
      ResponseTemplate response = new ResponseTemplate(HttpStatus.OK,
          i18nMessageService.getMessage(ErrorCodes.uc4_info_001));

      // delete data source
      if (paramObject.getSource() != null && !paramObject.getSource().trim().isEmpty()) {
        DataSourceDTO dataSourceObject =
            dataSourceService.findBySource(paramObject.getSource());

        if (dataSourceObject != null) {
          this.dataSourceService.delete(dataSourceObject.getId());
        }

      }

      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      ResponseTemplate response = new ResponseTemplate(HttpStatus.NOT_IMPLEMENTED,
          i18nMessageService.getMessage(ErrorCodes.uc1_warn_10));

      return new ResponseEntity<>(response, HttpStatus.OK);
    }

  }

  /**
   * Validate ParamCondition.
   *
   * @param dto
   *          the dto
   * @return the response entity
   */
  @SuppressWarnings("null")
  @GetMapping(value = "/add-condition")
  @ResponseBody
  @Permitted(features = {PermissionService.WRITE_PARAMETER})
  public Object validateParamCondition(@RequestParam(required = true) String name, String parameterIdEncrypt) {
    Object ob = null;
    List<ParameterConditionDTO> conditionExist = parameterConditionService.findByName(name.trim());

    if ((parameterIdEncrypt != null && !parameterIdEncrypt.trim().isEmpty())
        && !CollectionUtil.isNullOrEmpty(conditionExist)) {

      for (ParameterConditionDTO conditionDto : conditionExist) {

        if (parameterIdEncrypt.equalsIgnoreCase(conditionDto.getParameterIdEncrypt())) {
          return new ResponseEntity<>(conditionDto, HttpStatus.OK);
        }
      }
    }

    return new ResponseEntity<>(ob, HttpStatus.OK);
  }

  /**
   * Update Data Source.
   * 
   * @param dto
   * @param paramId
   * @return
   */
  private ResponseEntity<Object> updateDataSource(ParameterDTO dto, Integer paramId) {
    ResponseEntity<Object> responseUpdate = null;
    // check exist param
    ParameterDTO paramObject = this.dataService.findById(paramId);
    ResponseEntity<Object> returnValidate = validateUpdateParameter(paramObject, dto);

    if (returnValidate != null) {
      return returnValidate;
    }

    // check used source
    if (dto.getSource() != null && !dto.getSource().trim().isEmpty()
        && !dto.getSource().equalsIgnoreCase(paramObject.getSource())) {
      ParameterDTO paramSource = this.dataService.findBySource(dto.getSource());
      if (paramSource != null) {
        log.debug(
            "The data source file is already in use. Please update the new source name!");
        responseUpdate = new ResponseEntity<>(
            new ResponseTemplate(HttpStatus.BAD_REQUEST,
                i18nMessageService.getMessage(ErrorCodes.uc1_warn_11)),
            HttpStatus.BAD_REQUEST);
      }

      updateDataSource(dto);
    }

    return responseUpdate;

  }

  /**
   * Update Parameter Condition.
   * 
   * @param dto
   * @param paramId
   * @return
   */
  private ResponseEntity<Object> updateParameterCondition(ParameterDTO dto, Integer paramId) {
    List<ParameterConditionDTO> conditionDtos = dto.getParameterConditions();
    List<ParameterConditionDTO> conditionObjects =
        parameterConditionService.findByParameterId(paramId);

    // update
    if (conditionDtos.size() >= 1) {
      for (ParameterConditionDTO conditionDto : conditionDtos) {
        boolean isExisted = false;

        // check exist -> update
        for (ParameterConditionDTO object : conditionObjects) {
          if (conditionDto.getName().equalsIgnoreCase(object.getName())
              && conditionDto.getEncryptedId() != null
              && conditionDto.getEncryptedId().equalsIgnoreCase(object.getEncryptedId())) {
            Integer id = CryptoUtils.decryptId(conditionDto.getEncryptedId(), cryptoService.getParameterConditionKey());
            conditionDto.setParameterIdEncrypt(dto.getEncryptedId());
            parameterConditionService.update(id, conditionDto);
            isExisted = true;
            break;
          }
        }

        if (!isExisted) {
          List<ParameterConditionDTO> conditionExist = parameterConditionService.findByName(conditionDto.getName().trim());
          if (!CollectionUtil.isNullOrEmpty(conditionExist)) {
           for (ParameterConditionDTO conDto: conditionExist) {
             if (conDto.getParameterIdEncrypt().equalsIgnoreCase(dto.getEncryptedId())) {
               return new ResponseEntity<>(
                   new ResponseTemplate(HttpStatus.BAD_REQUEST,
                       i18nMessageService.getMessage(ErrorCodes.uc1_err_008)),
                   HttpStatus.BAD_REQUEST);
             }
           }
          }

          conditionDto.setParameterIdEncrypt(dto.getEncryptedId());
          parameterConditionService.insert(conditionDto);
          log.debug("The parameter condition: {{}} created successfully!",
              conditionDto.getName());
        }
      }
    }

    // delete
    for (ParameterConditionDTO object : conditionObjects) {
      boolean isExistedFlg = false;

      for (ParameterConditionDTO conditionDto : conditionDtos) {
        if (object.getName().equalsIgnoreCase(conditionDto.getName())) {
          isExistedFlg = true;
          break;
        }
      }

      if (!isExistedFlg) {
        this.parameterConditionService.delete(CryptoUtils.decryptId(object.getEncryptedId(),
            cryptoService.getParameterConditionKey()));
        log.debug("The parameter condition: {{}} deleted successfully!", object.getName());
      }
    }

    return null;
  }

  /**
   * Update data source.
   *
   * @param dto
   */
  private void updateDataSource(ParameterDTO dto) {
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
        log.debug("updateDataSource - The data source created successfully.!");
      }

    }

  }

  /**
   * Validate Update Parameter.
   *
   * @param paramObject
   * @param dto
   * @return message
   */
  private ResponseEntity<Object> validateUpdateParameter(ParameterDTO paramObject,
      ParameterDTO dto) {
    // check exist param
    if (paramObject == null) {
      log.debug("The action is invalid, ID=[{}] does not exist!", dto.getId());
      return new ResponseEntity<>(new ResponseTemplate(HttpStatus.BAD_REQUEST,
          i18nMessageService.getMessage(ErrorCodes.uc1_warn_06)), HttpStatus.BAD_REQUEST);
    }

    if (!dto.getParamName().equalsIgnoreCase(paramObject.getParamName())) {
      if (this.dataService.findByParamName(dto.getParamName()) != null) {
        log.warn("insertParam - The parameter name has existed in the system!");
        return new ResponseEntity<>(
            new ResponseTemplate(HttpStatus.BAD_REQUEST,
                i18nMessageService.getMessage(ErrorCodes.uc3_err_003)),
            HttpStatus.BAD_REQUEST);
      }
    }

    // check status "Draft", only change to "Active"
    if (paramObject.getStatus() != null
        && paramObject.getStatus().equalsIgnoreCase(Status.DRAFT.getValue())) {

      if (!dto.getStatus().equalsIgnoreCase(Status.DRAFT.getValue())
          && !dto.getStatus().equalsIgnoreCase(Status.ACTIVE.getValue())) {
        log.warn("The action is invalid, only change it status from Draft to Active!");
        return new ResponseEntity<>(
            new ResponseTemplate(HttpStatus.BAD_REQUEST,
                i18nMessageService.getMessage(ErrorCodes.uc1_warn_07)),
            HttpStatus.BAD_REQUEST);
      }

    }

    // check status of a parameter is "Active"
    if (paramObject.getStatus() != null
        && paramObject.getStatus().equalsIgnoreCase(Status.ACTIVE.getValue())) {

      // check active applied in scheme
      List<SchemeDTO> schemeDtos = paramObject.getSchemes();
      boolean isActived = false;

      for (SchemeDTO schemeDto : schemeDtos) {
        if (schemeDto.getStatus().equalsIgnoreCase(Status.ACTIVE.getValue())) {
          isActived = true;
          break;
        }
      }

      if (isActived && dto.getStatus().equalsIgnoreCase(Status.INACTIVE.getValue())) {
        log.warn(
            "You cannot change the status of this parameter because it is being applied by an active Scheme.");
        return new ResponseEntity<>(
            new ResponseTemplate(HttpStatus.BAD_REQUEST,
                i18nMessageService.getMessage(ErrorCodes.uc1_warn_12)),
            HttpStatus.BAD_REQUEST);
      }

/*      if (!dto.getParamName().equalsIgnoreCase(paramObject.getParamName())) {
        log.warn("The action is invalid, can NOT change the parameter name!");
        return new ResponseEntity<>(
            new ResponseTemplate(HttpStatus.BAD_REQUEST,
                i18nMessageService.getMessage(ErrorCodes.uc1_warn_08)),
            HttpStatus.BAD_REQUEST);
      }*/

      if (dto.getStatus().equalsIgnoreCase(Status.DRAFT.getValue())) {
        log.warn("The action is invalid, can only change its status from Active to Inactive!");
        return new ResponseEntity<>(
            new ResponseTemplate(HttpStatus.BAD_REQUEST,
                i18nMessageService.getMessage(ErrorCodes.uc1_warn_09)),
            HttpStatus.BAD_REQUEST);
      }
    }

    return null;
  }

  /**
   * Insert Data Source.
   * 
   * @param dto
   * @return
   */
  private ResponseEntity<Object> insertDataSource(ParameterDTO dto) {
    DataSourceDTO dataSourceDto = null;
    if (dto.getSource() == null) {
      return null;
    }

    // check used source
    if (dto.getSource() != null && !dto.getSource().trim().isEmpty()
        && this.dataService.findBySource(dto.getSource()) != null
        && (dto.getCloneParamId() == null || dto.getCloneParamId().trim().isEmpty())) {
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

    // case clone parameter
    if (dto.getDataSource() == null || dto.getDataSource().trim().isEmpty()) {
      String cloneSource = null;

      if (dto.getCloneParamId() != null && !dto.getCloneParamId().trim().isEmpty()) {
        ParameterDTO cloneParamDto = this.dataService.findById(
            CryptoUtils.decryptId(dto.getCloneParamId(), cryptoService.getParameterKey()));

        if (cloneParamDto != null) {
          cloneSource = cloneParamDto.getSource();
        }
      }

      if (cloneSource != null) {
        DataSourceDTO dsObjectDB = dataSourceService.findBySource(cloneSource);

        if (dsObjectDB != null) {
          String newSourceClone = convertNameWithTime(dsObjectDB.getSource());

          dataSourceDto = new DataSourceDTO(newSourceClone, dsObjectDB.getContent());
          // insert DB
          dataSourceService.insert(dataSourceDto);
          dto.setSource(newSourceClone);
          log.debug("insertDataSource - The data source created successfully.!");
        }
      }
    }

    return null;
  }

  /**
   * Rename for source file
   *
   * @param fileName
   * @return
   */
  private String convertNameWithTime(String fileName) {
    int lastIndexType = fileName.lastIndexOf(".");
    String typeFile = "";
    if (lastIndexType != -1) {
     typeFile = "." + fileName.substring(lastIndexType + 1);
      fileName = fileName.substring(0, lastIndexType);
    }

    int lastIndexofTime = fileName.lastIndexOf("_");
    if (lastIndexofTime != -1) {
      String oldValueTime = fileName.substring(lastIndexofTime + 1);

      if (isNumeric(oldValueTime)) {
        fileName = fileName.substring(0, lastIndexofTime);
      }
    }

    // set time current
    final Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    final StringBuilder str = new StringBuilder();
    str.append(cal.get(Calendar.YEAR));
    str.append(cal.get(Calendar.MONTH));
    str.append(cal.get(Calendar.DATE));
    str.append(cal.get(Calendar.HOUR));
    str.append(cal.get(Calendar.MINUTE));
    str.append(cal.get(Calendar.SECOND));

    return fileName + "_" + str + typeFile;
  }

  /**
   * Check is number
   * 
   * @param strNum
   * @return
   */
  public static boolean isNumeric(String strNum) {
    if (strNum == null) {
      return false;
    }
    try {
      double d = Double.parseDouble(strNum);
    } catch (NumberFormatException nfe) {
      return false;
    }
    return true;
  }
}
