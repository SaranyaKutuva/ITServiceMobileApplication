package com.example.kkutu.bitproject;

public class ClientLocation {
    private int location_Id;
    private String location_Name;
    private int client_Id;

    public void setClient_Id(int client_Id) {
        this.client_Id = client_Id;
    }

    public int getClient_Id() {
        return client_Id;
    }

    public void setLocation_Id(int location_Id) {
        this.location_Id = location_Id;
    }

    public void setLocation_Name(String location_Name) {
        this.location_Name = location_Name;
    }

    public int getLocation_Id() {
        return location_Id;
    }

    public String getLocation_Name() {
        return location_Name;
    }

    public ClientLocation()
    {

    }
    public ClientLocation(int location_Id,String location_Name,int client_Id)
    {
        setLocation_Id(location_Id);
        setLocation_Name(location_Name);
        setClient_Id(client_Id);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
