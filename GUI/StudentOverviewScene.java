package GUI;

import java.util.Arrays;

import database.StudentSQL;
import domain.Student;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

public class StudentOverviewScene {
    private StudentSQL sql = new StudentSQL();
    

    public Scene studentOverviewScene(Stage window) {
        HomescreenScene homescreenScene = new HomescreenScene();
        StudentOverviewScene studentOverviewScene = new StudentOverviewScene();
        StudentCreateScene studentCreateScene = new StudentCreateScene();
        StudentUpdateScene studentUpdateScene = new StudentUpdateScene();
        StudentRegistrationScene studentDetailScene = new StudentRegistrationScene();

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
            window.setScene(homescreenScene.homeScene(window));
        });

        Button createButton = new Button("Create student");
        createButton.setPrefSize(80, 37);
        createButton.setFont(font);
		createButton.setStyle("-fx-background-color: #6BCAE2; -fx-text-fill: #FFFFFF; -fx-font-size: 13");
        createButton.setOnAction((event) -> {
            window.setScene(studentCreateScene.studentCreateScene(window));
        });

        HBox menu = new HBox(backButton, createButton);
        menu.setSpacing(10);

        TableView<Student> table = new TableView<Student>();
        TableColumn<Student, String> emailCol = new TableColumn<Student, String>("Student email");
        TableColumn<Student, String> nameCol = new TableColumn<Student, String>("Student name");
        TableColumn<Student, String> birthDayCol = new TableColumn<Student, String>("Student birthday");
        TableColumn<Student, String> birthMonthCol = new TableColumn<Student, String>("Student birthmonth");
        TableColumn<Student, String> birthYearCol = new TableColumn<Student, String>("Student birthyear");
        TableColumn<Student, String> genderCol = new TableColumn<Student, String>("Student gender");
        TableColumn<Student, String> streetCol = new TableColumn<Student, String>("Student street");
        TableColumn<Student, String> houseNumberCol = new TableColumn<Student, String>("Student house number");
        TableColumn<Student, String> houseNumberAdditionCol = new TableColumn<Student, String>("Student house number addition");
        TableColumn<Student, String> postalCodeCol = new TableColumn<Student, String>("Student postal code");
        TableColumn<Student, String> residenceCol = new TableColumn<Student, String>("Student residence");
        TableColumn<Student, String> countryCol = new TableColumn<Student, String>("Student country");
        TableColumn<Student, String> editCol = new TableColumn<Student, String>("Edit");   
        TableColumn<Student, String> deleteCol = new TableColumn<Student, String>("Delete");      
        
        table.getColumns().addAll(Arrays.asList(emailCol, nameCol, birthDayCol, birthMonthCol, birthYearCol, genderCol, streetCol, houseNumberCol, houseNumberAdditionCol, postalCodeCol, residenceCol, countryCol, editCol, deleteCol));

        ObservableList<Student> list = sql.getStudentList();

        emailCol.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        birthDayCol.setCellValueFactory(new PropertyValueFactory<Student, String>("birthDay"));
        birthMonthCol.setCellValueFactory(new PropertyValueFactory<Student, String>("birthMonth"));
        birthYearCol.setCellValueFactory(new PropertyValueFactory<Student, String>("birthYear"));
        genderCol.setCellValueFactory(new PropertyValueFactory<Student, String>("gender"));
        streetCol.setCellValueFactory(new PropertyValueFactory<Student, String>("street"));
        houseNumberCol.setCellValueFactory(new PropertyValueFactory<Student, String>("houseNumber"));
        houseNumberAdditionCol.setCellValueFactory(new PropertyValueFactory<Student, String>("houseNumberAddition"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<Student, String>("postalCode"));
        residenceCol.setCellValueFactory(new PropertyValueFactory<Student, String>("residence"));
        countryCol.setCellValueFactory(new PropertyValueFactory<Student, String>("country"));
        editCol.setCellValueFactory(new PropertyValueFactory<>("Edit"));
        deleteCol.setCellValueFactory(new PropertyValueFactory<>("Delete"));

        table.setOnMouseClicked((event) -> {
            Student student = table.getSelectionModel().getSelectedItem();
            window.setScene(studentDetailScene.studentRegistrationScene(window, student));
        });
        
        Callback<TableColumn<Student, String>, TableCell<Student, String>> editCellFactory = new Callback<TableColumn<Student, String>, TableCell<Student, String>>() {
            @Override
            public TableCell<Student, String> call(final TableColumn<Student, String> param) {
                final TableCell<Student, String> cell = new TableCell<Student, String>() {

                    final Button editBtn = new Button("Edit");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            editBtn.setStyle("-fx-background-color: #0a9ec2; -fx-text-fill: #FFFFFF; -fx-font-size: 13");
                            editBtn.setOnAction(event -> {
                                Student student = getTableView().getItems().get(getIndex());
                                window.setScene(studentUpdateScene.studentUpdateScene(window, student));
                            });
                            setGraphic(editBtn);
                        }
                    }
                };
                return cell;
            }
        };

        Callback<TableColumn<Student, String>, TableCell<Student, String>> deleteCellFactory = new Callback<TableColumn<Student, String>, TableCell<Student, String>>() {
            @Override
            public TableCell<Student, String> call(final TableColumn<Student, String> param) {
                final TableCell<Student, String> cell = new TableCell<Student, String>() {

                    final Button deleteBtn = new Button("Delete");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            deleteBtn.setStyle("-fx-background-color: #0a9ec2; -fx-text-fill: #FFFFFF; -fx-font-size: 13");
                            deleteBtn.setOnAction(event -> {
                                Student student = getTableView().getItems().get(getIndex());
                                sql.deleteStudent(student);
                                window.setScene(studentOverviewScene.studentOverviewScene(window));
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
