package quanly_anything_you_want.manage.com.quanlyanything.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;

import quanly_anything_you_want.manage.com.quanlyanything.screen.login.LoginContact;

import static java.lang.Math.floor;
import static java.lang.Math.pow;

public class CommonUtil {
    public static final String formatVN = "#,###,###";

    public static String showName(String text) {
        if (TextUtils.isEmpty(text) || text.equalsIgnoreCase("null")) return "";
        return text;
    }

    public static float convertDpToPixel(Context context, float dp) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * (metrics.densityDpi / 160f);
    }


    public static String showPriceHasCurrency(double amount, String currency) {
        if (currency == null)
            currency = "";
        DecimalFormat formatter = (DecimalFormat) DecimalFormat.getInstance();
        formatter.applyPattern(formatVN);
        return formatter.format(amount).replace(",", ".") + " " + currency;
    }

    public static String showPriceNotCurrency(double amount) {

        DecimalFormat formatter = (DecimalFormat) DecimalFormat.getInstance();
        formatter.applyPattern(formatVN);

        return formatter.format(amount).replace(",", ".");
    }

    public static void delayButton(final View view) {
        view.setEnabled(false);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setEnabled(true);
            }
        }, 1500);
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

    public static void formatInputPrice(Editable text) {
        StringBuilder formattedString = new StringBuilder();
        // Remove everything except digits
        int p = 0;
        while (p < text.length()) {
            char ch = text.charAt(p);
            if (!Character.isDigit(ch)) {
                text.delete(p, p + 1);
            } else {
                p++;
            }
        }
        String allDigitString = text.toString();
        int totalDigitCount = allDigitString.length();
        int alreadyPlacedDigitCount = 0;

        for (int i = 0; i < 4; i++) {
            if (totalDigitCount - alreadyPlacedDigitCount > 3) {
                formattedString.append(allDigitString.substring(alreadyPlacedDigitCount, alreadyPlacedDigitCount + 3)).append(".");
                alreadyPlacedDigitCount += 3;
            } else break;
        }


        if (totalDigitCount > alreadyPlacedDigitCount) {
            formattedString.append(allDigitString.substring(alreadyPlacedDigitCount));
        }

        text.clear();
        text.append(formattedString.toString());
    }
}
