package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;


import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.estore.api.estoreapi.model.Login;

@Component
public class LoginFileDAO implements LoginDAO{
    Login[] logins;   // Provides a local cache of the login objects
                                // so that we don't need to read from the file
                                // each time
    private ObjectMapper objectMapper;  // Provides conversion between Login
                                        // objects and JSON text format written
                                        // to the file
    private String filename;  

    public LoginFileDAO(@Value("${login.file}") String filename, ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();  // load the parts from the file
    }



    private boolean load() throws IOException {
        // Deserializes the JSON objects from the file into an array of parts
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        Login[] loginArray = objectMapper.readValue(new File(filename),Login[].class);

        // Add each part to the tree map and keep track of the greatest id
        
        logins = loginArray;
        // Make the next id one greater than the maximum from the file
        return true;
    }

    /**
        * Retrieves a {@linkplain Login login} with the given user/pass
        * 
        * @param user Username and password to look for a Login object with
        * @param pass
        * @return a {@link Login login} object with the matching user/pass
        * <br>
        * null if no {@link Login login} with a matching user/pass is found
        * 
        * @throws IOException if an issue with underlying storage
        */
    @Override
    public Login getLogin(String user, String pass) throws IOException {
        synchronized(logins) {
            Login test = new Login(user, pass);
            for (Login login : logins){
                if (test.equals(login)) {
                    return login;
                }
            }
        }
        return new Login(user, pass);
    }

    /**
        * Creates and saves a {@linkplain Login login}
        * 
        * @param login {@linkplain Login login} object to be created and saved
        * <br>
        * The user/pass of the part object is used
        *
        * @return new {@link Part part} if successful, false otherwise 
        * 
        * @throws IOException if an issue with underlying storage
        */
    @Override
    public Login createLogin(Login login) throws IOException {
        int size = logins.length;
        size++;
        Login[] newLogins = new Login[size];
        this.logins = newLogins;
        return login;
    }
    
}
