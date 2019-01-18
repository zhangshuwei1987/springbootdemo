package com.framework.exception;

public class ZswException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2976609224083027270L;
	
	private String code;
    private String msg;

	public ZswException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

    

}
