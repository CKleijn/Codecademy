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
import javafx.stage.Stage;

public class HomescreenScene{
	StudentOverviewScene studentOverviewScene = new StudentOverviewScene();
	CourseOverviewScene courseOverviewScene = new CourseOverviewScene();
	StatisticOverviewScene statisticOverviewScene = new StatisticOverviewScene();
	
	public Scene homeScene(Stage window) {
		
		//Background image
		Image image = new Image("resources/backgroundImage.jpg");
		ImageView imageViewBackground = new ImageView(image);
		Group root = new Group();
		root.getChildren().addAll(imageViewBackground);
		
		//First button which leads to the studentOverviewScene.
		Button studentButton = new Button("Students");
		studentButton.setPrefSize(80, 37);
		studentButton.setOnAction((event) -> {
			window.setScene(studentOverviewScene.studentOverviewScene(window));
		});

		//Second button which leads to courseOverviewScene
		Button courseButton = new Button("Courses");
		courseButton.setPrefSize(80, 37);
		courseButton.setOnAction((event) -> {
			window.setScene(courseOverviewScene.courseOverviewScene(window));
		});

		//Third button which leads to statisticOverviewScene
		Button statisticButton = new Button("Statistics");
		statisticButton.setPrefSize(80, 37);
		statisticButton.setOnAction((event) -> {
			window.setScene(statisticOverviewScene.statisticOverviewScene(window));
		});

		//Hbox with the three buttons above
		HBox menu = new HBox(studentButton, courseButton, statisticButton);
		menu.setSpacing(10);

			
		//Titel in the middel of the homscreen which welcomes the user.
		Label firstTitelLabel = new Label("Welcome to the");
		firstTitelLabel.setId("firstTitelLabel");
		Label secondTitelLabel = new Label("Codecademy Application");
		secondTitelLabel.setId("secondTitelLabel");

		//Vbox with the two titel labels.
		VBox front = new VBox(firstTitelLabel, secondTitelLabel);
		front.setSpacing(5);
		front.setAlignment(Pos.BASELINE_CENTER);
		front.setPadding(new Insets(50, 0, 0, 0));
		
		
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(15, 15, 15, 15));
		pane.getChildren().add(imageViewBackground);
		pane.setTop(menu);
		pane.setCenter(front);
		
		Scene homescreenScene = new Scene(pane);

		window.setFullScreen(true);

		homescreenScene.getStylesheets().add("/resources/styleSheet.css");
		
		return homescreenScene;
	}
	
}
