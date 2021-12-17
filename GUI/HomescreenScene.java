package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HomescreenScene{
	StudentOverviewScene studentOverviewScene = new StudentOverviewScene();
	
	public Scene homeScene(Stage window) {
		
		//Background image of the startSceen
		Image image = new Image("resources/background_image.jpg");
		ImageView imageView = new ImageView(image);
		Group root = new Group();
		root.getChildren().addAll(imageView);
		
		//First button which leads to the studentScene.
		Button studentButton = new Button("Students");
		studentButton.setPrefSize(80, 37);
		studentButton.setOnAction((event) -> {
			window.setScene(studentOverviewScene.studentOverviewScene(window));
		});
		
		//Second button which leads to nothing yet.
		Button courseButton = new Button("Courses");
		courseButton.setPrefSize(80, 37);
		
		//Third button which leads to nothing yet.
		Button moduleButton = new Button("Modules");
		moduleButton.setPrefSize(80, 37);
		
		
		Label welcome1 = new Label("Welcome to the");
		welcome1.setFont(new Font("Verdana", 28));
		
		Label welcome2 = new Label("Codecademy Application");
		welcome2.setFont(new Font("Verdana", 35));
		
		HBox menu = new HBox(studentButton, courseButton, moduleButton);
		menu.setSpacing(10);
		
		
		VBox welcome = new VBox(welcome1, welcome2);
		welcome.setSpacing(5);
		welcome.setAlignment(Pos.BASELINE_CENTER);
		welcome.setPadding(new Insets(50, 50, 50, 50));
		
		
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(10, 10, 10, 10));
		pane.getChildren().add(imageView);
		pane.setTop(menu);
		pane.setCenter(welcome);
		
		Scene homescreenScene = new Scene(pane, 1000, 600);
		
		return homescreenScene;
	}
	
}
