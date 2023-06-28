package com.hackathon.going.global.error.exception;

import com.hackathon.going.global.error.dto.ErrorCode;

public class NotFoundException extends BusinessException {
	public NotFoundException(ErrorCode errorCode) {
		super(errorCode);
	}

	public NotFoundException(ErrorCode errorCode, String content) {
		super(errorCode, content + " is not found");
	}
}