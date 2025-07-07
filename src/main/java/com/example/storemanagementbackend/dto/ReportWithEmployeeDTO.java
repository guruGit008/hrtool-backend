package com.example.storemanagementbackend.dto;

import java.time.LocalDate;
import java.util.List;

public class ReportWithEmployeeDTO {
    private Long id;
    private String type;
    private String subtype;
    private String title;
    private String content;
    private LocalDate date;
    private String status;
    private String submittedBy;
    private String employeeName;
    private String employeeId;
    private String division;
    private List<String> attachments;
    private String customerName;
    private String designation;
    private String landlineOrMobile;
    private String emailId;
    private String remarks;
    private String productOrRequirements;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getSubtype() { return subtype; }
    public void setSubtype(String subtype) { this.subtype = subtype; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getSubmittedBy() { return submittedBy; }
    public void setSubmittedBy(String submittedBy) { this.submittedBy = submittedBy; }
    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    public String getDivision() {
        return division;
    }
    public void setDivision(String division) {
        this.division = division;
    }
    public List<String> getAttachments() { return attachments; }
    public void setAttachments(List<String> attachments) { this.attachments = attachments; }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public String getLandlineOrMobile() {
        return landlineOrMobile;
    }
    public void setLandlineOrMobile(String landlineOrMobile) {
        this.landlineOrMobile = landlineOrMobile;
    }
    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public String getProductOrRequirements() {
        return productOrRequirements;
    }
    public void setProductOrRequirements(String productOrRequirements) {
        this.productOrRequirements = productOrRequirements;
    }
} 