package GUI;

import database.CourseSQL;
import domain.Course;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CourseCreateScene {
    private CourseSQL sql = new CourseSQL();

    public Scene courseCreateScene(Stage window) {
        CourseOverviewScene courseOverviewScene = new CourseOverviewScene();

        //Layout of the text in the buttons
		Font font = Font.font("Verdana");
		
		//Background image
		Image image = new Image("resources/backgroundImage.jpg");
		ImageView imageView = new ImageView(image);
		Group root = new Group();
		root.getChildren().addAll(imageView);

        // Button to go back to the StudentOverviewScene.
        Button backButton = new Button("Back");
        backButton.setPrefSize(80, 37);
		backButton.setFont(font);
		backButton.setStyle("-fx-background-color: #6BCAE2; -fx-text-fill: #FFFFFF; -fx-font-size: 13");
        backButton.setOnAction((event) -> {
            window.setScene(courseOverviewScene.courseOverviewScene(window));
        });


        //All labels and input fields		
		Label nameLabel = new Label("Name: ");
		TextArea nameTextArea = new TextArea();
		nameTextArea.setPrefHeight(12);
		
		Label topicLabel = new Label("Topic: ");
		TextArea topicTextArea = new TextArea();
		topicTextArea.setPrefHeight(12);
		
		Label introductionLabel = new Label("Introduction: ");
		TextArea introductionTextArea = new TextArea();
		introductionTextArea.setPrefHeight(12);

        Label levelLabel = new Label("Level: ");
		TextArea levelTextArea = new TextArea();
		levelTextArea.setPrefHeight(12);

        Label hasRelevantLabel = new Label("Has Relevant course(s): ");
		TextArea hasRelevantTextArea = new TextArea();
		hasRelevantTextArea.setPrefHeight(12);

		//Last button to create with al the information in the textareas
		Button createCourseButton = new Button("Add course");
		createCourseButton.setPrefSize(120, 40);
		createCourseButton.setFont(font);
		createCourseButton.setStyle("-fx-background-color: #0B9EC3; -fx-text-fill: #FFFFFF; -fx-font-size: 13");

		createCourseButton.setOnAction((event) -> {
			if(!nameTextArea.getText().isEmpty() && !topicTextArea.getText().isEmpty() && !introductionTextArea.getText().isEmpty() && !levelTextArea.getText().isEmpty() && !hasRelevantTextArea.getText().isEmpty()){
				Course course = new Course(nameTextArea.getText(), topicTextArea.getText(), introductionTextArea.getText(), levelTextArea.getText(), hasRelevantTextArea.getText());
				sql.createCourse(course);
				window.setScene(courseOverviewScene.courseOverviewScene(window));

			}
		});

        HBox buttonHBox = new HBox();
        buttonHBox.getChildren().addAll(createCourseButton);

        GridPane grid = new GridPane();
		grid.setPadding(new Insets(40, 0, 0, 0));
		grid.setHgap(5);
		grid.setVgap(2);
		grid.add(nameLabel, 0, 0 , 1, 1);
		grid.add(nameTextArea, 1, 0, 1, 1);
		grid.add(topicLabel, 0, 1 , 1, 1);
		grid.add(topicTextArea, 1, 1, 1, 1);
		grid.add(introductionLabel, 0, 2, 1, 1);
		grid.add(introductionTextArea, 1, 2, 1, 1);
		grid.add(levelLabel, 0, 3, 1, 1);
		grid.add(levelTextArea, 1, 3, 1, 1);
		grid.add(hasRelevantLabel, 0, 4 , 1, 1);
		grid.add(hasRelevantTextArea, 1, 4 , 1, 1);
		grid.add(buttonHBox, 0, 5, 1, 1);

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(15, 15, 15, 15));
        pane.getChildren().add(imageView);
        pane.setTop(backButton);
        pane.setCenter(grid);

        Scene sscene = new Scene(pane, 1080, 620);
        return sscene;
    }
    
}
