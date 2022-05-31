/**
 * Copyright (C) 2020, Hitachi Vantara Vietnam Co., Ltd. All rights reserved. Proprietary and confidential.
 *                                                                                
 * Description: The file class                                                 
 *                                                                                
 * Change history:                                                                
 * Date             Defect#             Person             Comments               
 * -------------------------------------------------------------------------------
 * Sep 1, 2020     ********           Administrator            Initialize                  
 *                                                                                
 */
package com.gcs.vppa.filter;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.gcs.vppa.annotations.Permitted;
import com.gcs.vppa.common.constant.Constants;
import com.gcs.vppa.common.constant.ErrorCodes;
import com.gcs.vppa.common.dto.ErrorDetail;
import com.gcs.vppa.common.dto.ResponseTemplate;
import com.gcs.vppa.common.dto.UserType;
import com.gcs.vppa.common.service.I18nMessageService;
import com.gcs.vppa.common.util.MapperUtils;
import com.gcs.vppa.core.exception.NotExistVppaUserException;
import com.gcs.vppa.core.exception.RequestNotFoundException;
import com.gcs.vppa.core.exception.TokenExpiredException;
import com.gcs.vppa.core.exception.UnauthorizedException;
import com.gcs.vppa.core.exception.UserBlockedException;
import com.gcs.vppa.core.service.PermissionService;
import com.gcs.vppa.core.service.VppaUserDataService;
import com.gcs.vppa.dto.VppaUserLogginedDTO;
import com.gcs.vppa.util.AuthorizeUtils;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class PermissionServiceInterceptor.
 *
 * @author Administrator
 */
/**
 * The Constant log.
 */
@Component

/**
 * Instantiates a new jwt request filter.
 */

/**
 * Instantiates a new permission service filter.
 */
@NoArgsConstructor

/** The Constant log. */
@Slf4j
public class PermissionServiceFilter extends OncePerRequestFilter {
  /** The Authorization. */
  private static String AUTHORIZATION = "Authorization";

  /** The request mapping handler mapping. */
  private static String REQUEST_MAPPING_HANDLER_MAPPING = "requestMappingHandlerMapping";

  /** The vppa user data service. */
  @Autowired
  private VppaUserDataService vppaUserDataService;

  /** The app context. */
  @Autowired
  private org.springframework.context.ApplicationContext appContext;

  /** The i 18 n message service. */
  @Autowired
  protected I18nMessageService i18nMessageService;

  /**
   * The is ignore filter.
   */
  @Value(("${jwt.ignore-filter}"))
  private boolean isIgnoreFilter;

  /** The jwt exp. */
  @Value(("${jwt.exp}"))
  private Integer jwtExp;

  /**
   * Do filter internal.
   *
   * @param request  the request
   * @param response the response
   * @param chain    the chain
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request,
    HttpServletResponse response, FilterChain chain) {

    HandlerMethod handlerMethod = null;

    try {
      handlerMethod = this.getHandle(request);

      VppaUserLogginedDTO tokenInfo;
      if (handlerMethod != null) {
        tokenInfo = this.validateRequest(request, handlerMethod);
        request.setAttribute(Constants.ACCESS_USER, tokenInfo);
      }

      chain.doFilter(request, response);
    } catch (Exception ex) {
      this.writeException(response, ex);
    }
  }

  /**
   * Validate request.
   *
   * @param request the request
   * @param handlerMethod the handler method
   * @throws AccessDeniedException the access denied exception
   * @throws RequestNotFoundException the request not found exception
   */
  private VppaUserLogginedDTO validateRequest(HttpServletRequest request, HandlerMethod handlerMethod)
    throws AccessDeniedException, RequestNotFoundException {
    // user token info.
    VppaUserLogginedDTO tokenInfo = null;

    // check request is public or not.
    if (!this.isPublicAccess(handlerMethod.getMethodAnnotation(Permitted.class))) {
      // request is not a public request.
      tokenInfo = this.validateToken(request.getHeader(AUTHORIZATION));

      // check permission.
      if (!this.checkPermission(tokenInfo, handlerMethod)) {
        throw new AccessDeniedException("");
      }
    }

    // update expire time
    this.resetLastLoginTime(tokenInfo);
    
    // return accessed user.
    return tokenInfo;
  }

