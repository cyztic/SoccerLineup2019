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


public class Main extends Application {

    public Stage primaryStageClone = null;

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
    private Button createTeam;

    @FXML
    private Button useExistingTeam;

    @FXML
    private Button loadTeam;

    @FXML
    private Button quit;

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
        myStage.initModality(Modality.APPLICATION_MODAL);
        myStage.showAndWait();
    }


//testing if this is works
    @FXML
    void quit() {
        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
