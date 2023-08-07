package com.techelevator.model;

public class SwitchUserDto {

    private String username;
    private String pin;

    public String getUsername() {
        return username;
    }

    public String getPin() {
        return pin;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "username='" + username + '\'' +
                '}';
    }

}
