package com.mahindra.lead.controller;

import com.mahindra.lead.dto.LeadDTO;
import com.mahindra.lead.response.ApiResponse;
import com.mahindra.lead.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/leads")
public class LeadController {

    private final LeadService leadService;

    @Autowired
    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }


    @PostMapping("/add")
    public ApiResponse addLeads(@RequestBody LeadDTO leadDTO){
        return  leadService.createLead(leadDTO);
    }

//    @GetMapping("/get")
//    public ApiResponse getAllLeads(){
//        return leadService.getAllLeads();
//    }
}
