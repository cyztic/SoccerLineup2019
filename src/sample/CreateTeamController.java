package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.File;
import java.net.URL;
import java.util.Optional;
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

    //global variables
    private Button cur;
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    int i = 1;

    @FXML
    void add()
    {
        //open dialog box to get player name
        TextInputDialog dialog = new TextInputDialog("player name");
        dialog.setTitle("Add a player");
        dialog.setHeaderText("Enter player name");
        dialog.setContentText("Enter player name:");
        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        String r = result.get();

        //create player object
        Button player = new Button(r);
        player.setOnMousePressed(buttonMousePressedEventHandler);
        player.setOnMouseDragged(buttonOnMouseDraggedEventHandler);
        player.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {

                    }
                }
            }
        });

        bench.getChildren().add(player);
        player.setOnAction(p -> movePlayer(p, player));
    }

    //move player
    void movePlayer(ActionEvent e, Button b)
    {
        System.out.println("test");
        cur = b;
        b.toFront();
    }

    EventHandler<MouseEvent> buttonMousePressedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            orgTranslateX = ((Button) (t.getSource())).getTranslateX();
            orgTranslateY = ((Button) (t.getSource())).getTranslateY();

            ((Button) (t.getSource())).toFront();
        }
    };

    EventHandler<MouseEvent> buttonOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            ((Button) (t.getSource())).setTranslateX(newTranslateX);
            ((Button) (t.getSource())).setTranslateY(newTranslateY);
        }
    };

    //removes currently selected player from the bench
    @FXML
    void remove()
    {
        bench.getChildren().remove(cur);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        File file = new File("src/soccerfield.png");
        Image image = new Image(file.toURI().toString());
        imageViewer.setImage(image);
        imageViewer.toBack();
    }
}
