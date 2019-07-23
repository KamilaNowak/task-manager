package Dialogs;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.ResourceBundle;

public class DialogWindows {

    public static final String ICONS_BAD_PNG = "icons/bad.png";
    public static final String ICONS_QUESTION_PNG = "icons/question.png";
    public static final String ICONS_MAIL_PNG = "icons/mail.png";

    static ResourceBundle bundle = ResourceBundle.getBundle("bundles.controls");

    public static Optional<ButtonType> Close_app(){
        Alert closeAlert = new Alert(Alert.AlertType.CONFIRMATION);
        closeAlert.setTitle(bundle.getString("application.close.title"));
        closeAlert.setHeaderText(bundle.getString("application.close.header"));
        closeAlert.setGraphic(new ImageView(ICONS_BAD_PNG));
        Optional<ButtonType> result =  closeAlert.showAndWait();

        return result;
    }

    public static void About_app(){
        Alert aboutAlert = new Alert(Alert.AlertType.INFORMATION);
        aboutAlert.setTitle(bundle.getString("application.about.title"));
        aboutAlert.setHeaderText(bundle.getString("application.about.header"));
        aboutAlert.setContentText(bundle.getString("application.about.content"));
        aboutAlert.setGraphic(new ImageView(ICONS_QUESTION_PNG));
        aboutAlert.showAndWait();
    }

    public static void Contact_app(){
        Alert contactAlert = new Alert(Alert.AlertType.INFORMATION);
        contactAlert.setTitle(bundle.getString("application.contact.title"));
        contactAlert.setHeaderText(bundle.getString("application.contact.header"));
        contactAlert.setContentText(bundle.getString("application.contact.content"));
        contactAlert.setGraphic(new ImageView(ICONS_MAIL_PNG));
        contactAlert.showAndWait();
    }
    public static String editCategoryWindow(String comboboxvalue){
        TextInputDialog textInputDialog = new TextInputDialog(comboboxvalue);
        textInputDialog.setTitle(bundle.getString("editwindow.title"));
        textInputDialog.setHeaderText(bundle.getString("editwindow.header"));

        Optional<String> resullt = textInputDialog.showAndWait();
        if(resullt.isPresent()){
            return resullt.get();}
        else
            return  null;
    }
}
