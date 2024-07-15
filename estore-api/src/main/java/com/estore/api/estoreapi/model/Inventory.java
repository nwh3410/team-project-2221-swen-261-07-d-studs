/**
 * Holds the inventory of parts that of the store
 * capable of adding parts, removing parts, and adding 
 * or removing a specific quantity of a single part
 * @author Key'Mon Jenkins
 */

package com.estore.api.estoreapi.model;

import java.util.*;

public class Inventory {

    /** contains each product in our inventory
     * key - Part
     * value - Quantity of the part in the inventory
    */
    public static HashMap<Part,Integer> CurrentInventory;
    /** total number of items in inventory */
    private static int InventorySize;
    /** number of products in inventory */
    private static int TotalProducts;

    public Inventory(){
      CurrentInventory = new HashMap<>();
    }

    /**
     * Adds a part to the inventory and checks two things
     * if the part is not in the inventory, it is added with a value(quantity) of 1
     * else returns false
     * @param part part desired to be added to the inventory
     * @return true if the part was successfully added to the inventory
     */
    public boolean AddPart(Part part){
      if ( !CurrentInventory.containsKey(part) ){
        CurrentInventory.put(part, 1);
        InventorySize += 1;
        TotalProducts += 1;
        return true;
      }
      return false;
    }

    /**
     * removes a part from the inventory and checks two things
     * if the part is in the inventory, it is completely removed 
     * else returns false
     * @param part
     * @return true if the part was successfully removed to the inventory
     */
    public boolean RemovePart(Part part){
      if ( CurrentInventory.containsKey(part) ){
        InventorySize -= CurrentInventory.get(part);
        CurrentInventory.remove(part);
        TotalProducts -= 1;
        return true;
      }
      return false;
    }

    /**
     * adds to the quantity of a Part in the inventory
     * used if user returns an item or if admin adds 
     * to the quantity of a part
     * @param part  
     * @param quantity
     * @return true if the item was successfully incremented
     */
    public boolean IncermentQuantity(Part part, int quantity){
      if ( CurrentInventory.containsKey(part) ){
        int currentVal = CurrentInventory.get(part);
        currentVal += quantity;
        CurrentInventory.put(part, currentVal);
        InventorySize += quantity;
        return true;
      }
      return false;
    }

    /**
     * subtracts from the quantity of a Part in the invantory
     * used if a customer checks out 
     * @param part
     * @param quantity
     * @return true if the item was successfully decremented
     */
    public boolean DecrementQuantity(Part part, int quantity){
      if ( CurrentInventory.containsKey(part) ){
        int currentVal = CurrentInventory.get(part);
        currentVal -= quantity;
        CurrentInventory.put(part, currentVal);
        InventorySize -= quantity;
        return true;
      }
      return false;
    }
    
    /**
     * 
     */
    public int GetTotalProducts(){
      return TotalProducts;
    }

    /**
     * 
     * @return
     */
    public int GetInventorySize(){
      return InventorySize;
    }

    /**
     * 
     */
    public void ResetInventory(){
      TotalProducts = 0;
      InventorySize = 0;
      CurrentInventory.clear();
    }

}
