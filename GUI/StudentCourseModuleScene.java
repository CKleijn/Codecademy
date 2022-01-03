package GUI;

import database.ExternalPersonSQL;
import domain.Course;
import domain.Module;
import domain.Registration;
import domain.Student;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class StudentCourseModuleScene {
    ExternalPersonSQL sql = new ExternalPersonSQL();

    public Scene studentCourseModuleScene(Stage window, Module module, Registration registration, Course course, Student current_student) {
        StudentCourseScene studentCourseScene = new StudentCourseScene();

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
            window.setScene(studentCourseScene.studentCourseScene(window, registration, course, current_student));
        });

        HBox menu = new HBox(backButton);
        menu.setSpacing(10);

        Label infoIDLabel = new Label("Module ID: ");
        Label moduleIDLabel = new Label();
        moduleIDLabel.setText(String.valueOf(module.getItemId()));

        Label infoSerNrLabel = new Label("Module serial number: ");
        Label moduleSerNrLabel = new Label();
        moduleSerNrLabel.setText(String.valueOf(module.getSerialNumber()));

        Label infoVersionLabel = new Label("Module version: ");
        Label moduleVersionLabel = new Label();
        moduleVersionLabel.setText(module.getVersion());

        Label infoTitleLabel = new Label("Module title: ");
        Label moduleTitleLabel = new Label();
        moduleTitleLabel.setText(module.getTitle());

        Label infoDescLabel = new Label("Module description: ");
        Label moduleDescLabel = new Label();
        moduleDescLabel.setText(module.getDescription());

        Label infoDateLabel = new Label("Module publication date: ");
        Label moduleDateLabel = new Label();
        moduleDateLabel.setText(String.valueOf(module.getPublicationDate()));

        Label infoStatusLabel = new Label("Module status: ");
        Label moduleStatusLabel = new Label();
        moduleStatusLabel.setText(module.getStatus());

        Label infoExPerLabel = new Label("Module external person: ");
        Label moduleExPerLabel = new Label();
        moduleExPerLabel.setText(sql.getExternalPersonNameById(module));

        Label infoViewsLabel = new Label("Module views: ");
        Label moduleViewsLabel = new Label();
        moduleViewsLabel.setText(String.valueOf(module.getViewCount()));


        GridPane grid = new GridPane();
        
		grid.setPadding(new Insets(40, 0, 0, 0));
		grid.setHgap(5);
		grid.setVgap(5);
        grid.add(infoIDLabel, 0, 0 , 1, 1);
        grid.add(moduleIDLabel, 1, 0 , 1, 1);
        grid.add(infoSerNrLabel, 0, 1 , 1, 1);
        grid.add(moduleSerNrLabel, 1, 1 , 1, 1);
        grid.add(infoVersionLabel, 0, 2 , 1, 1);
        grid.add(moduleVersionLabel, 1, 2 , 1, 1);
        grid.add(infoTitleLabel, 0, 3 , 1, 1);
        grid.add(moduleTitleLabel, 1, 3 , 1, 1);
        grid.add(infoDescLabel, 0, 4 , 1, 1);
        grid.add(moduleDescLabel, 1, 4 , 1, 1);
        grid.add(infoDateLabel, 0, 5 , 1, 1);
        grid.add(moduleDateLabel, 1, 5 , 1, 1);
        grid.add(infoStatusLabel, 0, 6 , 1, 1);
        grid.add(moduleStatusLabel, 1, 6 , 1, 1);
        grid.add(infoExPerLabel, 0, 7 , 1, 1);
        grid.add(moduleExPerLabel, 1, 7 , 1, 1);
        grid.add(infoViewsLabel, 0, 8 , 1, 1);
        grid.add(moduleViewsLabel, 1, 8 , 1, 1);

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(15, 15, 15, 15));
        pane.getChildren().add(imageView);
        pane.setTop(menu);
        pane.setCenter(grid);


        Scene sscene = new Scene(pane, 1080, 620);
        return sscene;
    }
}
