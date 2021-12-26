package GUI;

import database.ExternalPersonSQL;
import domain.ExternalPerson;
import domain.Role;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ExternalPersonUpdateScene {
    private ExternalPersonSQL sql = new ExternalPersonSQL();

    public Scene externalPersonUpdateScene(Stage window, ExternalPerson old_externalPerson) {
        ExternalPersonOverviewScene externalPersonOverviewScene = new ExternalPersonOverviewScene();

        // Layout of the text in the buttons
        Font font = Font.font("Verdana");

        // Background image
        Image image = new Image("resources/backgroundImage.jpg");
        ImageView imageView = new ImageView(image);
        Group root = new Group();
        root.getChildren().addAll(imageView);

        // Button to go back to the StudentOverviewScene.
        Button backButton = new Button("Back");
        backButton.setPrefSize(80, 37);
        backButton.setFont(font);
        backButton.setStyle("-fx-background-color: #6BCAE2; -fx-text-fill: #FFFFFF; -fx-font-size: 13");
        backButton.setOnAction((event) -> {
            window.setScene(externalPersonOverviewScene.externalPersonOverviewScene(window));
        });

        Label nameLabel = new Label("Name: ");
        nameLabel.setVisible(false);
        TextArea nameTextArea = new TextArea();
        nameTextArea.setVisible(false);

        Label emailLabel = new Label("Email: ");
        emailLabel.setVisible(false);
        TextArea emailTextArea = new TextArea();
        emailTextArea.setVisible(false);

        Label organisationLabel = new Label("Organisation: ");
        organisationLabel.setVisible(false);
        TextArea organisationTextArea = new TextArea();
        organisationTextArea.setVisible(false);

        Button updateExternalPersonButton = new Button("Update external person");
        updateExternalPersonButton.setPrefSize(120, 40);
        updateExternalPersonButton.setFont(font);
        updateExternalPersonButton.setStyle("-fx-background-color: #0B9EC3; -fx-text-fill: #FFFFFF; -fx-font-size: 13");

        HBox buttonHBox = new HBox();
        buttonHBox.getChildren().addAll(updateExternalPersonButton);
        buttonHBox.setVisible(false);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(40, 0, 0, 0));
        grid.setHgap(5);
        grid.setVgap(2);
        grid.add(nameLabel, 0, 1, 1, 1);
        grid.add(nameTextArea, 1, 1, 1, 1);
        grid.add(emailLabel, 0, 3, 1, 1);
        grid.add(emailTextArea, 1, 3, 1, 1);
        grid.add(organisationLabel, 0, 3, 1, 1);
        grid.add(organisationTextArea, 1, 3, 1, 1);
        grid.add(buttonHBox, 1, 4, 1, 1);

        if (old_externalPerson.getRole().equals(Role.EMPLOYEE.name())) {
            nameLabel.setVisible(true);
            nameTextArea.setVisible(true);
            nameTextArea.setText(old_externalPerson.getName());
            emailLabel.setVisible(false);
            emailTextArea.setVisible(false);
            emailTextArea.setText(old_externalPerson.getEmail());
            organisationLabel.setVisible(false);
            organisationTextArea.setVisible(false);
            organisationTextArea.setText(old_externalPerson.getOrganisation());
            buttonHBox.setVisible(true);
        } else if (old_externalPerson.getRole().equals(Role.CONTACTPERSON.name())) {
            nameLabel.setVisible(true);
            nameTextArea.setVisible(true);
            nameTextArea.setText(old_externalPerson.getName());
            emailLabel.setVisible(true);
            emailTextArea.setVisible(true);
            emailTextArea.setText(old_externalPerson.getEmail());
            organisationLabel.setVisible(false);
            organisationTextArea.setVisible(false);
            organisationTextArea.setText(old_externalPerson.getOrganisation());
            buttonHBox.setVisible(true);
        } else if (old_externalPerson.getRole().equals(Role.REPRESENTATIVE.name())) {
            nameLabel.setVisible(true);
            nameTextArea.setVisible(true);
            nameTextArea.setText(old_externalPerson.getName());
            emailLabel.setVisible(false);
            emailTextArea.setVisible(false);
            emailTextArea.setText(old_externalPerson.getEmail());
            organisationLabel.setVisible(true);
            organisationTextArea.setVisible(true);
            organisationTextArea.setText(old_externalPerson.getOrganisation());
            buttonHBox.setVisible(true);
        }

        updateExternalPersonButton.setOnAction((event) -> {
            ExternalPerson externalPerson = new ExternalPerson(old_externalPerson.getExternalPersonId(), nameTextArea.getText(), emailTextArea.getText(), organisationTextArea.getText(), old_externalPerson.getRole());
            sql.updateExternalPerson(externalPerson);
            window.setScene(externalPersonOverviewScene.externalPersonOverviewScene(window));
        });

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(15, 15, 15, 15));
        pane.getChildren().add(imageView);
        pane.setTop(backButton);
        pane.setCenter(grid);

        Scene sscene = new Scene(pane, 1080, 620);
        return sscene;
    }
}
