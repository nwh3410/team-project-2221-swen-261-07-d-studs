package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.estore.api.estoreapi.model.Part;

/**
 * Implements the functionality for JSON file-based peristance for Parts
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as needed
 * 
 * @author SWEN Faculty
 *         SWEN-261-07-2221-team-d
 */
@Component
public class PartFileDAO implements PartDAO {
    Map<Integer,Part> parts;   // Provides a local cache of the part objects
                                // so that we don't need to read from the file
                                // each time
    private ObjectMapper objectMapper;  // Provides conversion between Part
                                        // objects and JSON text format written
                                        // to the file
    private static int nextId;  // The next Id to assign to a new part
    private String filename;    // Filename to read from and write to

    /**
     * Creates a Part File Data Access Object
     * 
     * @param filename Filename to read from and write to
     * @param objectMapper Provides JSON Object to/from Java Object serialization and deserialization
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    public PartFileDAO(@Value("${parts.file}") String filename,ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();  // load the parts from the file
    }

    /**
     * Generates the next id for a new {@linkplain Part part}
     * 
     * @return The next id
     */
    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    /**
     * Generates an array of {@linkplain Part parts} from the tree map
     * 
     * @return  The array of {@link Part parts}, may be empty
     */
    private Part[] getPartsArray() {
        return getPartsArray(null);
    }

    /**
     * Generates an array of {@linkplain Part parts} from the tree map for any
     * {@linkplain Part parts} that contains the text specified by containsText
     * <br>
     * If containsText is null, the array contains all of the {@linkplain Part parts}
     * in the tree map
     * 
     * @return  The array of {@link Part parts}, may be empty
     */
    private Part[] getPartsArray(String containsText) { // if containsText == null, no filter
        ArrayList<Part> partArrayList = new ArrayList<>();

        for (Part part : parts.values()) {
            if (containsText == null || part.getName().contains(containsText)) {
                partArrayList.add(part);
            }
        }

        Part[] partArray = new Part[partArrayList.size()];
        partArrayList.toArray(partArray);
        return partArray;
    }

    /**
     * Saves the {@linkplain Part parts} from the map into the file as an array of JSON objects
     * 
     * @return true if the {@link Part parts} were written successfully
     * 
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        Part[] partArray = getPartsArray();

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),partArray);
        return true;
    }

    /**
     * Loads {@linkplain Part Parts} from the JSON file into the map
     * <br>
     * Also sets next id to one more than the greatest id found in the file
     * 
     * @return true if the file was read successfully
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    private boolean load() throws IOException {
        parts = new TreeMap<>();
        nextId = 0;

        // Deserializes the JSON objects from the file into an array of parts
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        Part[] partArray = objectMapper.readValue(new File(filename),Part[].class);

        // Add each part to the tree map and keep track of the greatest id
        for (Part part : partArray) {
            parts.put(part.getId(),part);
            if (part.getId() > nextId)
                nextId = part.getId();
        }
        // Make the next id one greater than the maximum from the file
        ++nextId;
        return true;
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Part[] getParts() {
        synchronized(parts) {
            return getPartsArray();
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Part[] findParts(String containsText) {
        synchronized(parts) {
            return getPartsArray(containsText);
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Part getPart(int id) {
        synchronized(parts) {
            if (parts.containsKey(id))
                return parts.get(id);
            else
                return null;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Part createPart(Part part) throws IOException {
        synchronized(parts) {
            // We create a new part object because the id field is immutable
            // and we need to assign the next unique id
            Part newPart = new Part(nextId(),part.getName(),part.getQuantity(),part.getPrice(),part.getImage());
            parts.put(newPart.getId(),newPart);
            save(); // may throw an IOException
            return newPart;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Part updatePart(Part part) throws IOException {
        synchronized(parts) {
            if (parts.containsKey(part.getId()) == false)
                return null;  // part does not exist

            parts.put(part.getId(),part);
            save(); // may throw an IOException
            return part;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public boolean deletePart(int id) throws IOException {
        synchronized(parts) {
            if (parts.containsKey(id)) {
                parts.remove(id);
                return save();
            }
            else
                return false;
        }
    }
}
