package quanly_anything_you_want.manage.com.quanlyanything.dialog;

import android.annotation.SuppressLint;

import butterknife.BindView;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseDialogFragment;
import quanly_anything_you_want.manage.com.quanlyanything.custom_view.QLEditText;

@SuppressLint("ValidFragment")
public class InputUnitProductDialog extends BaseDialogFragment {
    @BindView(R.id.edt_input_unit)
    QLEditText edtInput;
    private OnClickSave mCallBack;

    public InputUnitProductDialog(OnClickSave callBack) {
        mCallBack = callBack;
    }


    @Override
    protected int layoutID() {
        return R.layout.layout_dialog_input_unit_produt;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @OnClick(R.id.btn_cancel)
    void onClickCancel() {
        dismiss();
    }

    @OnClick(R.id.btn_save)
    void onClickSave() {
        if (edtInput.getText().toString().isEmpty()) return;
        mCallBack.onSave(edtInput.getText().toString());
        dismiss();
    }

    public interface OnClickSave {
        void onSave(String value);
    }
}
