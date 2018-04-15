package quanly_anything_you_want.manage.com.quanlyanything.utils;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;

import static java.lang.Math.floor;
import static java.lang.Math.pow;

public class CommonUtil {

    public static String showName(String text) {
        if (TextUtils.isEmpty(text) || text.equalsIgnoreCase("null")) return "";
        return text;
    }

    public static float convertDpToPixel(Context context, float dp) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * (metrics.densityDpi / 160f);
    }

    public static String showPrice(String currency, double amount) {
        DecimalFormat formatter = (DecimalFormat) DecimalFormat.getInstance();
        formatter.applyPattern("#,###,###");
        return formatter.format(setRoundAmount(amount, 2)) + currency;
    }

    public static String showPriceNotCurrency(double amount) {
        DecimalFormat formatter = (DecimalFormat) DecimalFormat.getInstance();
        formatter.applyPattern("#,###,###");
        return formatter.format(setRoundAmount(amount, 2));
    }

    public static String showPriceNotCurrency(String amount) {
        double value = 0;
        if (!TextUtils.isEmpty(amount)) {
            value = Double.valueOf(amount);
        }

        DecimalFormat formatter = (DecimalFormat) DecimalFormat.getInstance();
        formatter.applyPattern("#,###,###");
        return formatter.format(setRoundAmount(value, 2));
    }

    private static double setRoundAmount(double amount, int precision) {
        double power = pow(10, precision);
        double poweredAmount = amount * power;
        double poweredPrimary = floor(poweredAmount);
        double poweredRemain = poweredAmount - poweredPrimary;
        if (poweredRemain > 0.5) {
            poweredPrimary += 1;
        }
        return poweredPrimary / power;
    }

}
