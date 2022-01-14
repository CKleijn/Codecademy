package GUI;

import java.util.Arrays;

import database.CourseSQL;
import domain.Course;
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
import javafx.stage.Stage;
import javafx.util.Callback;

public class CourseOverviewScene {
    private CourseSQL sql = new CourseSQL();
    

    public Scene courseOverviewScene(Stage window) {
        HomescreenScene homescreenScene = new HomescreenScene();
        CourseOverviewScene courseOverviewScene = new CourseOverviewScene();
        CourseUpdateScene  courseUpdateScene = new CourseUpdateScene();
        CourseCreateScene courseCreateScene = new CourseCreateScene();
        CourseDetailPage courseDetailPage = new CourseDetailPage();

        // Background image
        Image image = new Image("resources/backgroundImage.jpg");
        ImageView imageView = new ImageView(image);
        Group root = new Group();
        root.getChildren().addAll(imageView);

        // Button to go back to the homeScene.
        Button backButton = new Button("Back");
        backButton.setPrefSize(80, 37);
        backButton.setOnAction((event) -> {
            window.setScene(homescreenScene.homeScene(window));
        });

        Button createButton = new Button("Create");
        createButton.setPrefSize(80, 37);
        createButton.setOnAction((event) -> {
            window.setScene(courseCreateScene.courseCreateScene(window));
        });

        HBox menu = new HBox(backButton, createButton);
        menu.setSpacing(10);

        TableView<Course> table = new TableView<Course>();
        TableColumn<Course, String> nameCol = new TableColumn<Course, String>("Course name");
        TableColumn<Course, String> topicCol = new TableColumn<Course, String>("Course topic");
        TableColumn<Course, String> introductionCol = new TableColumn<Course, String>("Course Introduction");
        TableColumn<Course, String> levelCol = new TableColumn<Course, String>("Course Level");
        TableColumn<Course, String> editCol = new TableColumn<Course, String>("Edit");      
        TableColumn<Course, String> deleteCol = new TableColumn<Course, String>("Delete");      
        
        table.getColumns().addAll(Arrays.asList(nameCol, topicCol, introductionCol, levelCol, editCol, deleteCol));

        ObservableList<Course> list = sql.getCourseList();

        nameCol.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
        topicCol.setCellValueFactory(new PropertyValueFactory<Course, String>("topic"));
        introductionCol.setCellValueFactory(new PropertyValueFactory<Course, String>("introduction"));
        levelCol.setCellValueFactory(new PropertyValueFactory<Course, String>("level"));
        editCol.setCellValueFactory(new PropertyValueFactory<>("Edit"));
        deleteCol.setCellValueFactory(new PropertyValueFactory<>("Delete"));
        
        table.setOnMouseClicked((event) -> {
            Course course = table.getSelectionModel().getSelectedItem();
            window.setScene(courseDetailPage.CourseDetailScene(window, course));
        });
        

        Callback<TableColumn<Course, String>, TableCell<Course, String>> editCellFactory = new Callback<TableColumn<Course, String>, TableCell<Course, String>>() {
            @Override
            public TableCell<Course, String> call(final TableColumn<Course, String> param) {
                final TableCell<Course, String> cell = new TableCell<Course, String>() {

                    final Button editBtn = new Button("Edit");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            editBtn.setOnAction(event -> {
                                Course course = getTableView().getItems().get(getIndex());
                                window.setScene(courseUpdateScene.courseUpdateScene(window, course));
                            });
                            setGraphic(editBtn);
                        }
                    }
                };
                return cell;
            }
        };

        Callback<TableColumn<Course, String>, TableCell<Course, String>> deletecellFactory = new Callback<TableColumn<Course, String>, TableCell<Course, String>>() {
            @Override
            public TableCell<Course, String> call(final TableColumn<Course, String> param) {
                final TableCell<Course, String> cell = new TableCell<Course, String>() {

                    final Button deleteBtn = new Button("Delete");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            deleteBtn.setOnAction(event -> {
                                Course course = getTableView().getItems().get(getIndex());
                                sql.deleteCourse(course);
                                window.setScene(courseOverviewScene.courseOverviewScene(window));
                            });
                            setGraphic(deleteBtn);
                        }
                    }
                };
                return cell;
            }
        };

        editCol.setCellFactory(editCellFactory);
        deleteCol.setCellFactory(deletecellFactory);

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
