package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.contact;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseFragment;
import quanly_anything_you_want.manage.com.quanlyanything.dialog.DetailContactDialog;
import quanly_anything_you_want.manage.com.quanlyanything.model.ObjectContentList;
import quanly_anything_you_want.manage.com.quanlyanything.model.UserContact;
import quanly_anything_you_want.manage.com.quanlyanything.screen.chooseContact.ChooseContactActivity;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.contact.adapter.PhoneContactAdapter;
import quanly_anything_you_want.manage.com.quanlyanything.utils.AppConstants;

import static android.app.Activity.RESULT_OK;

public class ContactTapHoaFragment extends BaseFragment implements ContactTabHoaFragmentContact.View {
    @BindView(R.id.rcv_bill_list)
    RecyclerView rcvContact;

    private PhoneContactAdapter adapter;

    ContactTabHoaFragmentPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_contact_shop_tap_hoa;
    }

    @Override
    protected void onInitData() {
        mPresenter = new ContactTabHoaFragmentPresenter(this);
        adapter = new PhoneContactAdapter(getContext(), mPresenter.getContactList());
        rcvContact.setLayoutManager(new LinearLayoutManager(getContext()));
        rcvContact.setHasFixedSize(true);
        rcvContact.setAdapter(adapter);

    }

    private String phone;

    @Override
    protected void onInitListener() {
        adapter.setOnClickListener(new PhoneContactAdapter.OnItemClickListener() {
            @Override
            public void onDeleteContact(int position) {
                mPresenter.setOnDeleteContact(position);
            }

            @Override
            public void onDetailContact(final int position) {
                DetailContactDialog dialog = new DetailContactDialog(mPresenter.getContactList().get(position), new DetailContactDialog.OnClickSave() {
                    @Override
                    public void onSave(UserContact value) {
                        mPresenter.editContact(value, position);
                    }
                });
                dialog.show(getFragmentManager(), "DetailContactDialog");
            }

            @Override
            public void onCallContact(int position) {
                phone = mPresenter.getContactList().get(position).phone;
                checkLocationPermission();
            }
        });
    }

    @OnClick(R.id.btn_add_phone_number)
    void onClickAddPhoneNumber() {
        DetailContactDialog dialog = new DetailContactDialog(null, new DetailContactDialog.OnClickSave() {
            @Override
            public void onSave(UserContact value) {
                mPresenter.addMoreContact(value, 0);
            }
        });
        dialog.show(getFragmentManager(), "DetailContactDialog");
    }

    @OnClick(R.id.btn_add_phone_number_from_device)
    void onClickAddPhoneNumberFromDevice() {
        Intent intent = new Intent(getContext(), ChooseContactActivity.class);
        intent.putExtra(AppConstants.KEY_LIST_ID_CONTACT, mPresenter.getListNumberPhone());
        startActivityForResult(intent, AppConstants.REQUEST_CHOOSE_CONTACT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
        ObjectContentList list = (ObjectContentList) data.getSerializableExtra(AppConstants.RESULT_CHOOSE_CONTACT);
        if (list != null && list.userContacts != null && !list.userContacts.isEmpty())
            mPresenter.addMoreContact(list.userContacts);
    }

    @Override
    public void onNotifyAdapterContact() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onNotifyAdapterContactAtPosition(int position) {
        adapter.notifyItemChanged(position);
    }

    @Override
    public void onNotifyAdapterInsertedContactAtPosition(int position) {
        adapter.notifyItemInserted(position);
    }

    @Override
    public void onNotifyAdapterRemoveContactAtPosition(int position) {
        adapter.notifyItemRemoved(position);
    }


    public void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                    AppConstants.MY_PERMISSIONS_REQUEST_CALL_CONTACT);

        } else {
            callPhone();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case AppConstants.MY_PERMISSIONS_REQUEST_CALL_CONTACT: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        callPhone();
                    }
                } else {
                    Toast.makeText(getContext(), "permission denied", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void callPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        startActivity(intent);
    }

}
