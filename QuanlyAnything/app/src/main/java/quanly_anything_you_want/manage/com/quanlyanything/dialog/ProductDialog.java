package quanly_anything_you_want.manage.com.quanlyanything.dialog;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;

import butterknife.BindView;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseDialogFragment;
import quanly_anything_you_want.manage.com.quanlyanything.custom_view.QLEditText;
import quanly_anything_you_want.manage.com.quanlyanything.images.ImageLoader;
import quanly_anything_you_want.manage.com.quanlyanything.model.ProductTapHoa;
import quanly_anything_you_want.manage.com.quanlyanything.popupCommon.selectUnit.SelectUnitProduct;
import quanly_anything_you_want.manage.com.quanlyanything.screen.scanActivity.CustomScannerActivity;
import quanly_anything_you_want.manage.com.quanlyanything.utils.CommonUtil;

@SuppressLint("ValidFragment")
public class ProductDialog extends BaseDialogFragment {
    @BindView(R.id.imv_photo_product)
    ImageView imvPhotoProduct;

    @BindView(R.id.edt_name_product)
    QLEditText edtNameProduct;

    @BindView(R.id.tv_unit_currency)
    TextView tvUnitCurrency;

    @BindView(R.id.edt_total_quantity)
    QLEditText edtTotalQuantity;

    @BindView(R.id.tv_unit_of_total_quantity)
    TextView tvUnitTotalQuantity;

    @BindView(R.id.edt_total_purchase_price)
    QLEditText edtTotalPrice;

    @BindView(R.id.edt_price_wholesale)
    QLEditText edtPriceWholesale;

    @BindView(R.id.tv_unit_currency_total_price)
    TextView tvUnitCurrencyTotalPrice;

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

    @BindView(R.id.btn_choose_currency)
    Button btnChooseCurrency;

    @BindView(R.id.btn_choose_unit_sl)
    Button btnChooseUnitSl;

    @BindView(R.id.btn_choose_unit_wholesale)
    Button btnChooseUnitWholesale;

    @BindView(R.id.btn_choose_unit_retail)
    Button btnChooseUnitRetail;

    @BindView(R.id.btn_has_sell)
    Button btnHasSell;

    @BindView(R.id.btn_not_sell)
    Button btnNotSell;

    private ProductTapHoa mProduct;
    private OnSaveListener mCallBack;

    public ProductDialog(ProductTapHoa mProduct, OnSaveListener mCallBack) {
        this.mProduct = mProduct;
        this.mCallBack = mCallBack;
    }

    @Override

    protected int layoutID() {
        return R.layout.layout_dialog_product;
    }

