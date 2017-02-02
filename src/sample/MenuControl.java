package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Created by Admin on 20.11.2016.
 */
public class MenuControl {


    public void exitClick() {
        System.exit(0);
    }

    public void getInf(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/info.fxml"));
        Scene sceneInfo = new Scene(root, 720, 480);
        //sceneInfo.getStylesheets().add(0,"../Styles/style.css");
        Stage goInfSt = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        goInfSt.setScene(sceneInfo);
        goInfSt.setTitle("Информация");
    }

    public void getTrain(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/train.fxml"));
        Scene sceneTrain = new Scene(root, 720, 480);

        Stage goSceneTrain = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        goSceneTrain.setScene(sceneTrain);
        goSceneTrain.setTitle("Тренировка");
    }

    public void getExam(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/exam.fxml"));
        Scene sceneTrain = new Scene(root, 720, 480);

        Stage goSceneTrain = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        goSceneTrain.setScene(sceneTrain);
        goSceneTrain.setTitle("Экзамен");
    }
}
