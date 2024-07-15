package com.estore.api.estoreapi.model;


import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Represents a Part entity
 * 
 * @author SWEN Faculty
 *         SWEN-261-07-2221-team-d
 */
public class Part {
    // Package private for tests
    static final String STRING_FORMAT = "Part [id=%d, name=%s, quantity=%d, price=%.2f]";

    @JsonProperty("id") private int id;
    @JsonProperty("name") private String name;
    @JsonProperty("quantity") private int quantity;     //quantity and methods 
    @JsonProperty("price") private double price;
    @JsonProperty("image") private String image;

    /**
     * Create a part with the given id and name
     * @param id The id of the part
     * @param name The name of the part
     * @param quantity The quantity of the product
     * @param price The price of the part
     * @param image The image url
     * 
     * {@literal @}JsonProperty is used in serialization and deserialization
     * of the JSON object to the Java object in mapping the fields.  If a field
     * is not provided in the JSON object, the Java field gets the default Java
     * value, i.e. 0 for int
     */
    public Part(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("quantity") int quantity,@JsonProperty("price") double price,@JsonProperty("image") String image) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.image = image;

    }

    /**
     * Retrieves the id of the part
     * @return The id of the part
     */
    public int getId() {return id;}

    /**
     * Sets the price of the part - necessary for JSON object to Java object deserialization
     * @param price The price of the part
     */
    public void setPrice(double price) {this.price = price;}

    /**
     * Retrieves the price of the part
     * @return The price of the part
     */
    public double getPrice() {return price;}

    /**
     * Sets the name of the part - necessary for JSON object to Java object deserialization
     * @param name The name of the part
     */
    public void setName(String name) {this.name = name;}

    /**
     * Retrieves the name of the part
     * @return The name of the part
     */
    public String getName() {return name;}

    public int getQuantity() {return quantity;}

    public void setQuantity(int quantity) {this.quantity = quantity;}

    public String getImage() {return image;}

    public void setImage(String image) {this.image = image;}


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT,id,name,quantity,price);
    }
}