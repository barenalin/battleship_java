package final_project;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class main extends Application{
	@Override
	public void start(Stage Stage) throws IOException {		
		Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
		Scene scene=new Scene(root, 600, 400);
		Stage.setTitle("Battleship");
		Stage.setScene(scene);
		Stage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
