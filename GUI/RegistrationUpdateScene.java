package GUI;

import java.sql.Date;
import java.time.LocalDate;

import database.CourseSQL;
import database.RegistrationSQL;
import domain.Registration;
import domain.Student;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

//Class that creates the Registration Update scene
public class RegistrationUpdateScene extends domain.Validation{
    private CourseSQL sqlC = new CourseSQL();
    private RegistrationSQL sqlR = new RegistrationSQL();

    //Method that creates the Registration Update scene with the information from the given attributes
    public Parent registrationUpdateScene(Stage window, Registration current_registration, Student current_student) {
        StudentRegistrationScene studentDetailScene = new StudentRegistrationScene();

		//Background image
		Image image = new Image("resources/backgroundImage.jpg");
		ImageView imageView = new ImageView(image);
		Group root = new Group();
		root.getChildren().addAll(imageView);

        // Button to go back to the StudentOverviewScene.
        Button backButton = new Button("Back");
        backButton.setPrefSize(80, 37);
        backButton.setOnAction((event) -> {
            window.getScene().setRoot(studentDetailScene.studentRegistrationScene(window, current_student));
        });

        Label courseLabel = new Label("Choose course: ");
        ComboBox<String>cbxCourse = new ComboBox<>();
        cbxCourse.getItems().setAll(sqlC.getCourses());
        cbxCourse.getSelectionModel().select(current_registration.getCourseName());
        cbxCourse.setPrefHeight(1.0);

        Button updateRegistration = new Button("Update registration");
		updateRegistration.setPrefSize(140, 40);
        updateRegistration.setId("updateRegistration");

        HBox buttonHBox = new HBox();
        buttonHBox.getChildren().addAll(updateRegistration);

        GridPane grid = new GridPane();
		grid.setPadding(new Insets(40, 0, 0, 0));
		grid.setHgap(5);
		grid.setVgap(2);

		updateRegistration.setOnAction((event) -> {

            boolean validation = true;

            if(cbxCourse.getSelectionModel().isEmpty()){
                validation = false;
                Label errorText = new Label("dropdown menu isn't filled in");
				grid.add(errorText, 1, 1, 1, 1);
            }

            if(validation){
                sqlR.updateRegistration(current_registration, new Registration(Date.valueOf(LocalDate.now()), current_student.getEmail(), cbxCourse.getSelectionModel().getSelectedItem()));
                window.getScene().setRoot(studentDetailScene.studentRegistrationScene(window, current_student));
            }
		});

        grid.add(courseLabel, 0, 0 , 1, 1);
		grid.add(cbxCourse, 1, 0 , 1, 1);
        grid.add(buttonHBox, 0, 1 , 1, 1);
		
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(15, 15, 15, 15));
        pane.getChildren().add(imageView);
        pane.setTop(backButton);
        pane.setCenter(grid);

        pane.getStylesheets().add("/resources/styleSheet.css");

        return pane;
    }
}
