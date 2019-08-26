package com.example.kkutu.bitproject;

public class Jobs {

    private int jobRequest_Id;
    private String job_Type;
    private int client_Id;
    private String requestDate;
    private String contractorName;
    private String phoneNo;
    private String status;

    public void setClient_Id(int client_Id) {
        this.client_Id = client_Id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setJob_Type(String job_Type) {
        this.job_Type = job_Type;
    }

    public void setContractorName(String contractorName) {
        this.contractorName = contractorName;
    }

    public int getJobRequest_Id() {
        return jobRequest_Id;
    }

    public int getClient_Id() {
        return client_Id;
    }

    public String getJob_Type() {
        return job_Type;
    }

    public String getStatus() {
        return status;
    }

    public String getContractorName() {
        return contractorName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setJobRequest_Id(int jobRequest_Id) {
        this.jobRequest_Id = jobRequest_Id;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public Jobs()
    {

    }
    public Jobs(int jobRequest_Id,String job_Type,int client_Id,String requestDate,String contractorName,String phoneNo,String status)
    {
        setJobRequest_Id(jobRequest_Id);
        setJob_Type(job_Type);
        setClient_Id(client_Id);
        setRequestDate(requestDate);
        setContractorName(contractorName);
        setPhoneNo(phoneNo);
        setStatus(status);
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
