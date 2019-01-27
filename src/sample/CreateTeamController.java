package sample;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

public class CreateTeamController implements Initializable {

    //team name
    String teamName, fileName;

    //currently selected button
    Button curPlayer;

    //Scanner
    Scanner s;

    //list to hold players names
    ArrayList<Button> playerNames = new ArrayList();

    //label for title
    @FXML
    private Label teamTitle;

    //image viewer for soccer field image
    @FXML
    private ImageView imageViewer;

    //all buttons
    @FXML
    private Button st, lw, rw, cam, lm, rm, rbb, rcb, lcb, lb, gk, exit;

    // Receives: none
    // Task: removes currently selected player from the bench
    // Returns: nothing
    @FXML
    void exit() {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

    // Receives: none
    // Task: allows user to rename selected player
    // Returns: nothing
    @FXML
    void rename() {
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

    // Receives: none
    // Task: saves the team player names and postions
    // Returns: nothing
    @FXML
    void saveTeam() {

        if(!checkDuplicateName(teamName))
        {
            //add team name to list of team names
            addTeamToTeamNames();
        }


        // I use the three writers to be more efficient and i can use println function
        try (FileWriter fw = new FileWriter("Saved Teams/" + fileName, false);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            for(Button b : playerNames){
                out.println(b.getText());
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // Receives: none
    // Task: creates team names file
    // Returns: nothing
    public void createTeamNameFile() {
        File f = new File("TeamNames.txt");

        try {
            f.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //INPUT: none
    //TASK: adds the team name to the team names file. This allows program to check against duplicates
    //OUTPUT: none
    public void addTeamToTeamNames() {
        try (FileWriter fw = new FileWriter("TeamNames.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(teamName);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //INPUT: string holding the team name
    //TASK: adds the team name to the team names file. This allows program to check against duplicates
    //OUTPUT: none
    public Boolean checkDuplicateName(String str) {
        File f = new File("TeamNames.txt");
        s = getScanner(f);

        while (s.hasNextLine()) {
            String name = s.nextLine();
            if (name.equals(str)) {
                return true;
            }
        }
        return false;
    }

    // Receives: a file
    // Task: create a scanner to a file of users choice
    // Returns: Scanner
    public Scanner getScanner(File f) {
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return s;
    }

    //INPUT: Button of player
    //TASK: sets the drag drop, height, and width of button
    //OUTPUT: none
    private void populateTeam(Button player) {
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

    //INPUT: url and resource bundle
    //TASK: called to perform everything inside method on start up of form
    //OUTPUT: none
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File file = new File("src/soccerfield.png");
        Image image = new Image(file.toURI().toString());
        imageViewer.setImage(image);
        imageViewer.toBack();

        createTeamNameFile();

        //open dialog box to get player name and checks for duplication
        TextInputDialog dialog = new TextInputDialog("Team name");
        dialog.setTitle("Set Team name");
        dialog.setHeaderText("Enter Team name");
        dialog.setContentText("Enter Team name:");

        //creates a button and text field from dialog to validate input before granting access of creating team
        Button okButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        okButton.disableProperty();
        TextField inputField = dialog.getEditor();

        //create booleanbinding to validate the input
        BooleanBinding isInvalid = Bindings.createBooleanBinding(() -> checkDuplicateName(inputField.getText()), inputField.textProperty());
        okButton.disableProperty().bind(isInvalid);
        Optional<String> result = dialog.showAndWait();

        //if validated then set team name and label
        teamName = result.get();
        teamTitle.setText(teamName);
        fileName = teamName + ".txt";

        playerNames.add(st);
        playerNames.add(lw);
        playerNames.add(rw);
        playerNames.add(cam);
        playerNames.add(lm);
        playerNames.add(rm);
        playerNames.add(rcb);
        playerNames.add(lcb);
        playerNames.add(rbb);
        playerNames.add(lb);
        playerNames.add(gk);

        for(Button b : playerNames){
            populateTeam(b);
        }
    }
}

// records relative x and y co-ordinates.
class Delta {
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
}