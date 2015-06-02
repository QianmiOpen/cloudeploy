package com.qianmi;

/**
 * 自定义接口异常
 */
public class DeployException extends Exception {
    private String code;
    public DeployException(String code, String message) {
        super(message);
        this.code = code;
    }
    public String getCode() {
        return code;
    }
}
