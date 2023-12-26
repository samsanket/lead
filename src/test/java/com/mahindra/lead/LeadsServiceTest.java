package com.mahindra.lead;

import com.mahindra.lead.dto.LeadDTO;
import com.mahindra.lead.model.Lead;
import com.mahindra.lead.repository.LeadsRepository;
import com.mahindra.lead.response.ApiResponse;
import com.mahindra.lead.response.GetAllLeadsResponse;
import com.mahindra.lead.service.LeadService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class LeadsServiceTest {

    @Mock
    private LeadsRepository leadsRepository;

    @InjectMocks
    private LeadService leadService;

    @Test
    public void testCreateLead() {
        LeadDTO leadDTO = new LeadDTO();
        leadDTO.setLeadId(1);
        leadDTO.setFirstName("sanket");
        leadDTO.setMiddleName("sanjay");
        leadDTO.setLastName("deshpande");
        leadDTO.setMobileNumber("7218515261");
        leadDTO.setGender("Male");
        leadDTO.setDob(LocalDate.of(1998, 1, 15));
        leadDTO.setEmail("sanket.deshpande@example.com");

        Mockito.when(leadsRepository.findById(any())).thenReturn(Optional.empty());
        Mockito.when(leadsRepository.save(any())).thenReturn(new Lead(leadDTO));


    }



    @Test
    public void testGetAllLeads() {
        // Mock data
        String mobileNumber = "1234567890";
        Lead lead1 = new Lead(/* provide necessary lead details */);
        Lead lead2 = new Lead(/* provide necessary lead details */);
        List<Lead> mockLeads = new ArrayList<>();
        mockLeads.add(lead1);
        mockLeads.add(lead2);

        // Mock repository behavior
        Mockito.when(leadsRepository.findByMobileNumber(mobileNumber)).thenReturn(mockLeads);

        // Call the method to be tested
        GetAllLeadsResponse result = leadService.getLeadsByMobileNumber(mobileNumber);

        // Assert the result
        assertEquals("success", result.getStatus());
        assertEquals(mockLeads, result.getData());
    }

    @Test
    public void testGetAllLeadsNoLeadsFound() {
        // Mock data
        String mobileNumber = "1234567890";

        // Mock repository behavior
        Mockito.when(leadsRepository.findByMobileNumber(mobileNumber)).thenReturn(new ArrayList<>());

        // Call the method to be tested
        GetAllLeadsResponse result = leadService.getLeadsByMobileNumber(mobileNumber);

        // Assert the result
        assertEquals("error", result.getStatus());
        assertEquals("E10011", result.getErrorResponse().getCode());
        assertEquals("No Lead found with the Mobile Number " + mobileNumber, result.getErrorResponse().getMessages().get(0));
    }

}
