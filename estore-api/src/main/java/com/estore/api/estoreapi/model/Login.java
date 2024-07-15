package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Login {

    //Whether the current login is an admin or not, public so other classes can grab it
    private static boolean admin = false;
    //Login credentials
    private final static String password = "STUDS4LIFE";
    private final static String username = "Admin";
    private String user;
    private String pass;

    public Login(@JsonProperty("user") String user, @JsonProperty("pass") String pass) {
        this.user = user;
        this.pass = pass;
        
    }

    //Checks given username and password against Admin equivalent
    public static void signIn(String attemptUsername, String attemptPassword) {
        if (attemptUsername.equals(username)) {
            if (attemptPassword.equals(password)) {
                admin = true;
            }
            else {
                admin = false;
            }
        }
        else {
            admin = false;
        }
    }
    //returns admin status
    public static boolean getAdminStatus() {
        return admin;
    }
    //returns username
    public String getUser() {
        return this.user;
    }

    //To string
    @Override
    public String toString() {
        return String.format(user,pass);
    }


}
