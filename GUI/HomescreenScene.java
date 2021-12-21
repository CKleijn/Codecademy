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

		//Layout of the text in the buttons
		Font font = Font.font("Verdana");
		
		//Background image
		Image image = new Image("resources/backgroundImage.jpg");
		ImageView imageView = new ImageView(image);
		Group root = new Group();
		root.getChildren().addAll(imageView);
		
		//First button which leads to the studentScene.
		Button studentButton = new Button("Students");
		studentButton.setPrefSize(80, 37);
		studentButton.setFont(font);
		studentButton.setStyle("-fx-background-color: #6BCAE2; -fx-text-fill: #FFFFFF; -fx-font-size: 13");
		studentButton.setOnAction((event) -> {
			window.setScene(studentOverviewScene.studentOverviewScene(window));
		});
		
		//Second button which leads to nothing yet.
		Button courseButton = new Button("Courses");
		courseButton.setPrefSize(80, 37);
		courseButton.setFont(font);
		courseButton.setStyle("-fx-background-color: #6BCAE2; -fx-text-fill: #FFFFFF; -fx-font-size: 13");
		
		//Third button which leads to nothing yet.
		Button moduleButton = new Button("Modules");
		moduleButton.setPrefSize(80, 37);
		moduleButton.setFont(font);
		moduleButton.setStyle("-fx-background-color: #6BCAE2; -fx-text-fill: #FFFFFF; -fx-font-size: 13");
		
		
		Label welcome1 = new Label("Welcome to the");
		welcome1.setFont(font);
		welcome1.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 28;");
		

		Label welcome2 = new Label("Codecademy Application");
		welcome2.setFont(font);
		welcome2.setStyle("-fx-padding: 10; -fx-background-color: #0B9EC3; -fx-text-fill: #FFFFFF; -fx-font-size: 35;");


		Label description = new Label("description of the application");
		description.setFont(font);
		description.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 18;");
		description.setPadding(new Insets(50, 0, 0, 0));

		
		HBox menu = new HBox(studentButton, courseButton, moduleButton);
		menu.setSpacing(10);
		
		
		VBox front = new VBox(welcome1, welcome2, description);
		front.setSpacing(5);
		front.setAlignment(Pos.BASELINE_CENTER);
		front.setPadding(new Insets(150, 0, 0, 0));
		
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(15, 15, 15, 15));
		pane.getChildren().add(imageView);
		pane.setTop(menu);
		pane.setCenter(front);
		

		Scene homescreenScene = new Scene(pane, 1080, 620);
		
		return homescreenScene;
	}
	
}
