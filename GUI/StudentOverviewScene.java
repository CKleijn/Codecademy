package GUI;

import database.StudentSQL;
import domain.Student;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class StudentOverviewScene {
    private StudentSQL sql = new StudentSQL();

    public Scene studentOverviewScene(Stage window) {
        HomescreenScene homescreenScene = new HomescreenScene();
        StudentOverviewScene studentOverviewScene = new StudentOverviewScene();

        // Background image of the startSceen
        Image image = new Image("resources/background_image.jpg");
        ImageView imageView = new ImageView(image);
        Group root = new Group();
        root.getChildren().addAll(imageView);

        // Button to go back to the homeScene.
        Button backButton = new Button("Back");
        backButton.setPrefSize(80, 37);
        backButton.setOnAction((event) -> {
            window.setScene(homescreenScene.homeScene(window));
        });

        HBox menu = new HBox(backButton);
        menu.setSpacing(10);

        //Student
		Label email = new Label("Email: ");
		TextArea email2 = new TextArea();
		email2.setPrefHeight(12);
		
		Label name = new Label("Name: ");
		TextArea name2 = new TextArea();
		name2.setPrefHeight(12);
		
		Label birthdate = new Label("Birthdate: ");
		TextArea birthdate2 = new TextArea();
		birthdate2.setPrefHeight(12);
		
		Label gender = new Label("Gender: ");
		TextArea gender2 = new TextArea();
		gender2.setPrefHeight(12);
		
		Label address = new Label("Address: ");
		TextArea address2 = new TextArea();
		address2.setPrefHeight(12);
		
		Label residence = new Label("Residence: ");
		TextArea residence2 = new TextArea();
		residence2.setPrefHeight(12);
		
		Label country = new Label("Country: ");
		TextArea country2 = new TextArea();
		country2.setPrefHeight(12);
		
		Button createStudentButton = new Button("Add student");
		createStudentButton.setPrefSize(120, 40);
		createStudentButton.setOnAction((event) -> {
			Student student = new Student(email2.getText(), name2.getText(), birthdate2.getText(), gender2.getText(), address2.getText(), residence2.getText(), country2.getText());
			sql.createStudent(student);
			window.setScene(studentOverviewScene.studentOverviewScene(window));
		});

        Button editStudentButton = new Button("Update student");
		editStudentButton.setPrefSize(120, 40);
		editStudentButton.setOnAction((event) -> {
			Student student = new Student(email2.getText(), name2.getText(), birthdate2.getText(), gender2.getText(), address2.getText(), residence2.getText(), country2.getText());
			sql.updateStudent(student);
			window.setScene(studentOverviewScene.studentOverviewScene(window));
		});

        Button deleteStudentButton = new Button("Delete student");
		deleteStudentButton.setPrefSize(120, 40);
		deleteStudentButton.setOnAction((event) -> {
			Student student = new Student(email2.getText(), name2.getText(), birthdate2.getText(), gender2.getText(), address2.getText(), residence2.getText(), country2.getText());
			sql.deleteStudent(student);
			window.setScene(studentOverviewScene.studentOverviewScene(window));
		});

        HBox buttonHBox = new HBox();
        buttonHBox.getChildren().addAll(createStudentButton, editStudentButton, deleteStudentButton);

        TableView<Student> table = new TableView<Student>();
        TableColumn<Student, String> emailCol = new TableColumn<Student, String>("Student email");
        TableColumn<Student, String> nameCol = new TableColumn<Student, String>("Student name");
        TableColumn<Student, String> birthdateCol = new TableColumn<Student, String>("Student birthdate");
        TableColumn<Student, String> genderCol = new TableColumn<Student, String>("Student gender");
        TableColumn<Student, String> addressCol = new TableColumn<Student, String>("Student address");
        TableColumn<Student, String> residenceCol = new TableColumn<Student, String>("Student residence");
        TableColumn<Student, String> countryCol = new TableColumn<Student, String>("Student country");
        table.getColumns().addAll(emailCol, nameCol, birthdateCol, genderCol, addressCol, residenceCol, countryCol);

        ObservableList<Student> list = sql.getStudentList();

        emailCol.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        birthdateCol.setCellValueFactory(new PropertyValueFactory<Student, String>("dateOfBirth"));
        genderCol.setCellValueFactory(new PropertyValueFactory<Student, String>("gender"));
        addressCol.setCellValueFactory(new PropertyValueFactory<Student, String>("address"));
        residenceCol.setCellValueFactory(new PropertyValueFactory<Student, String>("livingPlace"));
        countryCol.setCellValueFactory(new PropertyValueFactory<Student, String>("country"));
        
        table.setOnMouseClicked((event) -> {
            Student student = table.getSelectionModel().getSelectedItem();
            email2.setText(student.getEmail());
            name2.setText(student.getName());
            birthdate2.setText(student.getDateOfBirth());
            gender2.setText(student.getGender());
            address2.setText(student.getAddress());
            residence2.setText(student.getLivingPlace());
            country2.setText(student.getCountry());
        });

        table.setItems(list);
        table.setPrefWidth(600);

        GridPane grid = new GridPane();
		grid.setPadding(new Insets(50, 50, 50, 50));
		grid.setHgap(5);
		grid.setVgap(5);
		grid.add(email, 0, 0 , 1, 1);
		grid.add(email2, 1, 0 , 1, 1);
		grid.add(name, 0, 1 , 1, 1);
		grid.add(name2, 1, 1 , 1, 1);
		grid.add(birthdate, 0, 2 , 1, 1);
		grid.add(birthdate2, 1, 2 , 1, 1);
		grid.add(gender, 0, 3 , 1, 1);
		grid.add(gender2, 1, 3 , 1, 1);
		grid.add(address, 0, 4 , 1, 1);
		grid.add(address2, 1, 4 , 1, 1);
		grid.add(residence, 0, 5 , 1, 1);
		grid.add(residence2, 1, 5 , 1, 1);
		grid.add(country, 0, 6 , 1, 1);
		grid.add(country2, 1, 6 , 1, 1);
		grid.add(buttonHBox, 1, 7 , 1, 1);
        grid.add(table, 1, 8 , 1, 1);

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.getChildren().add(imageView);
        pane.setTop(menu);
        pane.setCenter(grid);

        Scene sscene = new Scene(pane, 1000, 600);
        return sscene;
    }
}
