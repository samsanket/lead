package com.mahindra.lead.controller;

import com.mahindra.lead.dto.LeadDTO;
import com.mahindra.lead.response.ApiResponse;
import com.mahindra.lead.response.GetAllLeadsResponse;
import com.mahindra.lead.service.LeadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/leads")
public class LeadController {
    Logger log = LoggerFactory.getLogger(LeadController.class);


    private final LeadService leadService;

    @Autowired
    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }


    @PostMapping("/add")
    public ApiResponse addLeads(@RequestBody LeadDTO leadDTO){
        log.info("calling Add leads API ");
        return  leadService.createLead(leadDTO);
    }

    @GetMapping("/get")
    public GetAllLeadsResponse getLeadsByMobileNumber(@RequestParam String mobileNumber){
        log.info("getting leads for mobile number {} ",mobileNumber);
        return leadService.getLeadsByMobileNumber(mobileNumber);
    }
}
