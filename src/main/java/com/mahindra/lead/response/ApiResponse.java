package com.mahindra.lead.response;

public class ApiResponse{

    private String status;
    private String data;

    private ErrorResponse errorResponse;

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public ApiResponse() {
    }

    public ApiResponse(String status, String data) {
        this.status = status;
        this.data = data;
    }

    public  ApiResponse(String status,ErrorResponse errorResponse){
        System.out.println("errorResponse in cunstructor" + errorResponse);
        this.status=status;
        this.errorResponse=errorResponse;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}


