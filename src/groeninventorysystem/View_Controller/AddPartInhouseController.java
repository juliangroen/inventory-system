package groeninventorysystem.View_Controller;

import groeninventorysystem.Model.InhousePart;
import groeninventorysystem.Model.Inventory;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class AddPartInhouseController implements Initializable{

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
    private TextField machineIDInput;

    @FXML
    private TextField maxInput;

    @FXML
    private TextField minInput;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    private final SceneChangerController sceneChanger = new SceneChangerController();

    private final Inventory mainInventory = MainScreenController.mainInventory;

    @FXML
    public void exitAddPartScreen(ActionEvent event) throws IOException {

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
    public void saveNewPart(ActionEvent event) throws IOException {

        // Check if min, max, are not empty, and check if min is less than max.
        if (!minInput.getText().trim().isEmpty() && !maxInput.getText().trim().isEmpty() && Integer.parseInt(minInput.getText()) < Integer.parseInt(maxInput.getText())) {

            // Check if inStock ammount is less than or equal to max or greater than to min.
            if (!invInput.getText().trim().isEmpty() && Integer.parseInt(invInput.getText()) <= Integer.parseInt(maxInput.getText()) && Integer.parseInt(invInput.getText()) >= Integer.parseInt(minInput.getText())) {

                InhousePart newPart = new InhousePart();

                newPart.setPartID(Integer.parseInt(partIDInput.getText()));
                
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

                if (!machineIDInput.getText().trim().isEmpty()) {

                    newPart.setMachineID(Integer.parseInt(machineIDInput.getText()));

                }

                mainInventory.addPart(newPart);

                // pass the event node and desired GUI filename into the changeScene mentod.
                sceneChanger.changeScene(saveButton, "MainScreen");

            } else {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalid Value");
                alert.setHeaderText("Invalid Inv value.");
                alert.setContentText("Please ensure that the Inv value is within the bounds of the Min and Max values.");
                alert.showAndWait();

            }

        } else {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid Value");
            alert.setHeaderText("Invalid Max or Min value.");
            alert.setContentText("Please ensure that the Min value is less than the Max value.");
            alert.showAndWait();

        }

    }

    @FXML
    public void selectOutsourcedPart(ActionEvent event) throws IOException {
        
        // pass the event node and desired GUI filename into the changeScene mentod.
        sceneChanger.changeScene(outsourcedRadio, "AddPartOutsourced");       

    }

	@Override
	public void initialize(URL url, ResourceBundle rb) {
        
        int currentID;

        if (mainInventory.getAllParts().isEmpty()) {
            
            currentID = 0;

        } else {

            currentID = mainInventory.getAllParts().get(mainInventory.getAllParts().size() - 1).getPartID();

        }

        partIDInput.setText(Integer.toString(currentID + 1));

	}	

}