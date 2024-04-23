package GameVisual;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Projector extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root,Color.RED);
        Image icon =new Image("GameVisual/PhotoResource/LOGO.jpg");
        stage.setWidth(420);
        stage.setHeight(420);
        stage.setFullScreen(true);
        stage.getIcons().add(icon);
        stage.setTitle("2048");
        stage.setScene(scene);
        stage.show();
    }
}