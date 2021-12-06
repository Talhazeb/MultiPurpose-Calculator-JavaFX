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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class cal extends GridPane implements Initializable {

    @FXML
    private Button btnClean;
    @FXML
    private Button btnDivide;
    @FXML
    private Button btnMultiply;
    @FXML
    private Button btnSubtract;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnEquals;
    @FXML
    private Label lblMain;
    @FXML
    private Label lblHelper;

    private Arithmetic arithmetic = new Arithmetic();

    public cal() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblMain.textProperty().bind(arithmetic.mainText);
        lblHelper.textProperty().bind(arithmetic.helperText);
    }

    @FXML
    private void onKeyPressed(KeyEvent event) {
        KeyCode keyCode = event.getCode();
        if (keyCode.isDigitKey()) {
            int digit = Integer.parseInt(event.getText());
            arithmetic.clickNumber(digit);
        } else {
            String pressedKey = event.getText();
            if ("+".equals(pressedKey)) {
                arithmetic.clickOperation(Arithmetic.Operation.ADD);
            } else if ("-".equals(pressedKey)) {
                arithmetic.clickOperation(Arithmetic.Operation.SUB);
            } else if ("*".equals(pressedKey)) {
                arithmetic.clickOperation(Arithmetic.Operation.MUL);
            } else if ("/".equals(pressedKey)) {
                arithmetic.clickOperation(Arithmetic.Operation.DIV);
            } else if ("=".equals(pressedKey) || KeyCode.ENTER == keyCode) {
                arithmetic.clickEquals();
            } else if (KeyCode.BACK_SPACE == keyCode) {
                arithmetic.removeLast();
            }
        }
    }

    @FXML
    private void onClean() {
        arithmetic.clean();
    }

    @FXML
    private void onNumber(Event event) {
        Button button = (Button) event.getSource();
        int digit = Integer.parseInt(button.getText());
        arithmetic.clickNumber(digit);
    }

    @FXML
    private void onDot() {
        arithmetic.clickDot();
    }

    @FXML
    private void onOperation(Event event) {
        Object source = event.getSource();
        Arithmetic.Operation operation;
        if (btnAdd == source) {
            operation = Arithmetic.Operation.ADD;
        } else if (btnSubtract == source) {
            operation = Arithmetic.Operation.SUB;
        } else if (btnMultiply == source) {
            operation = Arithmetic.Operation.MUL;
        } else if (btnDivide == source) {
            operation = Arithmetic.Operation.DIV;
        } else {
            throw new RuntimeException("Unknown operation");
        }
        arithmetic.clickOperation(operation);
    }

    @FXML
    private void onEquals() {
        arithmetic.clickEquals();
    }

    @FXML
    ImageView b1;

    public void openDiscountView() throws IOException {
        Parent discountView = FXMLLoader.load(getClass().getResource("f_discount.fxml"));
        Stage Window = (Stage) b1.getScene().getWindow();
        Window.setScene(new Scene(discountView, 335, 600));
    }

    @FXML
    ImageView img_bmi;

    public void openBMIView() throws IOException {
        Parent bmiView = FXMLLoader.load(getClass().getResource("bmi.fxml"));
        Stage Window = (Stage) img_bmi.getScene().getWindow();
        Window.setScene(new Scene(bmiView, 335, 600));
    }

    @FXML
    ImageView img_currency;

    public void openCurrencyView() throws IOException {
        Parent currencyView = FXMLLoader.load(getClass().getResource("currency_convertor.fxml"));
        Stage Window = (Stage) img_currency.getScene().getWindow();
        Window.setScene(new Scene(currencyView, 335, 600));
    }

    @FXML
    ImageView img_length;

    public void openLengthView() throws IOException {
        Parent LengthView = FXMLLoader.load(getClass().getResource("length.fxml"));
        Stage Window = (Stage) img_length.getScene().getWindow();
        Window.setScene(new Scene(LengthView, 335, 600));
    }

    @FXML
    ImageView img_temp;

    public void openTemperatureView() throws IOException {
        Parent TemperatureView = FXMLLoader.load(getClass().getResource("temperature.fxml"));
        Stage Window = (Stage) img_temp.getScene().getWindow();
        Window.setScene(new Scene(TemperatureView, 335, 600));
    }

    @FXML
    ImageView img_area;

    public void openAreaView() throws IOException {
        Parent AreaView = FXMLLoader.load(getClass().getResource("area.fxml"));
        Stage Window = (Stage) img_area.getScene().getWindow();
        Window.setScene(new Scene(AreaView, 335, 600));
        
    }

    @FXML
    ImageView img_data;

    public void openDataView() throws IOException {
        Parent DataView = FXMLLoader.load(getClass().getResource("data.fxml"));
        Stage Window = (Stage) img_data.getScene().getWindow();
        Window.setScene(new Scene(DataView, 335, 600));
    }

    @FXML
    ImageView img_time;

    public void openTimeView() throws IOException {
        Parent TimeView = FXMLLoader.load(getClass().getResource("time.fxml"));
        Stage Window = (Stage) img_time.getScene().getWindow();
        Window.setScene(new Scene(TimeView, 335, 600));
    }

    @FXML
    ImageView img_volume;

    public void openVolumeView() throws IOException {
        Parent VolumeView = FXMLLoader.load(getClass().getResource("volume.fxml"));
        Stage Window = (Stage) img_volume.getScene().getWindow();
        Window.setScene(new Scene(VolumeView, 335, 600));
    }

    @FXML
    ImageView img_speed;

    public void openSpeedView() throws IOException {
        Parent SpeedView = FXMLLoader.load(getClass().getResource("speed.fxml"));
        Stage Window = (Stage) img_speed.getScene().getWindow();
        Window.setScene(new Scene(SpeedView, 335, 600));
    }

    @FXML
    ImageView img_mass;

    public void openMassView() throws IOException {
        Parent MassView = FXMLLoader.load(getClass().getResource("mass.fxml"));
        Stage Window = (Stage) img_mass.getScene().getWindow();
        Window.setScene(new Scene(MassView, 335, 600));
    }

    @FXML
    ImageView img_numeral;

    public void openNumeralView() throws IOException {
        Parent NumeralView = FXMLLoader.load(getClass().getResource("numeral.fxml"));
        Stage Window = (Stage) img_numeral.getScene().getWindow();
        Window.setScene(new Scene(NumeralView, 335, 600));
    }

    @FXML
    ImageView img_age;

    public void openAgeView() throws IOException {
        Parent AgeView = FXMLLoader.load(getClass().getResource("AGE.fxml"));
        Stage Window = (Stage) img_age.getScene().getWindow();
        Window.setScene(new Scene(AgeView, 335, 600));
    }
}
