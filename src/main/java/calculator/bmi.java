package calculator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class bmi implements Initializable {

    @FXML
    private Label lbl_Weight;
    @FXML
    private Label lbl_Height;
    @FXML
    private Label lbl_BMI;

    @FXML
    ImageView bmiBack;

    public void openMainView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("f1.fxml"));
        Stage Window = (Stage) bmiBack.getScene().getWindow();
        Window.setScene(new Scene(root, 335, 600));
    }

    @FXML
    ComboBox comboWeight;

    @FXML
    ComboBox comboHeight;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboWeight.getItems().clear();
        comboWeight.getItems().addAll("Kilograms", "Pounds");
        comboWeight.getSelectionModel().select("Kilograms");

        comboHeight.getItems().clear();
        comboHeight.getItems().addAll("Centimeters", "Inch");
        comboHeight.getSelectionModel().select("Centimeters");
    }

    boolean focus_W;
    boolean focus_H;

    public void setFocusWeight() {
        focus_W = true;
        focus_H = false;
        lbl_Weight.setStyle("-fx-font-size: 20;-fx-text-fill: #FFA500");
        lbl_Height.setStyle("-fx-font-size: 20;-fx-text-fill: #000000");
        
    }

    public void setFocusHeight() {
        focus_W = false;
        focus_H = true;
        lbl_Height.setStyle("-fx-font-size: 20;-fx-text-fill: #FFA500");
        lbl_Weight.setStyle("-fx-font-size: 20;-fx-text-fill: #000000");
    }

    StringBuilder digit_append_w = new StringBuilder("");
    StringBuilder digit_append_h = new StringBuilder("");

    public void onNumber(Event event) {
        Button button = (Button) event.getSource();
        String digit_string = button.getText();
        if (focus_W) {
            digit_append_w.append(digit_string);
            String singleString = digit_append_w.toString();
            lbl_Weight.setText(singleString);
        } else if (focus_H) {
            digit_append_h.append(digit_string);
            String singleString = digit_append_h.toString();
            lbl_Height.setText(singleString);
        }
    }

    public void clean() {

        if (focus_W) {
            digit_append_w = new StringBuilder("");
            lbl_Weight.setText("0");
            lbl_BMI.setText("0");
        } else if (focus_H) {
            digit_append_h = new StringBuilder("");
            lbl_Height.setText("0");
            lbl_BMI.setText("0");
        }
    }

    public void backspace() {
        if (focus_W) {

            String temp = lbl_Weight.getText();
            if (temp.length() != 0) {
                temp = temp.substring(0, temp.length() - 1);
                lbl_Weight.setText(temp);
                String singleString_w = digit_append_w.toString();
                singleString_w = singleString_w.substring(0, singleString_w.length() - 1);
                digit_append_w = new StringBuilder(singleString_w);
            }
        } else if (focus_H) {
            String temp = lbl_Height.getText();
            if (temp.length() != 0) {
                temp = temp.substring(0, temp.length() - 1);
                lbl_Height.setText(temp);
                String singleString_h = digit_append_h.toString();
                singleString_h = singleString_h.substring(0, singleString_h.length() - 1);
                digit_append_h = new StringBuilder(singleString_h);
            }
        }
    }

    public void cal_BMI() {
        String singleString_W = digit_append_w.toString();
        String singleString_H = digit_append_h.toString();

        double digit_W = Double.parseDouble(singleString_W);
        double digit_H = Double.parseDouble(singleString_H);

        if(comboWeight.getValue().equals("Pounds")){  
            digit_W /= 2.2;
        }
        if(comboHeight.getValue().equals("Inch")){
            digit_H /= 0.393701;
        }

        double bmi = (digit_W / digit_H / digit_H) * 10000;
        double roundOff = (double) Math.round(bmi * 100) / 100;
        String temp_bmi = String.valueOf(roundOff);
        lbl_BMI.setText(temp_bmi);
    }
}
