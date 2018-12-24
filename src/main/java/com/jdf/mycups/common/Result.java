package com.jdf.mycups.common;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result implements Serializable {

    private static final long serialVersionUID = 1L;

    private String retCode = "000000";

    private String reason = "成功";

    private Map<String, Object> data = new LinkedHashMap<>(); // 返回数据，如果返回异常则为空Map

    public Result(String retCode) { // 屏蔽掉默认的
        this.retCode = retCode;
    }

    public Result() { // 屏蔽掉默认的
    }

    public static Result init() {
        Result result = new Result("000000");
        result.setReason("成功");
        return result;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public void setData(String key, Object value) {
        this.data.put(key, value);
    }

    public Map<String, Object> getData() {
        return this.data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(retCode).append(":").append(reason).append(":");
        if (null != data) {
            sb.append("{");
            for (Map.Entry<String, Object> d : data.entrySet()) {
                if (d.getValue() instanceof Collection) {
                    sb.append(d.getKey()).append(".length=").append(data.size()).append(",");
                } else {
                    sb.append(d.getKey()).append("=").append(d.getValue()).append(",");
                }
            }
            sb.append("}");
        } else {
            sb.append("NULL");
        }
        return sb.toString();
    }
}
