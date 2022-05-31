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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.gcs.vppa.common.converter.BaseConverter;
import com.gcs.vppa.common.logging.InjectLog;
import com.gcs.vppa.common.service.CryptoService;
import com.gcs.vppa.common.util.CryptoUtils;
import com.gcs.vppa.dto.ProcessReportDTO;
import com.gcs.vppa.entity.ProcessReport;

import lombok.NoArgsConstructor;

/**
 * The ProcessReportConverter.
 */

/**
 * Instantiates a new Process converter.
 */
@NoArgsConstructor
@Service
@ComponentScan
public class ProcessReportConverter extends BaseConverter<ProcessReport, ProcessReportDTO> {
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
    public ProcessReportDTO toDto(ProcessReport entity) {
        if (entity == null) {
            return null;
        }

        ProcessReportDTO dto = new ProcessReportDTO();
        dto.setEncryptedId(CryptoUtils.encrypt(entity.getId().toString(), cryptoService.getProcessReport()));
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setSource(entity.getSource());
        dto.setSchemeId(CryptoUtils.encrypt(entity.getSchemeId().toString(), cryptoService.getSchemeKey()));
        dto.setProcessId(entity.getProcessId());
        dto.setTemplate(entity.getTemplate());
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
    public ProcessReport toEntity(ProcessReportDTO dto) {
        if (dto == null) {
            return null;
        }

        int id = 0;
        if (dto.getEncryptedId() != null) {
            id = Integer.parseInt(CryptoUtils.decrypt(dto.getEncryptedId(), cryptoService.getProcessReport()));
        } else {
            id = dto.getId();
        }

        ProcessReport entity = new ProcessReport();

        entity.setId(id);
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setSource(dto.getSource());
        entity.setTemplate(dto.getTemplate());

        int schemeId = 0;
        if (dto.getSchemeId() != null) {
          schemeId = Integer.parseInt(CryptoUtils.decrypt(dto.getSchemeId(), cryptoService.getSchemeKey()));
        }
        entity.setSchemeId(schemeId);
        entity.setProcessId(dto.getProcessId());

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
    public void toEntity(ProcessReportDTO dto, ProcessReport entity) {
        int id = 0;
        if (dto.getEncryptedId() != null) {
            id = Integer.parseInt(CryptoUtils.decrypt(dto.getEncryptedId(), cryptoService.getProcessReport()));
        } else {
            id = dto.getId();
        }
        entity.setId(id);
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setSource(dto.getSource());
        entity.setTemplate(dto.getTemplate());

        int schemeId = 0;
        if (dto.getSchemeId() != null) {
          schemeId = Integer.parseInt(CryptoUtils.decrypt(dto.getSchemeId(), cryptoService.getSchemeKey()));
        }
        entity.setSchemeId(schemeId);
        entity.setProcessId(dto.getProcessId());
    }
}
