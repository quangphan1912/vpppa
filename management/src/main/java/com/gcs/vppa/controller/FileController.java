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
package com.gcs.vppa.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcs.vppa.annotations.Permitted;
import com.gcs.vppa.common.dto.FileDTO;
import com.gcs.vppa.common.util.FileUtil;
import com.gcs.vppa.core.service.PermissionService;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class FileController.
 *
 * @author <a href="mailto:developer@hitachiconsulting.com">hangttran.ht</a>
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {
  /**
   * upload
   *
   * @return the upload
   */
  @PostMapping(value = "/upload", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  @Permitted(features = {
    PermissionService.WRITE_PARAMETER,
    PermissionService.WRITE_SCHEME,
    PermissionService.WRITE_PROCESS})
  public ResponseEntity<Object> upload(@RequestBody FileDTO file) throws IOException {
    log.debug("upload ");
    try {
      String path = FileUtil.uploadFileToServer(file.getData(), file.getDirFolder(),
        file.getName().concat(".").concat(file.getType()));
      return new ResponseEntity<>(path, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * download
   *
   * @return the download
   */
  @PostMapping(value = "/download",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  @Permitted(features = {
    PermissionService.WRITE_PARAMETER,
    PermissionService.WRITE_SCHEME,
    PermissionService.WRITE_PROCESS,
    PermissionService.READ_PARAMETER,
    PermissionService.READ_SCHEME,
    PermissionService.READ_PROCESS})
  public ResponseEntity<Object> download(@RequestBody FileDTO file) throws IOException {
    log.debug("download ");

    try {
      byte[] data = FileUtil.downloadFileFromServer(file.getDirFolder(),
        file.getName().concat(".").concat(file.getType()));
      file.setData(data);
      return new ResponseEntity<>(file, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * Delete file
   *
   * @param file
   * @return the response entity
   */
  @PutMapping(value = "/delete",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  @Permitted(features = {
    PermissionService.WRITE_PARAMETER,
    PermissionService.WRITE_SCHEME,
    PermissionService.WRITE_PROCESS,
    PermissionService.DELETE_PARAMETER,
    PermissionService.DELETE_SCHEME})
  public ResponseEntity<Object> delete(@RequestBody FileDTO file) {
    log.debug("delete ");
    try {
      boolean res = FileUtil.deleteFileOnServer(file.getDirFolder(), file.getName().concat(".").concat(file.getType()));
      return new ResponseEntity<>(res, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}
