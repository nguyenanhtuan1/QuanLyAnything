package quanly_anything_you_want.manage.com.quanlyanything.utils;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import java.text.NumberFormat;
import java.util.Currency;

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

    public static String showPrice(String currency, double value) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setCurrency(Currency.getInstance("VN"));
        return format.format(value) + currency;
    }

}
