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

public class volume implements Initializable {

    @FXML
    private Label lbl_t1;
    @FXML
    private Label lbl_t2;

    @FXML
    ImageView volumeBack;

    public void openMainView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("f1.fxml"));
        Stage Window = (Stage) volumeBack.getScene().getWindow();
        Window.setScene(new Scene(root, 335, 600));
    }

    @FXML
    ComboBox comboT1;

    @FXML
    ComboBox comboT2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboT1.getItems().clear();
        comboT1.getItems().addAll("Meter^3", "Centimeter^3");
        comboT1.getSelectionModel().select("Meter^3");

        comboT2.getItems().clear();
        comboT2.getItems().addAll("Meter^3", "Centimeter^3");
        comboT2.getSelectionModel().select("Centimeter^3");
    }

    boolean focus_t1;
    boolean focus_t2;

    public void setFocusWeight() {
        focus_t1 = true;
        focus_t2 = false;
        lbl_t1.setStyle("-fx-font-size: 20;-fx-text-fill: #FFA500");
        lbl_t2.setStyle("-fx-font-size: 20;-fx-text-fill: #000000");

    }

    public void setFocusHeight() {
        focus_t1 = false;
        focus_t2 = true;
        lbl_t2.setStyle("-fx-font-size: 20;-fx-text-fill: #FFA500");
        lbl_t1.setStyle("-fx-font-size: 20;-fx-text-fill: #000000");
    }

    StringBuilder digit_append_t1 = new StringBuilder("0");
    StringBuilder digit_append_t2 = new StringBuilder("0");

    public void onNumber(Event event) {
        Button button = (Button) event.getSource();
        String digit_string = button.getText();
        if (focus_t1) {
            digit_append_t1.append(digit_string);
            String singleString = digit_append_t1.toString();
            lbl_t1.setText(singleString);
            cal_convert();
        } else if (focus_t2) {
            digit_append_t2.append(digit_string);
            String singleString = digit_append_t2.toString();
            lbl_t2.setText(singleString);
            cal_convert();
        }
    }

    public void clean() {

        if (focus_t1) {
            digit_append_t1 = new StringBuilder("0");
            lbl_t1.setText("0");
            cal_convert();

        } else if (focus_t2) {
            digit_append_t2 = new StringBuilder("0");
            lbl_t2.setText("0");
            cal_convert();
        }
    }

    public void backspace() {
        if (focus_t1) {

            String temp = lbl_t1.getText();
            if (temp.length() != 0) {
                temp = temp.substring(0, temp.length() - 1);
                lbl_t1.setText(temp);
                String singleString_t1 = digit_append_t1.toString();
                singleString_t1 = singleString_t1.substring(0, singleString_t1.length() - 1);
                digit_append_t1 = new StringBuilder(singleString_t1);
                cal_convert();
            }
        } else if (focus_t2) {
            String temp = lbl_t2.getText();
            if (temp.length() != 0) {
                temp = temp.substring(0, temp.length() - 1);
                lbl_t2.setText(temp);
                String singleString_t2 = digit_append_t2.toString();
                singleString_t2 = singleString_t2.substring(0, singleString_t2.length() - 1);
                digit_append_t2 = new StringBuilder(singleString_t2);
                cal_convert();
            }
        }
    }

    public void cal_convert() {
        String singleString_t1 = digit_append_t1.toString();
        String singleString_t2 = digit_append_t2.toString();

        double digit_t1 = Double.parseDouble(singleString_t1);
        double digit_t2 = Double.parseDouble(singleString_t2);

        double f, c;
        if (focus_t1) {
            if (comboT1.getValue().equals("Meter^3")) {
                f = digit_t1;

                if (comboT2.getValue().equals("Centimeter^3")) {
                    c = f*1000000;
                    double roundOff = (double) Math.round(c * 100) / 100;
                    String temp = String.valueOf(roundOff);
                    lbl_t2.setText(temp);
                } else {
                    String temp = String.valueOf(f);
                    lbl_t2.setText(temp);
                }
            } else if (comboT1.getValue().equals("Centimeter^3")) {
                c = digit_t1;

                if (comboT2.getValue().equals("Meter^3")) {
                    f = c/1000000;
                    double roundOff = (double) Math.round(f * 100) / 100;
                    String temp = String.valueOf(roundOff);
                    lbl_t2.setText(temp);
                } else {
                    String temp = String.valueOf(c);
                    lbl_t2.setText(temp);
                }
            }
        }
        else if (focus_t2) {
            if (comboT2.getValue().equals("Meter^3")) {
                f = digit_t2;

                if (comboT1.getValue().equals("Centimeter^3")) {
                    c = f*1000000;
                    double roundOff = (double) Math.round(c * 100) / 100;
                    String temp = String.valueOf(roundOff);
                    lbl_t1.setText(temp);
                } else {
                    String temp = String.valueOf(f);
                    lbl_t1.setText(temp);
                }
            } else if (comboT2.getValue().equals("Centimeter^3")) {
                c = digit_t2;

                if (comboT1.getValue().equals("Meter^3")) {
                    f = c/1000000;
                    double roundOff = (double) Math.round(f * 100) / 100;
                    String temp = String.valueOf(roundOff);
                    lbl_t1.setText(temp);
                } else {
                    String temp = String.valueOf(c);
                    lbl_t1.setText(temp);
                }
            }
        }

    }
}
