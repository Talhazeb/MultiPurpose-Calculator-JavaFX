package calculator;

import java.util.function.DoubleBinaryOperator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Arithmetic {
    public static enum Operation {
        ADD((a, b) -> a + b, '+'),
        SUB((a, b) -> a - b, '-'),
        MUL((a, b) -> a * b, '*'),
        DIV((a, b) -> a / b, '/'),
        NOP((a, b) -> b, ' ');

        public final DoubleBinaryOperator operator;
        public final char sign;

        private Operation(DoubleBinaryOperator operator, char sign) {
            this.operator = operator;
            this.sign = sign;
        }
    }

    private static final int MAX_LENGTH = 15;

    private NumberInput input = new NumberInput();
    private double memory;
    private Operation operation = Operation.NOP;
    private boolean equalsPressed;

    public final StringProperty mainText = new SimpleStringProperty();
    public final StringProperty helperText = new SimpleStringProperty();

    public Arithmetic() {
        mainText.set(input.textProperty.get());
        input.textProperty.addListener((observable, oldValue, newValue) -> {
            mainText.set(newValue);
        });
        clean();
    }

    public void clean() {
        input.clean();
        memory = 0;
        operation = Operation.NOP;
        equalsPressed = false;
        mainText.set("0");
        helperText.set("");
    }

    private void cleanPreviousResult() {
        if (!equalsPressed)
            return;
        equalsPressed = false;
        input.clean();
    }

    public void removeLast() {
        cleanPreviousResult();
        input.removeLast();
    }

    public void clickNumber(int d) {
        cleanPreviousResult();
        input.putDigit(d);
    }

    public void clickDot() {
        cleanPreviousResult();
        input.putDot();
    }

    public void clickOperation(Operation operation) {
        double inputValue = Double.parseDouble(mainText.get());
        memory = this.operation.operator.applyAsDouble(memory, inputValue);
        this.operation = operation;
        input.clean();
        if (Operation.NOP == operation) {
            helperText.set("");
        } else {
            helperText.set(Double.toString(memory) + " " + operation.sign);
        }
    }

    public void clickEquals() {
        equalsPressed = true;
        double inputValue = Double.parseDouble(input.textProperty.get());
        double result = this.operation.operator.applyAsDouble(memory, inputValue);
        operation = Operation.NOP;
        mainText.set(Double.toString(result));
        helperText.set("");
    }
}
