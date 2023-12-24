package com.mahindra.lead.response;

public class ErrorApiResponse {

    private String status;
    private ErrorResponse errorResponse;

    // Constructors, getters, and setters

    public ErrorApiResponse() {
    }

    public ErrorApiResponse(String status, ErrorResponse errorResponse) {
        this.status = status;
        this.errorResponse = errorResponse;
    }

    // Getters and setters...

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }
}

