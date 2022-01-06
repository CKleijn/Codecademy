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
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CertificateUpdateScene extends domain.Validation{
    private CertificateSQL sqlC = new CertificateSQL();
    private ExternalPersonSQL sqlE = new ExternalPersonSQL();

    public Scene certificateUpdateScene(Stage window, Certificate current_certificate, Registration registration, Course course, Student current_student) {
        StudentCourseScene studentCourseScene = new StudentCourseScene();
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
            window.setScene(studentCourseScene.studentCourseScene(window, registration, course, current_student));
        });

        Label gradeLabel = new Label("Grade: ");
		TextArea gradeTextArea = new TextArea();
        gradeTextArea.setText(String.valueOf(current_certificate.getCertificateGrade()));

        Label externalPersonLabel = new Label("External person: ");
        ComboBox<String>cbxExternalPerson = new ComboBox<>();
        cbxExternalPerson.setValue(sqlE.getEmployeeNameById(current_certificate));
        cbxExternalPerson.getItems().setAll(sqlE.getEmployeeExternalPersons());

        Button updateCertificate = new Button("Update registration");
		updateCertificate.setPrefSize(120, 40);
        updateCertificate.setStyle("-fx-background-color: #0a9ec2; -fx-text-fill: #FFFFFF; -fx-font-size: 13");
		updateCertificate.setOnAction((event) -> {
            if(!gradeTextArea.getText().isEmpty() && !cbxExternalPerson.getSelectionModel().getSelectedItem().isEmpty()){
                Certificate certificate = new Certificate(current_certificate.getCertificateID(), Integer.valueOf(gradeTextArea.getText()), sqlE.findExternalPersonID(cbxExternalPerson.getSelectionModel().getSelectedItem()));
                sqlC.updateCertificate(certificate);
                window.setScene(studentCourseScene.studentCourseScene(window, registration, course, current_student));
            }
		});

        HBox buttonHBox = new HBox();
        buttonHBox.getChildren().addAll(updateCertificate);

        GridPane grid = new GridPane();
		grid.setPadding(new Insets(40, 0, 0, 0));
		grid.setHgap(5);
		grid.setVgap(2);
        grid.add(gradeLabel, 0, 0 , 1, 1);
		grid.add(gradeTextArea, 1, 0 , 1, 1);
        grid.add(externalPersonLabel, 0, 1 , 1, 1);
		grid.add(cbxExternalPerson, 1, 1 , 1, 1);
        grid.add(buttonHBox, 0, 2 , 1, 1);
		
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(15, 15, 15, 15));
        pane.getChildren().add(imageView);
        pane.setTop(backButton);
        pane.setCenter(grid);

        Scene sscene = new Scene(pane, 1080, 620);
        return sscene;
    }
}
