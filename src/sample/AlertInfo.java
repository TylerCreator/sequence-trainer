package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Admin on 29.11.2016.
 */
public class AlertInfo {

    public static void display(String title, String messege) {



        Stage window = new Stage();

        //Block events to other windows
        //window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        window.setResizable(false);
        Image infoIM = new Image("info.png");
        ImageView imageView = new ImageView();
        imageView.setImage(infoIM);
        imageView.setFitHeight(480);
        imageView.setFitWidth(480);

        AnchorPane layout = new AnchorPane();

        layout.getChildren().addAll(imageView);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.sizeToScene();
        window.showAndWait();
    }

}
