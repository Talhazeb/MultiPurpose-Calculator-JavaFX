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

public class length implements Initializable {

    @FXML
    private Label lbl_l1;
    @FXML
    private Label lbl_l2;

    @FXML
    ImageView lengthBack;

    public void openMainView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("f1.fxml"));
        Stage Window = (Stage) lengthBack.getScene().getWindow();
        Window.setScene(new Scene(root, 335, 600));
    }

    @FXML
    ComboBox comboL1;

    @FXML
    ComboBox comboL2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboL1.getItems().clear();
        comboL1.getItems().addAll("Kilometer km", "Meter m");
        comboL1.getSelectionModel().select("Kilometer km");

        comboL2.getItems().clear();
        comboL2.getItems().addAll("Kilometer km", "Meter m");
        comboL2.getSelectionModel().select("Meter m");
    }

    boolean focus_l1;
    boolean focus_l2;

    public void setFocusWeight() {
        focus_l1 = true;
        focus_l2 = false;
        lbl_l1.setStyle("-fx-font-size: 20;-fx-text-fill: #FFA500");
        lbl_l2.setStyle("-fx-font-size: 20;-fx-text-fill: #000000");

    }

    public void setFocusHeight() {
        focus_l1 = false;
        focus_l2 = true;
        lbl_l2.setStyle("-fx-font-size: 20;-fx-text-fill: #FFA500");
        lbl_l1.setStyle("-fx-font-size: 20;-fx-text-fill: #000000");
    }

    StringBuilder digit_append_l1 = new StringBuilder("0");
    StringBuilder digit_append_l2 = new StringBuilder("0");

    public void onNumber(Event event) {
        Button button = (Button) event.getSource();
        String digit_string = button.getText();
        if (focus_l1) {
            digit_append_l1.append(digit_string);
            String singleString = digit_append_l1.toString();
            lbl_l1.setText(singleString);
            cal_convert();
        } else if (focus_l2) {
            digit_append_l2.append(digit_string);
            String singleString = digit_append_l2.toString();
            lbl_l2.setText(singleString);
            cal_convert();
        }
    }

    public void clean() {

        if (focus_l1) {
            digit_append_l1 = new StringBuilder("0");
            lbl_l1.setText("0");
            cal_convert();

        } else if (focus_l2) {
            digit_append_l2 = new StringBuilder("0");
            lbl_l2.setText("0");
            cal_convert();
        }
    }

    public void backspace() {
        if (focus_l1) {

            String temp = lbl_l1.getText();
            if (temp.length() != 0) {
                temp = temp.substring(0, temp.length() - 1);
                lbl_l1.setText(temp);
                String singleString_l1 = digit_append_l1.toString();
                singleString_l1 = singleString_l1.substring(0, singleString_l1.length() - 1);
                digit_append_l1 = new StringBuilder(singleString_l1);
                cal_convert();
            }
        } else if (focus_l2) {
            String temp = lbl_l2.getText();
            if (temp.length() != 0) {
                temp = temp.substring(0, temp.length() - 1);
                lbl_l2.setText(temp);
                String singleString_l2 = digit_append_l2.toString();
                singleString_l2 = singleString_l2.substring(0, singleString_l2.length() - 1);
                digit_append_l2 = new StringBuilder(singleString_l2);
                cal_convert();
            }
        }
    }

    public void cal_convert() {
        String singleString_l1 = digit_append_l1.toString();
        String singleString_l2 = digit_append_l2.toString();

        double digit_l1 = Double.parseDouble(singleString_l1);
        double digit_l2 = Double.parseDouble(singleString_l2);

        double km, m;
        if (focus_l1) {
            if (comboL1.getValue().equals("Kilometer km")) {
                km = digit_l1;

                if (comboL2.getValue().equals("Meter m")) {
                    m = km * 1000;
                    double roundOff = (double) Math.round(m * 100) / 100;
                    String temp = String.valueOf(roundOff);
                    lbl_l2.setText(temp);
                } else {
                    String temp = String.valueOf(km);
                    lbl_l2.setText(temp);
                }
            } else if (comboL1.getValue().equals("Meter m")) {
                m = digit_l1;

                if (comboL2.getValue().equals("Kilometer km")) {
                    km = m / 1000;
                    double roundOff = (double) Math.round(km * 100) / 100;
                    String temp = String.valueOf(roundOff);
                    lbl_l2.setText(temp);
                } else {
                    String temp = String.valueOf(m);
                    lbl_l2.setText(temp);
                }
            }
        }
        else if (focus_l2) {
            if (comboL2.getValue().equals("Kilometer km")) {
                km = digit_l2;

                if (comboL1.getValue().equals("Meter m")) {
                    m = km * 1000;
                    double roundOff = (double) Math.round(m * 100) / 100;
                    String temp = String.valueOf(roundOff);
                    lbl_l1.setText(temp);
                } else {
                    String temp = String.valueOf(km);
                    lbl_l1.setText(temp);
                }
            } else if (comboL2.getValue().equals("Meter m")) {
                m = digit_l2;

                if (comboL1.getValue().equals("Kilometer km")) {
                    km = m / 1000;
                    double roundOff = (double) Math.round(km * 100) / 100;
                    String temp = String.valueOf(roundOff);
                    lbl_l1.setText(temp);
                } else {
                    String temp = String.valueOf(m);
                    lbl_l1.setText(temp);
                }
            }
        }

    }
}
