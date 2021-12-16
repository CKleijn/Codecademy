package GUI;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class BaseGUI extends Application{
	HomescreenScene homeScreen = new HomescreenScene();
	
	@Override
	public void start(Stage window) throws Exception {
		
		try {
			window.getIcons().add(new Image(getClass().getResourceAsStream("../resources/icon.png")));
		}catch(Exception e) {}
		
		window.setTitle("Codecademy");
		window.setScene(homeScreen.homeScene(window));
		window.show();
	}
}
