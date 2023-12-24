package com.mahindra.lead.response;

import java.util.Collections;

public class LeadAlreadyExistsException extends RuntimeException {

    private static final String ERROR_CODE = "E10010";

    public LeadAlreadyExistsException() {
        super("Lead Already Exists in the database with the lead id");
    }

    public ErrorResponse getErrorResponse() {
        return new ErrorResponse(ERROR_CODE, Collections.singletonList(getMessage()));
    }
}
