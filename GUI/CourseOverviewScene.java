package GUI;

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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

public class CourseOverviewScene {
    private CourseSQL sql = new CourseSQL();
    

    public Scene courseOverviewScene(Stage window) {
        HomescreenScene homescreenScene = new HomescreenScene();
        CourseOverviewScene courseOverviewScene = new CourseOverviewScene();
        CourseCreateScene courseCreateScene = new CourseCreateScene();
        CourseUpdateScene courseUpdateScene = new CourseUpdateScene();

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

        Button createButton = new Button("Create");
        createButton.setPrefSize(80, 37);
        createButton.setFont(font);
		createButton.setStyle("-fx-background-color: #6BCAE2; -fx-text-fill: #FFFFFF; -fx-font-size: 13");
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
        TableColumn<Course, String> relCol = new TableColumn<Course, String>("Course relevant");
        TableColumn deleteCol = new TableColumn("Delete");      
        
        table.getColumns().addAll(nameCol, topicCol, introductionCol, levelCol, relCol, deleteCol);

        ObservableList<Course> list = sql.getCourseList();

        nameCol.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
        topicCol.setCellValueFactory(new PropertyValueFactory<Course, String>("topic"));
        introductionCol.setCellValueFactory(new PropertyValueFactory<Course, String>("introduction"));
        levelCol.setCellValueFactory(new PropertyValueFactory<Course, String>("level"));
        relCol.setCellValueFactory(new PropertyValueFactory<Course, String>("hasrelevantcourse"));
        deleteCol.setCellValueFactory(new PropertyValueFactory<>("Delete"));
        
        table.setOnMouseClicked((event) -> {
            Course course = table.getSelectionModel().getSelectedItem();
            window.setScene(courseUpdateScene.courseUpdateScene(window, course));
        });
        
        Callback<TableColumn<Course, String>, TableCell<Course, String>> cellFactory = new Callback<TableColumn<Course, String>, TableCell<Course, String>>() {
            @Override
            public TableCell call(final TableColumn<Course, String> param) {
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

        deleteCol.setCellFactory(cellFactory);

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
