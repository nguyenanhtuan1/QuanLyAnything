package quanly_anything_you_want.manage.com.quanlyanything.base;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.IntentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

abstract public class BaseFragment extends Fragment implements IBaseView {

    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        onInitData();
        onInitListener();
    }

    protected abstract void onInitData();

    protected abstract void onInitListener();

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    abstract protected int getLayoutId();

    // progress dialog
    private DialogProgress mProgressDialog;

    protected void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    protected void showDialogLoading() {
        dismissDialogLoading();
        if (!getActivity().isDestroyed())
            mProgressDialog = DialogUtils.showProgressDialog(getContext());
    }

    protected void dismissDialogLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            if (!getActivity().isDestroyed()) {
                mProgressDialog.dismiss();
            }
            mProgressDialog = null;
        }
    }
    // end progress dialog

    // dialog with one button
    protected void showOkDialog(String title, String message, DialogOk.IOkDialogListener listener) {
        DialogUtils.showOkDialog(getContext(), title, message, listener);
    }

    protected void showErrorDialog(String message) {
        showOkDialog(getResources().getString(R.string.error), message, new DialogOk.IOkDialogListener() {
            @Override
            public void onIOkDialogAnswerOk(DialogOk dialog) {
                dialog.dismiss();
            }
        });
    }

    protected void showNoNetworkErrorDialog() {
        showErrorDialog(getString(R.string.no_internet_network));
    }
    // end dialog with one button

    // dialog with two button
    protected void showConfirmDialog(String title, String message, DialogPositiveNegative.IPositiveNegativeDialogListener listener) {
        DialogUtils.showConfirmDialog(getActivity(), title, message, listener);
    }

    protected void showConfirmDialog(String message, DialogPositiveNegative.IPositiveNegativeDialogListener listener) {
        DialogUtils.showConfirmDialog(getActivity(), getString(R.string.app_name), message, listener);
    }
    // end dialog with two button

    protected boolean checkPermissions(String[] permissions) {
        for (String s : permissions) {
            if (ContextCompat.checkSelfPermission(getContext(), s)
                    != PackageManager.PERMISSION_GRANTED)
                return false;
        }
        return true;
    }

    @SuppressLint("WrongConstant")
    protected void logoutUser() {
        Intent i = new Intent(getActivity(), LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    @Override
    public void showLoading() {
        showDialogLoading();
    }

    @Override
    public void hideLoading() {
        dismissDialogLoading();
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

    @Override
    public void onFail(final RestError error) {
        if (getActivity().isDestroyed()) {
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
        if (getActivity().isDestroyed()) {
            return;
        }
        dismissDialogLoading();
        showErrorDialog(error);
    }


    public interface IRestErrorListener {
        void onListener();
    }

    private boolean handleSpecialCode(RestError error) {
        return false;
    }

}
