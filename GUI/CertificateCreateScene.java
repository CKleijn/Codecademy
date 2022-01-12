package GUI;

import database.CertificateSQL;
import database.ExternalPersonSQL;
import domain.Certificate;
import domain.Course;
import domain.Registration;
import domain.Student;
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
import javafx.stage.Stage;

public class CertificateCreateScene extends domain.Validation{
    private CertificateSQL sqlC = new CertificateSQL();
    private ExternalPersonSQL sqlE = new ExternalPersonSQL();

    public Scene certificateCreateScene(Stage window, Registration registration, Course course, Student current_student) {
        StudentCourseScene studentCourseScene = new StudentCourseScene();
		
		//Background image
		Image image = new Image("resources/backgroundImage.jpg");
		ImageView imageView = new ImageView(image);
		Group root = new Group();
		root.getChildren().addAll(imageView);

        // Button to go back to the StudentOverviewScene.
        Button backButton = new Button("Back");
        backButton.setPrefSize(80, 37);
        backButton.setOnAction((event) -> {
            window.setScene(studentCourseScene.studentCourseScene(window, registration, course, current_student));
        });

        Label gradeLabel = new Label("Grade: ");
		TextArea gradeTextArea = new TextArea();

        Label externalPersonLabel = new Label("External person: ");
        ComboBox<String>cbxExternalPerson = new ComboBox<>();
        cbxExternalPerson.getItems().setAll(sqlE.getEmployeeExternalPersons());

        Button createCertificate = new Button("Create registration");
		createCertificate.setPrefSize(120, 40);

        HBox buttonHBox = new HBox();
        buttonHBox.getChildren().addAll(createCertificate);

        GridPane grid = new GridPane();
		grid.setPadding(new Insets(40, 0, 0, 0));
		grid.setHgap(5);
		grid.setVgap(2);

		createCertificate.setOnAction((event) -> {
            boolean validation = true;
            if (fieldIsEmpty(gradeTextArea.getText())) {
				validation = false;
				Label errorText = new Label("Text field isn't filled in");
				grid.add(errorText, 1, 1, 1, 1);
			} else if (!checkGrade(Integer.parseInt(gradeTextArea.getText()))) {
                validation = false;
				Label errorText = new Label("The grade isn't valid");
				grid.add(errorText, 1, 1, 1, 1);
            }

            if (cbxExternalPerson.getSelectionModel().isEmpty()) {
				validation = false;
				Label errorText = new Label("dropdown menu isn't filled in");
				grid.add(errorText, 1, 3, 1, 1);
			}

            if(validation){
                Certificate certificate = new Certificate(Integer.valueOf(gradeTextArea.getText()), sqlE.findExternalPersonID(cbxExternalPerson.getSelectionModel().getSelectedItem()), current_student.getEmail(), course.getName());
                sqlC.createCertificate(certificate);
                window.setScene(studentCourseScene.studentCourseScene(window, registration, course, current_student));
            }
		});

        grid.add(gradeLabel, 0, 0 , 1, 1);
		grid.add(gradeTextArea, 1, 0 , 1, 1);
        grid.add(externalPersonLabel, 0, 2 , 1, 1);
		grid.add(cbxExternalPerson, 1, 2 , 1, 1);
        grid.add(buttonHBox, 0, 4 , 1, 1);
		
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(15, 15, 15, 15));
        pane.getChildren().add(imageView);
        pane.setTop(backButton);
        pane.setCenter(grid);

        Scene sscene = new Scene(pane, 1080, 620);

        sscene.getStylesheets().add("/resources/styleSheet.css");

        return sscene;
    }
}
