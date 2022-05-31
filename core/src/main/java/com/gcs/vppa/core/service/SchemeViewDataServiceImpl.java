/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 * <p>
 * Description: The file class
 * <p>
 * Change history:
 * Date             Defect#             Person             Comments
 * -------------------------------------------------------------------------------
 * Aug 19, 2020     ********           hangttran.ht            Initialize
 */
package com.gcs.vppa.core.service;

import com.gcs.vppa.common.service.BaseDataServiceImpl;
import com.gcs.vppa.common.service.CryptoService;
import com.gcs.vppa.common.util.CollectionUtil;
import com.gcs.vppa.common.util.CryptoUtils;
import com.gcs.vppa.core.converter.SchemeViewConverter;
import com.gcs.vppa.dto.SchemeViewDTO;
import com.gcs.vppa.entity.SchemeView;
import com.gcs.vppa.repository.SchemeViewRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The class SchemeViewDataServiceImpl.
 *
 * @author hangttran.ht
 *
 */

/**
 * Instantiates a new SchemeView service impl.
 */
@NoArgsConstructor
@Service
@ComponentScan
public class SchemeViewDataServiceImpl extends
        BaseDataServiceImpl<Integer, SchemeView, SchemeViewDTO, SchemeViewRepository, SchemeViewConverter>
        implements SchemeViewDataService {


    /** The cryptography properties. */
    @Autowired
    private CryptoService cryptoService;

    /**
     * Gets the entity name.
     *
     * @return the entity name
     */
    @Override
    public String getEntityName() {
        return "SchemeView";
    }

    @Override
    public List<String> findAllByProcessIdAndStatus(Integer processId, String status) {
        List<SchemeView> schemeViewList = this.repository.findAllByProcessIdAndStatus(processId, status);
        if (!CollectionUtil.isNullOrEmpty(schemeViewList)) {
            return schemeViewList.stream()
                    .map(schemeView -> this.converter.toDto(schemeView))
                    .map(scheme -> scheme.getEncryptedId())
                    .map(id -> CryptoUtils.decrypt(id, cryptoService.getSchemeKey()))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
