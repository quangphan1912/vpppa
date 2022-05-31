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
package com.gcs.vppa.common.util;

import com.gcs.vppa.common.constant.ErrorCodes;
import com.gcs.vppa.common.exception.NotFoundException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Instantiates a new assert.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Assert {

  /**
   * Not null.
   *
   * @param <TId>
   *            the generic type
   * @param object
   *            the object
   * @param id
   *            the id
   * @throws NotFoundException
   *             the jars not found exception
   */
  public static <TId> void notNull(Object object, TId id) {
    if (object == null) {
      String message = String.format("Not found item with specified ID=[%s]", id.toString());
      throw new NotFoundException(ErrorCodes.NOT_FOUND, message);
    }
  }
}
