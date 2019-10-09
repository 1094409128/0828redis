package com.aaa.zxz.redis.Enum;

/**
 * @ProjectName: 0828redis
 * @Package: com.aaa.zxz.redis.Enum
 * @Author: zxz
 * @CreateDate: 2019/8/29 15:28
 * @Version: 1.0
 */
public enum  StatusEnum {

    CORRECT("result",200,404,500,"code");


    private String result;
    private Integer trueCode;
    private Integer falseCode;
    private String codeName;
    private Integer disable;

    StatusEnum(String result,Integer trueCode,Integer falseCode,Integer disable,String codeName){
        this.result = result;
        this.trueCode = trueCode;
        this.falseCode = falseCode;
        this.disable = disable;
        this.codeName = codeName;

    }


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getTrueCode() {
        return trueCode;
    }

    public void setTrueCode(Integer trueCode) {
        this.trueCode = trueCode;
    }

    public Integer getFalseCode() {
        return falseCode;
    }

    public void setFalseCode(Integer falseCode) {
        this.falseCode = falseCode;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public Integer getDisable() {
        return disable;
    }

    public void setDisable(Integer disable) {
        this.disable = disable;
    }
}
