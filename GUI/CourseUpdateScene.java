package GUI;

import database.CourseSQL;
import domain.Course;
import domain.Level;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CourseUpdateScene extends domain.Validation{

    private CourseSQL sql = new CourseSQL();

    public Scene courseUpdateScene(Stage window, Course old_course) {
        CourseOverviewScene courseOverviewScene = new CourseOverviewScene();

		//Layout of the text in the buttons
        Font font = Font.font("Verdana");

        // Background image
        Image image = new Image("resources/backgroundImage.jpg");
        ImageView imageView = new ImageView(image);
        Group root = new Group();
        root.getChildren().addAll(imageView);

        // Button to go back to the homeScene.
        Button backButton = new Button("Back");
        backButton.setPrefSize(80, 37);
		backButton.setFont(font);
		backButton.setStyle("-fx-background-color: #6BCAE2; -fx-text-fill: #FFFFFF; -fx-font-size: 13");
        backButton.setOnAction((event) -> {
            window.setScene(courseOverviewScene.courseOverviewScene(window));
        });

        //Course
		Label nameLabel = new Label("Name: ");
		TextArea nameTextArea = new TextArea();
        nameTextArea.setText(old_course.getName());
		nameTextArea.setPrefHeight(12);
		
		Label topicLabel = new Label("Topic: ");
		TextArea topicTextArea = new TextArea();
        topicTextArea.setText(old_course.getTopic());
		topicTextArea.setPrefHeight(12);
		
		Label introductionLabel = new Label("Introduction: ");
		TextArea introductionTextArea = new TextArea();
        introductionTextArea.setText(String.valueOf(old_course.getIntroduction()));
		introductionTextArea.setPrefHeight(12);

        Label levelLabel = new Label("Level: ");
		ComboBox<Level>cbxLevel = new ComboBox<>();
        cbxLevel.getItems().setAll(Level.values());
        Level level = Level.valueOf(old_course.getLevel());
        cbxLevel.getSelectionModel().select(level);

        Label moduleLabel = new Label("Add module: ");
        ComboBox<String>cbxModule = new ComboBox<>();
        cbxModule.getItems().setAll(sql.getModules(old_course));

        Button updateCourse = new Button("Update course");
		updateCourse.setPrefSize(120, 40);
		updateCourse.setOnAction((event) -> {

			if(!nameTextArea.getText().isEmpty() && !topicTextArea.getText().isEmpty() && !introductionTextArea.getText().isEmpty() && !cbxLevel.getSelectionModel().getSelectedItem().name().isEmpty()){
				Course course = new Course(nameTextArea.getText(), topicTextArea.getText(), introductionTextArea.getText(), cbxLevel.getSelectionModel().getSelectedItem().name());
				sql.updateCourse(course);
				window.setScene(courseOverviewScene.courseOverviewScene(window));
			}
		});

        HBox buttonHBox = new HBox();
        buttonHBox.getChildren().addAll(updateCourse);

        GridPane grid = new GridPane();
		grid.setPadding(new Insets(50, 50, 50, 50));
		grid.setHgap(5);
		grid.setVgap(5);
		grid.add(nameLabel, 0, 0 , 1, 1);
		grid.add(nameTextArea, 1, 0 , 1, 1);
		grid.add(topicLabel, 0, 1 , 1, 1);
		grid.add(topicTextArea, 1, 1 , 1, 1);
		grid.add(introductionLabel, 0, 2 , 1, 1);
		grid.add(introductionTextArea, 1, 2 , 1, 1);
        grid.add(levelLabel, 0, 3 , 1, 1);
		grid.add(cbxLevel, 1, 3 , 1, 1);
        grid.add(moduleLabel, 0, 4 , 1, 1);
		grid.add(cbxModule, 1, 4 , 1, 1);
		grid.add(buttonHBox, 0, 6 , 1, 1);


        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(15, 15, 15, 15));
        pane.getChildren().add(imageView);
        pane.setTop(backButton);
        pane.setCenter(grid);

        Scene sscene = new Scene(pane, 1080, 620);
        return sscene;
    }
    
}
