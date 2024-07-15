package com.estore.api.estoreapi.persistence;

import java.io.IOException;
import com.estore.api.estoreapi.model.Part;

/**
 * Defines the interface for Part object persistence
 * 
 * @author SWEN Faculty
 *         SWEN-261-07-2221-team-d
 */
public interface PartDAO {
    /**
     * Retrieves all {@linkplain Part parts}
     * 
     * @return An array of {@link Part part} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Part[] getParts() throws IOException;

    /**
     * Finds all {@linkplain Part parts} whose name contains the given text
     * 
     * @param containsText The text to match against
     * 
     * @return An array of {@link Part parts} whose nemes contains the given text, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Part[] findParts(String containsText) throws IOException;

    /**
     * Retrieves a {@linkplain Part part} with the given id
     * 
     * @param id The id of the {@link Part part} to get
     * 
     * @return a {@link Part part} object with the matching id
     * <br>
     * null if no {@link Part part} with a matching id is found
     * 
     * @throws IOException if an issue with underlying storage
     */
    Part getPart(int id) throws IOException;

    /**
     * Creates and saves a {@linkplain Part part}
     * 
     * @param hero {@linkplain Part part} object to be created and saved
     * <br>
     * The id of the part object is ignored and a new uniqe id is assigned
     *
     * @return new {@link Part part} if successful, false otherwise 
     * 
     * @throws IOException if an issue with underlying storage
     */
    Part createPart(Part hero) throws IOException;

    /**
     * Updates and saves a {@linkplain Part part}
     * 
     * @param {@link Part part} object to be updated and saved
     * 
     * @return updated {@link Part part} if successful, null if
     * {@link Part part} could not be found
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    Part updatePart(Part hero) throws IOException;

    /**
     * Deletes a {@linkplain Part part} with the given id
     * 
     * @param id The id of the {@link Part part}
     * 
     * @return true if the {@link Part part} was deleted
     * <br>
     * false if part with the given id does not exist
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    boolean deletePart(int id) throws IOException;
}
