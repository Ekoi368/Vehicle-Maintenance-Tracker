package com.vehicletacker.vehiclemaintenancetracker;

import javafx.application.Application;
import model.MaintenanceRecord;
import model.User;

import java.util.Scanner;

public class Launcher {
    public Launcher() {
    }

    public static void main(String[] args) {
        //Application.launch(HelloApplication.class, args);
        Scanner input =new Scanner(System.in);
        System.out.println("Enter your ID");
        int userid = input.nextInt();
        System.out.println("Enter your Full Name");
        String FullName = input.next();
        System.out.println("Enter your UserId");
        String UserId = input.next();
        System.out.println("Enter your Username");
        String username =input.next();
        System.out.println("Enter your Password");
        String password = input.next();
        System.out.println("Enter your Email");
        String email = input.next();
        System.out.println("Enter your Role");
        String role = input.next();



        User newuser = new User();
        newuser.setFullName(newuser.getFullName());
        newuser.setUserId(userid);
        newuser.setRole(role);
        newuser.setEmail(email);
        newuser.setPassword(password);
        newuser.setUsername(username);



        System.out.println(newuser.toString());






    }
}