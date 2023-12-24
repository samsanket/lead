package com.mahindra.lead.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.mahindra.lead.dto.LeadDTO;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

@Entity
@Table(name = "leads")
public class Lead {



    @Id
    private Integer leadId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "gender")
    private String gender;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "email")
    private String email;

    public Lead() {
    }

    public Lead(Integer leadId, String firstName, String middleName, String lastName, String mobileNumber,
                String gender, LocalDate dob, String email) {
        this.leadId = leadId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
        this.dob = dob;
        this.email = email;
    }

    public Lead(LeadDTO leadDTO) {
        this.leadId=leadDTO.getLeadId();
        this.firstName = leadDTO.getFirstName();
        this.middleName = leadDTO.getMiddleName();
        this.lastName = leadDTO.getLastName();
        this.mobileNumber = leadDTO.getMobileNumber();
        this.gender = leadDTO.getGender();
        this.dob = leadDTO.getDob();
        this.email = leadDTO.getEmail();
    }


    public Integer getLeadId() {
        return leadId;
    }

    public void setLeadId(Integer leadId) {
        this.leadId = leadId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Lead{" +
                "leadId=" + leadId +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                '}';
    }
}
