package com.vehicletacker.vehiclemaintenancetracker;

import javafx.application.Application;
import model.User;

public class Launcher {
    public static void main(String[] args) {
        //Application.launch(HelloApplication.class, args);
        User newuser = new User();
        newuser.setFullName("John Doe");
        newuser.setUserId(1);
        newuser.setRole("Customer");
        newuser.setEmail("john@gmail.com");
        newuser.setPassword("john123");
        newuser.setUsername("john");

        System.out.println(newuser.toString());


    }
}