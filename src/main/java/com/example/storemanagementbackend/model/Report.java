package com.example.storemanagementbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "reports") // Defines the table name in the database
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    private String subtype;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 2000)
    private String content;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String status;

    private String submittedBy;
    private String approvedBy;
    private LocalDate approvedDate;

    @ElementCollection
    @CollectionTable(name = "report_attachments", joinColumns = @JoinColumn(name = "report_id"))
    @Column(name = "attachment_url")
    private List<String> attachments;

    private String customerName;
    private String designation;
    private String landlineOrMobile;
    private String emailId;
    private String remarks;
    private String productOrRequirements;
    private String division;

    public Report() {}

    public Report(String type, String subtype, String title, String content, LocalDate date, String status, String submittedBy) {
        this.type = type;
        this.subtype = subtype;
        this.title = title;
        this.content = content;
        this.date = date;
        this.status = status;
        this.submittedBy = submittedBy;
    }

    // Getters and Setters

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

    public String getApprovedBy() { return approvedBy; }

    public void setApprovedBy(String approvedBy) { this.approvedBy = approvedBy; }

    public LocalDate getApprovedDate() { return approvedDate; }

    public void setApprovedDate(LocalDate approvedDate) { this.approvedDate = approvedDate; }

    public List<String> getAttachments() { return attachments; }

    public void setAttachments(List<String> attachments) { this.attachments = attachments; }

    public String getCustomerName() { return customerName; }

    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getDesignation() { return designation; }

    public void setDesignation(String designation) { this.designation = designation; }

    public String getLandlineOrMobile() { return landlineOrMobile; }

    public void setLandlineOrMobile(String landlineOrMobile) { this.landlineOrMobile = landlineOrMobile; }

    public String getEmailId() { return emailId; }

    public void setEmailId(String emailId) { this.emailId = emailId; }

    public String getRemarks() { return remarks; }

    public void setRemarks(String remarks) { this.remarks = remarks; }

    public String getProductOrRequirements() { return productOrRequirements; }

    public void setProductOrRequirements(String productOrRequirements) { this.productOrRequirements = productOrRequirements; }

    public String getDivision() { return division; }

    public void setDivision(String division) { this.division = division; }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", subtype='" + subtype + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", submittedBy='" + submittedBy + '\'' +
                ", approvedBy='" + approvedBy + '\'' +
                ", approvedDate=" + approvedDate +
                ", attachments=" + attachments +
                ", customerName='" + customerName + '\'' +
                ", designation='" + designation + '\'' +
                ", landlineOrMobile='" + landlineOrMobile + '\'' +
                ", emailId='" + emailId + '\'' +
                ", remarks='" + remarks + '\'' +
                ", productOrRequirements='" + productOrRequirements + '\'' +
                ", division='" + division + '\'' +
                '}';
    }
}
//ccsfsf