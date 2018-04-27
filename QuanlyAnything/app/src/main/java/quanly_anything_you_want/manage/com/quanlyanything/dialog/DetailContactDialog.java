package quanly_anything_you_want.manage.com.quanlyanything.dialog;

import android.annotation.SuppressLint;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseDialogFragment;
import quanly_anything_you_want.manage.com.quanlyanything.custom_view.QLEditText;
import quanly_anything_you_want.manage.com.quanlyanything.model.UserContact;

@SuppressLint("ValidFragment")
public class DetailContactDialog extends BaseDialogFragment {

    @BindView(R.id.edt_name_contact)
    QLEditText edtNameContact;

    @BindView(R.id.edt_phone_contact)
    QLEditText edtPhoneContact;

    @BindView(R.id.edt_desc_contact)
    QLEditText edtDescContact;

    private OnClickSave mCallBack;
    private UserContact contact;

    public DetailContactDialog(UserContact contact, OnClickSave callBack) {
        mCallBack = callBack;
        this.contact = contact;
    }


    @Override
    protected int layoutID() {
        return R.layout.layout_dialog_detail_contact;
    }

    @Override
    protected void initData() {
        if (contact == null) {
            contact = new UserContact();
        }
        edtNameContact.setText(contact.name != null ? contact.name : "");
        edtPhoneContact.setText(contact.phone != null ? contact.phone : "");
        edtDescContact.setText(contact.desc != null ? contact.desc : "");
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

        if (edtNameContact.getText().toString().isEmpty() || edtPhoneContact.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), getString(R.string.error_not_import_contact), Toast.LENGTH_SHORT).show();
            return;
        }
        contact.name = edtNameContact.getText().toString();
        contact.phone = edtPhoneContact.getText().toString();
        contact.desc = edtDescContact.getText().toString();
        mCallBack.onSave(contact);
        dismiss();
    }

    public interface OnClickSave {
        void onSave(UserContact userContact);
    }
}
