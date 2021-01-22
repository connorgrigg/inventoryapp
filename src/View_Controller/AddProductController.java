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
public class AddProductController implements Initializable {

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
    private Button CancelAddProd;
    @FXML
    private TextField AddProductIDField;
    @FXML
    private TextField AddProductNameField;
    @FXML
    private TextField AddProductInvField;
    @FXML
    private TextField AddProductPriceField;
    @FXML
    private TextField AddProductMaxField;
    @FXML
    private TextField AddProductMinField;
    @FXML
    private Button SaveAddProduct;
    private int prodID;
    @FXML
    private TableView<Part> PartProductAdd;
    @FXML
    private Button AddPartAddProduct;
    @FXML
    private TableView<Part> PartProductDelete;
    @FXML
    private Button AddPartDeleteProduct;
    private ObservableList<Part> existingParts = FXCollections.observableArrayList();
    @FXML
    private Button SearchAddPart;
    @FXML
    private TextField SearchAddPartField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prodID = Inventory.getProdIDCount();
        AddProductIDField.setText("Auto-Gen: " + prodID);
        updatePartsTable();
        updateUsedPartsTable();
        
        MainProductIDCol.setCellValueFactory(cellData -> cellData.getValue().getPartIDProperty().asObject());
        MainProductNameCol.setCellValueFactory(cellData -> cellData.getValue().getPartNameProperty());
        MainProductInvCol.setCellValueFactory(cellData -> cellData.getValue().getPartStockProperty().asObject());
        MainProductPriceCol.setCellValueFactory(cellData -> cellData.getValue().getPartPriceProperty().asObject());
        
        MainProductIDCol1.setCellValueFactory(cellData -> cellData.getValue().getPartIDProperty().asObject());
        MainProductNameCol1.setCellValueFactory(cellData -> cellData.getValue().getPartNameProperty());
        MainProductInvCol1.setCellValueFactory(cellData -> cellData.getValue().getPartStockProperty().asObject());
        MainProductPriceCol1.setCellValueFactory(cellData -> cellData.getValue().getPartPriceProperty().asObject());
    }    

    @FXML
    private void handleCancelAddProd(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Confirm cancellation of Product?");
        alert.setContentText("Are you sure you want to cancel new product?");
        Optional<ButtonType> option = alert.showAndWait();
        if(option.get() == ButtonType.OK) {
            Inventory.fixProdID();
            cancelAddProd(event);
        }
    }
    
    private void cancelAddProd(ActionEvent event) throws IOException{
        Parent loader = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(loader);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show(); 
    }

    @FXML
    private void handleSaveAddProduct(ActionEvent event) throws IOException{
        saveAddProd(event);
    }
    
    private void saveAddProd(ActionEvent event) throws IOException {
        String name = AddProductNameField.getText();
        int inv = Integer.parseInt(AddProductInvField.getText());
        double price = Double.parseDouble(AddProductPriceField.getText());
        int max = Integer.parseInt(AddProductMaxField.getText());
        int min = Integer.parseInt(AddProductMinField.getText());
        if(max >= min) {
            Product product = new Product();
            product.setID(prodID);
            product.setName(name);
            product.setStock(inv);
            product.setPrice(price);
            product.setMax(max);
            product.setMin(min);
            product.setProductParts(existingParts);
            Inventory.addProduct(product);
            
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
    
    private void updateUsedPartsTable() { 
        PartProductDelete.setItems(existingParts);
    }
    
    private void updatePartsTable() { 
        PartProductAdd.setItems(getPartInventory());  
    }

    @FXML
    private void handleAddPartAddProduct(ActionEvent event) {
        addPartAddProduct(event);
    }
    
    private void addPartAddProduct(ActionEvent event) {
        Part part = PartProductAdd.getSelectionModel().getSelectedItem();
        existingParts.add(part);
        updateUsedPartsTable();
    }

    @FXML
    private void handleAddPartDeleteProduct(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Confirm deletion of part");
        alert.setContentText("Are you sure you want to delete part?");
        Optional<ButtonType> option = alert.showAndWait();
        if(option.get() == ButtonType.OK) {
            addPartDeleteProduct(event);
        }
    }
    
    private void addPartDeleteProduct(ActionEvent event) {
        Part part = PartProductDelete.getSelectionModel().getSelectedItem();
        existingParts.remove(part);
        updateUsedPartsTable();
    }

    @FXML
    private void handleSearchAddPart(ActionEvent event) {
        searchAddPart(event);
    }
    
    private void searchAddPart(ActionEvent event) { 
        String searchTerm = SearchAddPartField.getText();
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
        if(SearchAddPartField.getText().equals(""))
            updatePartsTable();
    }
}
    

