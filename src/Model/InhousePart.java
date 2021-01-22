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
public class InhousePart extends Part {
    
    private IntegerProperty machineID;
    
    public InhousePart() { 
        super();
        machineID = new SimpleIntegerProperty();
    }
    
    public void setMachineID(int machineID) {
        this.machineID.set(machineID);
    }
    
    public int getMachineID() {
        return this.machineID.get();
    }  
}
