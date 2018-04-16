package quanly_anything_you_want.manage.com.quanlyanything.dialog;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseDialogFragment;
import quanly_anything_you_want.manage.com.quanlyanything.custom_view.QLEditText;
import quanly_anything_you_want.manage.com.quanlyanything.images.ImageLoader;
import quanly_anything_you_want.manage.com.quanlyanything.popupCommon.selectUnit.SelectUnitProduct;
import quanly_anything_you_want.manage.com.quanlyanything.utils.CommonUtil;

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
    QLEditText edtTotalPurchasePrice;

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

    @Override
    protected int layoutID() {
        return R.layout.layout_dialog_product;
    }

    @Override
    protected void initData() {
        ImageLoader.loadImagePhoto(getContext(),
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS-T8iovMyAqb7byyqo1PVU2s_bGIbQJPVDS5q11VknS5OjNAxg", imvPhotoProduct);
        edtTotalQuantity.setShowClear(false);
        edtTotalPurchasePrice.setShowClear(false);
        edtPriceWholesale.setShowClear(false);
        edtPriceRetail.setShowClear(false);
        edtCodeProduct.setShowClear(false);
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
    }

    @OnClick(R.id.btn_choose_image)
    void onClickChooseImage() {
    }

    @OnClick(R.id.btn_choose_currency)
    void onClickChooseCurrency() {
        SelectUnitProduct selectUnitProduct = new SelectUnitProduct(getContext());
        selectUnitProduct.showViewPopupUnitCurrency(btnChooseCurrency, tvUnitCurrency, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvUnitCurrencyTotalPrice.setText(((TextView) v).getText());
                tvUnitCurrencyWholesale.setText(((TextView) v).getText());
                tvUnitCurrencyRetail.setText(((TextView) v).getText());

                edtTotalPurchasePrice.setInputPrice(CommonUtil.isCurrencyVND(tvUnitCurrency.getText().toString()));
                edtPriceWholesale.setInputPrice(CommonUtil.isCurrencyVND(tvUnitCurrency.getText().toString()));
                edtPriceRetail.setInputPrice(CommonUtil.isCurrencyVND(tvUnitCurrency.getText().toString()));
            }
        });
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

    @OnClick(R.id.btn_scan_product)
    void onClickScanProduct() {
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
}
