package Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collection;
import java.util.ResourceBundle;

public class STARTController {
    @FXML
    private MainWindowController mainWindowController;

    @FXML
    private  BorderPane StartBorderPane;
    public void closeApp( ) {
        Platform.exit();
        System.exit(0);
    }
    public void beginApp() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/MainWindow.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.controls");
       fxmlLoader.setResources(bundle);
        Image mainIcon = new Image(this.getClass().getResourceAsStream("/MainIcon/icon.png"));
        BorderPane borderPane= fxmlLoader.load();
        Scene scene2 = new Scene(borderPane);
        Stage stage2 = new Stage();
        stage2.setScene(scene2);
        stage2.show();
       //BorderPane borderPane = FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml"));
       //StartBorderPane.getChildren().setAll( borderPane);
        //StartBorderPane.getChildren().setAll(FXMLLoader.load("/fxml/MainWindow.fxml"));
       // mainWindowController.setMainStageFXML("/fxml/MainWindow.fxml");

    }
}
