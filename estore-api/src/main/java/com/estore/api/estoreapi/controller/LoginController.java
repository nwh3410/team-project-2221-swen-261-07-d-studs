package com.estore.api.estoreapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estore.api.estoreapi.model.Login;
import com.estore.api.estoreapi.persistence.LoginDAO;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles the REST API requests for the Login resource
 * <p>
 * {@literal @}RestController Spring annotation identifies this class as a REST
 * API
 * method handler to the Spring framework
 * 
 * @author SWEN Faculty
 *         SWEN-261-07-2221-team-d
 */

@RestController
@RequestMapping("login")
public class LoginController {
    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());
    private LoginDAO LoginDao;

    /**
     * Creates a REST API controller to reponds to requests
     * 
     * @param loginDao The {@link loginDAO Login Data Access Object} to perform CRUD operations
     * <br>
     * This dependency is injected by the Spring Framework
     */
    public LoginController(LoginDAO loginDAO) {

        this.LoginDao = loginDAO;
    }

    /**
     * Creates a {@linkplain Login login} with the provided login object
     * 
     * @param login- The {@link Login login} to create
     * 
     * @return ResponseEntity with created {@link Login login} object and HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PostMapping("")
    public ResponseEntity<Login> createPart(@RequestBody Login login) {
        LOG.info("POST /login " + login);

        try {
            Login newlogin = LoginDao.createLogin(login);
            if (login != null)
                return new ResponseEntity<Login>(newlogin,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Responds to the GET request for a {@linkplain Login login} for the given id
     * 
     * @param user Username and password to look for a Login object with
     * @param pass 
     * 
     * @return ResponseEntity with {@link Part part} object and HTTP status of OK if found<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("/{find}/{user}/{pass}")
    public ResponseEntity<Login> findLogin(@PathVariable String user, @PathVariable String pass, @PathVariable String find) {
        LOG.info("GET /parts/" + user + "/" + pass);
        try {
            Login login = LoginDao.getLogin(user, pass);
            if (login != null)
                return new ResponseEntity<Login>(login,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Responds to the GET request for a {@linkplain Login login} for the given user/pass
     * 
     * @param user Username and password to look for a Login object with
     * @param pass
     * 
     * @return true or false based on whether username and password match Admin 
     */
    @GetMapping("/{username}/{password}")
    public ResponseEntity<Boolean> getLogin(@PathVariable String username, @PathVariable String password) {
        System.out.println("CALLED LOGIN SERVER");
        Login.signIn(username, password);
        boolean admin = Login.getAdminStatus();
        if (admin == true) {
            System.out.println("ALL GOOD");
            return new ResponseEntity<Boolean>(true, HttpStatus.ACCEPTED);
        } else {
            System.out.println("NOT ALL GOOD");
            return new ResponseEntity<Boolean>(false, HttpStatus.ACCEPTED);
        }
    }

}
