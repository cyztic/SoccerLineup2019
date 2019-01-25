package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main extends Application {

    public Stage primaryStageClone = null;
    Scanner s;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStageClone = primaryStage;


        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Soccer Lineup Manager 2019");
        primaryStage.setScene(new Scene(root, 750, 450));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @FXML
    private Button useExistingTeam;

    @FXML
    private Button loadTeam;

    @FXML
    void createTeam()
    {
        FXMLLoader loader = null;
        Stage myStage = new Stage();
        Scene myScene;
        try
        {
            loader = new FXMLLoader(getClass().getResource("CreateTeamFXML.fxml"));
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

    @FXML
    void loadATeam(){
        ArrayList<String> teamNames = new ArrayList();
        File f  = new File("TeamNames.txt");

        s = getScanner(f);

        while (s.hasNextLine())
        {
            String str = s.nextLine();

            teamNames.add(str);
        }


        //start up new form to pick team
        FXMLLoader loader = null;
        Stage myStage = new Stage();
        Scene myScene;
        try
        {
            loader = new FXMLLoader(getClass().getResource("CustomTeamsFXML.fxml"));
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

    @FXML
    void quit() {
        Platform.exit();
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

    public static void main(String[] args) {
        launch(args);
    }
}
