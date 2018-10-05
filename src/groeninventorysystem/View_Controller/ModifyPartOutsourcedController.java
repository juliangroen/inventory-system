package groeninventorysystem.View_Controller;

import groeninventorysystem.Model.Inventory;
import groeninventorysystem.Model.OutsourcedPart;
import groeninventorysystem.Model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ModifyPartOutsourcedController implements Initializable {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton inHouseRadio;

    @FXML
    private ToggleGroup sourceGroup;

    @FXML
    private RadioButton outsourcedRadio;

    @FXML
    private TextField partIDInput;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField invInput;

    @FXML
    private TextField priceInput;

    @FXML
    private TextField companyNameInput;

    @FXML
    private TextField maxInput;

    @FXML
    private TextField minInput;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    private final Inventory mainInventory = MainScreenController.mainInventory;

    private final SceneChangerController sceneChanger = new SceneChangerController();

    private Part selectedPart;

    private OutsourcedPart newPart;

    @FXML
    public void exitModifyPartScreen(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Part is not saved.");
        alert.setContentText("Would you like to proceed?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
                
            // pass the event node and desired GUI filename into the changeScene mentod.
            sceneChanger.changeScene(cancelButton, "MainScreen");

        } else {

        }

    }

    @FXML
    public void saveModifiedPart(ActionEvent event) throws IOException {

        newPart.setPartID(Integer.parseInt(partIDInput.getText()));

        // check each input field if it is empty before getting values.
        
        if (!nameInput.getText().trim().isEmpty()) {
            newPart.setName(nameInput.getText());
        }
        if (!invInput.getText().trim().isEmpty()) {
            newPart.setInStock(Integer.parseInt(invInput.getText()));
        }
        if (!priceInput.getText().trim().isEmpty()) {
            newPart.setPrice(Double.parseDouble(priceInput.getText()));
        }
        if (!maxInput.getText().trim().isEmpty()) {
            newPart.setMax(Integer.parseInt(maxInput.getText()));
        }
        if (!minInput.getText().trim().isEmpty()) {
            newPart.setMin(Integer.parseInt(minInput.getText()));
        }
        if (!companyNameInput.getText().trim().isEmpty()) {
            newPart.setCompanyName(companyNameInput.getText());
        }

        mainInventory.updatePart(newPart.getPartID(), newPart);
              
        // pass the event node and desired GUI filename into the changeScene mentod.
        sceneChanger.changeScene(saveButton, "MainScreen");

    }

    @FXML
    public void selectInhousePart(ActionEvent event) throws IOException {

        // pass the event node and desired GUI filename into the changeScene mentod.
        sceneChanger.changeScene(inHouseRadio, "ModifyPartInhouse");

    }

	@Override
	public void initialize(URL url, ResourceBundle rb) {

        selectedPart = MainScreenController.selectedPart;

        newPart = new OutsourcedPart(
        selectedPart.getPartID(), 
        selectedPart.getName(), 
        selectedPart.getPrice(), 
        selectedPart.getInStock(), 
        selectedPart.getMin(), 
        selectedPart.getMax(), 
        "");

        try {

            newPart.setCompanyName(((OutsourcedPart)selectedPart).getCompanyName());

        } catch(ClassCastException ex) {
           
        } finally {

        partIDInput.setText(Integer.toString(newPart.getPartID()));
        nameInput.setText(newPart.getName());
        invInput.setText(Integer.toString(newPart.getInStock()));
        priceInput.setText(Double.toString(newPart.getPrice()));
        maxInput.setText(Integer.toString(newPart.getMax()));
        minInput.setText(Integer.toString(newPart.getMin()));
        companyNameInput.setText(newPart.getCompanyName());

        }

	}	
	
}
