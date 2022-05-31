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
package com.gcs.vppa.common.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gcs.vppa.common.base.BaseDTO;
import com.gcs.vppa.common.base.BaseEntity;
import com.gcs.vppa.common.base.SearchResultDTO;
import com.gcs.vppa.common.constant.Constants;
import com.gcs.vppa.common.constant.ErrorCodes;
import com.gcs.vppa.common.converter.BaseConverter;
import com.gcs.vppa.common.dto.ResponseTemplate;
import com.gcs.vppa.common.exception.DecryptionException;
import com.gcs.vppa.common.logging.InjectLog;
import com.gcs.vppa.common.repository.BaseRepository;
import com.gcs.vppa.common.repository.Condition;
import com.gcs.vppa.common.repository.DataType;
import com.gcs.vppa.common.repository.SearchCondition;
import com.gcs.vppa.common.service.BaseDataService;
import com.gcs.vppa.common.service.CryptoService;
import com.gcs.vppa.common.service.I18nMessageService;
import com.gcs.vppa.common.util.CollectionUtil;
import com.gcs.vppa.common.util.CryptoUtils;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Instantiates a new base controller.
 *
 * @param <TId> the generic type
 * @param <TEntity> the generic type
 * @param <TDto> the generic type
 * @param <TRepo> the generic type
 * @param <TCvt> the generic type
 * @param <TSvc> the generic type
 */

/**
 * Instantiates a new base controller.
 */
@NoArgsConstructor

/** The Constant log. */

/** The Constant log. */
@Slf4j
public abstract class BaseController<TId, TEntity extends BaseEntity<TId>, TDto extends BaseDTO<TId>, TRepo extends BaseRepository<TEntity, TId>, TCvt extends BaseConverter<TEntity, TDto>, TSvc extends BaseDataService<TId, TEntity, TDto, TRepo, TCvt>> {

  /** The Constant GET_METHOD_TEMPLATE. */
  private static final String GET_METHOD_TEMPLATE = "get%sKey";

  /** The service. */
  @Autowired
  protected TSvc dataService;

  /** The i 18 n message service. */
  @Autowired
  protected I18nMessageService i18nMessageService;

  /** The crypto properties. */
  @Autowired
  protected CryptoService cryptoService;

  /**
   * Gets the all items.
   *
   * @param encryptedKey the encrypted key
   * @return the all items
   */
  protected ResponseEntity<Object> getAllItems() {
    List<TDto> dtoList = this.dataService.findAll();

    if (!CollectionUtil.isNullOrEmpty(dtoList)) {
      log.debug("Found [{}] items", dtoList.size());
    }

    return new ResponseEntity<>(dtoList, HttpStatus.OK);
  }

  /**
   * Search items.
   *
   * @param searchCondition the search condition
   * @param encryptedKey    the encrypted key
   * @return the response entity
   */
  protected ResponseEntity<Object> searchItems(SearchCondition searchCondition) {
    SearchResultDTO<TDto> searchResult = this.dataService.findByCriteria(searchCondition);

    log.debug("Total_items=[{}]", searchResult.getTotalItems());

    return new ResponseEntity<>(searchResult, HttpStatus.OK);
  }
  
  /**
   * Search items.
   *
   * @param searchCondition the search condition
   * @param fn the function callback.
   * @return the response entity
   */
  protected ResponseEntity<Object> searchItems(SearchCondition searchCondition, Function<SearchCondition, ?> fn) {
    if (fn != null) {
      fn.apply(searchCondition);
    }
    
    SearchResultDTO<TDto> searchResult = this.dataService.findByCriteria(searchCondition);

    log.debug("Total_items=[{}]", searchResult.getTotalItems());

    return new ResponseEntity<>(searchResult, HttpStatus.OK);
  }


  /**
   * Gets the item.
   *
   * @param id the id
   * @param encryptedKey the encrypted key
   * @return the item
   */
  protected ResponseEntity<Object> getItem(TId id) {
    TDto dto = this.dataService.findById(id);

    if (dto != null) {
      log.debug("Found item-ID=[{}]", id);
    }
    return new ResponseEntity<>(dto, HttpStatus.OK);
  }

