package com.mahindra.lead;
import com.mahindra.lead.controller.LeadController;
import com.mahindra.lead.dto.LeadDTO;
import com.mahindra.lead.response.ApiResponse;
import com.mahindra.lead.response.GetAllLeadsResponse;
import com.mahindra.lead.service.LeadService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class LeadControllerTest {

    @Mock
    private LeadService leadService;

    @InjectMocks
    private LeadController leadController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddLeads() {
        LeadDTO leadDTO = new LeadDTO(/* provide necessary leadDTO details */);
        ApiResponse expectedApiResponse = new ApiResponse("success", "Created Leads Successfully");

        when(leadService.createLead(any())).thenReturn(expectedApiResponse);

        ApiResponse actualApiResponse = leadController.addLeads(leadDTO);

        Mockito.verify(leadService).createLead(leadDTO);

        assertEquals(expectedApiResponse, actualApiResponse);
    }

    @Test
    public void testGetAllLeads() {
        String mobileNumber = "1234567890";
        GetAllLeadsResponse expectedResponse = new GetAllLeadsResponse(/* provide necessary response details */);

        when(leadService.getLeadsByMobileNumber(mobileNumber)).thenReturn(expectedResponse);

        GetAllLeadsResponse actualResponse = leadController.getLeadsByMobileNumber(mobileNumber);

        Mockito.verify(leadService).getLeadsByMobileNumber(mobileNumber);

        assertEquals(expectedResponse, actualResponse);
    }
}
