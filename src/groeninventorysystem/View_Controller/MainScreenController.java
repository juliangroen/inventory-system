package groeninventorysystem.View_Controller;

import groeninventorysystem.Model.InhousePart;
import groeninventorysystem.Model.Inventory;
import groeninventorysystem.Model.OutsourcedPart;
import groeninventorysystem.Model.Part;
import groeninventorysystem.Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainScreenController implements Initializable {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button searchPartsButton;

    @FXML
    private TextField searchPartsInput;
    
    @FXML
    private TableView<Part> partsTableMain;

    @FXML
    private TableColumn<Part, Integer> partIDColumn;

    @FXML
    private TableColumn<Part, String> partNameColumn;

    @FXML
    private TableColumn<Part, Integer> partInvColumn;

    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    @FXML
    private Button addPartsButton;

    @FXML
    private Button modifyPartsButton;

    @FXML
    private Button deletePartsButton;

    @FXML
    private TableView<Product> productsTableMain;

    @FXML
    private TableColumn<Product, Integer> productIDColumn;

    @FXML
    private TableColumn<Product, String> productNameColumn;

    @FXML
    private TableColumn<Product, Integer> productInvColumn;

    @FXML
    private TableColumn<Product, Double> productPriceColumn;

    @FXML
    private Button addProductsButton;

    @FXML
    private Button modifyProductsButton;

    @FXML
    private Button deleteProductsButton;

    @FXML
    private Button searchProductsButton;

    @FXML
    private TextField searchProductsInput;

    @FXML
    private Button exitMainScreenButton;

    @FXML
    private ObservableList<Part> partSearchData = FXCollections.observableArrayList();

    @FXML
    private ObservableList<Product> productSearchData = FXCollections.observableArrayList();

    private final SceneChangerController sceneChanger = new SceneChangerController();

    public static Inventory mainInventory = new Inventory();

    static boolean loaded;
    
    public static Part selectedPart;

    public static Product selectedProduct;

    @FXML
    public void addNewPart(ActionEvent event) throws IOException {
        
        // pass the event node and desired GUI filename into the changeScene mentod.
        sceneChanger.changeScene(addPartsButton, "AddPartInhouse");
    }

    @FXML
    public void addNewProduct(ActionEvent event) throws IOException {

        // pass the event node and desired GUI filename into the changeScene mentod.
        sceneChanger.changeScene(addProductsButton, "AddProduct");

    }

    @FXML
    public void deleteSelectedPart(ActionEvent event) {

        selectedPart = partsTableMain.getSelectionModel().getSelectedItem();

        try {

            if (selectedPart.getPartID()> 0 && selectedPart != null) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Delete selected part.");
                alert.setContentText("Would you like to proceed?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){

                    mainInventory.deletePart(selectedPart);

                } else {

                }

            }
            
        } catch(NullPointerException ex) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("No part selected!");
            alert.setContentText("Please select a part and try again.");
            alert.showAndWait();         

        }

    }

    @FXML
    public void deleteSelectedProduct(ActionEvent event) {

        selectedProduct = productsTableMain.getSelectionModel().getSelectedItem();

        try {

            if (selectedProduct.getProductID() > 0 && selectedProduct != null) {
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Delete selected product.");
                alert.setContentText("Would you like to proceed?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){

                    if (selectedProduct.associatedPartsProperty().isEmpty()) {

                        mainInventory.removeProduct(selectedProduct);

                    } else {

                        Alert alert2 = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Delete Product");
                        alert.setHeaderText("Unable to delete product.");
                        alert.setContentText("Cannot delete a product that contains associated parts.");
                        alert.showAndWait();  

                    }

                } else {

                }

            }
            
        } catch(NullPointerException ex) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("No product selected!");
            alert.setContentText("Please select a product and try again.");
            alert.showAndWait();         

        }

    }

    @FXML
    public void exitMainScreen(ActionEvent event) {
       
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Exit program");
        alert.setContentText("Are you sure you would like to exit?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){

            System.exit(0);

        } else {

        }

    }

    @FXML
    public void modifySelectedPart(ActionEvent event) throws IOException {

        selectedPart = partsTableMain.getSelectionModel().getSelectedItem();

        try {

            if (selectedPart.getPartID() > 0 && selectedPart != null) {

                if (selectedPart instanceof InhousePart) {

                // pass the event node and desired GUI filename into the changeScene mentod.
                sceneChanger.changeScene(modifyPartsButton, "ModifyPartInhouse");

                } else if (selectedPart instanceof OutsourcedPart) {

                // pass the event node and desired GUI filename into the changeScene mentod.
                sceneChanger.changeScene(modifyPartsButton, "ModifyPartOutsourced");

                }

            }

        } catch(NullPointerException ex) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("No part selected!");
            alert.setContentText("Please select a part and try again.");
            alert.showAndWait();           

        }

    }

    @FXML
    public void modifySelectedProduct(ActionEvent event) throws IOException {

        selectedProduct = productsTableMain.getSelectionModel().getSelectedItem();

        try {

            if (selectedProduct.getProductID() > 0 && selectedProduct != null) {

                // pass the event node and desired GUI filename into the changeScene mentod.
                sceneChanger.changeScene(modifyProductsButton, "ModifyProduct");

            }
            
        } catch(NullPointerException ex) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("No product selected!");
            alert.setContentText("Please select a product and try again.");
            alert.showAndWait();         

        }

    }

    @FXML
    public void searchForParts(ActionEvent event) {

        String searchItem = searchPartsInput.getText();

        try {
            
            int itemNumber = Integer.parseInt(searchItem);
            
            if (mainInventory.lookupPart(itemNumber) != null) {

                if (partSearchData.isEmpty()) {

                    partSearchData.add(mainInventory.lookupPart(itemNumber));

                } else {

                    partSearchData = partsTableMain.getItems();
                    partSearchData.add(mainInventory.lookupPart(itemNumber));

                }

                partIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
                partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                partInvColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
                partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
                partsTableMain.setItems(partSearchData);

            } else {
                
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Search Information");
                alert.setHeaderText("Error!");
                alert.setContentText("Part not found.");
                alert.showAndWait();

            }

        } catch(NumberFormatException ex) {
            
        }

    }

    @FXML
    public void searchForProducts(ActionEvent event) {

        String searchItem = searchProductsInput.getText();

        try {
            
            int itemNumber = Integer.parseInt(searchItem);
                    
            if (mainInventory.lookupProduct(itemNumber) != null) {

                if (productSearchData.isEmpty()) {

                        productSearchData.add(mainInventory.lookupProduct(itemNumber));

                    } else {

                        productSearchData = productsTableMain.getItems();
                        productSearchData.add(mainInventory.lookupProduct(itemNumber));

                    }

                    productIDColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
                    productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                    productInvColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
                    productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
                    productsTableMain.setItems(productSearchData);

            } else {
                
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Search Information");
                alert.setHeaderText("Error!");
                alert.setContentText("Product not found.");
                alert.showAndWait();

            }

        } catch(NumberFormatException ex) {
            
        }

    }

	@Override
	public void initialize(URL url, ResourceBundle rb) {

        // Generate sample part data

        /*
        if (!loaded) {
            for (int i = 0; i < 2; i++) {
                
                InhousePart part = new InhousePart();
                part.setPartID(mainInventory.getAllParts().size() + 1);
                part.setName("Inhouse Part " + (i + 1));
                part.setInStock(0);
                part.setPrice(10.0 * (i + 1));
                part.setMax(100);
                part.setMin(0);
                part.setMachineID(0 + (i + 1));
                
                mainInventory.getAllParts().add(part);
            }
            
            for (int i = 0; i < 2; i++) {
                
                OutsourcedPart part = new OutsourcedPart();
                part.setPartID(mainInventory.getAllParts().size() + 1);
                part.setName("Outsourced Part " + (i + 1));
                part.setInStock(0);
                part.setPrice(10.0 * (i + 1));
                part.setMax(100);
                part.setMin(0);
                part.setCompanyName("Company " + (i + 1));
                
                mainInventory.getAllParts().add(part);
            }

            loaded = true;
        }
        */

        // Cell factories for the Parts Table.
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partsTableMain.setItems(mainInventory.getAllParts());

        // Cell factories for the Products Table.
        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        productsTableMain.setItems(mainInventory.getProducts());
        
	}	
	
}
