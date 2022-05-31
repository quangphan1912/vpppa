/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 * <p>
 * Description: The file class
 * <p>
 * Change history:
 * Date             Defect#             Person             Comments
 * -------------------------------------------------------------------------------
 * July 27, 2020     ********           PhoVo            Initialize
 */
package com.gcs.vppa.core.converter;

import com.gcs.vppa.common.converter.BaseConverter;
import com.gcs.vppa.common.logging.InjectLog;
import com.gcs.vppa.common.service.CryptoService;
import com.gcs.vppa.common.util.CryptoUtils;
import com.gcs.vppa.dto.SchemeExecutorDTO;
import com.gcs.vppa.entity.SchemeExecutor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

/**
 * The SchemeExecutorConverter.
 */
@NoArgsConstructor
@Service
@ComponentScan
public class SchemeExecutorConverter extends BaseConverter<SchemeExecutor, SchemeExecutorDTO> {
    @Autowired
    private CryptoService cryptoService;

    /**
     * To data transfer object.
     *
     * @param entity the entity
     * @return the template DTO
     */
    @Override
    @InjectLog(logParams = false)
    public SchemeExecutorDTO toDto(SchemeExecutor entity) {
        if (entity == null) {
            return null;
        }

        SchemeExecutorDTO dto = new SchemeExecutorDTO();
        dto.setEncryptedId(CryptoUtils.encrypt(entity.getId().toString(), cryptoService.getSchemeExecutor()));
        dto.setId(entity.getId());
        dto.setIdentifier(entity.getIdentifier());
        dto.setExecutorId(entity.getExecutorId());
        dto.setStatus(entity.getStatus());
        dto.setType(entity.getType());
        dto.setResultFile(entity.getResultFile());
        dto.setExecuteBy(entity.getExecuteBy());
        dto.setExecuteDate(entity.getExecuteDate());
        dto.setExecuteMonth(entity.getExecuteMonth());
        dto.setSchemeId(CryptoUtils.encrypt(entity.getSchemeId().toString(), cryptoService.getSchemeKey()));
        dto.setProcessId(CryptoUtils.encrypt(entity.getProcessId().toString(), cryptoService.getProcessKey()));
        return dto;
    }

    /**
     * To entity.
     *
     * @param dto the dto
     * @return the t entity
     */
    @Override
    @InjectLog
    public SchemeExecutor toEntity(SchemeExecutorDTO dto) {
        if (dto == null) {
            return null;
        }

        Integer id = 0;
        if (dto.getEncryptedId() != null) {
            id = Integer.parseInt(CryptoUtils.decrypt(dto.getEncryptedId(), cryptoService.getSchemeExecutor()));
        } else {
            id = dto.getId();
        }

        SchemeExecutor entity = new SchemeExecutor();

        entity.setId(id);
        entity.setStatus(dto.getStatus());
        entity.setType(dto.getType());
        entity.setExecutorId(dto.getExecutorId());
        entity.setResultFile(dto.getResultFile());
        entity.setExecuteBy(dto.getExecuteBy());
        entity.setExecuteDate(dto.getExecuteDate());
        entity.setExecuteMonth(dto.getExecuteMonth());
        int schemeId = 0;
        if (dto.getSchemeId() != null) {
          schemeId = Integer.parseInt(CryptoUtils.decrypt(dto.getSchemeId(), cryptoService.getSchemeKey()));
        }
        entity.setSchemeId(schemeId);

        int processId = 0;
        if (dto.getProcessId() != null) {
          processId = Integer.parseInt(CryptoUtils.decrypt(dto.getProcessId(), cryptoService.getProcessKey()));
        }
        entity.setProcessId(processId);

        return entity;
    }

    /**
     * {@inheritDoc}
     *
     * @see BaseConverter#toEntity(Object,
     *      Object)
     */
    @Override
    @InjectLog(logParams = false)
    public void toEntity(SchemeExecutorDTO dto, SchemeExecutor entity) {
        int id = 0;
        if (dto.getEncryptedId() != null) {
            id = Integer.parseInt(CryptoUtils.decrypt(dto.getEncryptedId(), cryptoService.getSchemeExecutor()));
        } else {
            id = dto.getId();
        }
        entity.setId(id);
        entity.setStatus(dto.getStatus());
        entity.setType(dto.getType());
        entity.setExecutorId(dto.getExecutorId());
        entity.setResultFile(dto.getResultFile());
        entity.setExecuteBy(dto.getExecuteBy());
        entity.setExecuteDate(dto.getExecuteDate());
        entity.setExecuteMonth(dto.getExecuteMonth());
        int schemeId = 0;
        if (dto.getSchemeId() != null) {
          schemeId = Integer.parseInt(CryptoUtils.decrypt(dto.getSchemeId(), cryptoService.getSchemeKey()));
        }
        entity.setSchemeId(schemeId);

        int processId = 0;
        if (dto.getProcessId() != null) {
          processId = Integer.parseInt(CryptoUtils.decrypt(dto.getProcessId(), cryptoService.getProcessKey()));
        }
        entity.setProcessId(processId);

    }
}
