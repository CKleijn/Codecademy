package GUI;

import database.ExternalPersonSQL;
import database.RegistrationSQL;
import domain.Course;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CourseWebcastScene {
    ExternalPersonSQL sqlE = new ExternalPersonSQL();
    RegistrationSQL sqlR = new RegistrationSQL();

    public Scene courseWebcastScene(Stage window, Webcast webcast, Course course) {
        CourseDetailPage courseDetailPage = new CourseDetailPage();

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
            window.setScene(courseDetailPage.CourseDetailScene(window, course));
        });

        HBox menu = new HBox(backButton);
        menu.setSpacing(10);

        Label infoIDLabel = new Label("Webcast ID: ");
        Label webcastIDLabel = new Label();
        webcastIDLabel.setText(String.valueOf(webcast.getItemId()));

        Label infoDurationLabel = new Label("Webcast duration: ");
        Label webcastDurationLabel = new Label();
        webcastDurationLabel.setText(String.valueOf(webcast.getDuration()) + " minutes");

        Label infoURLLabel = new Label("Webcast URL: ");
        Label webcastURLLabel = new Label();
        webcastURLLabel.setText(webcast.getUrl());

        Label infoTitleLabel = new Label("Webcast title: ");
        Label webcastTitleLabel = new Label();
        webcastTitleLabel.setText(webcast.getTitle());

        Label infoDescLabel = new Label("Webcast description: ");
        Label webcastDescLabel = new Label();
        webcastDescLabel.setText(webcast.getDescription());

        Label infoDateLabel = new Label("Webcast publication date: ");
        Label webcastDateLabel = new Label();
        webcastDateLabel.setText(String.valueOf(webcast.getPublicationDate()));

        Label infoStatusLabel = new Label("Webcast status: ");
        Label webcastStatusLabel = new Label();
        webcastStatusLabel.setText(webcast.getStatus());

        Label infoExPerNameLabel = new Label("Webcast representative name: ");
        Label webcastExPerNameLabel = new Label();
        webcastExPerNameLabel.setText(sqlE.getExternalPersonById(webcast).getName());

        Label infoExPerOrganisationLabel = new Label("Webcast representative organisation: ");
        Label webcastExPerOrganisationLabel = new Label();
        webcastExPerOrganisationLabel.setText(sqlE.getExternalPersonById(webcast).getOrganisation());

        Label infoViewsLabel = new Label("Webcast average views: ");
        Label webcastViewsLabel = new Label();
        webcastViewsLabel.setText(String.valueOf(sqlR.getAverageViews(webcast)));

        Label infoProgressLabel = new Label("Webcast average progress: ");
        Label webcastProgressLabel = new Label();
        webcastProgressLabel.setText(String.valueOf(sqlR.getAverageProgress(webcast) + "%"));

        GridPane grid = new GridPane();
        
		grid.setPadding(new Insets(40, 0, 0, 0));
		grid.setHgap(5);
		grid.setVgap(5);
        grid.add(infoIDLabel, 0, 0 , 1, 1);
        grid.add(webcastIDLabel, 1, 0 , 1, 1);
        grid.add(infoDurationLabel, 0, 1 , 1, 1);
        grid.add(webcastDurationLabel, 1, 1 , 1, 1);
        grid.add(infoURLLabel, 0, 2 , 1, 1);
        grid.add(webcastURLLabel, 1, 2 , 1, 1);
        grid.add(infoTitleLabel, 0, 3 , 1, 1);
        grid.add(webcastTitleLabel, 1, 3 , 1, 1);
        grid.add(infoDescLabel, 0, 4 , 1, 1);
        grid.add(webcastDescLabel, 1, 4 , 1, 1);
        grid.add(infoDateLabel, 0, 5 , 1, 1);
        grid.add(webcastDateLabel, 1, 5 , 1, 1);
        grid.add(infoStatusLabel, 0, 6 , 1, 1);
        grid.add(webcastStatusLabel, 1, 6 , 1, 1);
        grid.add(infoExPerNameLabel, 0, 7 , 1, 1);
        grid.add(webcastExPerNameLabel, 1, 7 , 1, 1);
        grid.add(infoExPerOrganisationLabel, 0, 8 , 1, 1);
        grid.add(webcastExPerOrganisationLabel, 1, 8 , 1, 1);
        grid.add(infoViewsLabel, 0, 9 , 1, 1);
        grid.add(webcastViewsLabel, 1, 9 , 1, 1);
        grid.add(infoProgressLabel, 0, 10 , 1, 1);
        grid.add(webcastProgressLabel, 1, 10 , 1, 1);

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(15, 15, 15, 15));
        pane.getChildren().add(imageView);
        pane.setTop(menu);
        pane.setCenter(grid);


        Scene sscene = new Scene(pane, 1080, 620);
        return sscene;
    }
}
