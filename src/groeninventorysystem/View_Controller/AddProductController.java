package groeninventorysystem.View_Controller;

import groeninventorysystem.Model.Inventory;
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
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AddProductController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField productIDInput;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField invInput;

    @FXML
    private TextField priceInput;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchInput;
    
    @FXML
    private TableView<Part> tableTop;

    @FXML
    private TableColumn<Part, Integer> partIDColumnTop;

    @FXML
    private TableColumn<Part, String> partNameColumnTop;

    @FXML
    private TableColumn<Part, Integer> invColumnTop;

    @FXML
    private TableColumn<Part, Double> priceColumnTop;

    @FXML
    private Button addButton;
    
    @FXML
    private TableView<Part> tableBottom;

    @FXML
    private TableColumn<Part, Integer> partIDColumnBottom;

    @FXML
    private TableColumn<Part, String> partNameColumnBottom;

    @FXML
    private TableColumn<Part, Integer> invColumnBottom;

    @FXML
    private TableColumn<Part, Double> priceColumnBottom;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField minInput;

    @FXML
    private TextField maxInput;

    @FXML
    private Button saveButton;

    @FXML
    private Button exitButton;

    @FXML
    private ObservableList<Part> partSearchData = FXCollections.observableArrayList();

    @FXML
    private final ObservableList<Part> tempParts = FXCollections.observableArrayList();

    private final SceneChangerController sceneChanger = new SceneChangerController();

    private final Inventory mainInventory = MainScreenController.mainInventory;

    private Part selectedPartTop;

    private Part selectedPartBottom;

    @FXML
    public void addSelectedPart(ActionEvent event) {

        selectedPartTop = tableTop.getSelectionModel().getSelectedItem();

        tempParts.add(selectedPartTop);

    }

    @FXML
    public void deleteSelectedPart(ActionEvent event) {

        selectedPartBottom = tableBottom.getSelectionModel().getSelectedItem();

        try {

            if (selectedPartBottom.getPartID()> 0 && selectedPartBottom != null) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Delete selected part.");
                alert.setContentText("Would you like to proceed?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){

                    tempParts.remove(selectedPartBottom);

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
    public void searchForParts(ActionEvent event) {

        String searchItem = searchInput.getText();

        try {
            
            int itemNumber = Integer.parseInt(searchItem);
            
            if (mainInventory.lookupPart(itemNumber) != null) {

                if (partSearchData.isEmpty()) {

                    partSearchData.add(mainInventory.lookupPart(itemNumber));

                } else {

                    partSearchData = tableTop.getItems();
                    partSearchData.add(mainInventory.lookupPart(itemNumber));

                }

                partIDColumnTop.setCellValueFactory(new PropertyValueFactory<>("partID"));
                partNameColumnTop.setCellValueFactory(new PropertyValueFactory<>("name"));
                invColumnTop.setCellValueFactory(new PropertyValueFactory<>("inv"));
                priceColumnTop.setCellValueFactory(new PropertyValueFactory<>("price"));
                tableTop.setItems(partSearchData);

            } else {
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Search Information");
                alert.setHeaderText("Error!");
                alert.setContentText("Part not found.");
                alert.showAndWait();

            }

        } catch(NumberFormatException ex) {
            
        }

    }

    @FXML
    public void exitAddProductScreen(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Product is not saved.");
        alert.setContentText("Would you like to proceed?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
                
            // pass the event node and desired GUI filename into the changeScene mentod.
            sceneChanger.changeScene(exitButton, "MainScreen");

        } else {

        }

    }

    @FXML
    public void saveNewProduct(ActionEvent event) throws IOException {

        double partTotal = 0.0;

        for (Part part : tempParts) {
            partTotal = partTotal + part.getPrice();
        }

        if (tempParts.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add Product");
            alert.setHeaderText("Unable to save product.");
            alert.setContentText("Product must contain at least one associated part.");
            alert.showAndWait();           

        } else if (partTotal > (Double.parseDouble(priceInput.getText()))) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add Product");
            alert.setHeaderText("Unable to save product.");
            alert.setContentText("Product price must be greater than or equal to the total cost of all it's parts.");
            alert.showAndWait();             

        } else if (nameInput.getText().trim().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add Product");
            alert.setHeaderText("Unable to save product.");
            alert.setContentText("Please enter a name for the product.");
            alert.showAndWait(); 

        } else if (priceInput.getText().trim().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add Product");
            alert.setHeaderText("Unable to save product.");
            alert.setContentText("Please provide a price for the product.");
            alert.showAndWait(); 

        } else if (invInput.getText().trim().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add Product");
            alert.setHeaderText("Unable to save product.");
            alert.setContentText("Please provide an inventory amount for the product.");
            alert.showAndWait(); 

        } else {

            Product newProduct = new Product();

            newProduct.setProductID(Integer.parseInt(productIDInput.getText()));

            // check each input field if it is empty before getting values.
            
            if (!nameInput.getText().trim().isEmpty()) {

                newProduct.setName(nameInput.getText());

            }

            if (!invInput.getText().trim().isEmpty()) {

                newProduct.setInStock(Integer.parseInt(invInput.getText()));

            }

            if (!priceInput.getText().trim().isEmpty()) {

                newProduct.setPrice(Double.parseDouble(priceInput.getText()));

            }

            if (!maxInput.getText().trim().isEmpty()) {

                newProduct.setMax(Integer.parseInt(maxInput.getText()));

            }

            if (!minInput.getText().trim().isEmpty()) {

                newProduct.setMin(Integer.parseInt(minInput.getText()));

            }

            if (!tempParts.isEmpty()) {

                tempParts.forEach((part) -> {

                    newProduct.addAssociatedPart(part);

                });

            }

            mainInventory.addProduct(newProduct);
            
            // pass the event node and desired GUI filename into the changeScene mentod.
            sceneChanger.changeScene(saveButton, "MainScreen");

        } 

    }

	@Override
	public void initialize(URL url, ResourceBundle rb) {

        int currentID;
        
        if (mainInventory.getProducts().isEmpty()) {

            currentID = 0;     

        } else {

            currentID = mainInventory.getProducts().get(mainInventory.getProducts().size() - 1).getProductID();

        }

        productIDInput.setText(Integer.toString(currentID + 1));

        invInput.setText("0");
        priceInput.setText("0.0");

        partIDColumnTop.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumnTop.setCellValueFactory(new PropertyValueFactory<>("name"));
        invColumnTop.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        priceColumnTop.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableTop.setItems(mainInventory.getAllParts());

        partIDColumnBottom.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumnBottom.setCellValueFactory(new PropertyValueFactory<>("name"));
        invColumnBottom.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        priceColumnBottom.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableBottom.setItems(tempParts);

	}	
	
}
