package quanly_anything_you_want.manage.com.quanlyanything.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.dialog.dialogOk.DialogOk;
import quanly_anything_you_want.manage.com.quanlyanything.dialog.dialogProgress.DialogProgress;
import quanly_anything_you_want.manage.com.quanlyanything.utils.DialogUtils;

public abstract class BaseDialogFragment extends DialogFragment {

    protected abstract int layoutID();

    protected abstract void initData();

    protected abstract void initListener();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.FullscreenDialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(layoutID(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        initData();
        initListener();
    }

    private DialogProgress mProgressDialog;

    protected void showDialog() {
        dismissDialog();
        mProgressDialog = DialogUtils.showProgressDialog(getContext());
    }

    protected void dismissDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    protected void showOkDialog(String title, String message, DialogOk.IOkDialogListener listener) {
        DialogUtils.showOkDialog(getContext(), title, message, listener);
    }

    protected void showErrorDialog(String message) {
        showOkDialog(getContext().getResources().getString(R.string.error), message, new DialogOk.IOkDialogListener() {
            @Override
            public void onIOkDialogAnswerOk(DialogOk dialog) {
                dialog.dismiss();
            }
        });
    }

    protected void showNoNetworkErrorDialog() {
        showErrorDialog(getContext().getString(R.string.no_internet_network));
    }

}
