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
        String mobileNumber = "1234567890";
        Lead lead1 = new Lead();
        lead1.setLeadId(1);
        lead1.setFirstName("John");
        lead1.setLastName("Doe");
        lead1.setMobileNumber("9876543210");
        lead1.setGender("Male");
        lead1.setDob(LocalDate.of(1990, 1, 15));
        lead1.setEmail("john.doe@example.com");

        Lead lead2 = new Lead();
        lead2.setLeadId(1);
        lead2.setFirstName("sanket");
        lead2.setLastName("Doe");
        lead2.setMobileNumber("7218515261");
        lead2.setGender("Male");
        lead2.setDob(LocalDate.of(1990, 1, 15));
        lead2.setEmail("sanket.doe@example.com");

        List<Lead> mockLeads = new ArrayList<>();
        mockLeads.add(lead1);
        mockLeads.add(lead2);

        Mockito.when(leadsRepository.findByMobileNumber(mobileNumber)).thenReturn(mockLeads);

        GetAllLeadsResponse result = leadService.getLeadsByMobileNumber(mobileNumber);

        assertEquals("success", result.getStatus());
        assertEquals(mockLeads, result.getData());
    }

    @Test
    public void testGetAllLeadsNoLeadsFound() {
        String mobileNumber = "1234567890";

        Mockito.when(leadsRepository.findByMobileNumber(mobileNumber)).thenReturn(new ArrayList<>());

        GetAllLeadsResponse result = leadService.getLeadsByMobileNumber(mobileNumber);

        assertEquals("error", result.getStatus());
        assertEquals("E10011", result.getErrorResponse().getCode());
        assertEquals("No Lead found with the Mobile Number " + mobileNumber, result.getErrorResponse().getMessages().get(0));
    }

}
