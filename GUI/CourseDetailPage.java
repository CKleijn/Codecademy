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
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CourseDetailPage {
    private CourseSQL sql = new CourseSQL();
    

    public Scene CourseDetailScene(Stage window, Course course) {

        CourseOverviewScene courseOverviewScene = new CourseOverviewScene();
        CourseModifyModules courseModifyModules = new CourseModifyModules();
        CourseModifyWebcasts courseModifyWebcasts = new CourseModifyWebcasts();

        //Layout of the text in the buttons
        Font font = Font.font("Verdana");

        // Background image
        Image image = new Image("resources/backgroundImage.jpg");
        ImageView imageView = new ImageView(image);
        Group root = new Group();
        root.getChildren().addAll(imageView);

        // Menubar and the buttons that are in the menu
        HBox menu = new HBox();

        Button backButton = new Button("Back");
        backButton.setPrefSize(80, 37);
        backButton.setFont(font);
        backButton.setStyle("-fx-background-color: #6BCAE2; -fx-text-fill: #FFFFFF; -fx-font-size: 13");
        backButton.setOnAction((event) -> {
            window.setScene(courseOverviewScene.courseOverviewScene(window));
        });

        Button modifyModules = new Button("modify modules");
        modifyModules.setPrefSize(120, 37);
        modifyModules.setFont(font);
        modifyModules.setStyle("-fx-background-color: #6BCAE2; -fx-text-fill: #FFFFFF; -fx-font-size: 13");
        modifyModules.setOnAction((event) -> {
            window.setScene(courseModifyModules.CourseModifyModulesScene(window, course));
        });

        Button modifyWebcasts = new Button("modify webcasts");
        modifyWebcasts.setPrefSize(120, 37);
        modifyWebcasts.setFont(font);
        modifyWebcasts.setStyle("-fx-background-color: #6BCAE2; -fx-text-fill: #FFFFFF; -fx-font-size: 13");
        modifyWebcasts.setOnAction((event)-> {
            window.setScene(courseModifyWebcasts.CourseModifyWebcastsScene(window, course));
        });        

        menu.getChildren().addAll(backButton, modifyModules, modifyWebcasts);
        menu.setSpacing(15);

        //Course inforamtion
        Label infoNameLabel = new Label("Course name: ");
        Label nameLabel = new Label();
        nameLabel.setText(course.getName());

        Label infoTopicLabel = new Label("Course topic: ");
        Label topicLabel = new Label();
        topicLabel.setText(course.getTopic());

        Label infoIntroLabel = new Label("Course intoduction: ");
        Label introLabel = new Label();
        introLabel.setText(course.getIntroduction());

        Label infoLevelLabel = new Label("Course level: ");
        Label levelLabel = new Label();
        levelLabel.setText(course.getLevel());

        GridPane grid = new GridPane();

        Label infoModulesLabel = new Label("Modules: ");
        int j = 5;
        for(String module : sql.getSpecificModules(course)){
            Label label = new Label(module);
            grid.add(label, 1, j, 1, 1);
            j++;
        }

        Label infoWebcastLabel = new Label("Webcasts: ");
        j+=2;
        grid.add(infoWebcastLabel, 0, j, 1, 1) ;
        for(String webcast : sql.getSpecificWebcasts(course)){
            Label label = new Label(webcast);
            grid.add(label, 1, j, 1, 1);
            j++;
        }

        Label hasRelevantLabel = new Label("Relevant courses: ");
        j+=2;
        grid.add(hasRelevantLabel, 0, j, 1, 1);
        for(String relCourse : sql.relevantCourses(course)){
            Label label = new Label(relCourse);
            grid.add(label, 1, j, 1, 1);
            j++;
        }

        
		grid.setPadding(new Insets(40, 0, 0, 0));
		grid.setHgap(5);
		grid.setVgap(5);
        grid.add(infoNameLabel, 0, 0 , 1, 1);
        grid.add(nameLabel, 1, 0 , 1, 1);
        grid.add(infoTopicLabel, 0, 1 , 1, 1);
        grid.add(topicLabel, 1, 1 , 1, 1);
        grid.add(infoIntroLabel, 0, 2 , 1, 1);
        grid.add(introLabel, 1, 2 , 1, 1);
        grid.add(infoLevelLabel, 0, 3 , 1, 1);
        grid.add(levelLabel, 1, 3 , 1, 1);
        grid.add(infoModulesLabel, 0, 5 , 1, 1);
        

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(15, 15, 15, 15));
        pane.getChildren().add(imageView);
        pane.setTop(menu);
        pane.setCenter(grid);


        Scene sscene = new Scene(pane, 1080, 620);
        return sscene;

        
    }
}