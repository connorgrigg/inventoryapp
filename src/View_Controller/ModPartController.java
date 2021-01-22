/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author connor
 */
public class ModPartController implements Initializable {

    @FXML
    private ToggleGroup ModPartToggle;
    @FXML
    private Button CancelModPart;
    @FXML
    private TextField ModPartMachineID;
    @FXML
    private TextField ModPartCompanyName;
    @FXML
    private RadioButton ModInHouse;
    @FXML
    private RadioButton ModPartOutsource;
    
    private int index = MainScreenController.getModPartIndex();
    private int partID;
    private Part part = Inventory.getPartInventory().get(index);
    @FXML
    private TextField ModPartNameField;
    @FXML
    private TextField ModPartInvField;
    @FXML
    private TextField ModPartPriceField;
    @FXML
    private TextField ModPartMaxField;
    @FXML
    private TextField ModPartMinField;
    @FXML
    private Button SaveModifyPart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partID = Inventory.getPartInventory().get(index).getID();
        ModPartNameField.setText(part.getName());
        ModPartInvField.setText(Integer.toString(part.getStock()));
        ModPartPriceField.setText(Double.toString(part.getPrice()));
        ModPartMaxField.setText(Integer.toString(part.getMax()));
        ModPartMinField.setText(Integer.toString(part.getMin()));
        if(part instanceof InhousePart) {
            ModInHouse.setSelected(true);
            ModPartMachineID.setText(Integer.toString(((InhousePart) Inventory.getPartInventory().get(index)).getMachineID()));
            ModPartMachineID.setEditable(true);
            ModPartCompanyName.setEditable(false);
        }
        else {
            ModPartOutsource.setSelected(true);
            ModPartCompanyName.setText(((OutsourcedPart) Inventory.getPartInventory().get(index)).getCompanyName());
            ModPartCompanyName.setEditable(true);
            ModPartMachineID.setEditable(false);            
        }            
    }    

    @FXML
    private void handleCancelModPart(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Confirm Cancel");
        alert.setContentText("Are you sure you want to cancel new part?");
        Optional<ButtonType> option = alert.showAndWait();
        if(option.get() == ButtonType.OK) {
            Inventory.fixPartID();
            cancelModPart(event);
        }
    }

    
    private void cancelModPart(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(loader);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();    
    }
    @FXML
    private void handleModInHouse(ActionEvent event) throws IOException {
        modInHouse(event);
    }
    
    private void modInHouse(ActionEvent event) throws IOException {
        ModPartMachineID.setEditable(true);
        ModPartCompanyName.setEditable(false);
        ModPartCompanyName.clear();
        part = new InhousePart();
    }

    @FXML
    private void handleModOutsource(ActionEvent event) throws IOException {
        modOutsource(event);
    }
    private void modOutsource(ActionEvent event) throws IOException {
        ModPartMachineID.setEditable(false);
        ModPartCompanyName.setEditable(true);
        ModPartMachineID.clear();
        part = new OutsourcedPart();
    }

    @FXML
    private void handleSaveModifyPart(ActionEvent event) throws IOException {
        saveModifyPart(event);
    }
    private void saveModifyPart(ActionEvent event) throws IOException {
        String partName = ModPartNameField.getText();
        int inv = Integer.parseInt(ModPartInvField.getText());
        double price = Double.parseDouble(ModPartPriceField.getText());
        int min = Integer.parseInt(ModPartMinField.getText());
        int max = Integer.parseInt(ModPartMaxField.getText());
        if(max >= min) {
            if(part instanceof InhousePart) {
                int machineID;
                machineID = Integer.parseInt(ModPartMachineID.getText());
                InhousePart iPart = new InhousePart();
                iPart.setID(partID);
                iPart.setName(partName);
                iPart.setPrice(price);
                iPart.setMin(min);
                iPart.setMax(max);
                iPart.setStock(inv);
                iPart.setMachineID(machineID);
                Inventory.updatePart(index, iPart);
                
                Parent loader = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
                Scene scene = new Scene(loader);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            }
            else {
                String companyName = ModPartCompanyName.getText();
                OutsourcedPart oPart = new OutsourcedPart();
                oPart.setID(partID);
                oPart.setName(partName);
                oPart.setPrice(price);
                oPart.setMin(min);
                oPart.setMax(max);
                oPart.setStock(inv);
                oPart.setCompanyName(companyName);
                Inventory.updatePart(index, oPart);
                
                Parent loader = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
                Scene scene = new Scene(loader);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            }
        }
        else {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Submission Error!");
                alert.setHeaderText("Invalid Part");
                alert.setContentText("Min value must not exceed Max value");
                alert.showAndWait();
        }    
    }
}
