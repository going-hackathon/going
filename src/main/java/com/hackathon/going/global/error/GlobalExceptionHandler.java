package com.hackathon.going.global.error;

import com.hackathon.going.global.error.dto.ErrorCode;
import com.hackathon.going.global.error.dto.ErrorResponseDto;
import com.hackathon.going.global.error.exception.BusinessException;
import com.hackathon.going.global.error.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	@ExceptionHandler(BusinessException.class)
	protected ResponseEntity<ErrorResponseDto> handleBusinessException (
			final BusinessException e,
			final HttpServletRequest request
	) {
		log.error("BusinessException: {} {}", e.getErrorCode(), request.getRequestURL());
		return ResponseEntity
				.status(e.getErrorCode().getStatus().value())
				.body(new ErrorResponseDto(e.getErrorCode()));
	}

	@ExceptionHandler(NotFoundException.class)
	protected ResponseEntity<ErrorResponseDto> handleNotFoundException (
			final NotFoundException e,
			final HttpServletRequest request) {

		log.error("NotFoundException: {} {}", e.getErrorCode(), request.getRequestURL());
		return ResponseEntity
				.status(e.getErrorCode().getStatus().value())
				.body(new ErrorResponseDto(e.getErrorCode()));
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	protected ResponseEntity<ErrorResponseDto> handleMaxUploadSizeExceededException(
			final MaxUploadSizeExceededException e,
			final HttpServletRequest request) {
		log.error("MaxUploadSizeExceededException: {} {}", e.getMessage(), request.getRequestURL());
		ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
				.code(ErrorCode.FILE_MAX_UPLOAD_SIZE_EXCEED.getCode())
				.status(ErrorCode.FILE_MAX_UPLOAD_SIZE_EXCEED.getStatus().value())
				.error(ErrorCode.FILE_MAX_UPLOAD_SIZE_EXCEED.getStatus().name())
				.message(e.getMessage()).build();

		return ResponseEntity
				.status(ErrorCode.FILE_MAX_UPLOAD_SIZE_EXCEED.getStatus().value())
				.body(errorResponseDto);
	}
}
