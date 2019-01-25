package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CreateTeamController implements Initializable {

    //team name
    String teamName;

    //currently selected button
    Button curPlayer;

    @FXML
    private Label teamTitle;

    @FXML
    private ImageView imageViewer;

    //all buttons
    @FXML
    private Button st, lw, rw, cam, lm, rm, rbb, rcb, lcb, lb, gk, exit;

/*    //add button on action method
    @FXML
    void add() {
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
        player.setPrefWidth(120);
        player.setPrefHeight(40);
        bench.getChildren().add(player);

        //create delta object to hold x and y cords
        final Delta dragDelta = new Delta();

        player.setOnMousePressed((t) -> {
            dragDelta.orgSceneX = t.getSceneX();
            dragDelta.orgSceneY = t.getSceneY();
            dragDelta.orgTranslateX = ((Button) (t.getSource())).getTranslateX();
            dragDelta.orgTranslateY = ((Button) (t.getSource())).getTranslateY();
        });

        //mouse event for mouse drag
        player.setOnMouseDragged((t) -> {
            double offsetX = t.getSceneX() - dragDelta.orgSceneX;
            double offsetY = t.getSceneY() - dragDelta.orgSceneY;
            double newTranslateX = dragDelta.orgTranslateX + offsetX;
            double newTranslateY = dragDelta.orgTranslateY + offsetY;

            ((Button) (t.getSource())).setTranslateX(newTranslateX);
            ((Button) (t.getSource())).setTranslateY(newTranslateY);
        });
    }*/

    //removes currently selected player from the bench
    @FXML
    void exit() {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File file = new File("src/soccerfield.png" );
        Image image = new Image(file.toURI().toString());
        imageViewer.setImage(image);
        imageViewer.toBack();

        //set teeam name
        //open dialog box to get player name
        TextInputDialog dialog = new TextInputDialog("Team name");
        dialog.setTitle("Set Team name");
        dialog.setHeaderText("Enter Team name");
        dialog.setContentText("Enter Team name:");
        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        teamName = result.get();
        teamTitle.setText(teamName);

        populateTeam(st);
        populateTeam(lw);
        populateTeam(rw);
        populateTeam(cam);
        populateTeam(lm);
        populateTeam(rm);
        populateTeam(lb);
        populateTeam(rbb);
        populateTeam(rcb);
        populateTeam(lcb);
        populateTeam(gk);
    }

    @FXML
    void rename()
    {
        //open dialog box to get player name
        TextInputDialog dialog = new TextInputDialog("player name");
        dialog.setTitle("Add a player");
        dialog.setHeaderText("Enter player name");
        dialog.setContentText("Enter player name:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        String r = result.get();

        curPlayer.setText(r);
    }

    private void populateTeam(Button player){
        //create player object
        player.setPrefWidth(120);
        player.setPrefHeight(40);
        //bench.getChildren().add(player);

        //create delta object to hold x and y cords
        final Delta dragDelta = new Delta();

        player.setOnMousePressed((t) -> {
            dragDelta.orgSceneX = t.getSceneX();
            dragDelta.orgSceneY = t.getSceneY();
            dragDelta.orgTranslateX = ((Button) (t.getSource())).getTranslateX();
            dragDelta.orgTranslateY = ((Button) (t.getSource())).getTranslateY();
            curPlayer = player;
        });

        //mouse event for mouse drag
        player.setOnMouseDragged((t) -> {
            double offsetX = t.getSceneX() - dragDelta.orgSceneX;
            double offsetY = t.getSceneY() - dragDelta.orgSceneY;
            double newTranslateX = dragDelta.orgTranslateX + offsetX;
            double newTranslateY = dragDelta.orgTranslateY + offsetY;

            ((Button) (t.getSource())).setTranslateX(newTranslateX);
            ((Button) (t.getSource())).setTranslateY(newTranslateY);
        });
    }
}

// records relative x and y co-ordinates.
class Delta { double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;}