package com.dev.projects.pruebakosmos.dto.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class ResponseGeneric<T> {
    private int statusCode;
    private String messageCode;
    private T data;

    public ResponseGeneric() {
    }

    public ResponseGeneric(int statusCode, String messageCode, T data) {
        this.statusCode = statusCode;
        this.messageCode = messageCode;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseGeneric{" +
                "statusCode=" + statusCode +
                ", messageCode='" + messageCode + '\'' +
                ", data=" + data +
                '}';
    }
}
