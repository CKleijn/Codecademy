package GUI;

import database.StatisticSQL;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class StatisticOverviewScene {
    StatisticSQL sqlS = new StatisticSQL();

    public Scene statisticOverviewScene(Stage window) {
        HomescreenScene homescreenScene = new HomescreenScene();

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

        HBox menu = new HBox(backButton);
        menu.setSpacing(10);

        Label genderLabel = new Label("Choose gender: ");
        ComboBox<String>cbxGender = new ComboBox<>();
        String[] genders = {"M", "F"};
        cbxGender.getItems().setAll(genders);

        // cbxGender.setOnAction((event) -> {
        //     if(cbxGender.getSelectionModel().getSelectedItem().equals("M")) {
        //         sqlC.calculateGenderCertificates("M");
        //     } else if(cbxGender.getSelectionModel().getSelectedItem().equals("F")) {
        //         sqlC.calculateGenderCertificates("F");
        //     }
        // });

        GridPane grid = new GridPane();

        Label infoWebcastLabel = new Label("Top 3 most viewed webcasts: ");
        int i = 1;
        grid.add(infoWebcastLabel, 0, i, 1, 1) ;
        for(String webcast : sqlS.getTop3MostViewedWebcasts()){
            Label label = new Label(i + ". " + webcast);
            grid.add(label, 1, i, 1, 1);
            i++;
        }

        Label infoCourseLabel = new Label("Top 3 most certificates per course: ");
        int j = 5;
        int k = 1;
        grid.add(infoCourseLabel, 0, j, 1, 1) ;
        for(String course : sqlS.getTop3MostCertificateCourses()){
            Label label = new Label(k + ". " + course);
            grid.add(label, 1, j, 1, 1);
            j++;
            k++;
        }

		grid.setPadding(new Insets(40, 0, 0, 0));
		grid.setHgap(5);
		grid.setVgap(2);
        grid.add(genderLabel, 0, 0 , 1, 1);
		grid.add(cbxGender, 1, 0 , 1, 1);

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(15, 15, 15, 15));
        pane.getChildren().add(imageView);
        pane.setTop(menu);
        pane.setCenter(grid);

        Scene sscene = new Scene(pane, 1080, 620);
        return sscene;
    }
}
