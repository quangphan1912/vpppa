/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 27, 2020     ********           Administrator            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.common.util;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Class MapperUtils.
 *
 * @author Administrator
 */
public final class MapperUtils {

  /**
   * Instantiates a new mapper utils.
   */
  private MapperUtils() {
    // do nothing.
  }

  /**
   * Object to map.
   *
   * @param obj the object
   * @return the map
   */
  @SuppressWarnings("unchecked")
  public static Map<String, Object> objectToMap(Object obj) {
    ObjectMapper oMapper = new ObjectMapper();

    if (obj != null) {
      return oMapper.convertValue(obj, Map.class);
    }

    return new HashMap<>();
  }

  /**
   * To json.
   *
   * @param obj the obj
   * @return the string
   * @throws JsonProcessingException the json processing exception
   */
  public static String toJson(Object obj) throws JsonProcessingException {
    // Java object to JSON string
    return new ObjectMapper().writeValueAsString(obj);
  }
}
