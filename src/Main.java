import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    Scene sceneMenu;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setResizable(false);
        window.setTitle("Секвенция");
        Image stageIcon = new Image("png.png");
        window.getIcons().add(stageIcon);
        Parent root = FXMLLoader.load(getClass().getResource("/sample/menu.fxml"));
        sceneMenu = new Scene(root, 720, 480);
        window.setScene(sceneMenu);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
