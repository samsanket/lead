package com.mahindra.lead.response;

import java.util.List;

public class ErrorResponse {

    private String code;
    private List<String> messages;

    // Constructors, getters, and setters

    public ErrorResponse() {
    }

    public ErrorResponse(String code, List<String> messages) {
        this.code = code;
        this.messages = messages;
    }




    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "code='" + code + '\'' +
                ", messages=" + messages +
                '}';
    }
}
