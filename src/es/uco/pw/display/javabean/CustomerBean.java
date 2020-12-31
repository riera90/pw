package es.uco.pw.display.javabean;

import java.io.Serializable;

public class CustomerBean implements Serializable {
    private Integer userId;
    private Integer loginAttempts;

    public CustomerBean(){
        this.userId = -1;
        this.loginAttempts = 0;
    }

    public void setUserId(Integer userId){
        this.userId=userId;
    }

    public Integer getUserId(){
        return this.userId;
    }

    public Integer getLoginAttempts(){
        return this.loginAttempts;
    }

    public void registerLoginAttempt(){
        this.loginAttempts++;
    }

    public void clearLoginAttempt(){
        this.loginAttempts = 0;
    }
}
