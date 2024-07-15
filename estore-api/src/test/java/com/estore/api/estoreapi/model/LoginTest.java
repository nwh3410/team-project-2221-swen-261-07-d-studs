package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


/**
 * Unit testing file for LoginTest.java
 * 
 * @author: Ian Aiken
 */
@Tag("Model-tier")
public class LoginTest {
    


    @Test
    public void testAdminLogin() {

        String test_user = "Admin";
        String test_password = "STUDS4LIFE";
        boolean expected_result = true;
        Login.signIn(test_user, test_password);
        boolean temp = Login.getAdminStatus();

        assertEquals(expected_result, temp);
    }


    @Test
    public void testNonAdminLogin() {

        String test_user = "Kelp26";
        String test_password = "fart";
        boolean expected_result = false;
        Login.signIn(test_user, test_password);
        boolean temp = Login.getAdminStatus();

        assertEquals(expected_result, temp);
    }
}
