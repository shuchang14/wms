package com.shu.wms.entity;

public class ResponseData {

    private Boolean result;
    private String msg;
    private String error;
    private Object object;
    private Integer code;

    public ResponseData(){

    }
    public ResponseData(boolean result,String str){
        this.result=result;
        if(result) {
            this.msg = str;
        }else{
            this.error=str;
        }
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
