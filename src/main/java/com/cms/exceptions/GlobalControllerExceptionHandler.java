package com.cms.exceptions;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.authenticator.Constants;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cms.dto.ErrorResponseDto;



@RestControllerAdvice
public class GlobalControllerExceptionHandler {

	@ExceptionHandler(value = { DeniedPermissionException.class })
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public String deniedPermissionException(DeniedPermissionException ex) {
		return "Denied permission";
	}

	@ExceptionHandler(value = { ConstraintViolationException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String constraintViolationException(ConstraintViolationException ex) {
		return "Bad request";
	}

	@ExceptionHandler(value = { Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String internalServerError(Exception ex) {
		return "Internal error";
	}

	@ExceptionHandler(value = { AccessDeniedException.class })
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String authorizationError(Exception ex) {
		return "Not Authorized";
	}

	@ExceptionHandler(BaseException.class)
	public ResponseEntity<?> baseExceptionHandler(Exception ex) {
		ErrorResponseDto resp = ErrorResponseDto.builder().message(ex.getMessage()).status("FAILED")
				.statusCode(HttpStatus.NOT_FOUND.value()).build();
		return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
	}

//	@ExceptionHandler(FailureContractException.class)
//	public ResponseEntity<?> failueContractException(Exception ex) {
//		String msg = ex.getMessage().replaceAll("\r\n", ";");
//		List<String> msgList = new ArrayList<>();
//		String[] split = msg.split(";");
//		for (String e : split) {
//			msgList.add(e);
//		}
//		return CommonUtils.buildErrorResponse(Constants.FIELD_REQUIRED, msgList);
//	}
//
//	@ExceptionHandler(ResourceNotFoundException.class)
//	public ResponseEntity<?> ResourceNotFoundExceptionHandler(Exception ex) {
//		return CommonUtils.buildErrorResponseNotFound(ex.getMessage(), "");
//	}
//	
//	@ExceptionHandler({ MissingServletRequestParameterException.class })
//	public ResponseEntity<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
//		return CommonUtils.buildErrorResponse(Constants.FIELD_REQUIRED,
//				"Required parameter '" + ex.getParameterName() + "' is missing");
//
//	}

}
