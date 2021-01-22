/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author connor
 */
public class Product {
    private IntegerProperty ID;
    private StringProperty name;
    private DoubleProperty price;
    private IntegerProperty stock;
    private IntegerProperty min;
    private IntegerProperty max;
    public ObservableList<Part> parts = FXCollections.observableArrayList();
    
    public Product() {
        ID = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
        price = new SimpleDoubleProperty();
        stock = new SimpleIntegerProperty();
        min = new SimpleIntegerProperty();
        max = new SimpleIntegerProperty();
    }
    
    public void setID(int ID) {
        this.ID.set(ID);
    }
    
    public void setName(String name) {
        this.name.set(name);
    }
    
    public void setPrice(double price) {
        this.price.set(price);
    }
    
    public void setStock(int stock) {
        this.stock.set(stock);
    }
    
    public void setMin(int min) {
        this.min.set(min);
    }
    
    public void setMax(int max) {
        this.max.set(max);
    }
    
    public int getID() {
        return this.ID.get();
    }
    
    public IntegerProperty productIDProperty() {
        return ID;
    }
    
    public String getName() {
        return this.name.get();
    }
    
    public StringProperty productNameProperty() {
        return name;
    }
    
    public double getPrice() {
        return this.price.get();
    }
    
    public DoubleProperty productPriceProperty() {
        return price;
    }
    
    public int getStock() {
        return this.stock.get();
    }
    
    public IntegerProperty productStockProperty() {
        return stock;
    }
    
    public int getMin() {
        return this.min.get();
    }
    
    public IntegerProperty productMinProperty() {
        return min;
    }
    
    public int getMax() {
        return this.max.get();
    }
    
    public IntegerProperty productMaxProperty() {
        return max;
    }    
    
    public void setProductParts(ObservableList<Part> parts) {
        this.parts = parts;
    }
    
    public ObservableList getProductParts() {
        return parts;
    }
}
