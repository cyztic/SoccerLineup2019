package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ProClubsController implements Initializable {
    ProClubsController(String str){teamName = str;}
    //team name
    String teamName, fileName;

    //currently selected button
    Button curPlayer;

    //Scanner
    Scanner s;

    @FXML
    private Label teamTitle;

    @FXML
    private ImageView imageViewer;

    //all buttons
    @FXML
    private Button st, lw, rw, cam, lm, rm, rbb, rcb, lcb, lb, gk, exit;

    //list to hold players names
    ArrayList<Button> playerNames = new ArrayList();

    //removes currently selected player from the bench
    @FXML
    void exit() {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

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

    @FXML
    void saveTeam() {
        //add team name to list of team names

        // I use the three writers to be more efficient and i can use println function
        try (FileWriter fw = new FileWriter("Pro Teams/" + fileName, false);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            for(Button b : playerNames){
                out.println(b.getText());
            }
           /* out.println(st.getText());
            out.println(lw.getText());
            out.println(rw.getText());
            out.println(cam.getText());
            out.println(lm.getText());
            out.println(rm.getText());
            out.println(rcb.getText());
            out.println(lcb.getText());
            out.println(rbb.getText());
            out.println(lb.getText());
            out.println(gk.getText());*/

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // Receives: a string holding the file name
    // Task: checks to make sure all the database files exist, if they don't it
    // creates them
    // Returns: nothing
    public void createTeamNameFile() {
        File f = new File("ProClubs.txt");

        try {
            f.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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

    private void populateTeam(Button player) {
        //create player object
        player.setPrefWidth(120);
        player.setPrefHeight(40);
        //bench.getChildren().add(player);

        //create delta object to hold x and y cords
        final sample.Delta dragDelta = new sample.Delta();

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int counter = 0;

        File file = new File("src/soccerfield.png");
        Image image = new Image(file.toURI().toString());
        imageViewer.setImage(image);
        imageViewer.toBack();

        createTeamNameFile();

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


        File f  = new File("Pro Teams/"+ teamName + ".txt");

        s = getScanner(f);

        while (s.hasNextLine())
        {
            String str = s.nextLine();
            playerNames.get(counter).setText(str);
            counter++;
        }


    }
}
