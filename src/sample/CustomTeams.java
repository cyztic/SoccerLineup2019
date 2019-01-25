package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CustomTeams {

    @FXML
    private Button cancel;
    @FXML
    void exit() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void load() {
        Platform.exit();
    }
}
