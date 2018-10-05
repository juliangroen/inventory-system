package groeninventorysystem.View_Controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneChangerController {
    
    // Event node and desired GUI filename passed into method.
    protected void changeScene(Node node, String fxmlString) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/groeninventorysystem/View_Controller/" + fxmlString + ".fxml"));

        // create a new root node using desired GUI file.
        Parent root = loader.load();

        // get main stage from the event node.
        Stage stage = (Stage) node.getScene().getWindow();

        // create a new scene with the new root node.
        Scene scene = new Scene(root);

        // set the active scene of the main stage to the newly created scene.
        stage.setScene(scene);
        
    }

}
