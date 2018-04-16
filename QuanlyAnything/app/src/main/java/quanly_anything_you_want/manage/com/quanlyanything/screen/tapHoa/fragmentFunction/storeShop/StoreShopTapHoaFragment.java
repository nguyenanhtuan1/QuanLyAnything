package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.storeShop;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseFragment;
import quanly_anything_you_want.manage.com.quanlyanything.dialog.ProductDialog;
import quanly_anything_you_want.manage.com.quanlyanything.model.ProductTapHoa;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.storeShop.adapter.ListStoreAdapter;

public class StoreShopTapHoaFragment extends BaseFragment implements StoreShopTapHoaContact.View {
    @BindView(R.id.edt_search_product)
    EditText edtSearch;

    @BindView(R.id.rcv_list_product)
    RecyclerView lvProduct;

    ListStoreAdapter adapter;
    StoreShopTapHoaPresenter mPresenter;
    ProductDialog dialogProduct;

    @Override
    protected void onInitData() {
        mPresenter = new StoreShopTapHoaPresenter(this);

        adapter = new ListStoreAdapter(getContext(), mPresenter.getListProduct(), new ListStoreAdapter.OnItemClickListener() {
            @Override
            public void onAddMoreQuantity(final int position) {

            }

            @Override
            public void onClickDetail(final int position) {
                dialogProduct = new ProductDialog(mPresenter.getListProduct().get(position), new ProductDialog.OnSaveListener() {
                    @Override
                    public void onSaveValue(ProductTapHoa product) {
                        mPresenter.getListProduct().set(position, product);
                        adapter.notifyItemChanged(position);
                    }
                });
                dialogProduct.show(getFragmentManager(), "ProductDialog");
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
        dialogProduct = new ProductDialog(null, new ProductDialog.OnSaveListener() {
            @Override
            public void onSaveValue(ProductTapHoa product) {
                mPresenter.getListProduct().add(product);
                adapter.notifyDataSetChanged();
            }
        });
        dialogProduct.show(getFragmentManager(), "ProductDialog");
    }

    @Override
    public void setValueBarcode(String barcode) {
        dialogProduct.setValueBarcode(barcode);
    }

    @Override
    public void onNotifyAdapterProduct() {
        adapter.notifyDataSetChanged();
    }
}
