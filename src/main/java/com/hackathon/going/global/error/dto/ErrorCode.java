package com.hackathon.going.global.error.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C-000", "Internal Server Error"),
	S3_FILE_UPLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "C-001", "S3 File Upload Failed"),
	DUPLICATED_USER_ACCOUNT_ID(HttpStatus.CONFLICT, "U-000", "User Account Id is duplicated"),
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U-001", "User not founded"),
	INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "U-002", "Password is invalid"),
	INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "U-003", "Token is invalid"),
	INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, "U-004", "Permission is invalid"),
	FILE_EMPTY_ERROR(HttpStatus.BAD_REQUEST, "F-0000", "File Empty Error"),
	FILE_UPLOAD_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "F-0001", "File Upload Error"),
	FILE_MAX_UPLOAD_SIZE_EXCEED(HttpStatus.INTERNAL_SERVER_ERROR, "F-0002", "File Upload Size Exceeded"),
	TRAVEL_NOT_FOUND(HttpStatus.NOT_FOUND, "T-000", "Travel not founded"),
	PIN_NOT_FOUND(HttpStatus.NOT_FOUND, "P-000", "Travel not founded");

	private final HttpStatus status;
	private final String code;
	private final String message;

}
