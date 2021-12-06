package calculator;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class NumberInput {
    private static final int MAX_LENGTH = 15;

    private boolean isNegative;
    private String whole;
    private String fractional;
    private boolean hasDot;

    public final StringProperty textProperty = new SimpleStringProperty();

    public NumberInput() {
        clean();
        updateText();
    }

    private void updateText() {
        StringBuilder builder = new StringBuilder();
        if (isNegative)
            builder.append('-');
        builder.append(whole);
        if (hasDot) {
            builder.append('.');
            builder.append(fractional);
        }
        textProperty.set(builder.toString());
    }

    private int getNumberOfDigits() {
        return whole.length() + fractional.length();
    }

    public void clean() {
        isNegative = false;
        whole = "0";
        fractional = "";
        hasDot = false;
        updateText();
    }

    public void flipSign() {
        isNegative = !isNegative;
        updateText();
    }

    public void removeLast() {
        if (hasDot) {
            if (0 != fractional.length()) {
                fractional = fractional.substring(0, fractional.length() - 1);
            } else {
                hasDot = false;
            }
        } else {
            if (1 != whole.length()) {
                whole = whole.substring(0, whole.length() - 1);
            } else {
                whole = "0";
                isNegative = false;
            }
        }
        updateText();
    }

    public void putDigit(int digit) {
        if (digit < 0 || 9 < digit) {
            throw new IllegalArgumentException("Argument is not single digit: " + digit);
        }
        if (MAX_LENGTH == getNumberOfDigits()) {
            throw new IllegalStateException("Too many digits");
        }
        if (!hasDot) {
            if ("0".equals(whole)) {
                whole = Integer.toString(digit);
            } else {
                whole += digit;
            }
        } else {
            fractional += digit;
        }
        updateText();
    }

    public void putDot() {
        if (hasDot)
            throw new IllegalStateException("Number can have only one dot.");
        hasDot = true;
        updateText();
    }
}
