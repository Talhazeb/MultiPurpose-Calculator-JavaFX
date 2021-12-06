package calculator;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class discount {

    @FXML
    private Label lbl_OriginalPrice;
    @FXML
    private Label lbl_Discount;
    @FXML
    private Label lbl_FinalPrice;
    @FXML
    private Label lbl_Save;

    @FXML
    ImageView disBack;

    public void openMainView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("f1.fxml"));
        Stage Window = (Stage) disBack.getScene().getWindow();
        Window.setScene(new Scene(root, 335, 600));
    }

    boolean focus_OP;
    boolean focus_D;

    public void setFocusOriginalPrice() {
        focus_OP = true;
        focus_D = false;
        lbl_OriginalPrice.setStyle("-fx-font-size: 20;-fx-text-fill: #FFA500");
        lbl_Discount.setStyle("-fx-font-size: 20;-fx-text-fill: #000000");
        // lbl_OriginalPrice.setText("HEHE");
    }

    public void setFocusDiscountPrice() {
        focus_OP = false;
        focus_D = true;
        lbl_Discount.setStyle("-fx-font-size: 18;-fx-text-fill: #FFA500");
        lbl_OriginalPrice.setStyle("-fx-font-size: 20;-fx-text-fill: #000000");
    }

    StringBuilder digit_append_op = new StringBuilder("");
    StringBuilder digit_append_dis = new StringBuilder("");

    public void onNumber(Event event) {
        Button button = (Button) event.getSource();
        String digit_string = button.getText();
        if (focus_OP) {
            digit_append_op.append(digit_string);
            String singleString = digit_append_op.toString();
            lbl_OriginalPrice.setText(singleString);
        } else if (focus_D) {
            digit_append_dis.append(digit_string);
            String singleString = digit_append_dis.toString();
            lbl_Discount.setText(singleString);

            String singleString_OP = digit_append_op.toString();
    
            double digit_OP = Double.parseDouble(singleString_OP);
            double digit_D = Double.parseDouble(singleString);

            double total_discount = (digit_D / 100) * digit_OP;
            double roundOff_td = (double) Math.round(total_discount * 100) / 100;
            double new_price = digit_OP - total_discount;
            double roundOff_np = (double) Math.round(new_price * 100) / 100;

            String temp_fp = String.valueOf(roundOff_np);
            lbl_FinalPrice.setText(temp_fp);

            String temp_dp = String.valueOf(roundOff_td);
            lbl_Save.setText(temp_dp);

        }
    }

    public void clean() {

        if (focus_OP) {
            digit_append_op = new StringBuilder("");
            lbl_OriginalPrice.setText("0");
            lbl_FinalPrice.setText("0");
            lbl_Save.setText("0");
        } else if (focus_D) {
            digit_append_dis = new StringBuilder("");
            lbl_Discount.setText("0");
            lbl_FinalPrice.setText("0");
            lbl_Save.setText("0");
        }
    }

    public void backspace() {
        if (focus_OP) {

            String temp = lbl_OriginalPrice.getText();
            if (temp.length() != 0) {
                temp = temp.substring(0, temp.length() - 1);
                lbl_OriginalPrice.setText(temp);
                String singleString_OP = digit_append_op.toString();
                singleString_OP = singleString_OP.substring(0, singleString_OP.length() - 1);
                digit_append_op = new StringBuilder(singleString_OP);
            }
        } else if (focus_D) {
            String temp = lbl_Discount.getText();
            if (temp.length() != 0) {
                temp = temp.substring(0, temp.length() - 1);
                lbl_Discount.setText(temp);
                String singleString_dis = digit_append_dis.toString();
                singleString_dis = singleString_dis.substring(0, singleString_dis.length() - 1);
                digit_append_dis = new StringBuilder(singleString_dis);
            }
        }
    }

}
