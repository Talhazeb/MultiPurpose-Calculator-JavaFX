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

public class CurrencyConvertor implements Initializable {

    @FXML
    private Label lbl_c1;
    @FXML
    private Label lbl_c2;
    @FXML
    private Label lbl_c3;

    @FXML
    ImageView ccBack;

    public void openMainView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("f1.fxml"));
        Stage Window = (Stage) ccBack.getScene().getWindow();
        Window.setScene(new Scene(root, 335, 600));
    }

    @FXML
    ComboBox c1;
    @FXML
    ComboBox c2;
    @FXML
    ComboBox c3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        c1.getItems().clear();
        c1.getItems().addAll("PKR", "USD", "EUR");
        c1.getSelectionModel().select("PKR");

        c2.getItems().clear();
        c2.getItems().addAll("PKR", "USD", "EUR");
        c2.getSelectionModel().select("USD");

        c3.getItems().clear();
        c3.getItems().addAll("PKR", "USD", "EUR");
        c3.getSelectionModel().select("EUR");
    }

    StringBuilder digit_append_c = new StringBuilder("");

    public void onNumber(Event event) {
        Button button = (Button) event.getSource();
        String digit_string = button.getText();

        digit_append_c.append(digit_string);
        String singleString = digit_append_c.toString();
        lbl_c1.setText(singleString);

        cal_CC();

    }

    public void clean() {
        digit_append_c = new StringBuilder("");
        lbl_c1.setText("0");
        lbl_c2.setText("0");
        lbl_c3.setText("0");
    }

    public void backspace() {
        String temp = lbl_c1.getText();
        if (temp.length() != 0) {
            temp = temp.substring(0, temp.length() - 1);
            lbl_c1.setText(temp);
            String singleString_c = digit_append_c.toString();
            singleString_c = singleString_c.substring(0, singleString_c.length() - 1);
            digit_append_c = new StringBuilder(singleString_c);
            cal_CC();
        }
    }

    public void cal_CC() {
        String singleString_C = digit_append_c.toString();

        double digit_C = Double.parseDouble(singleString_C);

        double pkr, usd, eur;

        if (c1.getValue().equals("PKR")) {
            pkr = digit_C;
            usd = pkr/176;
            double roundOff_usd = (double) Math.round(usd * 100) / 100;
            eur = pkr/199;
            double roundOff_eur = (double) Math.round(eur * 100) / 100;

            if (c2.getValue().equals("USD")) {
                String temp = String.valueOf(roundOff_usd);
                lbl_c2.setText(temp);
            }
            else if (c2.getValue().equals("EUR")) {
                String temp = String.valueOf(roundOff_eur);
                lbl_c2.setText(temp);
            }
            else{
                String temp = String.valueOf(pkr);
                lbl_c2.setText(temp);
            }
            
            if (c3.getValue().equals("USD")) {
                String temp = String.valueOf(roundOff_usd);
                lbl_c3.setText(temp);
            }
            else if (c3.getValue().equals("EUR")) {
                String temp = String.valueOf(roundOff_eur);
                lbl_c3.setText(temp);
            }
            else{
                String temp = String.valueOf(pkr);
                lbl_c3.setText(temp);
            }
            
        }
        else if (c1.getValue().equals("USD")) {
            usd = digit_C;
            pkr = usd*176;
            double roundOff_pkr = (double) Math.round(pkr * 100) / 100;
            eur = pkr/199;
            double roundOff_eur = (double) Math.round(eur * 100) / 100;

            if (c2.getValue().equals("PKR")) {
                String temp = String.valueOf(roundOff_pkr);
                lbl_c2.setText(temp);
            }
            else if (c2.getValue().equals("EUR")) {
                String temp = String.valueOf(roundOff_eur);
                lbl_c2.setText(temp);
            }
            else{
                String temp = String.valueOf(usd);
                lbl_c2.setText(temp);
            }
            
            if (c3.getValue().equals("PKR")) {
                String temp = String.valueOf(roundOff_pkr);
                lbl_c3.setText(temp);
            }
            else if (c3.getValue().equals("EUR")) {
                String temp = String.valueOf(roundOff_eur);
                lbl_c3.setText(temp);
            }
            else{
                String temp = String.valueOf(usd);
                lbl_c3.setText(temp);
            }
            
        }
        else if (c1.getValue().equals("EUR")) {
            eur = digit_C;
            pkr = eur*199;
            double roundOff_pkr = (double) Math.round(pkr * 100) / 100;
            usd = pkr/176;
            double roundOff_usd = (double) Math.round(usd * 100) / 100;

            if (c2.getValue().equals("USD")) {
                String temp = String.valueOf(roundOff_usd);
                lbl_c2.setText(temp);
            }
            else if (c2.getValue().equals("PKR")) {
                String temp = String.valueOf(roundOff_pkr);
                lbl_c2.setText(temp);
            }
            else{
                String temp = String.valueOf(eur);
                lbl_c2.setText(temp);
            }
            
            if (c3.getValue().equals("USD")) {
                String temp = String.valueOf(roundOff_usd);
                lbl_c3.setText(temp);
            }
            else if (c3.getValue().equals("PKR")) {
                String temp = String.valueOf(roundOff_pkr);
                lbl_c3.setText(temp);
            }
            else{
                String temp = String.valueOf(eur);
                lbl_c3.setText(temp);
            }
            
        }
    }
}
