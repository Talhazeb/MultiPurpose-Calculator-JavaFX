package calculator;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class age implements Initializable {

    @FXML
    private Label lbl_t1;
    @FXML
    private Label lbl_t2;

    @FXML
    ImageView ageBack;

    public void openMainView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("f1.fxml"));
        Stage Window = (Stage) ageBack.getScene().getWindow();
        Window.setScene(new Scene(root, 335, 600));
    }

    @FXML
    ComboBox ca1;

    @FXML
    ComboBox ca2;

    @FXML
    ComboBox ca3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ca1.getItems().clear();
        for (int i = 1; i <= 31; i++) {
            ca1.getItems().addAll(String.valueOf(i));
        }

        ca2.getItems().clear();
        for (int i = 1; i <= 12; i++) {
            ca2.getItems().addAll(String.valueOf(i));
        }

        ca3.getItems().clear();
        for (int i = 1950; i <= 2021; i++) {
            ca3.getItems().addAll(String.valueOf(i));
        }

    }

    public void cal_convert() {
        
    }
}
