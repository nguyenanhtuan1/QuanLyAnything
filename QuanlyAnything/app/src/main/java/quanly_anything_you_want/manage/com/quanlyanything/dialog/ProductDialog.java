package quanly_anything_you_want.manage.com.quanlyanything.dialog;

import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseDialogFragment;
import quanly_anything_you_want.manage.com.quanlyanything.custom_view.QLEditText;
import quanly_anything_you_want.manage.com.quanlyanything.images.ImageLoader;

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

    @Override
    protected int layoutID() {
        return R.layout.layout_dialog_product;
    }

    @Override
    protected void initData() {
        ImageLoader.loadImagePhoto(getContext(),
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS-T8iovMyAqb7byyqo1PVU2s_bGIbQJPVDS5q11VknS5OjNAxg",imvPhotoProduct);
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
    }

    @OnClick(R.id.btn_choose_unit_sl)
    void onClickChooseUnitTotalQuantity() {
    }

    @OnClick(R.id.btn_choose_unit_wholesale)
    void onClickChooseUnit() {
    }

    @OnClick(R.id.btn_choose_unit_retail)
    void onClickChooseUnitRetail() {
    }

    @OnClick(R.id.btn_scan_product)
    void onClickScanProduct() {
    }
}
