/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import javafx.beans.property.*;

/**
 *
 * @author connor
 */
public abstract class Part {
    
    private IntegerProperty ID;
    private StringProperty name;
    private DoubleProperty price;
    private IntegerProperty stock;
    private IntegerProperty min;
    private IntegerProperty max;
    
    public Part() {
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
    public IntegerProperty getPartIDProperty() {
        return ID;
    }
    
    public String getName() {
        return this.name.get();
    }
    public StringProperty getPartNameProperty() {
        return name;
    }    
    public double getPrice() {
        return this.price.get();
    }
    public DoubleProperty getPartPriceProperty() {
        return price;
    }
    public int getStock() {
        return this.stock.get();
    }
    public IntegerProperty getPartStockProperty() {
        return stock;
    }
    public int getMin() {
        return this.min.get();
    }
    
    public int getMax() {
        return this.max.get();
    }
}
