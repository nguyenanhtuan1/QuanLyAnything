package quanly_anything_you_want.manage.com.quanlyanything.screen.detail_product;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseActivity;
import quanly_anything_you_want.manage.com.quanlyanything.custom_view.QLEditText;
import quanly_anything_you_want.manage.com.quanlyanything.dialog.InputUnitProductDialog;
import quanly_anything_you_want.manage.com.quanlyanything.dialog.dialogPositiveNegative.DialogPositiveNegative;
import quanly_anything_you_want.manage.com.quanlyanything.images.ImageLoader;
import quanly_anything_you_want.manage.com.quanlyanything.model.ProductTapHoa;
import quanly_anything_you_want.manage.com.quanlyanything.popupCommon.selectUnit.SelectUnitProduct;
import quanly_anything_you_want.manage.com.quanlyanything.screen.scanActivity.CustomScannerActivity;
import quanly_anything_you_want.manage.com.quanlyanything.utils.AppConstants;
import quanly_anything_you_want.manage.com.quanlyanything.utils.CommonUtil;

public class DetailProductActivity extends BaseActivity implements DetailProductContact.View {
    @BindView(R.id.imv_photo_product)
    ImageView imvPhotoProduct;

    @BindView(R.id.edt_name_product)
    QLEditText edtNameProduct;

    @BindView(R.id.edt_purchase_price)
    QLEditText edtPurchasePrice;

    @BindView(R.id.edt_price_wholesale)
    QLEditText edtPriceWholesale;

    @BindView(R.id.tv_unit_currency_purchase)
    TextView tvUnitCurrencyProduct;

    @BindView(R.id.tv_unit_currency_wholesale)
    TextView tvUnitCurrencyWholesale;

    @BindView(R.id.tv_unit_currency_retail)
    TextView tvUnitCurrencyRetail;

    @BindView(R.id.tv_unit_of_wholesale)
    TextView tvUnitOfWholesale;

    @BindView(R.id.edt_price_retail)
    QLEditText edtPriceRetail;

    @BindView(R.id.tv_unit_of_retail)
    TextView tvUnitOfRetail;

    @BindView(R.id.edt_code_product)
    QLEditText edtCodeProduct;

    @BindView(R.id.btn_choose_unit_wholesale)
    Button btnChooseUnitWholesale;

    @BindView(R.id.btn_choose_unit_retail)
    Button btnChooseUnitRetail;

    @BindView(R.id.btn_choose_unit_purchase)
    Button btnChooseUnitPurchase;

    @BindView(R.id.tv_unit_purchase_product)
    TextView tvUnitPurchase;

    @BindView(R.id.btn_has_sell)
    Button btnHasSell;

    @BindView(R.id.btn_not_sell)
    Button btnNotSell;

