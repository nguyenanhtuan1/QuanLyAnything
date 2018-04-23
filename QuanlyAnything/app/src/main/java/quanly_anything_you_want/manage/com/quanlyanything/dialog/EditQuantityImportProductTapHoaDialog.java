package quanly_anything_you_want.manage.com.quanlyanything.dialog;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseDialogFragment;
import quanly_anything_you_want.manage.com.quanlyanything.custom_view.QLEditText;
import quanly_anything_you_want.manage.com.quanlyanything.model.ProductTapHoa;
import quanly_anything_you_want.manage.com.quanlyanything.popupCommon.selectUnit.SelectUnitProduct;
import quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProduct.adapter.ProductChooseDto;
import quanly_anything_you_want.manage.com.quanlyanything.utils.CommonUtil;

@SuppressLint("ValidFragment")
public class EditQuantityImportProductTapHoaDialog extends BaseDialogFragment {

    @BindView(R.id.tv_name_product)
    TextView tvNameProduct;

    @BindView(R.id.tv_unit_product)
    TextView tvUnitProduct;

    @BindView(R.id.tv_currency)
    TextView tvCurrency;

    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;

    @BindView(R.id.edt_input_quantity)
    QLEditText edtQuantity;

    @BindView(R.id.edt_price_product)
    QLEditText edtPriceProduct;

    @BindView(R.id.btn_choose_unit_product)
    Button btnUnit;
    @BindView(R.id.tv_unit_quantity)
    TextView tvUnitQuantity;

    private ProductChooseDto mProduct;
    private OnSaveListener mCallBack;

    public EditQuantityImportProductTapHoaDialog(ProductChooseDto mProduct, OnSaveListener mCallBack) {
        this.mProduct = mProduct;
        this.mCallBack = mCallBack;
    }

    @Override
    protected int layoutID() {
        return R.layout.layout_dialog_add_quantity_product_tap_hoa;
    }

    @Override
    protected void initData() {
        edtQuantity.setShowClear(false);
        edtPriceProduct.setShowClear(false);

        tvNameProduct.setText(mProduct.name != null ? mProduct.name : "");
        tvUnitProduct.setText(mProduct.unitImport != null ? mProduct.unitImport : "");
        tvUnitQuantity.setText(mProduct.unitImport != null ? mProduct.unitImport : "");
        edtQuantity.setText(String.valueOf(mProduct.quantityImport));
        edtPriceProduct.setText(CommonUtil.showPriceNotCurrency(mProduct.priceImport));
        tvCurrency.setText(mProduct.currency != null ? mProduct.currency : "");
        tvTotalPrice.setText(CommonUtil.showPriceHasCurrency(mProduct.priceImport * mProduct.quantityImport, mProduct.currency));
    }

    @Override
    protected void initListener() {

        edtQuantity.setOnKeyboardListener(new QLEditText.KeyboardListener() {
            @Override
            public void onDismissKeyBoard(QLEditText keyboardEditText) {
                updateData();
            }
        });
        edtPriceProduct.setOnKeyboardListener(new QLEditText.KeyboardListener() {
            @Override
            public void onDismissKeyBoard(QLEditText keyboardEditText) {
                updateData();
            }
        });
    }

    private void updateData() {
        int quantity = edtQuantity.getValueInt();
        double eachPrice = edtPriceProduct.getValueDoubleForVND();
        double total = eachPrice * quantity;
        tvTotalPrice.setText(CommonUtil.showPriceHasCurrency(total, mProduct.currency));
    }

    @OnClick(R.id.btn_cancel)
    void onClickCancel() {
        dismiss();
    }

    @OnClick(R.id.btn_save)
    void onClickSave() {
        if (edtQuantity.getValueInt() == 0 || edtPriceProduct.getValueDoubleForVND() == 0) {
            Toast.makeText(getContext(), R.string.error_not_input_quantity_product, Toast.LENGTH_SHORT).show();
            return;
        }
        mProduct.unitImport = tvUnitProduct.getText().toString();
        mProduct.quantityImport = edtQuantity.getValueInt();
        mProduct.priceImport = edtPriceProduct.getValueDoubleForVND();
        mCallBack.onSaveValue(mProduct);
        dismiss();
    }

    @OnClick(R.id.btn_choose_unit_product)
    void onClickChooseUnitProduct() {
        SelectUnitProduct selectUnitProduct = new SelectUnitProduct(getContext());
        selectUnitProduct.showViewPopupUnitProduct(btnUnit, tvUnitProduct, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((TextView) v).getText().toString().isEmpty()) {
                    InputUnitProductDialog inputUnitProductDialog = new InputUnitProductDialog(new InputUnitProductDialog.OnClickSave() {
                        @Override
                        public void onSave(String text) {
                            tvUnitProduct.setText(text);
                            tvUnitQuantity.setText(text);
                        }
                    });
                    inputUnitProductDialog.show(getFragmentManager(), "InputUnitProductDialog");
                } else {
                    tvUnitQuantity.setText(tvUnitProduct.getText().toString());
                }
            }
        });
    }

    public interface OnSaveListener {
        void onSaveValue(ProductChooseDto product);
    }
}
