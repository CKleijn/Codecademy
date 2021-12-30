package GUI;

import database.RegistrationSQL;
import database.StudentSQL;
import domain.Registration;
import domain.Student;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

public class StudentDetailScene extends domain.Validation {
    private StudentSQL sqlS = new StudentSQL();
    private RegistrationSQL sqlR = new RegistrationSQL();

    public Scene studentDetailScene(Stage window, Student current_student) {
        StudentOverviewScene studentOverviewScene = new StudentOverviewScene();
        StudentDetailScene studentDetailScene = new StudentDetailScene();
        RegistrationCreateScene registrationCreateScene = new RegistrationCreateScene();
        RegistrationUpdateScene registrationUpdateScene = new RegistrationUpdateScene();

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
            window.setScene(studentOverviewScene.studentOverviewScene(window));
        });

        Button registerButton = new Button("Register");
        registerButton.setPrefSize(80, 37);
		registerButton.setFont(font);
		registerButton.setStyle("-fx-background-color: #6BCAE2; -fx-text-fill: #FFFFFF; -fx-font-size: 13");
        registerButton.setOnAction((event) -> {
            window.setScene(registrationCreateScene.registrationCreateScene(window, current_student));
        });

        HBox menu = new HBox(backButton, registerButton);
        menu.setSpacing(10);

        TableView<Registration> table = new TableView<Registration>();
        TableColumn<Registration, String> courseNameCol = new TableColumn<Registration, String>("Course name");
        TableColumn<Registration, String> registrationDateCol = new TableColumn<Registration, String>("Registration date");
        TableColumn editCol = new TableColumn("Edit");   
        TableColumn deleteCol = new TableColumn("Delete");      
        
        table.getColumns().addAll(courseNameCol, registrationDateCol, editCol, deleteCol);

        ObservableList<Registration> list = sqlR.getStudentRegistrationList(current_student);

        courseNameCol.setCellValueFactory(new PropertyValueFactory<Registration, String>("courseName"));
        registrationDateCol.setCellValueFactory(new PropertyValueFactory<Registration, String>("registrationDate"));
        editCol.setCellValueFactory(new PropertyValueFactory<>("Edit"));
        deleteCol.setCellValueFactory(new PropertyValueFactory<>("Delete"));
        
        Callback<TableColumn<Registration, String>, TableCell<Registration, String>> editCellFactory = new Callback<TableColumn<Registration, String>, TableCell<Registration, String>>() {
            @Override
            public TableCell call(final TableColumn<Registration, String> param) {
                final TableCell<Registration, String> cell = new TableCell<Registration, String>() {

                    final Button editBtn = new Button("Edit");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            editBtn.setOnAction(event -> {
                                Registration registration = getTableView().getItems().get(getIndex());
                                window.setScene(registrationUpdateScene.registrationUpdateScene(window, registration, current_student));
                            });
                            setGraphic(editBtn);
                        }
                    }
                };
                return cell;
            }
        };

        Callback<TableColumn<Registration, String>, TableCell<Registration, String>> deleteCellFactory = new Callback<TableColumn<Registration, String>, TableCell<Registration, String>>() {
            @Override
            public TableCell call(final TableColumn<Registration, String> param) {
                final TableCell<Registration, String> cell = new TableCell<Registration, String>() {

                    final Button deleteBtn = new Button("Delete");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            deleteBtn.setOnAction(event -> {
                                Registration registration = getTableView().getItems().get(getIndex());
                                sqlR.deleteRegistration(registration);
                                window.setScene(studentDetailScene.studentDetailScene(window, current_student));
                            });
                            setGraphic(deleteBtn);
                        }
                    }
                };
                return cell;
            }
        };

        editCol.setCellFactory(editCellFactory);
        deleteCol.setCellFactory(deleteCellFactory);

        table.setItems(list);
        table.setMaxSize(1485, 400);

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(15, 15, 15, 15));
        pane.getChildren().add(imageView);
        pane.setTop(menu);
        pane.setCenter(table);

        Scene sscene = new Scene(pane, 1080, 620);
        return sscene;
    }
}