  /**
   * Validate token.
   *
   * @param bearerToken the bearer token
   * @return the vppa user DTO
   */
  private VppaUserLogginedDTO validateToken(String bearerToken) {
    String token = AuthorizeUtils.getToken(bearerToken);

    if (StringUtils.isEmpty(token)) {
      throw new UnauthorizedException();
    }

    // get vppa user from database.
    VppaUserLogginedDTO tokenInfo = this.vppaUserDataService.findByToken(token);

    // throw when tocken null
    if (tokenInfo == null) {
      throw new TokenExpiredException();
    }

        // check user is active.
    if (tokenInfo.getUser() == null) {
      // throw not exist user.
      throw new NotExistVppaUserException();
    }
    
    if (!Constants.USER_STATUS_ACTIVE.equalsIgnoreCase(tokenInfo.getUser().getStatus())) {
      // throw user is in-active for working.
      throw new UserBlockedException(ErrorCodes.USER_BLOCKED_ID,
        i18nMessageService.getMessage(ErrorCodes.USER_BLOCKED, tokenInfo.getUser().getEmail()));
    }
    
    // check token expired.
    LocalDateTime tokenExp = tokenInfo.getLastAccessedTime();
    if (tokenInfo.isExpired()
     || tokenExp.plusMinutes(jwtExp).isBefore(LocalDateTime.now())) {
      // delete token.
      this.vppaUserDataService.logout(token);
      
      throw new TokenExpiredException();
    }

    return tokenInfo;
  }

  /**
   * Checks if is public access.
   *
   * @param handleInfo the handle info
   * @return true, if is public access
   */
  private boolean isPublicAccess(Permitted handleInfo) {
    return handleInfo != null && handleInfo.isPublic();
  }

  /**
   * Reset expire time.
   *
   * @param tokenInfo the token info
   */
  private void resetLastLoginTime(VppaUserLogginedDTO tokenInfo) {
    // update expire time.
    if (tokenInfo != null) {
      tokenInfo.setLastAccessedTime(LocalDateTime.now());
      tokenInfo.setUpdatedBy(tokenInfo.getId());
      tokenInfo.setUpdatedDate(LocalDateTime.now());

      this.vppaUserDataService.updateUserLoggined(tokenInfo);
    }
  }

  /**
   * Gets the handle.
   *
   * @param request the request
   * @return the handle
   */
  private HandlerMethod getHandle(HttpServletRequest request) {
    RequestMappingHandlerMapping mapping = (RequestMappingHandlerMapping) appContext
      .getBean(REQUEST_MAPPING_HANDLER_MAPPING);
    Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();

    RequestMappingInfo requestMappingInfo = handlerMethods
      .entrySet()
      .stream()
      .filter(entry -> entry.getKey().getMatchingCondition(request) != null)
      .findFirst()
      .map(java.util.Map.Entry::getKey)
      .orElse(null);

    if (Objects.nonNull(requestMappingInfo)) {
      return handlerMethods.get(requestMappingInfo);
    }

    return null;
  }

  /**
   * Check permission.
   *
   * @param loginDetail the login detail
   * @param handlerMethod the handler method
   * @return true, if successful
   * @throws RequestNotFoundException the request not found exception
   */
  private boolean checkPermission(VppaUserLogginedDTO loginDetail, HandlerMethod handlerMethod)
    throws RequestNotFoundException {

    boolean result = true;

    if (loginDetail != null) {
      Permitted handleInfo = handlerMethod.getMethodAnnotation(Permitted.class);

      if (handleInfo == null || handleInfo.isPrivate()) {
        throw new RequestNotFoundException();
      }

      int[] permission = null;
      UserType userType = loginDetail.getUser().getUserType();

      if (loginDetail.getUser().getUserRole() != null) {
        permission = loginDetail.getUser().getUserRole().getPermission();
      }

      if (UserType.ADMIN.equals(userType) || checkPermissionWithType(
        userType, handleInfo.userTypes())) {
        result = true;
      } else if (permission == null || permission.length == 0) {
        result = false;
      } else {
        result = checkPermissionWithFeatures(permission, handleInfo.features());
      }
    }

    return result;
  }

