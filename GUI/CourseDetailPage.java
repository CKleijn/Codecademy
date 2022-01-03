package GUI;

import database.CourseSQL;
import domain.Course;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CourseDetailPage {
    private CourseSQL sql = new CourseSQL();
    

    public Scene CourseDetailScene(Stage window, Course course) {

        CourseOverviewScene courseOverviewScene = new CourseOverviewScene();

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
            window.setScene(courseOverviewScene.courseOverviewScene(window));
        });

        Label info1Label = new Label("Course name: ");
        Label nameLabel = new Label();
        nameLabel.setText(course.getName());

        Label info2Label = new Label("Course topic: ");
        Label topicLabel = new Label();
        topicLabel.setText(course.getTopic());

        Label info3Label = new Label("Course intoduction: ");
        Label introLabel = new Label();
        introLabel.setText(course.getIntroduction());

        Label info4Label = new Label("Course level: ");
        Label levelLabel = new Label();
        levelLabel.setText(course.getLevel());

        GridPane grid = new GridPane();

        Label info5Label = new Label("Modules: ");
        for(String module : sql.getSpecificModules(course)){
            int j = 4;
            Label label = new Label(module);
            grid.add(label, 1, j, 1, 1);
            j++;
        }

        
		grid.setPadding(new Insets(40, 0, 0, 0));
		grid.setHgap(5);
		grid.setVgap(5);
        grid.add(info1Label, 0, 0 , 1, 1);
        grid.add(nameLabel, 1, 0 , 1, 1);
        grid.add(info2Label, 0, 1 , 1, 1);
        grid.add(topicLabel, 1, 1 , 1, 1);
        grid.add(info3Label, 0, 2 , 1, 1);
        grid.add(introLabel, 1, 2 , 1, 1);
        grid.add(info4Label, 0, 3 , 1, 1);
        grid.add(levelLabel, 1, 3 , 1, 1);
        grid.add(info5Label, 0, 4 , 1, 1);

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(15, 15, 15, 15));
        pane.getChildren().add(imageView);
        pane.setTop(backButton);
        pane.setCenter(grid);


        Scene sscene = new Scene(pane, 1080, 620);
        return sscene;

        
    }
}