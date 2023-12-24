package com.mahindra.lead.response;

public class LeadValidationException extends RuntimeException {

    public LeadValidationException(String message) {
        super(message);
    }

    public LeadValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public LeadValidationException(String leadIdAlreadyExists, ErrorResponse errorResponse) {
        super(leadIdAlreadyExists);
    }
}
