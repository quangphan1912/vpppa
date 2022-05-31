/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 18, 2020     ********           hangttran.ht            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.core.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.gcs.vppa.common.converter.BaseConverter;
import com.gcs.vppa.common.service.CryptoService;
import com.gcs.vppa.common.util.CryptoUtils;
import com.gcs.vppa.dto.ProductDTO;
import com.gcs.vppa.entity.Product;

import lombok.NoArgsConstructor;

/**
 * The class ProductConverter.
 * 
 * @author hangttran.ht
 *
 */
/**
 * Instantiates a new parameter converter.
 */
@NoArgsConstructor
@Service
@ComponentScan
public class ProductConverter extends BaseConverter<Product, ProductDTO> {
  /** The cryptography properties. */
  @Autowired
  private CryptoService cryptoService;

  /**
   * To dto.
   *
   * @param entity
   *          the entity
   * @return the Product DTO
   */
  @Override
  public ProductDTO toDto(Product entity) {
    if (entity == null) {
      return null;
    }

    ProductDTO dto = new ProductDTO();
    dto.setId(entity.getId());
    dto.setEncryptedId(
        CryptoUtils.encrypt(entity.getId().toString(), cryptoService.getProductKey()));
    dto.setCode(entity.getCode());
    dto.setName(entity.getName());
    dto.setParentId(entity.getParentId() == null ? null
        : CryptoUtils.encrypt(entity.getParentId().toString(), cryptoService.getProductKey()));

    return dto;
  }

  /**
   * To entity.
   *
   * @param dto
   *          the dto
   * @return the Product
   */
  @Override
  public Product toEntity(ProductDTO dto) {
    if (dto == null) {
      return null;
    }

    int productId = 0;
    if (dto.getEncryptedId() != null) {
      productId = CryptoUtils.decryptId(dto.getEncryptedId(),
          cryptoService.getProductKey());
    } else {
      productId = dto.getId();
    }

    Product entity = new Product();
    entity.setId(productId);
    entity.setCode(dto.getCode());
    entity.setName(dto.getName());
    entity.setParentId(entity.getParentId() == null ? null
        : CryptoUtils.decryptId(entity.getParentId().toString(),
            cryptoService.getProductKey()));
    return entity;

  }

  /**
   * To entity.
   *
   * @param dto
   *          the dto
   * @param entity
   *          the entity
   * @return the Product
   */
  @Override
  public void toEntity(ProductDTO dto, Product entity) {
    // TODO Auto-generated method stub
  }

}
