package quanly_anything_you_want.manage.com.quanlyanything.utils;

import android.content.Context;

import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.dialog.dialogOk.DialogOk;
import quanly_anything_you_want.manage.com.quanlyanything.dialog.dialogPositiveNegative.DialogPositiveNegative;
import quanly_anything_you_want.manage.com.quanlyanything.dialog.dialogProgress.DialogProgress;

public class DialogUtils {

    public static DialogProgress showProgressDialog(Context context) {
        DialogProgress progressDialog = new DialogProgress(context);
        progressDialog.show();
        return progressDialog;
    }

    public static void showOkDialog(Context context, String title, String message, DialogOk.IOkDialogListener listener) {
        DialogOk dialogOk = new DialogOk(context, title, message, listener);
        dialogOk.show();
    }

    public static void showConfirmDialog(Context context, String title, String message, DialogPositiveNegative.IPositiveNegativeDialogListener listener) {
        DialogPositiveNegative dialog = new DialogPositiveNegative(context, title, message, context.getString(R.string.error), context.getString(R.string.cancel));
        dialog.setOnIPositiveNegativeDialogListener(listener);
        dialog.show();
    }
}
