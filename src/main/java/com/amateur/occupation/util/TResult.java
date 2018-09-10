package com.amateur.occupation.util;


public class TResult {

    private Integer code;
    private String message;
    private Object data;

    public TResult() {
    }

    public TResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static TResult success() {
        TResult result = new TResult();
        result.setResultCode(TResultCode.SUCCESS);
        return result;
    }

    public static TResult success(Object data) {
        TResult result = new TResult();
        result.setResultCode(TResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static TResult success(String data) {
        TResult result = new TResult();
        result.setResultCode(TResultCode.SUCCESS);
        result.setMessage(data);
        return result;
    }

    public static TResult success(String data, Object object) {
        TResult result = new TResult();
        result.setResultCode(TResultCode.SUCCESS);
        result.setMessage(data);
        result.setData(object);
        return result;
    }


    public static TResult failure(String message) {
        TResult result = new TResult();
        result.setCode(TResultCode.FAILURE.getCode());
        result.message = message;
        return result;
    }

    public static TResult failure(TResultCode resultCode) {
        TResult result = new TResult();
        result.setResultCode(resultCode);
        return result;
    }

    public static TResult failure(TResultCode resultCode, Object data) {
        TResult result = new TResult();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    /**
     *
     *
     * @param resultCode
     */
    private void setResultCode(TResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

