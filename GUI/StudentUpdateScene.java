package GUI;

import database.StudentSQL;
import domain.Student;
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
import javafx.stage.Stage;

public class StudentUpdateScene {
    private StudentSQL sql = new StudentSQL();

    public Scene studentUpdateScene(Stage window, Student old_student) {
        StudentOverviewScene studentOverviewScene = new StudentOverviewScene();

        // Background image of the startSceen
        Image image = new Image("resources/backgroundImage.jpg");
        ImageView imageView = new ImageView(image);
        Group root = new Group();
        root.getChildren().addAll(imageView);

        // Button to go back to the homeScene.
        Button backButton = new Button("Back");
        backButton.setPrefSize(80, 37);
        backButton.setOnAction((event) -> {
            window.setScene(studentOverviewScene.studentOverviewScene(window));
        });

        HBox menu = new HBox(backButton);
        menu.setSpacing(10);

        //Student
		Label emailLabel = new Label("Email: ");
		TextArea emailTextArea = new TextArea();
        emailTextArea.setText(old_student.getEmail());
		emailTextArea.setPrefHeight(12);
		
		Label nameLabel = new Label("Name: ");
		TextArea nameTextArea = new TextArea();
        nameTextArea.setText(old_student.getName());
		nameTextArea.setPrefHeight(12);
		
		Label birthDayLabel = new Label("Birthday: ");
		TextArea birthDayTextArea = new TextArea();
        birthDayTextArea.setText(String.valueOf(old_student.getBirthDay()));
		birthDayTextArea.setPrefHeight(12);

        Label birthMonthLabel = new Label("Birthmonth: ");
		TextArea birthMonthTextArea = new TextArea();
        birthMonthTextArea.setText(String.valueOf(old_student.getBirthMonth()));
		birthMonthTextArea.setPrefHeight(12);

        Label birthYearLabel = new Label("Birthyear: ");
		TextArea birthYearTextArea = new TextArea();
        birthYearTextArea.setText(String.valueOf(old_student.getBirthYear()));
		birthYearTextArea.setPrefHeight(12);
		
		Label genderLabel = new Label("Gender: ");
		TextArea genderTextArea = new TextArea();
        genderTextArea.setText(old_student.getGender());
		genderTextArea.setPrefHeight(12);
		
		Label streetLabel = new Label("Street: ");
		TextArea streetTextArea = new TextArea();
        streetTextArea.setText(old_student.getStreet());
		streetTextArea.setPrefHeight(12);

        Label houseNumberLabel = new Label("House number: ");
		TextArea houseNumberTextArea = new TextArea();
        houseNumberTextArea.setText(old_student.getHouseNumber());
		houseNumberTextArea.setPrefHeight(12);

        Label houseNumberAdditionLabel = new Label("House number addition: ");
		TextArea houseNumberAdditionTextArea = new TextArea();
        houseNumberAdditionTextArea.setText(old_student.getHouseNumberAddition());
		houseNumberAdditionTextArea.setPrefHeight(12);

        Label postalCodeLabel = new Label("Postal code: ");
		TextArea postalCodeTextArea = new TextArea();
        postalCodeTextArea.setText(old_student.getPostalCode());
		postalCodeTextArea.setPrefHeight(12);
		
		Label residenceLabel = new Label("Residence: ");
		TextArea residenceTextArea = new TextArea();
        residenceTextArea.setText(old_student.getResidence());
		residenceTextArea.setPrefHeight(12);
		
		Label countryLabel = new Label("Country: ");
		TextArea countryTextArea = new TextArea();
        countryTextArea.setText(old_student.getCountry());
		countryTextArea.setPrefHeight(12);

        Button editStudentButton = new Button("Update student");
		editStudentButton.setPrefSize(120, 40);
		editStudentButton.setOnAction((event) -> {
			Student student = new Student(emailTextArea.getText(), nameTextArea.getText(), Integer.parseInt(birthDayTextArea.getText()), Integer.parseInt(birthMonthTextArea.getText()), Integer.parseInt(birthYearTextArea.getText()), genderTextArea.getText(), streetTextArea.getText(), houseNumberTextArea.getText(), houseNumberAdditionTextArea.getText(), postalCodeTextArea.getText(), residenceTextArea.getText(), countryTextArea.getText());
			sql.updateStudent(student);
			window.setScene(studentOverviewScene.studentOverviewScene(window));
		});

        HBox buttonHBox = new HBox();
        buttonHBox.getChildren().addAll(editStudentButton);

        GridPane grid = new GridPane();
		grid.setPadding(new Insets(50, 50, 50, 50));
		grid.setHgap(5);
		grid.setVgap(5);
		grid.add(emailLabel, 0, 0 , 1, 1);
		grid.add(emailTextArea, 1, 0 , 1, 1);
		grid.add(nameLabel, 0, 1 , 1, 1);
		grid.add(nameTextArea, 1, 1 , 1, 1);
		grid.add(birthDayLabel, 0, 2 , 1, 1);
		grid.add(birthDayTextArea, 1, 2 , 1, 1);
        grid.add(birthMonthLabel, 0, 3 , 1, 1);
		grid.add(birthMonthTextArea, 1, 3 , 1, 1);
        grid.add(birthYearLabel, 0, 4 , 1, 1);
		grid.add(birthYearTextArea, 1, 4 , 1, 1);
		grid.add(genderLabel, 0, 5 , 1, 1);
		grid.add(genderTextArea, 1, 5 , 1, 1);
		grid.add(streetLabel, 0, 6 , 1, 1);
		grid.add(streetTextArea, 1, 6 , 1, 1);
        grid.add(houseNumberLabel, 0, 7 , 1, 1);
		grid.add(houseNumberTextArea, 1, 7 , 1, 1);
        grid.add(houseNumberAdditionLabel, 0, 8 , 1, 1);
		grid.add(houseNumberAdditionTextArea, 1, 8 , 1, 1);
        grid.add(postalCodeLabel, 0, 9 , 1, 1);
		grid.add(postalCodeTextArea, 1, 9 , 1, 1);
		grid.add(residenceLabel, 0, 10 , 1, 1);
		grid.add(residenceTextArea, 1, 10 , 1, 1);
		grid.add(countryLabel, 0, 11, 1, 1);
		grid.add(countryTextArea, 1, 11, 1, 1);
		grid.add(buttonHBox, 1, 12, 1, 1);

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.getChildren().add(imageView);
        pane.setTop(menu);
        pane.setCenter(grid);

        Scene sscene = new Scene(pane);
        return sscene;
    }
}
