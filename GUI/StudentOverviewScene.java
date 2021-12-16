package GUI;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentOverviewScene {

    public Scene studentOverviewScene(Stage window) {
        HomescreenScene homescreenScene = new HomescreenScene();
        StudentCreateScene studentCreateScene = new StudentCreateScene();

        // Background image of the startSceen
        Image image = new Image("resources/background_image.jpg");
        ImageView imageView = new ImageView(image);
        Group root = new Group();
        root.getChildren().addAll(imageView);

        // Button to go back to the homeScene.
        Button backButton = new Button("Back");
        backButton.setPrefSize(80, 37);
        backButton.setOnAction((event) -> {
            window.setScene(homescreenScene.homeScene(window));
        });

        HBox menu = new HBox(backButton);
        menu.setSpacing(10);

        TableView<String> table = new TableView<String>();
        // Creating columns
        TableColumn emailCol = new TableColumn("Student email");
        emailCol.setPrefWidth(300);
        TableColumn nameCol = new TableColumn("Student name");
        nameCol.setPrefWidth(300);
        TableColumn birthdateCol = new TableColumn("Student birthdate");
        birthdateCol.setPrefWidth(300);
        // Setting the size of the table
        table.setPrefSize(900, 300);
        table.getColumns().addAll(emailCol, nameCol, birthdateCol);
        // Create VBOX
        VBox vbox = new VBox();
        vbox.setSpacing(300);
        vbox.getChildren().addAll(table);

        Button createStudentButton = new Button("Create a new student");
        createStudentButton.setPrefSize(120, 40);
        createStudentButton.setOnAction((event) -> {
            window.setScene(studentCreateScene.studentCreateScene(window));
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(50, 50, 50, 50));
        grid.setHgap(5);
        grid.setVgap(5);
        grid.add(createStudentButton, 1, 3);
        grid.add(vbox, 1, 2);

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.getChildren().add(imageView);
        pane.setTop(menu);
        pane.setCenter(grid);

        Scene sscene = new Scene(pane, 1000, 600);
        return sscene;
    }

}
