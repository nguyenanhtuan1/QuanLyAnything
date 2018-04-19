package quanly_anything_you_want.manage.com.quanlyanything.dialog;

import android.annotation.SuppressLint;
import android.text.TextUtils;
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

    @BindView(R.id.edt_purchase_price)
    QLEditText edtPurchasePrice;

    @BindView(R.id.edt_price_wholesale)
    QLEditText edtPriceWholesale;

    @BindView(R.id.tv_unit_currency_purchase)
    TextView tvUnitCurrencyPurchase;

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
        edtPurchasePrice.setShowClear(false);
        edtPriceWholesale.setShowClear(false);
        edtPriceRetail.setShowClear(false);
        edtCodeProduct.setShowClear(false);

        edtPurchasePrice.setInputPrice(true);
        edtPriceWholesale.setInputPrice(true);
        edtPriceRetail.setInputPrice(true);
        setCurrencyValueToEdt(mProduct.currency);

        edtNameProduct.setText(mProduct.name != null ? mProduct.name : "");
        tvUnitCurrency.setText(mProduct.currency != null ? mProduct.currency : "");

        StringBuffer stringPurchase = new StringBuffer();

        if (mProduct.currency != null) {
            stringPurchase.append(mProduct.currency);
        }
        if (!TextUtils.isEmpty(mProduct.unitPurchase)) {
            stringPurchase.append("/ 1 ");
            stringPurchase.append(mProduct.unitPurchase);
        }
        tvUnitCurrencyPurchase.setText(stringPurchase);

        tvUnitCurrencyWholesale.setText(mProduct.currency != null ? mProduct.currency : "");
        tvUnitCurrencyRetail.setText(mProduct.currency != null ? mProduct.currency : "");
        edtPurchasePrice.setText(mProduct.purchasePrice != 0 ? CommonUtil.showPriceNotCurrency(mProduct.currency, mProduct.purchasePrice) : "");
        edtPriceWholesale.setText(mProduct.priceWholesale != 0 ? CommonUtil.showPriceNotCurrency(mProduct.currency, mProduct.priceWholesale) : "");
        edtPriceRetail.setText(mProduct.priceRetail != 0 ? CommonUtil.showPriceNotCurrency(mProduct.currency, mProduct.priceRetail) : "");
        tvUnitPurchase.setText(mProduct.unitPurchase != null ? mProduct.unitPurchase : "");
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
        mProduct.unitPurchase = tvUnitPurchase.getText().toString();
        if (edtPurchasePrice.getText().toString().isEmpty()) {
            mProduct.purchasePrice = 0;
        } else {
            if (CommonUtil.isCurrencyVND(mProduct.currency)) {
                mProduct.purchasePrice = Double.valueOf(edtPurchasePrice.getText().toString().replace(".", ""));
            } else {
                mProduct.purchasePrice = Double.valueOf(edtPurchasePrice.getText().toString().replace(".", "").replace(",", "."));
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
                    tvUnitCurrencyPurchase.setText(currency);
                    tvUnitCurrencyWholesale.setText(currency);
                    tvUnitCurrencyRetail.setText(currency);
                    setCurrencyValueToEdt(currency);

                    edtPurchasePrice.setText("");
                    edtPriceWholesale.setText("");
                    edtPriceRetail.setText("");
                }
            }
        });
    }

    private void setCurrencyValueToEdt(String currency) {
        edtPurchasePrice.setCurrencyVN(currency);
        edtPriceWholesale.setCurrencyVN(currency);
        edtPriceRetail.setCurrencyVN(currency);
    }


    @OnClick(R.id.btn_choose_unit_wholesale)
    void onClickChooseUnitWholesale() {
        showDialogPickUnitProduct(btnChooseUnitWholesale, tvUnitOfWholesale);
    }

    @OnClick(R.id.btn_choose_unit_retail)
    void onClickChooseUnitRetail() {
        showDialogPickUnitProduct(btnChooseUnitRetail, tvUnitOfRetail);
    }

    @OnClick(R.id.btn_choose_unit_purchase)
    void onClickChooseUnitPurchase() {
        showDialogPickUnitProduct(btnChooseUnitPurchase, tvUnitPurchase);
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

                if (tv == tvUnitPurchase) {
                    StringBuffer value = new StringBuffer();
                    value.append(tvUnitCurrency.getText());
                    value.append("/ 1 ");
                    value.append(((TextView) v).getText().toString());
                    tvUnitCurrencyPurchase.setText(value);
                }
                if (((TextView) v).getText().toString().isEmpty()) {
                    InputUnitProductDialog inputUnitProductDialog = new InputUnitProductDialog(new InputUnitProductDialog.OnClickSave() {
                        @Override
                        public void onSave(String text) {
                            tv.setText(text);
                            if (tv == tvUnitPurchase) {
                                StringBuffer value2 = new StringBuffer();
                                value2.append(tvUnitCurrency.getText());
                                value2.append("/ 1 ");
                                value2.append(text);
                                tvUnitCurrencyPurchase.setText(value2);
                            }
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
