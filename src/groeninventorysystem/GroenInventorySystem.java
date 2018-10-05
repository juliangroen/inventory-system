package groeninventorysystem;

import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

// JavaFX class must inherit from Application class
public class GroenInventorySystem extends Application {

	@Override
    // Create default stage within class declaration.
	public void start(Stage mainStage) throws Exception { 

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/groeninventorysystem/View_Controller/MainScreen.fxml"));

        Parent root = loader.load();
		
		Scene scene = new Scene(root);
		
        mainStage.setTitle("Inventory System");

        // Handle event of trying to close the program.

        mainStage.setOnCloseRequest((WindowEvent ev) -> {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Exit program");
            alert.setContentText("Are you sure you would like to exit?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){

                System.exit(0);

            } else {

                ev.consume();

            }
            
        });

		mainStage.setScene(scene);
		mainStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
