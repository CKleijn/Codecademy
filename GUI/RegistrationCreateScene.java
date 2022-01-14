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

public class RegistrationCreateScene extends domain.Validation{
    private CourseSQL sqlC = new CourseSQL();
    private RegistrationSQL sqlR = new RegistrationSQL();

    public Parent registrationCreateScene(Stage window, Student current_student) {
        StudentOverviewScene studentOverviewScene = new StudentOverviewScene();
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
            window.getScene().setRoot(studentOverviewScene.studentOverviewScene(window));
        });

        Label courseLabel = new Label("Choose course: ");
        ComboBox<String>cbxCourse = new ComboBox<>();
        cbxCourse.getItems().setAll(sqlC.getCourses());

        Button createRegistration = new Button("Create registration");
		createRegistration.setPrefSize(140, 40);
        createRegistration.setStyle("-fx-background-color: #0a9ec2; -fx-text-fill: #FFFFFF; -fx-font-size: 13");

        HBox buttonHBox = new HBox();
        buttonHBox.getChildren().addAll(createRegistration);

        GridPane grid = new GridPane();
		grid.setPadding(new Insets(40, 0, 0, 0));
		grid.setHgap(5);
		grid.setVgap(2);

		createRegistration.setOnAction((event) -> {

            boolean validation = true;

            if(cbxCourse.getSelectionModel().isEmpty()){
                validation = false;
                Label errorText = new Label("dropdown menu isn't filled in");
				grid.add(errorText, 1, 1, 1, 1);
            }

            if(validation){
                sqlR.createRegistration(new Registration(Date.valueOf(LocalDate.now()), current_student.getEmail(), cbxCourse.getSelectionModel().getSelectedItem()));
                window.getScene().setRoot(studentDetailScene.studentRegistrationScene(window, current_student));
            }
		});

       
        grid.add(courseLabel, 0, 0 , 1, 1);
		grid.add(cbxCourse, 1, 0 , 1, 1);
        grid.add(buttonHBox, 0, 2 , 1, 1);
		
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(15, 15, 15, 15));
        pane.getChildren().add(imageView);
        pane.setTop(backButton);
        pane.setCenter(grid);

        pane.getStylesheets().add("/resources/styleSheet.css");

        return pane;
    }
}
