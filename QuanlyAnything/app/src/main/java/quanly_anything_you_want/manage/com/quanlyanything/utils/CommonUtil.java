package quanly_anything_you_want.manage.com.quanlyanything.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.Editable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

import quanly_anything_you_want.manage.com.quanlyanything.BuildConfig;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.screen.login.LoginContact;

import static java.lang.Math.floor;
import static java.lang.Math.pow;

public class CommonUtil {
    public static final String JPEG_FILE_PREFIX = "IMG_";
    public static final String JPEG_FILE_SUFFIX = ".jpg";

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


    public static String showPriceHasCurrency(double amount) {
        DecimalFormat formatter = (DecimalFormat) DecimalFormat.getInstance();
        formatter.applyPattern(formatVN);
        return formatter.format(amount).replace(",", ".") + " " + "VND";
    }

    public static String showPriceHasCurrencyAndUnit(double amount, String unit) {
        if (unit == null)
            unit = "";
        DecimalFormat formatter = (DecimalFormat) DecimalFormat.getInstance();
        formatter.applyPattern(formatVN);
        return formatter.format(amount).replace(",", ".") + " " + "VND" + " / " + unit;
    }

    public static String showPriceNotCurrency(double amount) {

        DecimalFormat formatter = (DecimalFormat) DecimalFormat.getInstance();
        formatter.applyPattern(formatVN);

        return formatter.format(amount).replace(",", ".");
    }


    public static String showQuantityHasUnit(int quantity, String unit) {
        if (unit == null)
            unit = "";
        return String.valueOf(quantity) + " " + unit;
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

    public static String numberToString(String sNumber) {
        // Tao mot bien tra ve
        String sReturn = "";
        // Tim chieu dai cua chuoi
        int iLen = sNumber.length();
        // Lat nguoc chuoi nay lai
        StringBuilder sNumber1 = new StringBuilder();
        for (int i = iLen - 1; i >= 0; i--) {
            sNumber1.append(sNumber.charAt(i));
        }
        // Tao mot vong lap de doc so
        // Tao mot bien nho vi tri cua sNumber
        int iRe = 0;
        do {
            // Tao mot bien cat tam
            String sCut = "";
            if (iLen > 3) {
                sCut = sNumber1.substring((iRe * 3), (iRe * 3) + 3);
                sReturn = Read(sCut, iRe) + sReturn;
                iRe++;
                iLen -= 3;
            } else {
                sCut = sNumber1.substring((iRe * 3), (iRe * 3) + iLen);
                sReturn = Read(sCut, iRe) + sReturn;
                break;
            }
        } while (true);
        if(sReturn.length() > 1){
            sReturn = sReturn.substring(0,1).toUpperCase() + sReturn.substring(1);
        }
        sReturn = sReturn + "đồng";
        return sReturn;
    }

    // Khoi tao ham Read
    private static String Read(String sNumber, int iPo) {
        // Tao mot bien tra ve
        StringBuilder sReturn = new StringBuilder();
        // Tao mot bien so
        String sPo[] = { "", "ngàn" + " ",
                "triệu" + " ", "tỷ" + " " };
        String sSo[] = { "không" + " ", "một" + " ",
                "hai" + " ", "ba" + " ",
                "bốn" + " ", "năm" + " ",
                "sáu" + " ", "bảy" + " ",
                "tám" + " ", "chín" + " " };
        String sDonvi[] = { "", "mươi" + " ",
                "trăm" + " " };
        // Tim chieu dai cua chuoi
        int iLen = sNumber.length();
        // Tao mot bien nho vi tri doc
        int iRe = 0;
        // Tao mot vong lap de doc so
        for (int i = 0; i < iLen; i++) {
            String sTemp = "" + sNumber.charAt(i);
            int iTemp = Integer.parseInt(sTemp);
            // Tao mot bien ket qua
            String sRead = "";
            // Kiem tra xem so nhan vao co = 0 hay ko
            if (iTemp == 0) {
                switch (iRe) {
                    case 0:
                        break;// Khong lam gi ca
                    case 1: {
                        if (Integer.parseInt("" + sNumber.charAt(0)) != 0) {
                            sRead = "lẻ" + " ";
                        }
                        break;
                    }
                    case 2: {
                        if (Integer.parseInt("" + sNumber.charAt(0)) != 0
                                && Integer.parseInt("" + sNumber.charAt(1)) != 0) {
                            sRead = "không trăm" + " ";
                        }
                        break;
                    }
                }
            } else if (iTemp == 1) {
                switch (iRe) {
                    case 1:
                        sRead = "mười" + " ";
                        break;
                    default:
                        sRead = "một" + " " + sDonvi[iRe];
                        break;
                }
            } else if (iTemp == 5) {
                switch (iRe) {
                    case 0: {
                        if(sNumber.length() <= 1){
                            sRead = "năm" + " ";
                        }
                        else if (Integer.parseInt("" + sNumber.charAt(1)) != 0) {
                            sRead = "lăm" + " ";
                        } else
                            sRead = "năm" + " ";
                        break;
                    }
                    default:
                        sRead = sSo[iTemp] + sDonvi[iRe];
                }
            } else {
                sRead = sSo[iTemp] + sDonvi[iRe];
            }

            sReturn.insert(0, sRead);
            iRe++;
        }
        if (sReturn.length() > 0) {
            sReturn.append(sPo[iPo]);
        }

        return sReturn.toString();
    }

    public static File startActionImageCapture(Activity activity, int requestCode) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file;
        try {
            file = createTempFile(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                    activity.getResources().getString(R.string.app_name),
                    JPEG_FILE_PREFIX,
                    JPEG_FILE_SUFFIX
            );
            if (file == null) {
                return null;
            }
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, getUriFromFile(activity, file));
            activity.startActivityForResult(takePictureIntent, requestCode);
        } catch (IOException e) {
            e.printStackTrace();
            file = null;
        }
        return file;
    }

    public static Uri getUriFromFile(Context context, File file) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            return FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", file);
        }
        return Uri.fromFile(file);
    }


    private static File createTempFile(File rootDir, String dirName, String prefix, String suffix) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        String fileName = prefix + timeStamp + "_";
        File storageDir = getStorageDir(rootDir, dirName);
        if (storageDir == null) {
            return null;
        }
        return File.createTempFile(fileName, suffix, storageDir);
    }

    private static File getStorageDir(File dir, String name) {
        File storageDir = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            storageDir = new File(dir, name);
            if (!storageDir.mkdirs()) {
                if (!storageDir.exists()) {
                    return null;
                }
            }
        }
        return storageDir;
    }


}
