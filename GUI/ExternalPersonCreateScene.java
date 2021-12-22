package GUI;

import database.ExternalPersonSQL;
import domain.ExternalPerson;
import domain.Role;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ExternalPersonCreateScene {
    private ExternalPersonSQL sql = new ExternalPersonSQL();

    public Scene externalPersonCreateScene(Stage window) {
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

        Label chooseLabel = new Label("Choose role: ");
        ComboBox<Role> cbxRole = new ComboBox<>();
        cbxRole.getItems().setAll(Role.values());

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

        Button createExternalPersonButton = new Button("Add external person");
        createExternalPersonButton.setPrefSize(120, 40);
        createExternalPersonButton.setFont(font);
        createExternalPersonButton.setStyle("-fx-background-color: #0B9EC3; -fx-text-fill: #FFFFFF; -fx-font-size: 13");

        HBox buttonHBox = new HBox();
        buttonHBox.getChildren().addAll(createExternalPersonButton);
        buttonHBox.setVisible(false);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(40, 0, 0, 0));
        grid.setHgap(5);
        grid.setVgap(2);
        grid.add(chooseLabel, 0, 0, 1, 1);
        grid.add(cbxRole, 1, 0, 1, 1);
        grid.add(nameLabel, 0, 1, 1, 1);
        grid.add(nameTextArea, 1, 1, 1, 1);
        grid.add(emailLabel, 0, 3, 1, 1);
        grid.add(emailTextArea, 1, 3, 1, 1);
        grid.add(organisationLabel, 0, 3, 1, 1);
        grid.add(organisationTextArea, 1, 3, 1, 1);
        grid.add(buttonHBox, 1, 4, 1, 1);

        cbxRole.setOnAction((event) -> {
            if (cbxRole.getSelectionModel().getSelectedItem() == Role.EMPLOYEE) {
                nameLabel.setVisible(true);
                nameTextArea.setVisible(true);
                emailLabel.setVisible(false);
                emailTextArea.setVisible(false);
                organisationLabel.setVisible(false);
                organisationTextArea.setVisible(false);
                buttonHBox.setVisible(true);
            } else if (cbxRole.getSelectionModel().getSelectedItem() == Role.CONTACTPERSON) {
                nameLabel.setVisible(true);
                nameTextArea.setVisible(true);
                emailLabel.setVisible(true);
                emailTextArea.setVisible(true);
                organisationLabel.setVisible(false);
                organisationTextArea.setVisible(false);
                buttonHBox.setVisible(true);
            } else if (cbxRole.getSelectionModel().getSelectedItem() == Role.REPRESENTATIVE) {
                nameLabel.setVisible(true);
                nameTextArea.setVisible(true);
                emailLabel.setVisible(false);
                emailTextArea.setVisible(false);
                organisationLabel.setVisible(true);
                organisationTextArea.setVisible(true);
                buttonHBox.setVisible(true);
            }
        });

        createExternalPersonButton.setOnAction((event) -> {
            ExternalPerson externalPerson = new ExternalPerson(nameTextArea.getText(), emailTextArea.getText(), organisationTextArea.getText(), cbxRole.getSelectionModel().getSelectedItem().name());
            sql.createExternalPerson(externalPerson);
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
