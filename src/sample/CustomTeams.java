package sample;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

public class CustomTeams implements Initializable {

    Scanner s;

    @FXML
    private Button cancel;

    @FXML
    private ListView names;
    @FXML
    void exit() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void load() {
        FXMLLoader loader = null;
        Stage myStage = new Stage();
        Scene myScene;
        try
        {
            loader = new FXMLLoader(getClass().getResource("LoadedTeamFXML.fxml"));
            myScene = new Scene(loader.load());
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong while building new fxml");
            System.out.println(e);
            return;
        }

        myStage.setScene(myScene);
        myStage.initStyle(StageStyle.UNDECORATED);
        myStage.initModality(Modality.APPLICATION_MODAL);
        myStage.setResizable(false);
        myStage.showAndWait();
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ArrayList<String> teamNames = new ArrayList();

        File f  = new File("TeamNames.txt");

        s = getScanner(f);

        while (s.hasNextLine())
        {
            String str = s.nextLine();

            teamNames.add(str);
            names.getItems().add(str);
        }


    }
}
