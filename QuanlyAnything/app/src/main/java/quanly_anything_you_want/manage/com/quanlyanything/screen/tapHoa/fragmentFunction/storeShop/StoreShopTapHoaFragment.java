package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.storeShop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseFragment;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.storeShop.adapter.ListStoreAdapter;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.storeShop.dialog.ProductDialog;

public class StoreShopTapHoaFragment extends BaseFragment implements StoreShopTapHoaContact.View {
    @BindView(R.id.edt_search_product)
    EditText edtSearch;

    @BindView(R.id.rcv_list_product)
    RecyclerView lvProduct;

    ListStoreAdapter adapter;
    StoreShopTapHoaPresenter mPresenter;

    @Override
    protected void onInitData() {
        mPresenter = new StoreShopTapHoaPresenter(this);

        adapter = new ListStoreAdapter(getContext(), mPresenter.getListProduct(), new ListStoreAdapter.OnItemClickListener() {
            @Override
            public void onAddMoreQuantity(int position) {

            }
        });

        lvProduct.setLayoutManager(new LinearLayoutManager(getActivity()));
        lvProduct.setHasFixedSize(true);
        lvProduct.setAdapter(adapter);

    }

    @Override
    protected void onInitListener() {
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
    protected int getLayoutId() {
        return R.layout.layout_fragment_store_shop_tap_hoa;
    }

    @OnClick(R.id.btn_new_product)
    void onClickNewProduct() {
        ProductDialog dialog = new ProductDialog();
        dialog.show(getFragmentManager(), "ProductDialog");
    }

    @Override
    public void onNotifyAdapterProduct() {
        adapter.notifyDataSetChanged();
    }
}
