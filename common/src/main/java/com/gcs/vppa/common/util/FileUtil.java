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
package com.gcs.vppa.common.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

import com.gcs.vppa.common.constant.Constants;

import lombok.NoArgsConstructor;

/**
 * Instantiates a new File Util.
 */
@NoArgsConstructor
public final class FileUtil {
    public static byte[] downloadFileFromServer(String path, String name) throws IOException {
        String dirFolder = Paths.get(Constants.RESOURCE_SERVER, path + File.separator + name).toString();
        File fileDir = new File(dirFolder);

        byte[] data = FileUtils.readFileToByteArray(fileDir);
        return data;
    }

    public static String uploadFileToServer(byte[] uploadFile, String path, String name) throws FileNotFoundException {
        if (uploadFile == null) {
            throw new FileNotFoundException("Failed to store file");
        }

        try {
            String dirFolder = Paths.get(Constants.RESOURCE_SERVER, path).toString();
            File fileDir = new File(dirFolder);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            Path pathFile = Paths.get(dirFolder, name);
            Files.write(pathFile, uploadFile);
            return dirFolder + File.separator + name;
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileNotFoundException("Failed to store file");
        }
    }

    public static boolean deleteFileOnServer(String path, String name) {
        String dirFolder = Paths.get(Constants.RESOURCE_SERVER, path + File.separator + name).toString();
        File fileDir = new File(dirFolder);

        fileDir.delete();
        return true;
    }
}
