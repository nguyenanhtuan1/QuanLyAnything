package quanly_anything_you_want.manage.com.quanlyanything.screen.chooseContact;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseActivity;
import quanly_anything_you_want.manage.com.quanlyanything.custom_view.QLEditText;
import quanly_anything_you_want.manage.com.quanlyanything.model.ObjectContentList;
import quanly_anything_you_want.manage.com.quanlyanything.model.UserContact;
import quanly_anything_you_want.manage.com.quanlyanything.screen.chooseContact.adapter.PickContactAdapter;
import quanly_anything_you_want.manage.com.quanlyanything.utils.AppConstants;

public class ChooseContactActivity extends BaseActivity implements ChooseContactContact.View {

    @BindView(R.id.rcv_list_product)
    RecyclerView rcvProduct;

    @BindView(R.id.edt_search_product)
    QLEditText edtSearch;

    PickContactAdapter adapter;
    ChooseContactPresenter mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.choose_contact_activity);
        mPresenter = new ChooseContactPresenter(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onInitData() {
        adapter = new PickContactAdapter(this, mPresenter.getListDisplay());
        rcvProduct.setLayoutManager(new LinearLayoutManager(this));
        rcvProduct.setAdapter(adapter);

        checkLocationPermission();
    }

    @Override
    public void onInitListener() {
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPresenter.onSearchProduct(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onNotifyAdapterProduct() {
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null && result.getContents() != null) {
            edtSearch.setText(result.getContents());
        }
    }

    @OnClick(R.id.btn_cancel)
    void onClickCancel() {
        finish();
    }

    @OnClick(R.id.btn_save)
    void onClickSave() {
        Intent intent = new Intent();
        ObjectContentList data = new ObjectContentList();
        data.userContacts = mPresenter.getListSave();
        intent.putExtra(AppConstants.RESULT_CHOOSE_CONTACT, data);
        setResult(RESULT_OK, intent);
        finish();
    }


    @SuppressLint("StaticFieldLeak")
    private void getContactInDevice() {

        showLoading();
        final List<UserContact> list = new ArrayList<>();
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                ContentResolver cr = getContentResolver();
                Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                        null, null, null, null);

                if ((cur != null ? cur.getCount() : 0) > 0) {
                    while (cur.moveToNext()) {

                        String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                        String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        String phoneNo = null;

                        if (cur.getInt(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {

                            Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                    null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                    new String[]{id}, null);

                            while (pCur != null && pCur.moveToNext()) {

                                phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            }
                            if (pCur != null)
                                pCur.close();
                        }
                        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(phoneNo))
                            list.add(new UserContact(name, phoneNo));
                    }
                }
                if (cur != null) {
                    cur.close();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                mPresenter.setListStore(list, getIntent().getStringArrayListExtra(AppConstants.KEY_LIST_ID_CONTACT));
                hideLoading();
            }

        }.execute();
    }


    public void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CONTACTS},
                    AppConstants.MY_PERMISSIONS_REQUEST_READ_CONTACT);
        } else {
            getContactInDevice();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case AppConstants.MY_PERMISSIONS_REQUEST_READ_CONTACT: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.WRITE_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                        getContactInDevice();
                    }
                } else {
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

}