  /**
   * Initialize item.
   *
   * @param id the id
   * @param fn the fn
   * @return the response entity
   */
  protected ResponseEntity<Object> initItemForEdit(TId id, Function<TDto, ?> fn) {
    TDto dto = null;
    
    if ((id instanceof Integer) && ((int)id) > 0) {
      dto = this.dataService.findById(id);
    }

    Object obj = null;

    if (fn != null) {
      obj = fn.apply(dto);
    } else {
      obj = dto;
    }
    
    if (obj == null) {
      return new ResponseEntity<>(new ResponseTemplate(HttpStatus.BAD_REQUEST,
        i18nMessageService.getMessage(ErrorCodes.UC_COMMON_OBJECT_NOT_EXIST_WARN_003)),
        HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(obj, HttpStatus.OK);
  }

  /**
   * Insert db.
   *
   * @param dto the dto
   * @return the response entity
   */
  protected ResponseEntity<Object> insertItemDB(TDto dto) {
    this.dataService.insertDB(dto);
    ResponseTemplate response = new ResponseTemplate(HttpStatus.OK,
      i18nMessageService.getMessage(ErrorCodes.uc1_info_001));

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Insert item.
   *
   * @param dto the dto
   * @return the response entity
   */
  protected ResponseEntity<Object> insertItem(TDto dto, Function<TDto, ?> fn) {
    // check request object is not null.
    if (dto == null) {
      return new ResponseEntity<>(new ResponseTemplate(HttpStatus.BAD_REQUEST,
        i18nMessageService.getMessage(ErrorCodes.UC_COMMON_REQUEST_INVALID_WARN_002)), HttpStatus.BAD_REQUEST);
    }

    // validate information.
    String validateErrorMessage = null;
    if (fn != null) {
      validateErrorMessage = (String) fn.apply(dto);
    }

    // insert data.
    TDto newDto = null;
    if (StringUtils.isEmpty(validateErrorMessage)) {
      newDto = this.dataService.insert(dto);
      log.debug("{}-ID=[{}] is inserted successfully", this.dataService.getEntityName(), dto.getIdentifier());
    } else {
      return new ResponseEntity<>(new ResponseTemplate(HttpStatus.BAD_REQUEST, validateErrorMessage),
        HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(newDto, HttpStatus.OK);
  }

  /**
   * Update item.
   *
   * @param id the id
   * @param dto          the dto
   * @param fn the fn
   * @return the response entity
   */
  protected ResponseEntity<Object> updateItem(TId id, TDto dto, Function<TDto, ?> fn) {
    // check request object is not null.
    if (dto == null) {
      return new ResponseEntity<>(new ResponseTemplate(HttpStatus.BAD_REQUEST,
        i18nMessageService.getMessage(ErrorCodes.UC_COMMON_REQUEST_INVALID_WARN_002)), HttpStatus.BAD_REQUEST);
    }

    // validate information.
    String validateErrorMessage = null;
    if (fn != null) {
      validateErrorMessage = (String) fn.apply(dto);
    }

    // update data.
    TDto updatedDto = null;
    if (StringUtils.isEmpty(validateErrorMessage)) {
      updatedDto = this.dataService.update(id, dto);
      log.debug("{}-ID=[{}] is updated successfully", this.dataService.getEntityName(), dto.getIdentifier());
    } else {
      return new ResponseEntity<>(new ResponseTemplate(HttpStatus.BAD_REQUEST, validateErrorMessage),
        HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(updatedDto, HttpStatus.OK);
  }

  /**
   * Delete item.
   *
   * @param id the id
   * @param messages the messages, messages[0]: success message, messages[1]: error message.
   * @return the response entity
   */
  protected ResponseEntity<Object> deleteItem(TId id, String... messages) {
    ResponseTemplate response = null;

    String successMessage = StringUtils.EMPTY;
    String errorMessage = StringUtils.EMPTY;

    if (messages != null && messages.length > 0) {
      if (messages.length >= 1) {
        successMessage = messages[0];
      } else {
        successMessage = i18nMessageService.getMessage(ErrorCodes.UC_COMMON_DELETE_INFO_001);
      }

      if (messages.length >= 2) {
        errorMessage = messages[1];
      } else {
        errorMessage = i18nMessageService.getMessage(ErrorCodes.UC_COMMON_DELETE_WARN_001);
      }
    }

    try {
      this.dataService.delete(id);
      response = new ResponseTemplate(HttpStatus.OK, successMessage);
    } catch (EmptyResultDataAccessException e) {
      log.warn(e.getMessage(), e);
      response = new ResponseTemplate(HttpStatus.BAD_REQUEST, errorMessage);
    }

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Delete item list.
   *
   * @param listDto the list dto
   * @return the response entity
   */
  @InjectLog(logParams = false)
  protected ResponseEntity<Object> deleteItemList(List<TDto> listDto) {
    this.dataService.deleteList(listDto);
    ResponseTemplate response = new ResponseTemplate(HttpStatus.OK, String
      .format("%s - [%d] items is deleted successfully", this.dataService.getEntityName(), listDto.size()));
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Update item list.
   *
   * @param listDto the list dto
   * @return the response entity
   */
  @InjectLog(logParams = false)
  protected ResponseEntity<Object> updateItemList(List<TDto> listDto) {
    this.dataService.updateList(listDto);
    ResponseTemplate response = new ResponseTemplate(HttpStatus.OK, String
      .format("%s - [%d] items are updated successfully", this.dataService.getEntityName(), listDto.size()));
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  
  /**
   * Modify search condition.
   *
   * @param searchCondition the search condition
   * @param ignoreKeys the ignore keys
   */
  protected void modifySearchCondition(String statusFilter, SearchCondition searchCondition, String[] ignoreKeys,
    Function<Condition, Object> fn) {
    if (searchCondition == null) {
      return;
    }

    // create new conditions listing.
    List<Condition> newConditions = new ArrayList<>(searchCondition.getConditions().size());
    List<Condition> conditions = searchCondition.getConditions();
    boolean isExistStatus = false;

    for (Condition condition : conditions) {
      Object preVal = null;

      if (fn != null) {
        preVal = fn.apply(condition);
      }

      if (preVal == null) {
        String key = condition.getKey().trim();

        if (key != null && key.endsWith("Id") && !isExistedIgnoreKeys(ignoreKeys, key)) {
          this.updateValue(condition, getDecryptKey(condition));
        }

        // fix search "_", "%"
        if(condition.getType().equalsIgnoreCase(Constants.TYPE_STRING)) {
          String preValue = updateUnderscores(condition);
          condition.setValue(preValue);
        }

      } else {
        condition.setValue(preVal);
      }

      // check exist condition status
      if (condition.getKey().equalsIgnoreCase(Constants.KEY_STATUS)) {
        isExistStatus = true;
      }

      newConditions.add(condition);

    }

    // if not exist status -> add filter status
    if (!isExistStatus && statusFilter != null) {
      Condition filterStatus = setFilterStatus(statusFilter);
      newConditions.add(filterStatus);
    }

    searchCondition.setConditions(newConditions);
  }

  /**
   * Gets the date value.
   *
   * @param condition the condition
   * @return the date value
   */
  protected Date getDateValue(Condition condition) {
    // convert String to LocalDate
    try {
      return new SimpleDateFormat(Constants.DATE_FORMAT).parse(String.valueOf(condition.getValue()));
    } catch (ParseException ex) {
      log.warn(ex.getMessage(), ex);
      throw new DateTimeParseException(String.format("Parsing %s error.", condition.getKey()),
        String.valueOf(condition.getValue()), 0);
    }
  }

  /**
   * Gets the date value.
   *
   * @param condition the condition
   * @return the date value
   */
  protected Date getDateTimeValue(Condition condition) {
    // convert String to LocalDate
    try {
      return new SimpleDateFormat(Constants.DATE_TIME_FORMAT).parse(String.valueOf(condition.getValue()));
    } catch (ParseException ex) {
      log.warn(ex.getMessage(), ex);
      throw new DateTimeParseException(String.format("Parsing %s error.", condition.getKey()),
        String.valueOf(condition.getValue()), 0);
    }
  }

  /**
   * Gets the array values.
   *
   * @param preVal the pre val
   * @param key the key
   * @return the array values
   */
  protected String getArrayValues(String preVal, String key) {
    String[] encryptedIds = preVal.substring(1, preVal.length() - 1).split(",");
    int[] ids = new int[encryptedIds.length];
    int idx = 0;

    for (String itemId : encryptedIds) {
      String trimVal = itemId.trim();

      if (StringUtils.isNotEmpty(trimVal)) {
        ids[idx++] = CryptoUtils.decryptId(trimVal, key);
      }
    }

    if (idx < encryptedIds.length) {
      ids = Arrays.copyOfRange(ids, 0, idx);
    }

    return String.format("%s", StringUtils.join(ArrayUtils.toObject(ids), ","));
  }

  /**
   * Gets the decrypt key.
   *
   * @param condition the condition
   * @return the decrypt key
   */
  protected String getDecryptKey(Condition condition) {
    String key = null;

    // get object type. Ex: division, center.
    String masterObjectType = condition.getKey().substring(0, condition.getKey().length() - 2);

    // to first letter to upper case. Ex: Division, Center.
    masterObjectType = masterObjectType.substring(0, 1).toUpperCase() + masterObjectType.substring(1);

    // get method name.
    String methodName = String.format(GET_METHOD_TEMPLATE, masterObjectType);

    try {
      // create method invoking.
      Method method = cryptoService.getClass().getMethod(methodName);

      key = (String) method.invoke(cryptoService);

    } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
      | InvocationTargetException e) {
      log.warn(e.getMessage(), e);
      throw new DecryptionException(null, e.getMessage());
    }

    return key;
  }

  /**
   * Gets the plain text id.
   *
   * @param encryptedId the encrypted id
   * @param key the key
   * @param defaultValue the default value
   * @return the plain text id
   */
  protected int getPlainTextId(String encryptedId, String key, Integer defaultValue) {
    int id = 0;

    if (StringUtils.isNotEmpty(encryptedId)) {
      id = CryptoUtils.decryptId(encryptedId, key);
    } else {
      id = defaultValue;
    }

    return id;
  }

  
  /**
   * Checks if is existed ignore keys.
   *
   * @param ignoreKeys the ignore keys
   * @param key the key
   * @return true, if is existed ignore keys
   */
  private boolean isExistedIgnoreKeys(String[] ignoreKeys, String key) {
    if (ignoreKeys != null) {
      return Arrays.stream(ignoreKeys).anyMatch(key::equals);
    }

    return false;
  }

  /**
   * Update value.
   *
   * @param condition the condition
   * @param key the key
   */
  private void updateValue(Condition condition, String key) {
    if (condition.getType().equalsIgnoreCase(DataType.NUMBER)) {
      condition.setValue(CryptoUtils.decryptId(String.valueOf(condition.getValue()), key));
    } else {
      String strVal = condition.getValue().toString().trim();

      if (strVal.length() >= 2) {
        if (strVal.charAt(0) == '[' && strVal.charAt(strVal.length() - 1) == ']') {
          condition.setValue(getArrayValues(strVal, key));
        } else {
          condition.setValue(String.valueOf(CryptoUtils.decryptId(strVal, key)));
        }
      }
    }
  }

  /**
   * Set FilterStatus.
   * 
   * @return condition
   */
  private Condition setFilterStatus(String statusFilter) {
    Condition filterStatus = new Condition();

    filterStatus.setKey(Constants.KEY_STATUS);
    filterStatus.setValue(statusFilter);
    filterStatus.setType(Constants.TYPE_STRING);
    filterStatus.setOperator(Constants.OPERATOR_NOT_EQ_IN);
    filterStatus.setJoinType(Constants.JOIN_TYPE_INNER);

    return filterStatus;
  }

  /**
   * Update value.
   *
   * @param condition the condition
   */
  private String updateUnderscores(Condition condition) {
    String value = condition.getValue().toString().trim();
    if(value.contains("_")) {
      value = value.replace("_", "\\_");
    }
    if(value.contains("%")) {
      value = value.replace("%", "\\%");
    }
    return value;
  }

  /**
   * Convert arrray to list string.
   * 
   * @param schemeIdEncrypt
   * @param key
   * @return
   */
  protected List<String> getListSchemeId(String[] schemeIdEncrypt, String key) {
    String[] idDecrypt = new String[schemeIdEncrypt.length];
    int idx = 0;

    for (String itemId : schemeIdEncrypt) {
      String trimVal = itemId.trim();

      if (StringUtils.isNotEmpty(trimVal)) {
        Integer value = CryptoUtils.decryptId(trimVal, key);
        idDecrypt[idx++] = value.toString();
      }
    }

    if (idx < schemeIdEncrypt.length) {
      idDecrypt = Arrays.copyOfRange(idDecrypt, 0, idx);
    }

    List<String> list = new ArrayList<>();
    for (String t : idDecrypt) {
      list.add(t);
    }

    return list;
  }
  
  protected String formatSchemeId(String[] schemeIdEncrypt, String key) {
    int[] idDecrypt = new int[schemeIdEncrypt.length];
    int idx = 0;

    for (String itemId : schemeIdEncrypt) {
      String trimVal = itemId.trim();

      if (StringUtils.isNotEmpty(trimVal)) {
        idDecrypt[idx++] = CryptoUtils.decryptId(trimVal, key);
      }
    }

    if (idx < schemeIdEncrypt.length) {
      idDecrypt = Arrays.copyOfRange(idDecrypt, 0, idx);
    }

    if (idx < schemeIdEncrypt.length) {
      idDecrypt = Arrays.copyOfRange(idDecrypt, 0, idx);
    }

    return String.format("%s", StringUtils.join(ArrayUtils.toObject(idDecrypt), ","));
  }

}
