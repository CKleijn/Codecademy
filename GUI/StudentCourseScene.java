package GUI;

import database.CertificateSQL;
import database.ExternalPersonSQL;
import database.RegistrationSQL;
import domain.Module;
import domain.Registration;
import domain.Certificate;
import domain.Course;
import domain.Student;
import domain.Webcast;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class StudentCourseScene {
    RegistrationSQL sqlR = new RegistrationSQL();
    CertificateSQL sqlC = new CertificateSQL();
    ExternalPersonSQL sqlE = new ExternalPersonSQL();

    public Scene studentCourseScene(Stage window, Registration registration, Course course, Student current_student) {
        StudentRegistrationScene studentRegistrationScene = new StudentRegistrationScene();
        StudentCourseModuleScene studentCourseModuleScene = new StudentCourseModuleScene();
        StudentCourseWebcastScene studentCourseWebcastScene = new StudentCourseWebcastScene();
        CertificateCreateScene certificateCreateScene = new CertificateCreateScene();
        CertificateUpdateScene certificateUpdateScene = new CertificateUpdateScene();

        // Background image
        Image image = new Image("resources/backgroundImage.jpg");
        ImageView imageView = new ImageView(image);
        Group root = new Group();
        root.getChildren().addAll(imageView);

        // Button to go back to the homeScene.
        Button backButton = new Button("Back");
        backButton.setPrefSize(80, 37);
        backButton.setOnAction((event) -> {
            window.setScene(studentRegistrationScene.studentRegistrationScene(window, current_student));
        });

        Button createButton = new Button("Create certificate");
        createButton.setPrefSize(120, 37);
        createButton.setOnAction((event) -> {
            window.setScene(certificateCreateScene.certificateCreateScene(window, registration, course, current_student));
        });

        Button checkButton = new Button("Check certificates");
        checkButton.setPrefSize(120, 37);
        checkButton.setOnAction((event) -> {
            sqlC.checkIfStudentReceiveCertificate(sqlC.getSingleCertificateFromStudentForSpecificCourse(course, current_student), registration);
            window.setScene(studentCourseScene(window, registration, course, current_student));
        });

        HBox menu = new HBox(backButton, createButton, checkButton);
        menu.setSpacing(10);

        Label infoNameLabel = new Label("Course name: ");
        Label nameLabel = new Label();
        nameLabel.setText(course.getName());

        Label infoTopicLabel = new Label("Course topic: ");
        Label topicLabel = new Label();
        topicLabel.setText(course.getTopic());

        Label infoIntroductionLabel = new Label("Course introduction: ");
        Label introLabel = new Label();
        introLabel.setText(course.getIntroduction());

        Label infoLevelLabel = new Label("Course level: ");
        Label levelLabel = new Label();
        levelLabel.setText(course.getLevel());

        GridPane grid = new GridPane();

        Label infoModuleLabel = new Label("Modules: ");
        int i = 4;
        for (Module module : sqlR.getSpecificModules(registration)) {
            Button button = new Button(module.getTitle());
            button.setOnAction((event) -> {
                window.setScene(studentCourseModuleScene.studentCourseModuleScene(window, module, registration, course, current_student));
            });
            grid.add(button, 1, i, 1, 1);
            i++;
        }

        Label infoWebcastLabel = new Label("Webcasts: ");
        int j = 4;
        for (Webcast webcast : sqlR.getSpecificWebcasts(registration)) {
            Button button = new Button(webcast.getTitle());
            button.setOnAction((event) -> {
                window.setScene(studentCourseWebcastScene.studentCourseWebcastScene(window, webcast, registration, course, current_student));
            });
            button.setStyle("-fx-background-color: #0a9ec2; -fx-text-fill: #FFFFFF; -fx-font-size: 13");
            grid.add(button, 3, j, 1, 1);
            j++;
        }

        int k = 4;
        for (Certificate certificate : sqlC.getCertificatesFromStudentForSpecificCourse(course, current_student)) {
            Label infoCertificateLabel = new Label("Certificate: ");
            Label certificateGradeLabel = new Label("Certificate grade: " + String.valueOf(certificate.getCertificateGrade()));
            Label certificateExternalPersonLabel = new Label("Certificate employee: " + sqlE.getEmployeeNameById(certificate));
            Button editButton = new Button("Edit");
            editButton.setOnAction((event) -> {
                window.setScene(certificateUpdateScene.certificateUpdateScene(window, certificate, registration, course, current_student));
            });
            Button deleteButton = new Button("Delete");
            deleteButton.setOnAction((event) -> {
                sqlC.deleteCertificate(certificate);
                window.setScene(studentCourseScene(window, registration, course, current_student));
            });
            grid.add(infoCertificateLabel, 4, 4 , 1, 1);
            grid.add(certificateGradeLabel, 5, k, 1, 1);
            grid.add(certificateExternalPersonLabel, 6, k, 1, 1);
            grid.add(editButton, 7, k, 1, 1);
            grid.add(deleteButton, 8, k, 1, 1);
            k++;
        }

        
		grid.setPadding(new Insets(40, 0, 0, 0));
		grid.setHgap(5);
		grid.setVgap(5);
        grid.add(infoNameLabel, 0, 0 , 1, 1);
        grid.add(nameLabel, 1, 0 , 1, 1);
        grid.add(infoTopicLabel, 0, 1 , 1, 1);
        grid.add(topicLabel, 1, 1 , 1, 1);
        grid.add(infoIntroductionLabel, 0, 2 , 1, 1);
        grid.add(introLabel, 1, 2 , 1, 1);
        grid.add(infoLevelLabel, 0, 3 , 1, 1);
        grid.add(levelLabel, 1, 3 , 1, 1);
        grid.add(infoModuleLabel, 0, 4 , 1, 1);
        grid.add(infoWebcastLabel, 2, 4 , 1, 1);

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(15, 15, 15, 15));
        pane.getChildren().add(imageView);
        pane.setTop(menu);
        pane.setCenter(grid);

        Scene sscene = new Scene(pane, 1080, 620);

        sscene.getStylesheets().add("/resources/styleSheet.css");

        return sscene;

        
    }
}