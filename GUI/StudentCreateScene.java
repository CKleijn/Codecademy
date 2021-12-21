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
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class StudentCreateScene extends domain.StudentValidation{
    private StudentSQL sql = new StudentSQL();

    public Scene studentInputScene(Stage window) {
        StudentOverviewScene studentOverviewScene = new StudentOverviewScene();

        //Layout of the text in the buttons
		Font font = Font.font("Verdana");

		//Height of the input fields
		double heightTextAreas = 3000;
		
		//Background image
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
            window.setScene(studentOverviewScene.studentOverviewScene(window));
        });


        //All labels and input fields
		Label emailLabel = new Label("Email: ");
		TextArea emailTextArea = new TextArea();
		emailTextArea.setPrefHeight(heightTextAreas);
		
		Label nameLabel = new Label("Name: ");
		TextArea nameTextArea = new TextArea();
		nameTextArea.setPrefHeight(heightTextAreas);
		
		Label birthDayLabel = new Label("Birthday: ");
		TextArea birthDayTextArea = new TextArea();
		birthDayTextArea.setPrefHeight(heightTextAreas);

        Label birthMonthLabel = new Label("Birthmonth: ");
		TextArea birthMonthTextArea = new TextArea();
		birthMonthTextArea.setPrefHeight(heightTextAreas);

        Label birthYearLabel = new Label("Birthyear: ");
		TextArea birthYearTextArea = new TextArea();
		birthYearTextArea.setPrefHeight(heightTextAreas);
		
		Label genderLabel = new Label("Gender: ");
		TextArea genderTextArea = new TextArea();
		genderTextArea.setPrefHeight(heightTextAreas);
		
		Label streetLabel = new Label("Street: ");
		TextArea streetTextArea = new TextArea();
		streetTextArea.setPrefHeight(heightTextAreas);

        Label houseNumberLabel = new Label("House number: ");
		TextArea houseNumberTextArea = new TextArea();
		houseNumberTextArea.setPrefHeight(heightTextAreas);

        Label houseNumberAdditionLabel = new Label("House number addition: ");
		TextArea houseNumberAdditionTextArea = new TextArea();
		houseNumberAdditionTextArea.setPrefHeight(heightTextAreas);

        Label postalCodeLabel = new Label("Postal code: ");
		TextArea postalCodeTextArea = new TextArea();
		postalCodeTextArea.setPrefHeight(heightTextAreas);
		
		Label residenceLabel = new Label("Residence: ");
		TextArea residenceTextArea = new TextArea();
		residenceTextArea.setPrefHeight(heightTextAreas);
		
		Label countryLabel = new Label("Country: ");
		TextArea countryTextArea = new TextArea();
		residenceTextArea.setPrefHeight(heightTextAreas);

		//Last button to create with al the information in the textareas
		Button createStudentButton = new Button("Add student");
		createStudentButton.setPrefSize(120, 40);
		createStudentButton.setFont(font);
		createStudentButton.setStyle("-fx-background-color: #0B9EC3; -fx-text-fill: #FFFFFF; -fx-font-size: 13");

		createStudentButton.setOnAction((event) -> {
			if(!checkEmail(emailTextArea.getText()) && checkDate(Integer.parseInt(birthDayTextArea.getText()), Integer.parseInt(birthMonthTextArea.getText()), Integer.parseInt(birthYearTextArea.getText())) 
			&& checkPostalCode(postalCodeTextArea.getText()) && checkGender(genderTextArea.getText()) && !nameTextArea.getText().isEmpty() && !streetTextArea.getText().isEmpty() && !houseNumberTextArea.getText().isEmpty() && !residenceTextArea.getText().isEmpty() && !countryTextArea.getText().isEmpty()){
				
				Student student = new Student(emailTextArea.getText(), nameTextArea.getText(), Integer.parseInt(birthDayTextArea.getText()), Integer.parseInt(birthMonthTextArea.getText()), Integer.parseInt(birthYearTextArea.getText()), 
				genderTextArea.getText(), streetTextArea.getText(), houseNumberTextArea.getText(), houseNumberAdditionTextArea.getText(), postalCodeTextArea.getText(), residenceTextArea.getText(), countryTextArea.getText());
				sql.createStudent(student);
				window.setScene(studentOverviewScene.studentOverviewScene(window));

			}
		});

        HBox buttonHBox = new HBox();
        buttonHBox.getChildren().addAll(createStudentButton);

        GridPane grid = new GridPane();
		grid.setPadding(new Insets(40, 0, 0, 0));
		grid.setHgap(5);
		grid.setVgap(2);
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
        pane.setPadding(new Insets(15, 15, 15, 15));
        pane.getChildren().add(imageView);
        pane.setTop(backButton);
        pane.setCenter(grid);

        Scene sscene = new Scene(pane, 1080, 620);
        return sscene;
    }
}
