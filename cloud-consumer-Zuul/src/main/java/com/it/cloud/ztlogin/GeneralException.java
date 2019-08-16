package com.it.cloud.ztlogin;

/**
 * @description: GeneralException
 * @author: MrGao
 * @create: 2018/11/06 10:44
 */
public class GeneralException extends Exception{
    private String errorCode;
    private String provinceCode;

    public GeneralException(String errorCode) {
        this.errorCode = errorCode;
    }

    public GeneralException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public GeneralException(String errorCode, Throwable cause) {
        this(errorCode, errorCode, cause);
    }

    public GeneralException(String errorCode, String message, String provinceCode) {
        super(message);
        this.errorCode = errorCode;
        this.provinceCode = provinceCode;
    }

    public GeneralException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public GeneralException(String errorCode, String message, String provinceCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.provinceCode = provinceCode;
    }

    public String getProvinceCode() {
        return this.provinceCode != null && !"".equalsIgnoreCase(this.provinceCode) ? this.provinceCode : "000";
    }

    public String getErrorCode() {
        return this.errorCode != null && !"".equalsIgnoreCase(this.errorCode) ? this.errorCode : "000000";
    }

    public String getMessage() {
        String message = super.getMessage();
        if (message == null || "".equalsIgnoreCase(message)) {
            if (ExceptionEnv.isLoaded()) {
                message = ExceptionEnv.getString(this.getErrorCode(), (String)null);
            } else {
                message = "业务异常未定义！";
            }
        }

        return message;
    }
}
