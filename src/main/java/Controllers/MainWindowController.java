package Controllers;

import Dialogs.DialogWindows;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainWindowController {
    @FXML
    private BorderPane borderPane;
    @FXML
    private TopButtonsWindowController topButtonsWindowController;
    @FXML
    STARTController startController;

    public void initialize() {
        topButtonsWindowController.setMainWindowController(this);
    }

    public void setMainStageFXML(String fxmlpath){
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource(fxmlpath));
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.controls");
        fxmlLoader.setResources(bundle);
        Parent parent = null;
        try {
            parent = fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        borderPane.setCenter(parent);
    }
    //-------------------------------------------------------------------------------------------------------
    public void menubar_close(ActionEvent actionEvent) {
        Optional<ButtonType> res = DialogWindows.Close_app();
        if (res.get() == ButtonType.OK) {
            Platform.exit();
            System.exit(0);
        }
    }

    public void menubar_ontop(ActionEvent actionEvent) {
        Stage stage = (Stage) borderPane.getScene().getWindow();
        boolean value = ((CheckMenuItem) actionEvent.getSource()).selectedProperty().get();
        stage.setAlwaysOnTop(value);
    }

    public void menubar_setModena(ActionEvent actionEvent) {
        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
    }

    public void menubar_setCaspian(ActionEvent actionEvent) {
        Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
    }

    public void menubar_about(ActionEvent actionEvent) {
        DialogWindows.About_app();
    }

    public void menubar_contact(ActionEvent actionEvent) {
        DialogWindows.Contact_app();
    }

    public void changeLan_EN(ActionEvent actionEvent) {
        // Locale.setDefault(new Locale("en"));
    }

    public void changeLan_PL(ActionEvent actionEvent) {
        // Locale.setDefault(new Locale("pl"));
    }
}
