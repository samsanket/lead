package com.mahindra.lead.response;

import com.mahindra.lead.model.Lead;

import java.util.List;

public class GetAllLeadsResponse {

    private String status;

    public List<Lead> data;

    public ErrorResponse errorResponse;

    public List<Lead> getData() {
        return data;
    }

    public void setData(List<Lead> data) {
        this.data = data;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public GetAllLeadsResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
