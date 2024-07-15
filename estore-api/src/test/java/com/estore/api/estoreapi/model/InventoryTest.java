/**
 * Tests for Inventory class
 * 
 * @author: Nate Hall
 */

package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;


public class InventoryTest {

    private Inventory inventory;

    private ArrayList<Part> listOfPartsAdded = new ArrayList<>(); 

    private ArrayList<Part> listOfPartsNotAdded = new ArrayList<>();

    Part testPart = new Part(1, "test", 1, 3.0f, "NULL");

    

    int id;
    double price;
    int quantity;
    String name;

    public InventoryTest(){
        inventory = new Inventory();

        price = 3.0;
        for (int i = 0; i < 50; i++ ){
            id = i;
            name = "part" + id;
            int quantity = 1;
            price = price + (price * 0.5);
            String image = "NULL";
            listOfPartsAdded.add(new Part(id, name, quantity, price, image));
        }

        for (int i = 50; i < 100; i++ ){
            id = i;
            name = "part" + id;
            int quantity = 1;
            price = price + (price * 0.5);
            String image = "NULL";
            listOfPartsNotAdded.add(new Part(id, name, quantity, price, image));
        }

    }

    @Test
    void testResetInventory() {

        //adding parts back to the inventory
        for ( Part part : listOfPartsAdded ){
            inventory.AddPart(part);
        }

        inventory.ResetInventory();
        assertEquals(0, Inventory.CurrentInventory.size());
        assertEquals(0, inventory.GetInventorySize());
        assertEquals(0, inventory.GetTotalProducts());
    }


    @Test
    void testAddPart() {

        inventory.ResetInventory();
        
        //tests adding a part to the inventory
        for ( Part part : listOfPartsAdded){
            assertTrue(inventory.AddPart(part));
        }

        //tests adding duplicate parts to the inventory
        for ( Part part : listOfPartsAdded ){
            assertFalse(inventory.AddPart(part));
        }
        
        inventory.ResetInventory();
    }

    
    @Test
    void testGetInventorySize() {

        inventory.ResetInventory();
        
        for ( Part part : listOfPartsAdded){
            inventory.AddPart(part);
        }
        
        assertEquals(50, inventory.GetInventorySize());
        
        //testing if increasing the quantity of a part affects ths inventory size
        inventory.AddPart(testPart);
        inventory.IncermentQuantity(testPart, 5);
        assertEquals(56, inventory.GetInventorySize());

        inventory.ResetInventory();
    }

    @Test
    void testGetTotalProducts() {

        inventory.ResetInventory();

        for ( Part part : listOfPartsAdded){
            inventory.AddPart(part);
        }

        assertEquals(50, inventory.GetTotalProducts());

        //testing if adding a part before checking the value works
        inventory.AddPart(testPart);
        assertEquals(51, inventory.GetTotalProducts());
        

        //testing if increasing the quantity of a part affects ths inventory size
        inventory.IncermentQuantity(testPart, 5);
        assertEquals(51, inventory.GetTotalProducts());

        inventory.ResetInventory();
    }

    @Test
    void testIncermentQuantity() {

        inventory.ResetInventory();


        //tests incrementing a single part
        for ( Part part : listOfPartsAdded ){
            inventory.AddPart(part);
            assertTrue(inventory.IncermentQuantity(part, 50));
            assertEquals(51, Inventory.CurrentInventory.get(part));
        }

        //tests incrementing a part that isn't in the inventory
        for ( Part part : listOfPartsNotAdded ){
            assertTrue(!inventory.IncermentQuantity(part, 1));
        }

        inventory.ResetInventory();

    }
    
    @Test
    void testDecrementQuantity() {

        inventory.ResetInventory();

        //tests decrementing a single part
        for ( Part part : listOfPartsAdded ){
            inventory.AddPart(part);
            inventory.IncermentQuantity(part, 50);
            assertTrue(inventory.DecrementQuantity(part, 50));
            assertEquals(1, Inventory.CurrentInventory.get(part));
        }

        //tests decrementing a part that isn't in the inventory
        for ( Part part : listOfPartsNotAdded ){
            assertTrue(!inventory.DecrementQuantity(part, 1));
        }

        inventory.ResetInventory();

    }
    

    @Test
    void testRemovePart() {

        inventory.ResetInventory();

        //tests removing all parts from inventory
        for ( Part part : listOfPartsAdded ){
            inventory.AddPart(part);
        }
        for ( Part part : listOfPartsAdded ){
            assertTrue(inventory.RemovePart(part));
        }

        //tests removing parts from an empty inventory
        for ( Part part : listOfPartsAdded ){
            assertFalse(inventory.RemovePart(part));
        }

        //adding parts back to the inventory
        for ( Part part : listOfPartsAdded ){
            inventory.AddPart(part);
        }

        //testing removing parts that aren't in the inventory
        for ( Part part : listOfPartsNotAdded ){
            assertFalse(inventory.RemovePart(part));
        }

        //testing removing a part with a high quantity
        inventory.AddPart(testPart);
        inventory.IncermentQuantity(testPart, 50);
        assertTrue(inventory.RemovePart(testPart));

        inventory.ResetInventory();

    }

}
