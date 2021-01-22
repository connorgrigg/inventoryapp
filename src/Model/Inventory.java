/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import View_Controller.MainScreenController;
import javafx.collections.*;
/**
 *
 * @author connor
 */
public class Inventory {
    @SuppressWarnings("FieldMayBeFinal")
    private static ObservableList<Product> prodInv = FXCollections.observableArrayList();
    private static ObservableList<Part> partInv = FXCollections.observableArrayList();
    private static int partIDCount = 0;
    private static int productIDCount = 0;

    
    public static boolean isInt(String s) {
            try {
                    Integer.parseInt(s);
                    return true;
            }
            catch (Exception e) {
                return false;
            }
    }
    
    public static void addPart(Part part) {
        partInv.add(part);
    }
    
    public static void addProduct(Product product) {
        prodInv.add(product);
    }    
    
        
    public static Part lookupPart(int partID) {
        Part tempPart = null;
        for(int i = 0; i < partInv.size(); i++) {
            Part part = partInv.get(i);
            if(partID == part.getID()) {
                tempPart = part;
                break;
            }
        }
        return tempPart;
    }
    
   public static Product lookupProduct(int productID) {
      Product tempProduct = null;
        for(int i = 0; i < prodInv.size(); i++) {
            Product product = prodInv.get(i);
            if(productID == product.getID()) {
                tempProduct = product;
                break;
            }
        }
        return tempProduct;
   }
   
   public static ObservableList<Part> lookupPart(String partName) {
      
       ObservableList<Part> returnList = FXCollections.observableArrayList();
       
       for(int i = 0; i < partInv.size(); i++) {
           Part part = partInv.get(i);
           if(partName.equals(part.getName())) {
               returnList.add(part);
           }
       }       
       return returnList;
    }
   
   public static ObservableList<Product> lookupProduct(String productName) {
      
       ObservableList<Product> returnList = FXCollections.observableArrayList();
       
       for(int i = 0; i < prodInv.size(); i++)
       {
           Product product = prodInv.get(i);
           if(productName.equals(product.getName()))
               returnList.add(product);
       }       
       
       return returnList;
   }
    
    public static void updatePart(int index, Part part){
        partInv.set(index, part);
    }
    
    public static void updateProd(int index, Product product){
        prodInv.set(index, product);
    }
    
    public static void deletePart(Part part){
        partInv.remove(part);
    }
    
    public static void deleteProd(Product product){
        prodInv.remove(product);
    }
 
    public static ObservableList<Part> getPartInventory() {
        return partInv;
    }
    
    public static ObservableList<Product> getProdInventory() {
        return prodInv;
    }
    
    public static int getPartIDCount(){
        partIDCount++;
        return partIDCount;
    }
    
    public static int cleanIDCount() {
        int cleanID = partIDCount;
        return cleanID;
    }
    
    public static int getProdIDCount(){
        productIDCount++;
        return productIDCount;
    }
    //if user cancels, these reduce part/product ID to not skip numbers 
    public static void fixPartID() {
        partIDCount--;
    }
    
    public static void fixProdID() {
        productIDCount--;
    }
}