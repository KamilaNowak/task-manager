import Database.DatabaseManager.DatabaseManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;

public class Main extends Application
{
    public static void main(String[] args) {
    launch(args);
    }
    @Override
    public void start(Stage primarystage) throws Exception{
        Locale.setDefault(new Locale("p"));
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.controls");

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("fxml/STARTWindow.fxml"));
        loader.setResources(bundle);
        BorderPane borderPane = loader.load();
        Scene scene = new Scene(borderPane);
        primarystage.setScene(scene);
        primarystage.setTitle(bundle.getString("application.title"));
        Image mainIcon = new Image(this.getClass().getResourceAsStream("/MainIcon/icon.png"));
        primarystage.getIcons().add(mainIcon);
        primarystage.setResizable(false);
        primarystage.show();

        DatabaseManager.initializeDatabase();
    }
}
