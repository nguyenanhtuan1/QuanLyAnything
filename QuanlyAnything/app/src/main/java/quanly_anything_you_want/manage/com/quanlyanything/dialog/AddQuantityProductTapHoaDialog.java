package quanly_anything_you_want.manage.com.quanlyanything.dialog;

import android.annotation.SuppressLint;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseDialogFragment;
import quanly_anything_you_want.manage.com.quanlyanything.custom_view.QLEditText;
import quanly_anything_you_want.manage.com.quanlyanything.model.ProductTapHoa;
import quanly_anything_you_want.manage.com.quanlyanything.utils.CommonUtil;

@SuppressLint("ValidFragment")
public class AddQuantityProductTapHoaDialog extends BaseDialogFragment {
    @BindView(R.id.tv_unit_quantity_product)
    TextView tvUnitQuantityProduct;

    @BindView(R.id.edt_input_quantity)
    QLEditText edtInputQuantity;

    @BindView(R.id.edt_total_price)
    QLEditText edtTotalPrice;

    @BindView(R.id.edt_input_name_seller)
    QLEditText edtNameSeller;

    @BindView(R.id.tv_unit_currency)
    TextView tvUnitCurrency;

    private ProductTapHoa mProduct;
    private OnSaveListener mCallBack;

    public AddQuantityProductTapHoaDialog(ProductTapHoa mProduct, OnSaveListener mCallBack) {
        this.mProduct = mProduct;
        this.mCallBack = mCallBack;
    }

    @Override
    protected int layoutID() {
        return R.layout.layout_dialog_add_quantity_product_tap_hoa;
    }

    @Override
    protected void initData() {
        edtInputQuantity.setShowClear(false);
        edtTotalPrice.setShowClear(false);

        tvUnitQuantityProduct.setText(mProduct.unitTotalQuantity);
        tvUnitCurrency.setText(mProduct.currency);
        edtTotalPrice.setInputPrice(true);
        edtTotalPrice.setCurrencyVN(mProduct.currency);
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
        if (edtInputQuantity.getText().toString().isEmpty() || edtTotalPrice.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), R.string.error_not_input_quantity_product, Toast.LENGTH_SHORT).show();
            return;
        }

        int quantity = Integer.valueOf(edtInputQuantity.getText().toString());
        double totalPrice;

        if (CommonUtil.isCurrencyVND(mProduct.currency)) {
            totalPrice = Double.valueOf(edtTotalPrice.getText().toString().replace(".", ""));
        } else {
            totalPrice = Double.valueOf(edtTotalPrice.getText().toString().replace(".", "").replace(",", "."));
        }
        mCallBack.onSaveValue(quantity, totalPrice, edtNameSeller.getText().toString());
        dismiss();
    }

    public interface OnSaveListener {
        void onSaveValue(int quantity, double price, String seller);
    }
}
