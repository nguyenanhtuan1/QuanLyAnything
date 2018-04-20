package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.storeShop;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.google.zxing.integration.android.IntentIntegrator;

import butterknife.BindView;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseFragment;
import quanly_anything_you_want.manage.com.quanlyanything.dialog.AddQuantityProductTapHoaDialog;
import quanly_anything_you_want.manage.com.quanlyanything.dialog.ProductDialog;
import quanly_anything_you_want.manage.com.quanlyanything.model.ProductTapHoa;
import quanly_anything_you_want.manage.com.quanlyanything.screen.scanActivity.CustomScannerActivity;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.storeShop.adapter.ListStoreAdapter;
import quanly_anything_you_want.manage.com.quanlyanything.utils.CommonUtil;

public class StoreShopTapHoaFragment extends BaseFragment implements StoreShopTapHoaContact.View {
    @BindView(R.id.edt_search_product)
    EditText edtSearch;

    @BindView(R.id.rcv_list_product)
    RecyclerView lvProduct;

    @BindView(R.id.btn_new_product)
    Button btnNewProduct;

    ListStoreAdapter adapter;
    StoreShopTapHoaPresenter mPresenter;
    ProductDialog dialogProduct;

    private boolean isScanFromThis;

    @Override
    protected void onInitData() {
        mPresenter = new StoreShopTapHoaPresenter(this);

        adapter = new ListStoreAdapter(getContext(), mPresenter.getListProduct(), new ListStoreAdapter.OnItemClickListener() {
            @Override
            public void onClickDetail(final int position) {
                dialogProduct = new ProductDialog(mPresenter.getListProduct().get(position), new ProductDialog.OnSaveListener() {
                    @Override
                    public void onSaveValue(ProductTapHoa product) {
                        mPresenter.setUpdateChangeProduct(position, product);
                    }

                    @Override
                    public void onDeleteProduct() {
                        mPresenter.deleteProduct(position);
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
        CommonUtil.delayButton(btnNewProduct);
        dialogProduct = new ProductDialog(null, new ProductDialog.OnSaveListener() {
            @Override
            public void onSaveValue(ProductTapHoa product) {
                mPresenter.addItemProduct(product);
            }

            @Override
            public void onDeleteProduct() {

            }
        });
        dialogProduct.show(getFragmentManager(), "ProductDialog");
    }

    @OnClick(R.id.btn_scan_product)
    void onClickScanCode() {
        isScanFromThis = true;
        IntentIntegrator integrator = new IntentIntegrator(getActivity());
        integrator.setPrompt("");
        integrator.setCaptureActivity(CustomScannerActivity.class);
        integrator.setOrientationLocked(false);
        integrator.initiateScan();
    }

    @Override
    public void setValueBarcode(String barcode) {
        if (isScanFromThis) {
            edtSearch.setText(barcode);
            edtSearch.setSelection(edtSearch.getText().length());
            isScanFromThis = false;
        } else {
            dialogProduct.setValueBarcode(barcode);
        }
    }

    @Override
    public void onNotifyAdapterProduct() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onNotifyAdapterProductAtPosition(int position) {
        adapter.notifyItemChanged(position);
    }
}
