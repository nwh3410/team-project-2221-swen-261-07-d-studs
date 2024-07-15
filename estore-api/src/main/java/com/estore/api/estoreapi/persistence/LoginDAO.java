package com.estore.api.estoreapi.persistence;

import java.io.IOException;

import com.estore.api.estoreapi.model.Login;

public interface LoginDAO {
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
       Login getLogin(String user, String pass) throws IOException;
   
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
       Login createLogin(Login login) throws IOException;
}
