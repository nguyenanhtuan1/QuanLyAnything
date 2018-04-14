package quanly_anything_you_want.manage.com.quanlyanything.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.dialog.dialogOk.DialogOk;
import quanly_anything_you_want.manage.com.quanlyanything.dialog.dialogPositiveNegative.DialogPositiveNegative;
import quanly_anything_you_want.manage.com.quanlyanything.dialog.dialogProgress.DialogProgress;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.network.RestError;
import quanly_anything_you_want.manage.com.quanlyanything.screen.login.LoginActivity;
import quanly_anything_you_want.manage.com.quanlyanything.utils.DialogUtils;

abstract public class BaseActivity extends AppCompatActivity implements IBaseView {

    private Unbinder unbinder;
    private boolean hideKeyBoard = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (isTransparentStatusBar()) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        }

        unbinder = ButterKnife.bind(this);

        onInitData();
        onInitListener();
    }

    abstract public void onInitData();

    abstract public void onInitListener();

    public void setHideKeyBoard(boolean state) {
        this.hideKeyBoard = state;
    }

    public boolean isTransparentStatusBar() {
        return true;
    }

    // progress dialog
    private DialogProgress mProgressDialog;

    protected void showDialogLoading() {
        dismissDialogLoading();
        if (!isDestroyed())
            mProgressDialog = DialogUtils.showProgressDialog(this);
    }

    protected void dismissDialogLoading() {
        if (!isDestroyed() && mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }
    // end progress dialog

    // dialog with one button
    protected void showOkDialog(String title, String message, DialogOk.IOkDialogListener listener) {
        DialogUtils.showOkDialog(this, title, message, listener);
    }

    protected void showErrorDialog(String message) {
        showOkDialog(getResources().getString(R.string.error), message, new DialogOk.IOkDialogListener() {
            @Override
            public void onIOkDialogAnswerOk(DialogOk dialog) {
                dialog.dismiss();
            }
        });
    }

    protected void showSuccessDialog(String message) {
        showOkDialog(getResources().getString(R.string.success), message, new DialogOk.IOkDialogListener() {
            @Override
            public void onIOkDialogAnswerOk(DialogOk dialog) {
                dialog.dismiss();
            }
        });
    }

    protected void showRestErrorDialog(RestError error, final IRestErrorListener listener) {
        dismissDialogLoading();
        showOkDialog(getResources().getString(R.string.error), error.message, new DialogOk.IOkDialogListener() {
            @Override
            public void onIOkDialogAnswerOk(DialogOk dialog) {
                dialog.dismiss();
                listener.onListener();
            }
        });
    }

    public interface IRestErrorListener {
        void onListener();
    }

    protected void showNoNetworkErrorDialog() {
        showErrorDialog(getString(R.string.no_internet_network));
    }
    // end dialog with one button

    // dialog with two button
    protected void showConfirmDialog(String title, String message, DialogPositiveNegative.IPositiveNegativeDialogListener listener) {
        DialogUtils.showConfirmDialog(this, title, message, listener);
    }

    protected void showConfirmDialog(String message, DialogPositiveNegative.IPositiveNegativeDialogListener listener) {
        DialogUtils.showConfirmDialog(this, getString(R.string.app_name), message, listener);
    }
    // end dialog with two button

    // Toast
    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    // End toast

    // Keyboard utils

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager ipm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            assert ipm != null;
            ipm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    protected boolean checkPermissions(String[] permissions) {
        for (String s : permissions) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), s) != PackageManager.PERMISSION_GRANTED)
                return false;
        }
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) ev.getRawX(), (int) ev.getRawY()) && hideKeyBoard) {
                    v.clearFocus();
                    hideKeyboard();
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @SuppressLint("WrongConstant")
    protected void logoutUser() {
        Intent i = new Intent(this, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    public void showLoading() {
        showDialogLoading();
    }

    @Override
    public void hideLoading() {
        dismissDialogLoading();
    }

    @Override
    public void onFail(final RestError error) {
        if (isDestroyed()) {
            return;
        }
        dismissDialogLoading();
        showRestErrorDialog(error, new IRestErrorListener() {
            @Override
            public void onListener() {
                if (handleSpecialCode(error))
                    logoutUser();
            }
        });
    }

    @Override
    public void showErrorNormal(String error) {
        dismissDialogLoading();
        showErrorDialog(error);
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    private boolean handleSpecialCode(RestError error) {
        return false;
    }

}