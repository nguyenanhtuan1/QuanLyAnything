package quanly_anything_you_want.manage.com.quanlyanything.utils;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.DisplayMetrics;

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

}
