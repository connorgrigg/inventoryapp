/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import static Model.Inventory.*;
import Model.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

/**
 * FXML Controller class
 *
 * @author connor
 */
public class MainScreenController implements Initializable {

    @FXML
    private Label MainTitleLabel;
    @FXML
    private TableColumn<Part, Integer> MainPartIDCol;
    @FXML
    private TableColumn<Part, String> MainPartNameCol;
    @FXML
    private TableColumn<Part, Integer> MainPartInvCol;
    @FXML
    private TableColumn<Part, Double> MainPartPriceCol;
    @FXML
    private TableColumn<Product, Integer> MainProductIDCol;
    @FXML
    private TableColumn<Product, String> MainProductNameCol;
    @FXML
    private TableColumn<Product, Integer> MainProductInvCol;
    @FXML
    private TableColumn<Product, Double> MainProductPriceCol;
    @FXML
    private Button AddPart;
    @FXML
    private Button IMSExit;
    @FXML
    private Button ModifyPartButton;
    @FXML
    private Button AddProduct;
    @FXML
    private Button ModProduct;
    @FXML
    private TableView<Part> MainPartsTable;
    @FXML
    private TableView<Product> MainProductsTable;
    @FXML
    private Button DeleteParts;
    @FXML
    private Button DeleteProducts;
    @FXML
    private Button SearchPart;
    @FXML
    private TextField SearchPartField;
    @FXML
    private Button SearchProduct;
    @FXML
    private TextField SearchProductField;
    
    private static Part modPart;
    private static Product modProduct;
    private static int modPartIndex;
    private static int modProductIndex;
    
    public static int getModPartIndex() {
        return modPartIndex;
    }
    
    public static int getModProductIndex() {
        return modProductIndex;
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MainPartIDCol.setCellValueFactory(cellData -> cellData.getValue().getPartIDProperty().asObject());
        MainPartNameCol.setCellValueFactory(cellData -> cellData.getValue().getPartNameProperty());
        MainPartInvCol.setCellValueFactory(cellData -> cellData.getValue().getPartStockProperty().asObject());
        MainPartPriceCol.setCellValueFactory(cellData -> cellData.getValue().getPartPriceProperty().asObject());
        MainProductIDCol.setCellValueFactory(cellData -> cellData.getValue().productIDProperty().asObject());
        MainProductNameCol.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        MainProductInvCol.setCellValueFactory(cellData -> cellData.getValue().productStockProperty().asObject());
        MainProductPriceCol.setCellValueFactory(cellData -> cellData.getValue().productPriceProperty().asObject());
        updatePartTable();
        updateProductTable();
    }    
    


    @FXML
    private void handleAddPart(ActionEvent event) throws IOException {
        openAddParts(event);
    }
    private void openAddParts(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        Scene scene = new Scene(loader);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();       
    }

    private void IMSExit(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Exit");
        alert.setHeaderText("Confirm system exit");
        alert.setContentText("Are you sure you want to Quit?");
        Optional<ButtonType> option = alert.showAndWait();
        if(option.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    @FXML
    private void handleIMSExit(ActionEvent event) throws IOException{
        IMSExit(event);
    }

    @FXML
    private void handleModifyPartButton(ActionEvent event) throws IOException {
        modifyPartButton(event);
    }
    
    private void modifyPartButton(ActionEvent event) throws IOException {
        modPart = MainPartsTable.getSelectionModel().getSelectedItem();
        modPartIndex = getPartInventory().indexOf(modPart);
        Parent loader = FXMLLoader.load(getClass().getResource("ModPart.fxml"));
        Scene scene = new Scene(loader);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show(); 
    }

    @FXML
    private void handleAddProduct(ActionEvent event) throws IOException {
        addProduct(event);
    }
    
    private void addProduct(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        Scene scene = new Scene(loader);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show(); 
    }

    @FXML
    private void handleModProduct(ActionEvent event) throws IOException {
        modProduct(event);
    }
    private void modProduct(ActionEvent event) throws IOException {
        modProduct = MainProductsTable.getSelectionModel().getSelectedItem();
        modProductIndex = getProdInventory().indexOf(modProduct);
        Parent loader = FXMLLoader.load(getClass().getResource("ModProduct.fxml"));
        Scene scene = new Scene(loader);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show(); 
    }

    public void updatePartTable() {
        try {
            MainPartsTable.setItems(getPartInventory());
        }
        catch(Exception e) {
        }
    }
        
    public void updateProductTable() {
        try {
            MainProductsTable.setItems(getProdInventory());
        }
        catch(Exception e) {
            
        }
    }

    @FXML
    private void handleDeleteParts(ActionEvent event) {
        deleteParts(event);
    }
    
    private void deleteParts(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Confirm deletion of part");
        alert.setContentText("Are you sure you want to delete part?");
        Optional<ButtonType> option = alert.showAndWait();
        if(option.get() == ButtonType.OK) {
            Part part = MainPartsTable.getSelectionModel().getSelectedItem();
            Inventory.deletePart(part);
            updatePartTable();
        }
    }

    @FXML
    private void handleDeleteProducts(ActionEvent event) {
        deleteProducts(event);
    }
    private void deleteProducts(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Confirm deletion of part");
        alert.setContentText("Are you sure you want to delete part?");
        Optional<ButtonType> option = alert.showAndWait();
        if(option.get() == ButtonType.OK) {
            Product prod = MainProductsTable.getSelectionModel().getSelectedItem();
            Inventory.deleteProd(prod);
            updateProductTable();
        }
    }

    @FXML
    private void handleSearchPart(ActionEvent event) throws IOException{
        searchPart(event);
    }
            
            
    private void searchPart(ActionEvent event) {
        String searchTerm = SearchPartField.getText();
        ObservableList<Part> searchList = FXCollections.observableArrayList();
        if(Inventory.isInt(searchTerm) == true) {
            Part part = Inventory.lookupPart(Integer.parseInt(searchTerm));
            searchList.add(part);
            updatePartsTable(searchList);        
        }
        else if(isInt(searchTerm) == false) {
            searchList = (Inventory.lookupPart(searchTerm));
            updatePartsTable(searchList);        
        }     
    }

    @FXML
    private void handleSearchProduct(ActionEvent event) {
        searchProduct(event);
    }
    
    private void searchProduct(ActionEvent event) {
        String searchTerm = SearchProductField.getText();
        ObservableList<Product> searchList = FXCollections.observableArrayList();
        if(Inventory.isInt(searchTerm) == true) {
            Product product = Inventory.lookupProduct(Integer.parseInt(searchTerm));
            searchList.add(product);
            updateProductsTable(searchList);        
        }
        else if(isInt(searchTerm) == false) {
            searchList = (Inventory.lookupProduct(searchTerm));
            updateProductsTable(searchList);        
        }
        
    }
    
    
    private void updatePartsTable(ObservableList<Part> list) {
        MainPartsTable.setItems(list);
    }
    
    private void updateProductsTable(ObservableList<Product> list) {
        MainProductsTable.setItems(list);
    }
}    