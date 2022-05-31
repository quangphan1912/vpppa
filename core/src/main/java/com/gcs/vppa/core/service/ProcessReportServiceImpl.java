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
package com.gcs.vppa.core.service;

import com.gcs.vppa.common.service.BaseDataServiceImpl;
import com.gcs.vppa.common.util.CollectionUtil;
import com.gcs.vppa.core.converter.ProcessReportConverter;
import com.gcs.vppa.dto.ProcessReportDTO;
import com.gcs.vppa.entity.ProcessReport;
import com.gcs.vppa.repository.ProcessReportRepository;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Class ProcessServiceImpl.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */

/**
 * Instantiates a new parameter data service impl.
 */
@NoArgsConstructor
@Service
@ComponentScan
public class ProcessReportServiceImpl
  extends BaseDataServiceImpl<Integer, ProcessReport, ProcessReportDTO, ProcessReportRepository, ProcessReportConverter>
  implements ProcessReportService {

  /**
   * Gets the entity name.
   *
   * @return the entity name
   */
  @Override
  public String getEntityName() {
    return "ProcessReport";
  }

  /**
   * Find by param name.
   *
   * @param name the name
   * @return the parameter DTO
   */
  @Override
  public ProcessReportDTO findByName(String name) {
    return this.converter.toDto(this.repository.findByName(name));
  }

  @Override
  public List<ProcessReportDTO> findByProcessId(Integer processId) {
    List<ProcessReport> processReportList = this.repository.findAllByProcessId(processId);
    if (CollectionUtil.isNullOrEmpty(processReportList)) return new ArrayList<>();
    return processReportList.stream().map(report -> this.converter.toDto(report)).collect(Collectors.toList());
  }
}
