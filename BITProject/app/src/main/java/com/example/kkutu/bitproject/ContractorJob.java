package com.example.kkutu.bitproject;

public class ContractorJob {

    private int job_Id;
    private String job_Type;
    private String request_Date;
    private String client_Name;
    private String client_Phoneno;
    private String client_Address;

    public ContractorJob()
    {

    }
    public ContractorJob(int job_Id,String job_Type,String request_Date,String client_Name,String client_Phoneno,String client_Address)
    {

    }

    public String getJob_Type() {
        return job_Type;
    }

    public int getJob_Id() {
        return job_Id;
    }

    public String getClient_Address() {
        return client_Address;
    }

    public String getClient_Phoneno() {
        return client_Phoneno;
    }

    public String getClient_Name() {
        return client_Name;
    }

    public String getRequest_Date() {
        return request_Date;
    }

    public void setJob_Type(String job_Type) {
        this.job_Type = job_Type;
    }

    public void setClient_Address(String client_Address) {
        this.client_Address = client_Address;
    }

    public void setClient_Name(String client_Name) {
        this.client_Name = client_Name;
    }

    public void setClient_Phoneno(String client_Phoneno) {
        this.client_Phoneno = client_Phoneno;
    }

    public void setJob_Id(int job_Id) {
        this.job_Id = job_Id;
    }

    public void setRequest_Date(String request_Date) {
        this.request_Date = request_Date;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
