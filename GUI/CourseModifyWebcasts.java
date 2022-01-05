package GUI;

import java.util.ArrayList;

import database.CourseSQL;
import domain.Course;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CourseModifyWebcasts {
    
    public Scene CourseModifyWebcastsScene(Stage window, Course course){
    
        CourseDetailPage courseDetailPage = new CourseDetailPage();
        CourseSQL sql = new CourseSQL();

        // sql.setModulesCourseName(itemTitle, CourseName);
        
        //Layout of the text in the buttons
        Font font = Font.font("Verdana");

        // Background image
        Image image = new Image("resources/backgroundImage.jpg");
        ImageView imageView = new ImageView(image);
        Group root = new Group();
        root.getChildren().addAll(imageView);
 
        // Button to go back to the courseDetailScene

        Button backButton = new Button("Back");
        backButton.setPrefSize(80, 37);
        backButton.setFont(font);
        backButton.setStyle("-fx-background-color: #6BCAE2; -fx-text-fill: #FFFFFF; -fx-font-size: 13");
        backButton.setOnAction((event) -> {
            window.setScene(courseDetailPage.CourseDetailScene(window, course));
        });
        
        //create the gridpane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(40, 0, 0, 0));
		grid.setHgap(5);
		grid.setVgap(5);

        //an arrayList van alle checkboxes
        ArrayList<CheckBox> checkBoxes = new ArrayList<>();

        //make a loop to iterate trough the modules that aren't added to a course yet
        int i = 0;
        for(String module : sql.getWebcasts()){
            CheckBox checkbox = new CheckBox(module);
            checkBoxes.add(checkbox);
            
            grid.add(checkbox, 0, i, 1, 1);
            i++;
        }

        //make a loop to iterate trough the modules that are added to the selected course
        for(String module : sql.getSpecificWebcasts(course)){
            CheckBox checkBox = new CheckBox(module);
            checkBoxes.add(checkBox);
            checkBox.setSelected(true);

            grid.add(checkBox, 0, i , 1, 1);
            i++;
        }

        //make a button to submit the changes
        Button submitButton = new Button("update webcasts");
        submitButton.setPrefSize(180, 40);
		submitButton.setFont(font);
		submitButton.setStyle("-fx-background-color: #0B9EC3; -fx-text-fill: #FFFFFF; -fx-font-size: 13");

        //Set an onclick event with a loop wich iterates over all the checkboxes and submit the changes to the db
        submitButton.setOnAction((event) -> {
            for(CheckBox checkBox : checkBoxes){
                boolean isSelected = checkBox.isSelected();
                if(isSelected){
                    sql.setCourseName(checkBox.getText(), course.getName());
                    System.out.println("test 1");
                } else {
                    sql.setNull(checkBox.getText());
                    System.out.println("test 2");
                }
            }
            window.setScene(courseDetailPage.CourseDetailScene(window, course));
        });

        grid.add(submitButton, 0, i+2, 1, 1);
        
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(15, 15, 15, 15));
        pane.getChildren().add(imageView);
        pane.setTop(backButton);
        pane.setCenter(grid);

        Scene sscene = new Scene(pane, 1080, 620);
        return sscene;
    }
    
}
