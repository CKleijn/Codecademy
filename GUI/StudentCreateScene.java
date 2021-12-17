package GUI;

import domain.*;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class StudentCreateScene {
	
	public Scene studentCreateScene(Stage window) {
		StudentOverviewScene studentOverviewScene = new StudentOverviewScene();
		
		//Background image of the startSceen
		Image image = new Image("resources/background_image.jpg");
		ImageView imageView = new ImageView(image);
		Group root = new Group();
		root.getChildren().addAll(imageView);
		
		//Button to go back to the homeScene.
		Button backButton = new Button("Back");
		backButton.setPrefSize(80, 37);
		backButton.setOnAction((event) -> {
			window.setScene(studentOverviewScene.studentOverviewScene(window));
		});

		HBox menu = new HBox(backButton);
		menu.setSpacing(10);
		
		//Student
		Label email = new Label("Email: ");
		TextArea email2 = new TextArea();
		email2.setPrefHeight(16);
		
		Label name = new Label("Name: ");
		TextArea name2 = new TextArea();
		name2.setPrefHeight(16);
		
		Label birthdate = new Label("Birthdate: ");
		TextArea birthdate2 = new TextArea();
		birthdate2.setPrefHeight(16);
		
		Label gender = new Label("Gender: ");
		TextArea gender2 = new TextArea();
		gender2.setPrefHeight(16);
		
		Label address = new Label("Address: ");
		TextArea address2 = new TextArea();
		address2.setPrefHeight(16);
		
		Label residence = new Label("Residence: ");
		TextArea residence2 = new TextArea();
		residence2.setPrefHeight(16);
		
		Label country = new Label("Country: ");
		TextArea country2 = new TextArea();
		country2.setPrefHeight(16);
		
		Button createStudentButton = new Button("+ Add student");
		createStudentButton.setPrefSize(120, 40);
		createStudentButton.setOnAction((event) -> {
			// Student student = new Student(email2.getText(), name2.getText(), birthdate2.getText(), gender2.getText(), address2.getText(), residence2.getText(), country2.getText());
			// window.setScene(studentOverviewScene.studentOverviewScene(window));
		});
		
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(50, 50, 50, 50));
		grid.setHgap(5);
		grid.setVgap(5);
		grid.add(email, 1, 0);
		grid.add(email2, 2, 0);
		grid.add(name, 1, 1);
		grid.add(name2, 2, 1);
		grid.add(birthdate, 1, 2);
		grid.add(birthdate2, 2, 2);
		grid.add(gender, 1, 3);
		grid.add(gender2, 2, 3);
		grid.add(address, 1, 4);
		grid.add(address2, 2, 4);
		grid.add(residence, 1, 5);
		grid.add(residence2, 2, 5);
		grid.add(country, 1, 6);
		grid.add(country2, 2, 6);
		
		grid.add(createStudentButton, 2, 7);
		
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(10, 10, 10, 10));
		pane.getChildren().add(imageView);
		pane.setTop(menu);
		pane.setCenter(grid);
		
		Scene sscene = new Scene(pane, 1000, 600);
		return sscene;
	}
	
}
