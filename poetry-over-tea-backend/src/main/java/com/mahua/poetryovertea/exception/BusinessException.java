package com.mahua.poetryovertea.exception;


import com.mahua.poetryovertea.common.ErrorCode;

/**
 * 自定义业务异常类
 *
 * mahua
 */
public class BusinessException extends RuntimeException {

	private final int code;
	private String description;

	public BusinessException(int code, String msg, String description) {
		super(msg);
		this.code = code;
		this.description = description;
	}

	public BusinessException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.code = errorCode.getCode();
	}

	public BusinessException(ErrorCode errorCode, String description) {
		super(errorCode.getMessage());
		this.code = errorCode.getCode();
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
}
