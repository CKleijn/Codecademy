package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class BaseGUI extends Application{
	HomescreenScene homeScreen = new HomescreenScene();
	
	@Override
	public void start(Stage window) throws Exception {
		
		//Icon of the application (If the icon can't be found it gives and exception)
		try {
			window.getIcons().add(new Image(getClass().getResourceAsStream("/resources/icon.png")));
		} catch(Exception e) {
			e.getMessage();
		}

		Scene scene = new Scene(homeScreen.homeScene(window));
		window.setTitle("Calvin Kleijn (2186254), Guus van Damme (2182402), Matthijs Feringa (2185220), Timothy Borghouts (2182610)");
		window.setScene(scene);
		window.setMaximized(true);
		window.show();
	}
}
