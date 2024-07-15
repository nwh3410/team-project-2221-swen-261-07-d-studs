package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Unit testing file for Part.java
 * 
 * @author: Nate Hall
 */

@Tag("Model-tier")
public class PartTest {
    
    /**
     * Test class for creating a part and getting the
     * id, name, and price. Will create a part and then
     * use assertEquals to test getID(), getName(), and
     * getPrice().
     */

    @Test
    public void testPart() {

        int expected_id = 99;
        String expected_name = "Motherboard";
        int expected_quantity = 1;
        double expected_price = 150.00;
        String expected_image = "NULL";

        Part part = new Part(expected_id, expected_name, expected_quantity, expected_price,expected_image);

        assertEquals(expected_id, part.getId());
        assertEquals(expected_name, part.getName());
        assertEquals(expected_quantity, part.getQuantity());
        assertEquals(expected_price, part.getPrice());
    }

    /**
     * Test class for the setName function. Will
     * create a part and set the name to a different
     * string. Then assertEquals will determine whether
     * or not the function is working properly.
     */

    @Test
    public void testName() {

        int id = 99;
        String name = "Motherboard";
        int quantity = 1;
        double price = 150.00;
        String image = "NULL";
        Part part = new Part(id,name, quantity, price, image);

        String expected_name = "Hard Drive";

        part.setName(expected_name);

        assertEquals(expected_name,part.getName());
    }

    /**
     * Test class for the setPrice function. Will
     * create a part and set the price to a different
     * double. Then assertEquals will determine whether
     * or not the function is working properly.
     */

    @Test
    public void testPrice() {

        int id = 99;
        String name = "Motherboard";
        int quantity = 1;
        double price = 150.00;
        String image = "NULL";
        Part part = new Part(id,name,quantity,price,image);

        double expected_price = 200.00;

        part.setPrice(expected_price);

        assertEquals(expected_price,part.getPrice());
    }

    /**
     * Use assertEquals to test the toString
     * function.
     */

    @Test
    public void testToString() {
    
        int id = 99;
        String name = "Motherboard";
        int quantity = 1;
        double price = 150.00;
        String image = "NULL";
        String expected_string = String.format(Part.STRING_FORMAT,id,name,quantity,price);
        Part part = new Part(id,name,quantity,price,image);

        String actual_string = part.toString();

        assertEquals(expected_string,actual_string);
    }

}