    @BindView(R.id.btn_delete_product)
    Button btnDelete;
    private File mUploadFile;
    private ProductTapHoa mProduct;
    DetailProductPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.detail_product_activity);
        mPresenter = new DetailProductPresenter(this);
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onInitData() {
        mProduct = (ProductTapHoa) getIntent().getSerializableExtra(AppConstants.KEY_DETAIL_PRODUCT);
        if (mProduct == null) {
            mProduct = new ProductTapHoa();
        }
        edtPurchasePrice.setShowClear(false);
        edtPriceWholesale.setShowClear(false);
        edtPriceRetail.setShowClear(false);
        edtCodeProduct.setShowClear(false);

        edtPurchasePrice.setInputPrice(true);
        edtPriceWholesale.setInputPrice(true);
        edtPriceRetail.setInputPrice(true);

        edtNameProduct.setText(mProduct.name != null ? mProduct.name : "");

        StringBuffer stringPurchase = new StringBuffer();

        if (mProduct.currency != null) {
            stringPurchase.append(mProduct.currency);
        }
        if (!TextUtils.isEmpty(mProduct.unitProduct)) {
            stringPurchase.append("/ 1 ");
            stringPurchase.append(mProduct.unitProduct);
        }
        tvUnitCurrencyProduct.setText(stringPurchase);
        tvUnitCurrencyWholesale.setText(mProduct.currency != null ? mProduct.currency : "");
        tvUnitCurrencyRetail.setText(mProduct.currency != null ? mProduct.currency : "");
        edtPurchasePrice.setText(mProduct.pricePurchase != 0 ? CommonUtil.showPriceNotCurrency(mProduct.pricePurchase) : "");
        edtPriceWholesale.setText(mProduct.priceWholesale != 0 ? CommonUtil.showPriceNotCurrency(mProduct.priceWholesale) : "");
        edtPriceRetail.setText(mProduct.priceRetail != 0 ? CommonUtil.showPriceNotCurrency(mProduct.priceRetail) : "");
        tvUnitPurchase.setText(mProduct.unitProduct != null ? mProduct.unitProduct : "");
        tvUnitOfWholesale.setText(mProduct.unitWholesale);
        tvUnitOfRetail.setText(mProduct.unitRetail);
        edtCodeProduct.setText(mProduct.codeProduct);
        btnHasSell.setSelected(mProduct.status);
        btnNotSell.setSelected(!mProduct.status);
        ImageLoader.loadImagePhoto(this, mProduct.photo, imvPhotoProduct);
        btnDelete.setVisibility(!TextUtils.isEmpty(mProduct.name) ? View.VISIBLE : View.GONE);

    }

    @Override
    public void onInitListener() {

    }

    @OnClick(R.id.btn_cancel)
    void onClickCancel() {
        finish();
    }

    @OnClick(R.id.btn_save)
    void onClickSave() {
        mProduct.name = edtNameProduct.getText().toString();
        mProduct.unitProduct = tvUnitPurchase.getText().toString();
        if (edtPurchasePrice.getText().toString().isEmpty()) {
            mProduct.pricePurchase = 0;
        } else {
            mProduct.pricePurchase = Double.valueOf(edtPurchasePrice.getText().toString().replace(".", "").replace(",", ""));
        }

        if (edtPriceWholesale.getText().toString().isEmpty()) {
            mProduct.priceWholesale = 0;
        } else {
            mProduct.priceWholesale = Double.valueOf(edtPriceWholesale.getText().toString().replace(".", "").replace(",", ""));
        }

        if (edtPriceRetail.getText().toString().isEmpty()) {
            mProduct.priceRetail = 0;
        } else {
            mProduct.priceRetail = Double.valueOf(edtPriceRetail.getText().toString().replace(".", "").replace(",", ""));
        }
        mProduct.unitWholesale = tvUnitOfWholesale.getText().toString();
        mProduct.unitRetail = tvUnitOfRetail.getText().toString();
        mProduct.codeProduct = edtCodeProduct.getText().toString();
        mProduct.status = btnHasSell.isSelected();

        Intent intent = new Intent();
        intent.putExtra(AppConstants.KEY_DETAIL_PRODUCT, mProduct);
        setResult(RESULT_OK, intent);
        finish();
    }


    @OnClick(R.id.btn_choose_image)
    void onClickChooseImage() {
        String[] s = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (checkPermissions(s)) {
            mUploadFile = CommonUtil.startActionImageCapture(this, AppConstants.REQUEST_CAPTURE_IMAGE);
        } else {
            ActivityCompat.requestPermissions(this, s, AppConstants.REQUEST_PERMISSION_CAPTURE_IMAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && requestCode == AppConstants.REQUEST_PERMISSION_CAPTURE_IMAGE) {
            mUploadFile = CommonUtil.startActionImageCapture(this, AppConstants.REQUEST_CAPTURE_IMAGE);
        }
    }

    @Override
    public void onActivityResult(final int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppConstants.REQUEST_CAPTURE_IMAGE) {
            if (mUploadFile != null) {
                ImageLoader.loadImagePhoto2(this, mUploadFile, imvPhotoProduct);
            }
        } else {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result != null && result.getContents() != null) {
                edtCodeProduct.setText(result.getContents());
            }
        }
    }

    @OnClick(R.id.btn_delete_product)
    void onClickDeleteProduct() {
        showConfirmDialog(getString(R.string.app_name), getString(R.string.question_delete_product), new DialogPositiveNegative.IPositiveNegativeDialogListener() {
            @Override
            public void onIPositiveNegativeDialogAnswerPositive(DialogPositiveNegative dialog) {
                dialog.dismiss();
                Intent intent = new Intent();
                mProduct.isDelete = true;
                intent.putExtra(AppConstants.KEY_DETAIL_PRODUCT, mProduct);
                setResult(RESULT_OK, intent);
                finish();
            }

            @Override
            public void onIPositiveNegativeDialogAnswerNegative(DialogPositiveNegative dialog) {
                dialog.dismiss();
            }
        });
    }

    @OnClick(R.id.btn_choose_unit_purchase)
    void onClickChooseUnitPurchase() {
        showDialogPickUnitProduct(btnChooseUnitPurchase, tvUnitPurchase);
    }

    @OnClick(R.id.btn_choose_unit_wholesale)
    void onClickChooseUnitWholesale() {
        showDialogPickUnitProduct(btnChooseUnitWholesale, tvUnitOfWholesale);
    }

    @OnClick(R.id.btn_choose_unit_retail)
    void onClickChooseUnitRetail() {
        showDialogPickUnitProduct(btnChooseUnitRetail, tvUnitOfRetail);
    }

    @OnClick(R.id.btn_has_sell)
    void onClickHasSell() {
        btnHasSell.setSelected(true);
        btnNotSell.setSelected(false);
    }

    @OnClick(R.id.btn_not_sell)
    void onClickNotSell() {
        btnHasSell.setSelected(false);
        btnNotSell.setSelected(true);
    }

    @OnClick(R.id.btn_scan_product)
    void onClickScanProduct() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setPrompt("");
        integrator.setCaptureActivity(CustomScannerActivity.class);
        integrator.setOrientationLocked(false);
        integrator.initiateScan();
    }

    private void showDialogPickUnitProduct(Button btn, final TextView tv) {
        SelectUnitProduct selectUnitProduct = new SelectUnitProduct(this);
        selectUnitProduct.showViewPopupUnitProduct(btn, tv, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tv == tvUnitPurchase) {
                    StringBuffer value = new StringBuffer();
                    value.append(mProduct.currency);
                    value.append("/ 1 ");
                    value.append(((TextView) v).getText().toString());
                    tvUnitCurrencyProduct.setText(value);

                    if (tvUnitOfWholesale.getText().toString().isEmpty()) {
                        tvUnitOfWholesale.setText(((TextView) v).getText().toString());
                    }
                }
                if (((TextView) v).getText().toString().isEmpty()) {
                    InputUnitProductDialog inputUnitProductDialog = new InputUnitProductDialog(new InputUnitProductDialog.OnClickSave() {
                        @Override
                        public void onSave(String text) {
                            tv.setText(text);
                            if (tv == tvUnitPurchase) {
                                StringBuffer value2 = new StringBuffer();
                                value2.append(mProduct.currency);
                                value2.append("/ 1 ");
                                value2.append(text);
                                tvUnitCurrencyProduct.setText(value2);

                                if (tvUnitOfWholesale.getText().toString().isEmpty()) {
                                    tvUnitOfWholesale.setText(text);
                                }
                            }
                        }
                    });
                    inputUnitProductDialog.show(getSupportFragmentManager(), "InputUnitProductDialog");
                }
            }
        });
    }


}
