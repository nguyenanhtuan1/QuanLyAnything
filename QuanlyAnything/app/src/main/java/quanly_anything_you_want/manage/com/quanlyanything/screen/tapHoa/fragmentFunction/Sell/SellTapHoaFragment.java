package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Sell;

import android.content.Intent;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseFragment;
import quanly_anything_you_want.manage.com.quanlyanything.dialog.EditQuantityProductTapHoaDialog;
import quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProduct.ChooseProductActivity;
import quanly_anything_you_want.manage.com.quanlyanything.utils.AppConstants;

public class SellTapHoaFragment extends BaseFragment implements SellTapHoaContact.View {
    SellTapHoaPresenter mPresenter;

    @Override
    protected void onInitData() {
        mPresenter = new SellTapHoaPresenter(this);
    }

    @Override
    protected void onInitListener() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_sell_tap_hoa;
    }

    int mQuantityRetail;

    @OnClick(R.id.btn_edit)
    void onClickEdit() {
        EditQuantityProductTapHoaDialog dialog = new EditQuantityProductTapHoaDialog(0,1, mQuantityRetail, "Thùng", "kg",
                new EditQuantityProductTapHoaDialog.OnSaveListener() {
            @Override
            public void onSaveValue(int quantityWholesale, int quantityRetail) {
                mQuantityRetail=quantityRetail;
            }
        });
        dialog.show(getFragmentManager(), "EditQuantityProductTapHoaDialog");

    }

    @OnClick(R.id.btn_add_product)
    void onClickAddMoreProduct() {
        Intent intent = new Intent(getContext(), ChooseProductActivity.class);
        startActivityForResult(intent, AppConstants.REQUEST_CHOOSE_PRODUCT);
    }

}
