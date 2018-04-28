package quanly_anything_you_want.manage.com.quanlyanything.dialog;

import android.annotation.SuppressLint;
import android.widget.Toast;

import java.sql.Time;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseDialogFragment;
import quanly_anything_you_want.manage.com.quanlyanything.custom_view.QLEditText;
import quanly_anything_you_want.manage.com.quanlyanything.model.UserContact;

@SuppressLint("ValidFragment")
public class PickTimeDialog extends BaseDialogFragment {

    private OnClickSave mCallBack;
    private Date timeStart;
    private Date timeEnd;

    public PickTimeDialog(Date timeStart, Date timeEnd, OnClickSave callBack) {
        mCallBack = callBack;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }


    @Override
    protected int layoutID() {
        return R.layout.layout_dialog_pick_time;
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
        mCallBack.onSave(timeStart, timeEnd);
        dismiss();
    }

    public interface OnClickSave {
        void onSave(Date timeStart, Date timeEnd);
    }
}
