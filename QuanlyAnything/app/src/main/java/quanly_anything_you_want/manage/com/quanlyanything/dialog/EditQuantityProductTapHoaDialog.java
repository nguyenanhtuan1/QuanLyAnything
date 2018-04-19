package quanly_anything_you_want.manage.com.quanlyanything.dialog;

import android.annotation.SuppressLint;
import android.text.InputType;
import android.view.View;
import android.widget.LinearLayout;
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
public class EditQuantityProductTapHoaDialog extends BaseDialogFragment {
    @BindView(R.id.tv_unit_quantity_wholesale)
    TextView tvUnitQuantityWholesale;

    @BindView(R.id.tv_unit_quantity_retail)
    TextView tvUnitQuantityRetail;

    @BindView(R.id.edt_input_quantity_wholesale)
    QLEditText edtQuantityWholesale;

    @BindView(R.id.edt_input_quantity_retail)
    QLEditText edtQuantityRetail;

    @BindView(R.id.ln_content_wholesale)
    LinearLayout lnContentWholesale;

    private OnSaveListener mCallBack;
    int quantityWholesale;
    int quantityRetail;
    String unitWholesale;
    String unitRetail;
    double priceWholesale;

    public EditQuantityProductTapHoaDialog(double priceWholesale, int quantityWholesale, int quantityRetail, String unitWholesale, String unitRetail, OnSaveListener mCallBack) {
        this.mCallBack = mCallBack;
        this.quantityWholesale = quantityWholesale;
        this.quantityRetail = quantityRetail;
        this.unitWholesale = unitWholesale;
        this.unitRetail = unitRetail;
        this.priceWholesale = priceWholesale;
    }

    @Override
    protected int layoutID() {
        return R.layout.layout_dialog_edit_quantity_product_tap_hoa;
    }

    @Override
    protected void initData() {
        edtQuantityWholesale.setShowClear(false);
        edtQuantityRetail.setShowClear(false);

        lnContentWholesale.setVisibility(priceWholesale != 0 ? View.VISIBLE : View.GONE);

        tvUnitQuantityWholesale.setText(unitWholesale != null ? unitWholesale : "");
        tvUnitQuantityRetail.setText(unitRetail != null ? unitRetail : "");

        edtQuantityWholesale.setText(quantityWholesale != 0 ? String.valueOf(quantityWholesale) : "");
        edtQuantityRetail.setText(quantityRetail != 0 ? String.valueOf(quantityRetail) : "");
    }

    @Override
    protected void initListener() {

    }

    @OnClick(R.id.btn_minus_wholesale)
    void onClickMinusWholesale() {
        if (quantityWholesale == 0) return;
        quantityWholesale--;
        edtQuantityWholesale.setText(quantityWholesale != 0 ? String.valueOf(quantityWholesale) : "");
    }

    @OnClick(R.id.btn_plus_wholesale)
    void onClickPlusWholesale() {
        quantityWholesale++;
        edtQuantityWholesale.setText(quantityWholesale != 0 ? String.valueOf(quantityWholesale) : "");
    }

    @OnClick(R.id.btn_minus_retail)
    void onClickMinusRetail() {
        if (quantityRetail == 0) return;
        quantityRetail--;
        edtQuantityRetail.setText(quantityRetail != 0 ? String.valueOf(quantityRetail) : "");

    }

    @OnClick(R.id.btn_plus_retail)
    void onClickPlusRetail() {
        quantityRetail++;
        edtQuantityRetail.setText(quantityRetail != 0 ? String.valueOf(quantityRetail) : "");

    }

    @OnClick(R.id.btn_cancel)
    void onClickCancel() {
        dismiss();
    }

    @OnClick(R.id.btn_save)
    void onClickSave() {
        if (edtQuantityWholesale.getText().toString().isEmpty() && edtQuantityRetail.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), R.string.error_not_input_quantity_product, Toast.LENGTH_SHORT).show();
            return;
        }

        int quantityWhole = 0;
        if (!edtQuantityWholesale.getText().toString().isEmpty()) {
            quantityWhole = Integer.valueOf(edtQuantityWholesale.getText().toString());
        }

        int quantityRetail = 0;
        if (!edtQuantityRetail.getText().toString().isEmpty()) {
            quantityRetail = Integer.valueOf(edtQuantityRetail.getText().toString());
        }

        mCallBack.onSaveValue(quantityWhole, quantityRetail);
        dismiss();
    }

    public interface OnSaveListener {
        void onSaveValue(int quantityWholesale, int quantityRetail);
    }
}
