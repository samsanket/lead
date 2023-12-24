package com.mahindra.lead.service;

import com.mahindra.lead.dto.LeadDTO;
import com.mahindra.lead.model.Lead;
import com.mahindra.lead.repository.LeadsRepository;
import com.mahindra.lead.response.ApiResponse;
import com.mahindra.lead.response.ErrorResponse;
import com.mahindra.lead.response.LeadAlreadyExistsException;
import com.mahindra.lead.response.LeadValidationException;
import com.mahindra.lead.validations.LeadValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeadService {

    private final LeadValidator leadValidator;

    private final LeadsRepository leadsRepository;

    @Autowired
    public LeadService(LeadValidator leadValidator, LeadsRepository leadsRepository) {
        this.leadValidator = leadValidator;
        this.leadsRepository=leadsRepository;
    }

    public ApiResponse createLead(LeadDTO leadDTO) {
        Errors errors = new BeanPropertyBindingResult(leadDTO, "leadDTO");
        leadValidator.validate(leadDTO, errors);

        if (errors.hasErrors()) {
            throw new LeadValidationException(errors.getAllErrors().get(0).getDefaultMessage());
        }
        Optional<Lead> existingLead = leadsRepository.findById(leadDTO.getLeadId());

        if (existingLead.isPresent()) {
            List<String> errorMessage= new ArrayList<>();
            errorMessage.add("Lead Already Exists in the database with the lead id");
            return new ApiResponse("error",new ErrorResponse("E10010",errorMessage));
        }


        Lead lead= new Lead(leadDTO);
        System.out.println(lead);
        leadsRepository.save(lead);
        return new ApiResponse("success", "Created Leads Successfully");
    }

}
