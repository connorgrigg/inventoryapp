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
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author connor
 */
public class AddPartController implements Initializable {
    @FXML
    private Button AddPartSave;
    @FXML
    private Button AddPartCancel;
    @FXML
    private RadioButton inHouseAddButton;
    @FXML
    private RadioButton addOutsourceButton;
    @FXML
    private TextField AddMachineID;
    @FXML
    private TextField AddCompanyName;
    @FXML
    private ToggleGroup inHouseOutsourceAdd;
    @FXML
    private TextField PartIDField;
    @FXML
    private TextField PartNameField;
    @FXML
    private TextField PartInvField;
    @FXML
    private TextField PartPriceField;
    @FXML
    private TextField PartMaxField;
    @FXML
    private TextField PartMinField;
    private int partID;
    private int outsourcedPart = -1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partID = Inventory.getPartIDCount();
        PartIDField.setText("Auto-Gen: " + partID);
    }    
    @FXML
    private void handleCancelAdd(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Confirm Cancel");
        alert.setContentText("Are you sure you want to cancel new part?");
        Optional<ButtonType> option = alert.showAndWait();
        if(option.get() == ButtonType.OK) {
            Inventory.fixPartID();
            cancelAddPart(event);
        }
    }
    private void cancelAddPart(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(loader);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();    
    }
    @FXML
    private void handleSaveAdd(ActionEvent event) throws IOException{
        String name = PartNameField.getText();
        int inv = Integer.parseInt(PartInvField.getText());
        double price = Double.parseDouble(PartPriceField.getText());
        int max = Integer.parseInt(PartMaxField.getText());
        int min = Integer.parseInt(PartMinField.getText());
        String companyName;
        int machineID;
        if(max >= min) {
            if(outsourcedPart == 1) {
                    companyName = AddCompanyName.getText();
                    OutsourcedPart oPart = new OutsourcedPart();
                    oPart.setID(partID);
                    oPart.setName(name);
                    oPart.setStock(inv);
                    oPart.setPrice(price);
                    oPart.setMax(max);
                    oPart.setMin(min);
                    oPart.setCompanyName(companyName);
                    Inventory.addPart(oPart);
                    
                    Parent loader = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
                    Scene scene = new Scene(loader);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    window.show(); 
                }             
            else if (outsourcedPart == 0){
                machineID = Integer.parseInt(AddMachineID.getText());
                InhousePart iPart = new InhousePart();
                iPart.setID(partID);
                iPart.setName(name);
                iPart.setStock(inv);
                iPart.setPrice(price);
                iPart.setMax(max);
                iPart.setMin(min);
                iPart.setMachineID(machineID);
                Inventory.addPart(iPart);
                
                Parent loader = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
                Scene scene = new Scene(loader);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show(); 
            }
            else if (outsourcedPart == -1) {
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setHeaderText("Invalid Submission");
                errorAlert.setContentText("Select whether part is Inhouse or Outsourced");
                errorAlert.showAndWait();
            }
        }
        else
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Submission Error!");
                alert.setHeaderText("Invalid Part");
                alert.setContentText("Min value must not exceed Max value");
                alert.showAndWait();
            }    
        }
    
    @FXML
    private void handleAddInHouse(ActionEvent event) throws IOException {
        addInHouse(event);
    }
    private void addInHouse(ActionEvent event) throws IOException {
        AddMachineID.setEditable(true);
        AddCompanyName.setEditable(false);       
        AddCompanyName.clear();
        outsourcedPart = 0;
    }

    @FXML
    private void handleAddOutsource(ActionEvent event) throws IOException {
        addOutsource(event);
    }
    private void addOutsource(ActionEvent event) throws IOException {
        AddMachineID.setEditable(false);
        AddCompanyName.setEditable(true);
        AddMachineID.clear();
        outsourcedPart = 1;
    }
}
