package GUI;

import java.util.Arrays;

import database.CertificateSQL;
import domain.Certificate;
import domain.Student;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class StudentCertificateScene {
    private CertificateSQL sqlC = new CertificateSQL();
    

    public Scene studentCertificateScene(Stage window, Student current_student) {
        StudentRegistrationScene studentRegistrationScene = new StudentRegistrationScene();

        // Background image
        Image image = new Image("resources/backgroundImage.jpg");
        ImageView imageView = new ImageView(image);
        Group root = new Group();
        root.getChildren().addAll(imageView);

        // Button to go back to the previous scene.
        Button backButton = new Button("Back");
        backButton.setPrefSize(80, 37);
        backButton.setOnAction((event) -> {
            window.setScene(studentRegistrationScene.studentRegistrationScene(window, current_student));
        });

        HBox menu = new HBox(backButton);
        menu.setSpacing(10);

        TableView<Certificate> table = new TableView<Certificate>();
        TableColumn<Certificate, String> idCol = new TableColumn<Certificate, String>("Certificate ID");
        TableColumn<Certificate, String> gradeCol = new TableColumn<Certificate, String>("Certificate grade");
        TableColumn<Certificate, String> externalPersonIdCol = new TableColumn<Certificate, String>("External Person ID");
        TableColumn<Certificate, String> studentEmailCol = new TableColumn<Certificate, String>("Student email");
        TableColumn<Certificate, String> courseNameCol = new TableColumn<Certificate, String>("Course name");
        
        table.getColumns().addAll(Arrays.asList(idCol, gradeCol, externalPersonIdCol, studentEmailCol, courseNameCol));

        ObservableList<Certificate> list = sqlC.getCertificateListFromStudent(current_student);

        idCol.setCellValueFactory(new PropertyValueFactory<Certificate, String>("certificateID"));
        gradeCol.setCellValueFactory(new PropertyValueFactory<Certificate, String>("certificateGrade"));
        externalPersonIdCol.setCellValueFactory(new PropertyValueFactory<Certificate, String>("externalPersonID"));
        studentEmailCol.setCellValueFactory(new PropertyValueFactory<Certificate, String>("studentEmail"));
        courseNameCol.setCellValueFactory(new PropertyValueFactory<Certificate, String>("courseName"));

        table.setItems(list);
        table.setMaxSize(1485, 400);

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(15, 15, 15, 15));
        pane.getChildren().add(imageView);
        pane.setTop(menu);
        pane.setCenter(table);

        Scene sscene = new Scene(pane);

        window.setFullScreen(true);

        sscene.getStylesheets().add("/resources/styleSheet.css");

        return sscene;
    }
}