  /**
   * Check permission with type.
   *
   * @param userType    the user type
   * @param permissions the permissions
   * @return true, if successful
   */
  private boolean checkPermissionWithType(UserType userType, int[] permissions) {
    return Arrays.stream(permissions).anyMatch(i -> i == userType.getValue());
  }

  /**
   * Check permission with features.
   *
   * @param permissions   the permissions
   * @param groupFeatures the group features
   * @return true, if successful
   */
  private boolean checkPermissionWithFeatures(int[] permissions, int[] groupFeatures) {
    if (Arrays.stream(permissions).anyMatch(featureId -> featureId == PermissionService.ADMIN)) {
      return true;
    }
    
    for (int item : permissions) {
      for (int group : groupFeatures) {
        if (item == group) {
          return true;
        }
      }
    }

    return false;
  }

  /**
   * Write exception.
   * This method is used for all authentication logic.
   * In case of there're exception, used method only call this method & 
   * pass corresponding parameters for write exception to client.
   *
   * @param response  the response
   * @param exception the exception
   */
  private void writeException(
    HttpServletResponse response,
    Exception exception) {

    try {
      response.setContentType("text/plain;charset=UTF-8");
      response.setStatus(HttpStatus.OK.value());

      long id = 0;
      String message = null;
      ResponseTemplate template = null;
      List<ErrorDetail> errors = new ArrayList<>();

      // Catch url invalid from client
      if (exception instanceof RequestNotFoundException
        || exception instanceof HttpRequestMethodNotSupportedException) {
        
        id = ErrorCodes.REQUEST_NOT_FOUND_ID;
        message = i18nMessageService.getMessage(ErrorCodes.REQUEST_NOT_FOUND);
        errors.add(new ErrorDetail(ErrorCodes.REQUEST_NOT_FOUND, message));
        
      } else if (exception instanceof AccessDeniedException) {
        
        id = ErrorCodes.ACCESS_DENIED_ID;
        message = i18nMessageService.getMessage(ErrorCodes.ACCESS_DENIED);
        errors.add(new ErrorDetail(ErrorCodes.ACCESS_DENIED, message));
        
      } else if (exception instanceof UnauthorizedException) {
        
        id = ErrorCodes.UNAUTHORIZED_ID;
        message = i18nMessageService.getMessage(ErrorCodes.UNAUTHORIZED);
        errors.add(new ErrorDetail(ErrorCodes.UNAUTHORIZED, message));
        
      } else if (exception instanceof TokenExpiredException) {
        
        id = ErrorCodes.TOKEN_EXPIRED_ID;
        message = i18nMessageService.getMessage(ErrorCodes.TOKEN_EXPIRED);
        errors.add(new ErrorDetail(ErrorCodes.TOKEN_EXPIRED, message));
        
      } else if (exception instanceof NotExistVppaUserException) {
        
        id = ErrorCodes.NOT_EXIST_USER_ID;
        message = i18nMessageService.getMessage(ErrorCodes.NOT_EXIST_VPPA_USER);
        errors.add(new ErrorDetail(ErrorCodes.NOT_EXIST_VPPA_USER, message));
        
      } else if (exception instanceof UserBlockedException) {
        UserBlockedException ex = (UserBlockedException) exception;
        
        id = ex.getCode();
        message = ex.getMessage();
        errors.add(new ErrorDetail(ErrorCodes.USER_BLOCKED, message));
        
      }

      if (!errors.isEmpty()) {
        template = new ResponseTemplate(id, HttpStatus.BAD_REQUEST, message);
        template.setErrors(errors);
      } else {
        template = new ResponseTemplate(HttpStatus.OK, "");
      }

      // Write detail error to response
      response.getWriter().write(MapperUtils.toJson(template));
      response.getWriter().close();
    } catch (IOException ex) {
      log.error(ex.getMessage(), ex);
    }
  }
}
