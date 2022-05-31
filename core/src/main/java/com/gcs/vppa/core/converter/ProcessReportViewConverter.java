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
import com.gcs.vppa.dto.ProcessReportViewDTO;
import com.gcs.vppa.entity.ProcessReportView;

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
public class ProcessReportViewConverter extends BaseConverter<ProcessReportView, ProcessReportViewDTO> {
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
    public ProcessReportViewDTO toDto(ProcessReportView entity) {
        if (entity == null) {
            return null;
        }

        ProcessReportViewDTO dto = new ProcessReportViewDTO();
        dto.setEncryptedId(CryptoUtils.encrypt(entity.getId().toString(), cryptoService.getProcessReport()));
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setSource(entity.getSource());
        dto.setSchemeId(CryptoUtils.encrypt(entity.getSchemeId().toString(), cryptoService.getSchemeKey()));
        dto.setSchemeName(entity.getSchemeName());
        dto.setSchemeIdName(entity.getSchemeIdName());
        dto.setProcessId(entity.getProcessId());
        dto.setTemplate(entity.getTemplate());
        dto.setStatus(entity.getStatus());
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
    public ProcessReportView toEntity(ProcessReportViewDTO dto) {
        if (dto == null) {
            return null;
        }

        int id = 0;
        if (dto.getEncryptedId() != null) {
            id = Integer.parseInt(CryptoUtils.decrypt(dto.getEncryptedId(), cryptoService.getProcessReport()));
        } else {
            id = dto.getId();
        }

        ProcessReportView entity = new ProcessReportView();

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
        entity.setSchemeName(dto.getSchemeName());
        entity.setSchemeIdName(dto.getSchemeIdName());
        entity.setProcessId(dto.getProcessId());
        entity.setStatus(dto.getStatus());

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
    public void toEntity(ProcessReportViewDTO dto, ProcessReportView entity) {
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
        entity.setSchemeName(dto.getSchemeName());
        entity.setSchemeIdName(dto.getSchemeIdName());
        entity.setProcessId(dto.getProcessId());
        entity.setStatus(dto.getStatus());
    }
}
