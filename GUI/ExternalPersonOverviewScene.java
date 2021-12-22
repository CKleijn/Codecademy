package GUI;

import database.ExternalPersonSQL;
import domain.ExternalPerson;
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

public class ExternalPersonOverviewScene {
    private ExternalPersonSQL sql = new ExternalPersonSQL();
    

    public Scene externalPersonOverviewScene(Stage window) {
        HomescreenScene homescreenScene = new HomescreenScene();
        ExternalPersonOverviewScene externalPersonOverviewScene = new ExternalPersonOverviewScene();
        ExternalPersonCreateScene externalPersonCreateScene = new ExternalPersonCreateScene();
        ExternalPersonUpdateScene externalPersonUpdateScene = new ExternalPersonUpdateScene();

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
            window.setScene(externalPersonCreateScene.externalPersonCreateScene(window));
        });

        HBox menu = new HBox(backButton, createButton);
        menu.setSpacing(10);

        TableView<ExternalPerson> table = new TableView<ExternalPerson>();
        TableColumn<ExternalPerson, String> idCol = new TableColumn<ExternalPerson, String>("ExternalPerson ID");
        TableColumn<ExternalPerson, String> nameCol = new TableColumn<ExternalPerson, String>("ExternalPerson name");
        TableColumn<ExternalPerson, String> emailCol = new TableColumn<ExternalPerson, String>("ExternalPerson email");
        TableColumn<ExternalPerson, String> organisationCol = new TableColumn<ExternalPerson, String>("ExternalPerson organisation");
        TableColumn<ExternalPerson, String> roleCol = new TableColumn<ExternalPerson, String>("ExternalPerson role");
        TableColumn deleteCol = new TableColumn("Delete");  
        
        table.getColumns().addAll(idCol, nameCol, emailCol, organisationCol, roleCol, deleteCol);

        ObservableList<ExternalPerson> list = sql.getExternalPersonList();

        idCol.setCellValueFactory(new PropertyValueFactory<ExternalPerson, String>("externalPersonId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<ExternalPerson, String>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<ExternalPerson, String>("email"));
        organisationCol.setCellValueFactory(new PropertyValueFactory<ExternalPerson, String>("organisation"));
        roleCol.setCellValueFactory(new PropertyValueFactory<ExternalPerson, String>("role"));
        deleteCol.setCellValueFactory(new PropertyValueFactory<>("Delete"));
        
        table.setOnMouseClicked((event) -> {
            ExternalPerson externalPerson = table.getSelectionModel().getSelectedItem();
            window.setScene(externalPersonUpdateScene.externalPersonUpdateScene(window, externalPerson));
        });
        
        Callback<TableColumn<ExternalPerson, String>, TableCell<ExternalPerson, String>> cellFactory = new Callback<TableColumn<ExternalPerson, String>, TableCell<ExternalPerson, String>>() {
            @Override
            public TableCell call(final TableColumn<ExternalPerson, String> param) {
                final TableCell<ExternalPerson, String> cell = new TableCell<ExternalPerson, String>() {

                    final Button deleteBtn = new Button("Delete");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            deleteBtn.setOnAction(event -> {
                                ExternalPerson externalPerson = getTableView().getItems().get(getIndex());
                                sql.deleteExternalPerson(externalPerson);
                                window.setScene(externalPersonOverviewScene.externalPersonOverviewScene(window));
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
