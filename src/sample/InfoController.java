package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;


public class InfoController {

    @FXML
    Label lbl_inf;

    @FXML
    ScrollPane scrl_inf;

    public void backToMenu(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/menu.fxml"));
        Scene sceneMenu = new Scene(root, 720, 480);
        Stage goSceneMenu = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        goSceneMenu.setScene(sceneMenu);
        goSceneMenu.setTitle("Секвенция");
    }


}

