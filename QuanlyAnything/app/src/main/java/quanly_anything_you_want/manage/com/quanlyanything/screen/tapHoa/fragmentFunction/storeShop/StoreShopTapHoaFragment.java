package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.storeShop;

import android.content.Intent;
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
import quanly_anything_you_want.manage.com.quanlyanything.model.ProductTapHoa;
import quanly_anything_you_want.manage.com.quanlyanything.screen.detail_product.DetailProductActivity;
import quanly_anything_you_want.manage.com.quanlyanything.screen.scanActivity.CustomScannerActivity;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.storeShop.adapter.ListStoreAdapter;
import quanly_anything_you_want.manage.com.quanlyanything.utils.AppConstants;
import quanly_anything_you_want.manage.com.quanlyanything.utils.CommonUtil;

import static android.app.Activity.RESULT_OK;

public class StoreShopTapHoaFragment extends BaseFragment implements StoreShopTapHoaContact.View {
    @BindView(R.id.edt_search_product)
    EditText edtSearch;

    @BindView(R.id.rcv_list_product)
    RecyclerView lvProduct;

    @BindView(R.id.btn_new_product)
    Button btnNewProduct;

    ListStoreAdapter adapter;
    StoreShopTapHoaPresenter mPresenter;

    private boolean isScanFromThis;
    private int mPosition;

    @Override
    protected void onInitData() {
        mPresenter = new StoreShopTapHoaPresenter(this);

        adapter = new ListStoreAdapter(getContext(), mPresenter.getListProduct(), new ListStoreAdapter.OnItemClickListener() {
            @Override
            public void onClickDetail(final int position) {
                mPosition = position;
                Intent intent = new Intent(getContext(), DetailProductActivity.class);
                intent.putExtra(AppConstants.KEY_DETAIL_PRODUCT, mPresenter.getListProduct().get(position));
                startActivityForResult(intent, AppConstants.REQUEST_EDIT_PRODUCT);
            }
        });

        lvProduct.setLayoutManager(new LinearLayoutManager(getActivity()));
        lvProduct.setHasFixedSize(true);
        lvProduct.setAdapter(adapter);

        mPresenter.getAllProductFromServer();
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
        Intent intent = new Intent(getContext(), DetailProductActivity.class);
        startActivityForResult(intent, AppConstants.REQUEST_NEW_PRODUCT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            ProductTapHoa tapHoa = (ProductTapHoa) data.getSerializableExtra(AppConstants.KEY_DETAIL_PRODUCT);

            if (requestCode == AppConstants.REQUEST_EDIT_PRODUCT) {
                if (tapHoa.isDelete) {
                    mPresenter.deleteProduct(mPosition);
                } else {
                    mPresenter.setUpdateChangeProduct(mPosition, tapHoa);
                }
            } else if (requestCode == AppConstants.REQUEST_NEW_PRODUCT) {
                mPresenter.addItemProduct(tapHoa);
            }
        }

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

    @Override
    public void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }
}
