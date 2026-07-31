package com.vehicletacker.vehiclemaintenancetracker;

import javafx.application.Application;
import model.MaintenanceRecord;
import model.User;
import util.DBAccess;

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

        DBAccess db = new DBAccess();
        if (db.isConnected()){
            System.out.println("Database is connected");
        }
        else{
            System.out.println("Error connecting to Database");
        }




        /* User newuser = new User();
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
        System.out.println(newuser.toString()); */






    }
}