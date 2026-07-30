package com.vehicletacker.vehiclemaintenancetracker;

import javafx.application.Application;
import model.User;

import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        //Application.launch(HelloApplication.class, args);
        User newuser = new User();
        Scanner input = new Scanner(System.in);

        System.out.println("Enter your ID");
        int Id = input.nextInt();

        System.out.println("Enter your full name");
        String fullName = input.nextLine();
        input.nextLine();

        System.out.println("Enter your contact");
        int contact = input.nextInt();
        input.nextLine();

        System.out.println("Enter your role");
        String role= input.nextLine();

        System.out.println("Enter your email ");
        String email = input.nextLine();

        System.out.println("Enter your username");
        String username = input.nextLine();

        System.out.println("Enter your password");
        String password = input.nextLine();

        newuser.setFullName(fullName);
        newuser.setUserContact(contact);
        newuser.setUserId(Id);
        newuser.setRole(role);
        newuser.setEmail(email);
        newuser.setPassword(password);
        newuser.setUsername(username);

        System.out.println(newuser.toString());


    }
}