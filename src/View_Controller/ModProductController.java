/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.*;
import static Model.Inventory.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.*;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;


/**
 * FXML Controller class
 *
 * @author connor
 */
public class ModProductController implements Initializable {

    @FXML
    private TableColumn<Part, Integer> MainProductIDCol;
    @FXML
    private TableColumn<Part, String> MainProductNameCol;
    @FXML
    private TableColumn<Part, Integer> MainProductInvCol;
    @FXML
    private TableColumn<Part, Double> MainProductPriceCol;
    @FXML
    private TableColumn<Part, Integer> MainProductIDCol1;
    @FXML
    private TableColumn<Part, String> MainProductNameCol1;
    @FXML
    private TableColumn<Part, Integer> MainProductInvCol1;
    @FXML
    private TableColumn<Part, Double> MainProductPriceCol1;
    @FXML
    private Button CancelModProd;
    @FXML
    private TextField ModProdIDField;
    @FXML
    private TextField ModProdNameField;
    @FXML
    private TextField ModProdInvField;
    @FXML
    private TextField ModProdPriceField;
    @FXML
    private TextField ModProdMaxField;
    @FXML
    private TextField ModProdMinField;
    @FXML
    private Button AddModProd;
    @FXML
    private Button ModProdSave;
    @FXML
    private Button DeleteModProd;
    @FXML
    private Button SearchModProd;
    @FXML
    private TextField SearchModProdField;
    
    private int index = MainScreenController.getModProductIndex();
    private int prodID;
    private Product product = Inventory.getProdInventory().get(index);
    private ObservableList<Part> existingParts = FXCollections.observableArrayList();
    @FXML
    private TableView<Part> PartProductAdd;
    @FXML
    private TableView<Part> PartProductDelete;
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {  

        prodID = Inventory.getProdInventory().get(index).getID();
        ModProdNameField.setText(product.getName());
        ModProdInvField.setText(Integer.toString(product.getStock()));
        ModProdPriceField.setText(Double.toString(product.getPrice()));
        ModProdMaxField.setText(Integer.toString(product.getMax()));
        ModProdMinField.setText(Integer.toString(product.getMin()));
        
        MainProductIDCol.setCellValueFactory(cellData -> cellData.getValue().getPartIDProperty().asObject());
        MainProductNameCol.setCellValueFactory(cellData -> cellData.getValue().getPartNameProperty());
        MainProductInvCol.setCellValueFactory(cellData -> cellData.getValue().getPartStockProperty().asObject());
        MainProductPriceCol.setCellValueFactory(cellData -> cellData.getValue().getPartPriceProperty().asObject());
        
        existingParts = product.getProductParts();
        
        MainProductIDCol1.setCellValueFactory(cellData -> cellData.getValue().getPartIDProperty().asObject());
        MainProductNameCol1.setCellValueFactory(cellData -> cellData.getValue().getPartNameProperty());
        MainProductInvCol1.setCellValueFactory(cellData -> cellData.getValue().getPartStockProperty().asObject());
        MainProductPriceCol1.setCellValueFactory(cellData -> cellData.getValue().getPartPriceProperty().asObject());
        
        updatePartsTable();
        updateUsedPartsTable();
    }    

    @FXML
    private void handleCancelModProd (ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Confirm Cancel");
        alert.setContentText("Are you sure you want to cancel Modify Product?");
        Optional<ButtonType> option = alert.showAndWait();
        if(option.get() == ButtonType.OK) {
            cancelModProd(event);
        }
    }
    private void cancelModProd(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(loader);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show(); 
    }

    @FXML
    private void handleAddModProd(ActionEvent event) {
        addModProd(event);
    }
    
    private void addModProd(ActionEvent event) {
        Part part = PartProductAdd.getSelectionModel().getSelectedItem();
        existingParts.add(part);
        updateUsedPartsTable();
    }
    
    private void updateUsedPartsTable() { 
        PartProductDelete.setItems(existingParts);
    }
    
    private void updatePartsTable() { 
        PartProductAdd.setItems(getPartInventory());  
    }

    @FXML
    private void handleModProdSave(ActionEvent event) throws IOException {
        saveModProd(event);
    }
    
    private void saveModProd(ActionEvent event) throws IOException {
        String prodName = ModProdNameField.getText();
        int inv = Integer.parseInt(ModProdInvField.getText());
        double price = Double.parseDouble(ModProdPriceField.getText());
        int min = Integer.parseInt(ModProdMinField.getText());
        int max = Integer.parseInt(ModProdMaxField.getText());
        if(max >= min) {
            product.setName(prodName);
            product.setStock(inv);
            product.setPrice(price);
            product.setMin(min);
            product.setMax(max);
            product.setProductParts(existingParts);
            Inventory.updateProd(index, product);

            Parent loader = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene scene = new Scene(loader);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();   
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Submission Error!");
            alert.setHeaderText("Invalid Product");
            alert.setContentText("Min value must not exceed Max value");
            alert.showAndWait();
        }
    }
    

    @FXML
    private void handleDeleteModProd(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText("Confirm Delete");
        alert.setContentText("Are you sure you want to Delete part?");
        Optional<ButtonType> option = alert.showAndWait();
        if(option.get() == ButtonType.OK) {
            deleteModProd(event);
        }
    }
    
    private void deleteModProd(ActionEvent event) {
        Part part = PartProductDelete.getSelectionModel().getSelectedItem();
        existingParts.remove(part);
        updateUsedPartsTable();
    }

    @FXML
    private void handleSearchModProd(ActionEvent event) {
        searchModProd(event);
    }
    private  void searchModProd(ActionEvent event) {
        String searchTerm = SearchModProdField.getText();
        ObservableList<Part> searchList = FXCollections.observableArrayList();
        if(Inventory.isInt(searchTerm) == true) {
            Part part = Inventory.lookupPart(Integer.parseInt(searchTerm));
            searchList.add(part);
            PartProductAdd.setItems(searchList);        
        }
        else if(isInt(searchTerm) == false) {
            searchList = (Inventory.lookupPart(searchTerm));
            PartProductAdd.setItems(searchList);        
        }
        if(SearchModProdField.getText().equals(""))
            updatePartsTable();
        
    }
    
}