package com.estore.api.estoreapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.estore.api.estoreapi.persistence.PartDAO;
import com.estore.api.estoreapi.model.Part;

/**
 * Handles the REST API requests for the Part resource
 * <p>
 * {@literal @}RestController Spring annotation identifies this class as a REST API
 * method handler to the Spring framework
 * 
 * @author SWEN Faculty
 *         SWEN-261-07-2221-team-d
 */

@RestController
@RequestMapping("parts")
public class PartController {
    private static final Logger LOG = Logger.getLogger(PartController.class.getName());
    private PartDAO partDao;

    /**
     * Creates a REST API controller to reponds to requests
     * 
     * @param partDao The {@link PartDAO Part Data Access Object} to perform CRUD operations
     * <br>
     * This dependency is injected by the Spring Framework
     */
    public PartController(PartDAO partDao) {
        this.partDao = partDao;
    }

    /**
     * Responds to the GET request for a {@linkplain Part part} for the given id
     * 
     * @param id The id used to locate the {@link Part part}
     * 
     * @return ResponseEntity with {@link Part part} object and HTTP status of OK if found<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("/{id}")
    public ResponseEntity<Part> getPart(@PathVariable int id) {
        LOG.info("GET /parts/" + id);
        try {
            Part part = partDao.getPart(id);
            if (part != null)
                return new ResponseEntity<Part>(part,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Responds to the GET request for all {@linkplain Part parts}
     * 
     * @return ResponseEntity with array of {@link Part part} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("")
    public ResponseEntity<Part[]> getParts() {
        LOG.info("GET /parts");
        try {
            Part[] parts = partDao.getParts();
            if (parts != null)
               return new ResponseEntity<Part[]>(parts,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Responds to the GET request for all {@linkplain Part parts} whose name contains
     * the text in name
     * 
     * @param name The name parameter which contains the text used to find the {@link Part parts}
     * 
     * @return ResponseEntity with array of {@link Part part} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * <p>
     * Example: Find all heroes that contain the text "ma"
     * GET http://localhost:8080/parts/?name=ma
     */
    @GetMapping("/")
    public ResponseEntity<Part[]> searchParts(@RequestParam String name) {
        LOG.info("GET /parts/?name="+name);

        try {
            Part[] part = partDao.findParts(name);
            if (part != null)
                return new ResponseEntity<Part[]>(part,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Creates a {@linkplain Part part} with the provided part object
     * 
     * @param part - The {@link Part part} to create
     * 
     * @return ResponseEntity with created {@link Part part} object and HTTP status of CREATED<br>
     * ResponseEntity with HTTP status of CONFLICT if {@link Part part} object already exists<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PostMapping("")
    public ResponseEntity<Part> createPart(@RequestBody Part part) {
        LOG.info("POST /parts " + part);

        try {
            Part newPart = partDao.createPart(part);
            if (part != null)
                return new ResponseEntity<Part>(newPart,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Updates the {@linkplain Part part} with the provided {@linkplain Part part} object, if it exists
     * 
     * @param part The {@link Part part} to update
     * 
     * @return ResponseEntity with updated {@link Part part} object and HTTP status of OK if updated<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PutMapping("")
    public ResponseEntity<Part> updatePart(@RequestBody Part part) {
        LOG.info("PUT /part " + part);

        try {
            Part updatePart = partDao.updatePart(part);
            if (part != null)
                return new ResponseEntity<Part>(updatePart,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Deletes a {@linkplain Part part} with the given id
     * 
     * @param id The id of the {@link Part part} to deleted
     * 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Part> deletePart(@PathVariable int id) {
        LOG.info("DELETE /parts/" + id);

        try {
            Boolean part = partDao.deletePart(id);
            if (part != false)
                return new ResponseEntity<Part>(HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
