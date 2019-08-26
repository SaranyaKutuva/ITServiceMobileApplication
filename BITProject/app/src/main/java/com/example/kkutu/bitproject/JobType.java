package com.example.kkutu.bitproject;

public class JobType {
    private int job_Type_Id;
    private String job_Type;

    public void setJob_Type(String job_Type) {
        this.job_Type = job_Type;
    }

    public void setJob_Type_Id(int job_Type_Id) {
        this.job_Type_Id = job_Type_Id;
    }

    public int getJob_Type_Id() {
        return job_Type_Id;
    }

    public String getJob_Type() {
        return job_Type;
    }
    public JobType()
    {

    }
    public JobType(int job_Type_Id,String job_Type)
    {
        setJob_Type(job_Type);
        setJob_Type_Id(job_Type_Id);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
