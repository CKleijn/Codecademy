package GUI;

import database.CourseSQL;
import domain.Course;
import domain.Module;
import domain.Webcast;
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
import javafx.stage.Stage;

public class CourseDetailPage {
    private CourseSQL sql = new CourseSQL();
    

    public Scene CourseDetailScene(Stage window, Course course) {

        CourseOverviewScene courseOverviewScene = new CourseOverviewScene();
        CourseModifyModules courseModifyModules = new CourseModifyModules();
        CourseModuleScene courseModuleScene = new CourseModuleScene();
        CourseDetailPage courseDetailPage = new CourseDetailPage();

        // Background image
        Image image = new Image("resources/backgroundImage.jpg");
        ImageView imageView = new ImageView(image);
        Group root = new Group();
        root.getChildren().addAll(imageView);

        Button backButton = new Button("Back");
        backButton.setPrefSize(80, 37);
        backButton.setOnAction((event) -> {
            window.setScene(courseOverviewScene.courseOverviewScene(window));
        });

        Button modifyModules = new Button("modify modules");
        modifyModules.setPrefSize(120, 37);
        modifyModules.setOnAction((event) -> {
            window.setScene(courseModifyModules.CourseModifyModulesScene(window, course));
        });    

        HBox menu = new HBox(backButton, modifyModules);
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

        Label infoCertificateLabel = new Label("Certificates obtained : ");
        Label certificateLabel = new Label(String.valueOf(sql.obtainedCertificates(course)));

        GridPane grid = new GridPane();

        Label infoModulesLabel = new Label("Modules: ");
        int j = 6;
        for(Module module : sql.getSpecificModules(course)){
            Button button = new Button(module.getTitle());
            button.setOnAction((event) -> {
                window.setScene(courseModuleScene.courseModuleScene(window, module, course));
            });
            button.setStyle("-fx-background-color: #0a9ec2; -fx-text-fill: #FFFFFF; -fx-font-size: 13");
            grid.add(button, 1, j, 1, 1);
            j++;
        }

        Label hasRelevantLabel = new Label("Relevant courses: ");
        j+=2;
        grid.add(hasRelevantLabel, 0, j, 1, 1);
        for(Course relCourse : sql.relevantCourses(course)){
            Button button = new Button(relCourse.getName());
            button.setOnAction((event) -> {
                window.setScene(courseDetailPage.CourseDetailScene(window, relCourse));
            });
            grid.add(button, 1, j, 1, 1);
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
        grid.add(certificateLabel, 1, 4 , 1, 1);
        grid.add(infoCertificateLabel, 0, 4 , 1, 1);
        grid.add(infoModulesLabel, 0, 6 , 1, 1);
        

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(15, 15, 15, 15));
        pane.getChildren().add(imageView);
        pane.setTop(menu);
        pane.setCenter(grid);


        Scene sscene = new Scene(pane);

        window.setFullScreen(true);

        sscene.getStylesheets().add("/resources/styleSheet.css");

        return sscene;

        
    }
}