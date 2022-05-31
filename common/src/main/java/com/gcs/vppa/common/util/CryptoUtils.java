/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Aug 13, 2020     ********           Administrator            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.common.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import com.gcs.vppa.common.exception.DecryptionException;
import com.gcs.vppa.common.exception.EncryptionException;
import com.gcs.vppa.common.exception.HashException;

import lombok.extern.slf4j.Slf4j;

/** The Constant log. */
@Slf4j
/**
 * The Class CryptoUtils.
 *
 * @author Administrator
 */
public final class CryptoUtils {

  /**
   * Instantiates a new cryptography utilities.
   */
  private CryptoUtils() {
    // do nothing.
  }

  /**
   * Sets the key.
   *
   * @param myKey          the new key
   * @return the key
   * @throws UnsupportedEncodingException           the unsupported encoding exception
   * @throws NoSuchAlgorithmException           the no such algorithm exception
   */
  private static SecretKeySpec getKey(String myKey)
    throws UnsupportedEncodingException, NoSuchAlgorithmException {
    MessageDigest sha = null;

    try {
      byte[] key = myKey.getBytes("UTF-8");
      sha = MessageDigest.getInstance("SHA-1");
      key = sha.digest(key);
      key = Arrays.copyOf(key, 16);
      return new SecretKeySpec(key, "AES");
    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
      log.warn(e.getMessage(), e);
      throw e;
    }
  }

  /**
   * Convert string to hex.
   *
   * @param str
   *          the str
   * @return the string
   */
  public static String convertStringToHex(String str) {
    return String.valueOf(Hex.encodeHex(str.getBytes(StandardCharsets.UTF_8)));
  }

  /**
   * Convert hex to string.
   *
   * @param hex
   *          the hex
   * @return the string
   */
  public static String convertHexToString(String hex) {
    try {
      return new String(Hex.decodeHex(hex), StandardCharsets.UTF_8);
    } catch (DecoderException e) {
      throw new IllegalArgumentException("Invalid Hex format!");
    }
  }

  /**
   * Encrypt.
   *
   * @param strToEncrypt
   *          the string to encrypt
   * @param secret
   *          the secret
   * @return the string
   * @throws EncryptionException
   *           the encryption exception.
   */
  public static String encrypt(String strToEncrypt, String secret) throws EncryptionException {
    try {
      Cipher cipher = getCipher();
      cipher.init(Cipher.ENCRYPT_MODE, getKey(secret));

      return convertStringToHex(
        Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8"))));

    } catch (UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException
      | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
      log.warn(e.getMessage(), e);
      throw new EncryptionException(null, e.getMessage(), e);
    }
  }

  /**
   * Decrypt a string .
   *
   * @param strToDecrypt
   *          the str to decrypt
   * @param secret
   *          the secret
   * @return the string
   */
  public static String decrypt(String strToDecrypt, String secret) throws DecryptionException {
    try {
      Cipher cipher = getCipher();
      cipher.init(Cipher.DECRYPT_MODE, getKey(secret));
      return new String(
        cipher.doFinal(Base64.getDecoder().decode(convertHexToString(strToDecrypt))));
    } catch (UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException
      | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
      log.warn(e.getMessage(), e);
      throw new DecryptionException(null, e.getMessage(), e);
    }
  }

  /**
   * Decrypt id.
   *
   * @param encryptedId
   *          the encrypted id
   * @param key
   *          the key
   * @return the int
   */
  public static int decryptId(String encryptedId, String key) {
    if (encryptedId == null || StringUtils.isEmpty(encryptedId)) {
      return 0;
    }

    return Integer.parseInt(decrypt(encryptedId, key));
  }

  /**
   * Hash.
   *
   * @param plainText
   *          the plain text
   * @return the string
   * @throws HashException
   *           the hash exception
   */
  public static String hash(String plainText) throws HashException {
    MessageDigest messageDigest;
    try {
      messageDigest = MessageDigest.getInstance("SHA-256");
      messageDigest.update(plainText.getBytes());
      return convertStringToHex(new String(messageDigest.digest()));
    } catch (NoSuchAlgorithmException e) {
      log.warn(e.getMessage(), e);
      throw new HashException(null, e.getLocalizedMessage(), e);
    }
  }

  /**
   * Hash with local date.
   *
   * @param plainText
   *          the plain text
   * @param dt
   *          the local date time.
   * @return the string
   * @throws HashException
   *           the hash exception
   */
  public static String hashWithLocalDateTime(String plainText, LocalDateTime dt)
    throws HashException {
    LocalDateTime dtKey = null;

    if (dt == null) {
      dtKey = LocalDateTime.now();
    } else {
      dtKey = dt;
    }

    return hash(String.format("%s:%s", plainText, dtKey.toString()));
  }

  /**
   * Encrypt array value.
   *
   * @param value
   *          the value
   * @param key
   *          the key
   * @return 
   */
  public static String encryptArrayId(String value, String key) {
    if (value == null) {
      return StringUtils.EMPTY;
    }

    String strVal = value.trim();

    if (strVal.length() >= 2) {
      if (strVal.charAt(0) == '[' && strVal.charAt(strVal.length() - 1) == ']') {
        value = getArrayEnctyptValues(strVal, key);
      } else {
        value = String.valueOf(CryptoUtils.encrypt(strVal, key));
      }

    }
    return value;
  }

  /**
   * Gets the cipher.
   *
   * @return the cipher
   * @throws NoSuchPaddingException 
   * @throws NoSuchAlgorithmException 
   */
  private static Cipher getCipher() throws NoSuchAlgorithmException, NoSuchPaddingException {
    return Cipher.getInstance("AES/ECB/PKCS5PADDING");
  }

  /**
   * Gets the array values.
   *
   * @param preVal
   *          the pre val
   * @param key
   *          the key
   * @return the array values
   */
  private static String getArrayEnctyptValues(String preVal, String key) {
    String[] ids = preVal.substring(1, preVal.length() - 1).split(",");
    String[] encryptedIds = new String[ids.length];
    int idx = 0;

    for (String itemId : ids) {
      String trimVal = itemId.trim();

      if (StringUtils.isNotEmpty(trimVal)) {
        encryptedIds[idx++] = CryptoUtils.encrypt(trimVal, key);
      }
    }

    if (idx < encryptedIds.length) {
      encryptedIds = Arrays.copyOfRange(encryptedIds, 0, idx);
    }

    return String.format("[%s]", StringUtils.join(encryptedIds, ","));
  }

}
