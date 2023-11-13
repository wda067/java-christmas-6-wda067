package christmas.util;

import java.text.DecimalFormat;

public class NumberFormatter {
    public static String formatNumber(int number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(number);
    }
}
