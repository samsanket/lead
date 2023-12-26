package com.mahindra.lead.service;

import com.mahindra.lead.dto.LeadDTO;
import com.mahindra.lead.model.Lead;
import com.mahindra.lead.repository.LeadsRepository;
import com.mahindra.lead.response.*;
import com.mahindra.lead.validations.LeadValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeadService {
    Logger log = LoggerFactory.getLogger(LeadService.class);

    private final LeadValidator leadValidator;

    private final LeadsRepository leadsRepository;

    @Autowired
    public LeadService(LeadValidator leadValidator, LeadsRepository leadsRepository) {
        this.leadValidator = leadValidator;
        this.leadsRepository=leadsRepository;
    }

    public ApiResponse createLead(LeadDTO leadDTO) {
        log.info("Create lead service ");
        Errors errors = new BeanPropertyBindingResult(leadDTO, "leadDTO");
        leadValidator.validate(leadDTO, errors);
        log.info("Validation PASS");

        if (errors.hasErrors()) {
            throw new LeadValidationException(errors.getAllErrors().get(0).getDefaultMessage());
        }
        Optional<Lead> existingLead = leadsRepository.findById(leadDTO.getLeadId());

        if (existingLead.isPresent()) {
            Integer leadId =existingLead.get().getLeadId();
            log.info("Lead Already Exists in the database with the lead id {} ", leadId);
            List<String> errorMessage= new ArrayList<>();
            errorMessage.add("Lead Already Exists in the database with the lead id "+ leadId);
            return new ApiResponse("error",new ErrorResponse("E10010",errorMessage));
        }

        Lead lead= new Lead(leadDTO);
        leadsRepository.save(lead);
        log.info("Lead successfully saved in DB");

        return new ApiResponse("success", "Created Leads Successfully");
    }

    public GetAllLeadsResponse getLeadsByMobileNumber(String mobileNumber) {
        log.info("Getting lead for mobile number {}",mobileNumber);
     List<Lead> leads =leadsRepository.findByMobileNumber(mobileNumber);

     if (leads.size()==0){
         log.info("Lead not found with mobile  number {}", mobileNumber);
         return getResponse(mobileNumber);
     }
     GetAllLeadsResponse getAllLeadsResponse = new GetAllLeadsResponse();
     getAllLeadsResponse.setData(leads);
     getAllLeadsResponse.setStatus("success");
    return getAllLeadsResponse;
    }

    private static GetAllLeadsResponse getResponse(String mobileNumber) {
        GetAllLeadsResponse getAllLeadsResponse= new GetAllLeadsResponse();
        getAllLeadsResponse.setStatus("error");
        ErrorResponse errorResponse = getErrorResponse(mobileNumber);
        getAllLeadsResponse.setErrorResponse(errorResponse);
        return getAllLeadsResponse;
    }

    private static ErrorResponse getErrorResponse(String mobileNumber) {
        List<String> errorMessage= new ArrayList<>();
        errorMessage.add("No Lead found with the Mobile Number "+mobileNumber );
        ErrorResponse errorResponse= new ErrorResponse();
        errorResponse.setMessages(errorMessage);
        errorResponse.setCode("E10011");
        return errorResponse;
    }
}
