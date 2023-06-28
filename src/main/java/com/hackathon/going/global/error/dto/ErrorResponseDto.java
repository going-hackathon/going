package com.hackathon.going.global.error.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponseDto {
	@JsonFormat(
			shape = JsonFormat.Shape.STRING,
			pattern = "yyyy-MM-dd HH:mm:ss",
			locale = "Asia/Seoul"
	)
	private final LocalDateTime timestamp = LocalDateTime.now();
	private final int status;
	private final String error;
	private final String code;
	private final String message;

	public ErrorResponseDto(ErrorCode errorCode) {
		this.status = errorCode.getStatus().value();
		this.error = errorCode.getStatus().name();
		this.code = errorCode.getCode();
		this.message = errorCode.getMessage();
	}

	@Builder
	public ErrorResponseDto(int status, String error, String code, String message) {
		this.status = status;
		this.error = error;
		this.code = code;
		this.message = message;
	}
}
