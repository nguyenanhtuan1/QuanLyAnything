package quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProductImport;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


import butterknife.BindView;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseActivity;
import quanly_anything_you_want.manage.com.quanlyanything.custom_view.QLEditText;
import quanly_anything_you_want.manage.com.quanlyanything.model.ObjectContentList;
import quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProductImport.adapter.PickProductImportAdapter;
import quanly_anything_you_want.manage.com.quanlyanything.screen.scanActivity.CustomScannerActivity;
import quanly_anything_you_want.manage.com.quanlyanything.utils.AppConstants;

public class ChooseProductImportActivity extends BaseActivity implements ChooseProductImportContact.View {

    @BindView(R.id.rcv_list_product)
    RecyclerView rcvProduct;

    @BindView(R.id.edt_search_product)
    QLEditText edtSearch;

    PickProductImportAdapter adapter;
    ChooseProductImportPresenter mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.choose_product_import_activity);
        mPresenter = new ChooseProductImportPresenter(this,getIntent().getStringArrayListExtra(AppConstants.KEY_LIST_ID_PRODUCT));
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onInitData() {
        adapter = new PickProductImportAdapter(this, mPresenter.getListDisplay());
        adapter.setFragmentManager(getSupportFragmentManager());
        rcvProduct.setLayoutManager(new LinearLayoutManager(this));
        rcvProduct.setAdapter(adapter);
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

    @OnClick(R.id.btn_scan_product)
    void onClickScanCode() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setPrompt("");
        integrator.setCaptureActivity(CustomScannerActivity.class);
        integrator.setOrientationLocked(false);
        integrator.initiateScan();
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
        data.chooseDtoList = mPresenter.getListSave();
        intent.putExtra(AppConstants.RESULT_CHOOSE_PRODUCT, data);
        setResult(RESULT_OK, intent);
        finish();
    }
}
