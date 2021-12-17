package GUI;

import database.StudentSQL;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentOverviewScene {
    private StudentSQL sql = new StudentSQL();

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

        TableView<Student> table = new TableView<Student>();
        TableColumn<Student, String> emailCol = new TableColumn<Student, String>("Student email");
        TableColumn<Student, String> nameCol = new TableColumn<Student, String>("Student name");
        TableColumn<Student, String> birthdateCol = new TableColumn<Student, String>("Student birthdate");
        TableColumn<Student, String> genderCol = new TableColumn<Student, String>("Student gender");
        TableColumn<Student, String> addressCol = new TableColumn<Student, String>("Student address");
        TableColumn<Student, String> residenceCol = new TableColumn<Student, String>("Student residence");
        TableColumn<Student, String> countryCol = new TableColumn<Student, String>("Student country");
        table.getColumns().addAll(emailCol, nameCol, birthdateCol, genderCol, addressCol, residenceCol, countryCol);

        ObservableList<Student> list = sql.getStudentList();

        emailCol.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        birthdateCol.setCellValueFactory(new PropertyValueFactory<Student, String>("dateOfBirth"));
        genderCol.setCellValueFactory(new PropertyValueFactory<Student, String>("gender"));
        addressCol.setCellValueFactory(new PropertyValueFactory<Student, String>("address"));
        residenceCol.setCellValueFactory(new PropertyValueFactory<Student, String>("livingPlace"));
        countryCol.setCellValueFactory(new PropertyValueFactory<Student, String>("country"));

        table.setItems(list);

        Button createStudentButton = new Button("Create a new student");
        createStudentButton.setPrefSize(120, 40);
        createStudentButton.setOnAction((event) -> {
            window.setScene(studentCreateScene.studentCreateScene(window));
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(50, 50, 50, 50));
        grid.setHgap(5);
        grid.setVgap(5);
        grid.add(table, 1, 2);
        grid.add(createStudentButton, 1, 3);

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.getChildren().add(imageView);
        pane.setTop(menu);
        pane.setCenter(grid);

        Scene sscene = new Scene(pane, 1000, 600);
        return sscene;
    }

}
