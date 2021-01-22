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
public class OutsourcedPart extends Part{
    
    private StringProperty companyName;
    
    public OutsourcedPart() { 
        super();
        companyName = new SimpleStringProperty();
    }
    
    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }
    
    public String getCompanyName() {
        return this.companyName.get();
    }
}
