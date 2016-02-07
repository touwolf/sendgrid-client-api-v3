package com.touwolf.sendgrid3.model;

public class SendgridResponse<T>
{
    private T data;

    private Integer code;

    private String url;

    private String payload;

    private String response;

    public SendgridResponse(String url, String payload, String response)
    {
        this.url = url;
        this.payload = payload;
        this.response = response;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
