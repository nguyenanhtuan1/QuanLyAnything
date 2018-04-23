package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Import;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseFragment;
import quanly_anything_you_want.manage.com.quanlyanything.custom_view.QLEditText;
import quanly_anything_you_want.manage.com.quanlyanything.dialog.EditQuantityImportProductTapHoaDialog;
import quanly_anything_you_want.manage.com.quanlyanything.dialog.dialogPositiveNegative.DialogPositiveNegative;
import quanly_anything_you_want.manage.com.quanlyanything.model.ObjectContentList;
import quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProduct.adapter.ProductChooseDto;
import quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProductImport.ChooseProductImportActivity;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Import.adapter.ProductImportAdapter;
import quanly_anything_you_want.manage.com.quanlyanything.utils.AppConstants;

import static android.app.Activity.RESULT_OK;

public class ImportTapHoaFragment extends BaseFragment implements ImportTapHoaFragmentContact.View {
    @BindView(R.id.edt_name_seller)
    QLEditText edtSeller;

    @BindView(R.id.tv_total_name_product)
    TextView tvTotalNameProduct;

    @BindView(R.id.tv_total_amount)
    TextView tvTotalAmount;

    @BindView(R.id.rcv_list_product)
    RecyclerView rcvProduct;

    ImportTapHoaFragmentPresenter mPresenter;
    ProductImportAdapter adapter;

    @Override
    protected void onInitData() {
        mPresenter = new ImportTapHoaFragmentPresenter(this);

        adapter = new ProductImportAdapter(getContext(), mPresenter.getBillImportProductDto().getListProduct(), new ProductImportAdapter.OnItemClickListener() {
            @Override
            public void onClickDelete(final int position) {
                showConfirmDialog(getString(R.string.question_delete_product), new DialogPositiveNegative.IPositiveNegativeDialogListener() {
                    @Override
                    public void onIPositiveNegativeDialogAnswerPositive(DialogPositiveNegative dialog) {
                        mPresenter.setRemoveProduct(position);
                    }

                    @Override
                    public void onIPositiveNegativeDialogAnswerNegative(DialogPositiveNegative dialog) {
                    }
                });

            }

            @Override
            public void onClickEdit(final int position) {
                ProductChooseDto item = mPresenter.getBillImportProductDto().getListProduct().get(position);
                EditQuantityImportProductTapHoaDialog dialog = new EditQuantityImportProductTapHoaDialog(item,
                        new EditQuantityImportProductTapHoaDialog.OnSaveListener() {
                            @Override
                            public void onSaveValue(ProductChooseDto product) {
                                adapter.notifyItemChanged(position);
                                updateData();
                            }
                        });
                dialog.show(getFragmentManager(), "EditQuantitySellProductTapHoaDialog");
            }
        });

        rcvProduct.setLayoutManager(new LinearLayoutManager(getContext()));
        rcvProduct.setHasFixedSize(true);
        rcvProduct.setAdapter(adapter);
        rcvProduct.setNestedScrollingEnabled(false);
    }

    @Override
    protected void onInitListener() {

    }

    @Override
    public void updateData() {
        tvTotalNameProduct.setText(mPresenter.getBillImportProductDto().getNameTotalProduct());
        tvTotalAmount.setText(mPresenter.getBillImportProductDto().getTotalAmountProduct());
    }

    @Override
    public void notifyAllDataAdapter() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void notifyRemoveDataAdapter(int position) {
        adapter.notifyItemRemoved(position);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_import_product_taphoa;
    }

    @OnClick(R.id.btn_add_product)
    void onClickAddMoreProduct() {
        Intent intent = new Intent(getContext(), ChooseProductImportActivity.class);
        intent.putExtra(AppConstants.KEY_LIST_ID_PRODUCT, mPresenter.getListNameProduct());
        startActivityForResult(intent, AppConstants.REQUEST_CHOOSE_PRODUCT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
        ObjectContentList product = (ObjectContentList) data.getSerializableExtra(AppConstants.RESULT_CHOOSE_PRODUCT);
        if (product != null && product.chooseDtoList != null && !product.chooseDtoList.isEmpty()) {
            mPresenter.getBillImportProductDto().getListProduct().addAll(product.chooseDtoList);
            adapter.notifyDataSetChanged();
            updateData();
        }
    }


    @OnClick(R.id.btn_cancel_import)
    void onClickCancelImport() {
        showConfirmDialog(getString(R.string.question_cancel_import_product), new DialogPositiveNegative.IPositiveNegativeDialogListener() {
            @Override
            public void onIPositiveNegativeDialogAnswerPositive(DialogPositiveNegative dialog) {
                edtSeller.setText("");
                mPresenter.resetData();
            }

            @Override
            public void onIPositiveNegativeDialogAnswerNegative(DialogPositiveNegative dialog) {

            }
        });

    }

    @OnClick(R.id.btn_completed)
    void onClickCompleteImport() {
        showConfirmDialog(getString(R.string.question_complete_import_product), new DialogPositiveNegative.IPositiveNegativeDialogListener() {
            @Override
            public void onIPositiveNegativeDialogAnswerPositive(DialogPositiveNegative dialog) {
                mPresenter.completeImportProduct(edtSeller.getText().toString());
                edtSeller.setText("");
            }

            @Override
            public void onIPositiveNegativeDialogAnswerNegative(DialogPositiveNegative dialog) {
            }
        });

    }
}