    @Override
    protected void initData() {
        if (mProduct == null) {
            mProduct = new ProductTapHoa();
        }
        edtTotalQuantity.setShowClear(false);
        edtTotalPrice.setShowClear(false);
        edtPriceWholesale.setShowClear(false);
        edtPriceRetail.setShowClear(false);
        edtCodeProduct.setShowClear(false);

        edtTotalPrice.setInputPrice(true);
        edtPriceWholesale.setInputPrice(true);
        edtPriceRetail.setInputPrice(true);
        setCurrencyValueToEdt(mProduct.currency);

        edtNameProduct.setText(mProduct.name != null ? mProduct.name : "");
        tvUnitCurrency.setText(mProduct.currency != null ? mProduct.currency : "");
        tvUnitCurrencyTotalPrice.setText(mProduct.currency != null ? mProduct.currency : "");
        tvUnitCurrencyWholesale.setText(mProduct.currency != null ? mProduct.currency : "");
        tvUnitCurrencyRetail.setText(mProduct.currency != null ? mProduct.currency : "");
        edtTotalQuantity.setText(mProduct.totalQuantity != 0 ? String.valueOf(mProduct.totalQuantity) : "");
        tvUnitTotalQuantity.setText(mProduct.unitTotalQuantity != null ? mProduct.unitTotalQuantity : "");
        edtTotalPrice.setText(mProduct.totalPrice != 0 ? CommonUtil.showPriceNotCurrency(mProduct.currency, mProduct.totalPrice) : "");
        edtPriceWholesale.setText(mProduct.priceWholesale != 0 ? CommonUtil.showPriceNotCurrency(mProduct.currency, mProduct.priceWholesale) : "");
        edtPriceRetail.setText(mProduct.priceRetail != 0 ? CommonUtil.showPriceNotCurrency(mProduct.currency, mProduct.priceRetail) : "");
        tvUnitOfWholesale.setText(mProduct.unitWholesale);
        tvUnitOfRetail.setText(mProduct.unitRetail);
        edtCodeProduct.setText(mProduct.codeProduct);
        btnHasSell.setSelected(mProduct.status);
        btnNotSell.setSelected(!mProduct.status);
        ImageLoader.loadImagePhoto(getContext(), "https://taphoahoanganh.com/wp-content/uploads/2017/08/sua-dac-ong-tho-3.jpg", imvPhotoProduct);
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
        mProduct.name = edtNameProduct.getText().toString();
        mProduct.currency = tvUnitCurrency.getText().toString();

        if (edtTotalQuantity.getText().toString().isEmpty()) {
            mProduct.totalQuantity = 0;
        } else {
            mProduct.totalQuantity = Integer.valueOf(edtTotalQuantity.getText().toString());
        }

        if (edtTotalPrice.getText().toString().isEmpty()) {
            mProduct.totalPrice = 0;
        } else {
            if (CommonUtil.isCurrencyVND(mProduct.currency)) {
                mProduct.totalPrice = Double.valueOf(edtTotalPrice.getText().toString().replace(".", ""));
            } else {
                mProduct.totalPrice = Double.valueOf(edtTotalPrice.getText().toString().replace(".", "").replace(",", "."));
            }
        }

        if (edtPriceWholesale.getText().toString().isEmpty()) {
            mProduct.priceWholesale = 0;
        } else {
            if (CommonUtil.isCurrencyVND(mProduct.currency)) {
                mProduct.priceWholesale = Double.valueOf(edtPriceWholesale.getText().toString().replace(".", ""));
            } else {
                mProduct.priceWholesale = Double.valueOf(edtPriceWholesale.getText().toString().replace(".", "").replace(",", "."));
            }
        }

        if (edtPriceRetail.getText().toString().isEmpty()) {
            mProduct.priceRetail = 0;
        } else {
            if (CommonUtil.isCurrencyVND(mProduct.currency)) {
                mProduct.priceRetail = Double.valueOf(edtPriceRetail.getText().toString().replace(".", ""));
            } else {
                mProduct.priceRetail = Double.valueOf(edtPriceRetail.getText().toString().replace(".", "").replace(",", "."));
            }
        }
        mProduct.unitTotalQuantity = tvUnitTotalQuantity.getText().toString();
        mProduct.unitWholesale = tvUnitOfWholesale.getText().toString();
        mProduct.unitRetail = tvUnitOfRetail.getText().toString();
        mProduct.codeProduct = edtCodeProduct.getText().toString();
        mProduct.status = btnHasSell.isSelected();
        mCallBack.onSaveValue(mProduct);
        dismiss();
    }

    @OnClick(R.id.btn_choose_image)
    void onClickChooseImage() {

    }

    @OnClick(R.id.btn_choose_currency)
    void onClickChooseCurrency() {
        SelectUnitProduct selectUnitProduct = new SelectUnitProduct(getContext());

        final String unit = tvUnitCurrency.getText().toString();
        selectUnitProduct.showViewPopupUnitCurrency(btnChooseCurrency, tvUnitCurrency, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currency = ((TextView) v).getText().toString();
                if (!unit.equalsIgnoreCase(currency)) {
                    tvUnitCurrencyTotalPrice.setText(currency);
                    tvUnitCurrencyWholesale.setText(currency);
                    tvUnitCurrencyRetail.setText(currency);
                    setCurrencyValueToEdt(currency);

                    edtTotalPrice.setText("");
                    edtPriceWholesale.setText("");
                    edtPriceRetail.setText("");
                }
            }
        });
    }

    private void setCurrencyValueToEdt(String currency) {
        edtTotalPrice.setCurrencyVN(currency);
        edtPriceWholesale.setCurrencyVN(currency);
        edtPriceRetail.setCurrencyVN(currency);
    }

    @OnClick(R.id.btn_choose_unit_sl)
    void onClickChooseUnitTotalQuantity() {
        showDialogPickUnitProduct(btnChooseUnitSl, tvUnitTotalQuantity);
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
        IntentIntegrator integrator = new IntentIntegrator(getActivity());
        integrator.setPrompt("");
        integrator.setCaptureActivity(CustomScannerActivity.class);
        integrator.setOrientationLocked(false);
        integrator.initiateScan();
    }

    public void setValueBarcode(String value) {
        edtCodeProduct.setText(value);
    }

    private void showDialogPickUnitProduct(Button btn, final TextView tv) {
        SelectUnitProduct selectUnitProduct = new SelectUnitProduct(getContext());
        selectUnitProduct.showViewPopupUnitProduct(btn, tv, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((TextView) v).getText().toString().isEmpty()) {
                    InputUnitProductDialog inputUnitProductDialog = new InputUnitProductDialog(new InputUnitProductDialog.OnClickSave() {
                        @Override
                        public void onSave(String value) {
                            tv.setText(value);
                        }
                    });
                    inputUnitProductDialog.show(getChildFragmentManager(), "InputUnitProductDialog");
                }
            }
        });
    }

    public interface OnSaveListener {
        void onSaveValue(ProductTapHoa product);
    }
}
