package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateTeamController implements Initializable {

    @FXML
    private Button addPlayer;

    @FXML
    private Button removePlayer;

    @FXML
    private VBox bench;

    @FXML
    private ImageView imageViewer;

    @FXML
    void add()
    {

    }

    @FXML
    void remove()
    {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        File file = new File("src/soccerfield.png");
        Image image = new Image(file.toURI().toString());
        imageViewer.setImage(image);
    }
}
