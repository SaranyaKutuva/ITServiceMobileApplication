package com.example.kkutu.bitproject;

public class Login {
    private String userName;
    private String password;
    private String account_Type;
    private String account_Ref;

    public String getUserName()
    {
        return  userName;
    }
    public String getPassword()
    {
        return  password;
    }
    public String getAccount_Type(){return account_Type;}
    public String getAccount_Ref(){return  account_Ref;}
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public void setAccount_Type(String account_Type){this.account_Type = account_Type;}
    public void setAccount_Ref(String account_Ref){this.account_Ref = account_Ref;}
    Login()
    {

    }
    Login(String userName,String password,String account_Type,String account_Ref)
    {
        setUserName(userName);
        setPassword(password);
        setAccount_Type(account_Type);
        setAccount_Ref(account_Ref);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
