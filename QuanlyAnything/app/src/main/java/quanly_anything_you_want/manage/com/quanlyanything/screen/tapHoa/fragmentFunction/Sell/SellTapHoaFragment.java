package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Sell;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseFragment;
import quanly_anything_you_want.manage.com.quanlyanything.dialog.EditQuantitySellProductTapHoaDialog;
import quanly_anything_you_want.manage.com.quanlyanything.dialog.dialogPositiveNegative.DialogPositiveNegative;
import quanly_anything_you_want.manage.com.quanlyanything.model.ObjectContentList;
import quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProduct.ChooseProductActivity;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Sell.adapter.BillSellAdapter;
import quanly_anything_you_want.manage.com.quanlyanything.utils.AppConstants;

import static android.app.Activity.RESULT_OK;

public class SellTapHoaFragment extends BaseFragment implements SellTapHoaContact.View {
    @BindView(R.id.rcv_bill_list)
    RecyclerView rcvBill;

    private SellTapHoaPresenter mPresenter;
    private BillSellAdapter adapter;
    private int positionBill;

    @Override
    protected void onInitData() {
        mPresenter = new SellTapHoaPresenter(this);
        adapter = new BillSellAdapter(getContext(), mPresenter.getListBill());
        rcvBill.setLayoutManager(new LinearLayoutManager(getContext()));
        rcvBill.setHasFixedSize(false);
        rcvBill.setAdapter(adapter);
    }

    @Override
    protected void onInitListener() {
        adapter.setOnItemClickListener(new BillSellAdapter.OnClickItem() {
            @Override
            public void onClickCancelBill(final int positionParent) {
                showConfirmDialog(getString(R.string.question_delete_bill), new DialogPositiveNegative.IPositiveNegativeDialogListener() {
                    @Override
                    public void onIPositiveNegativeDialogAnswerPositive(DialogPositiveNegative dialog) {
                        mPresenter.setCancelBill(positionParent);
                        dialog.dismiss();
                    }

                    @Override
                    public void onIPositiveNegativeDialogAnswerNegative(DialogPositiveNegative dialog) {
                    }
                });
            }

            @Override
            public void onClickAddMoreProduct(int position) {
                positionBill = position;
                Intent intent = new Intent(getContext(), ChooseProductActivity.class);
                intent.putExtra(AppConstants.KEY_LIST_ID_PRODUCT, mPresenter.getListNameProduct(position));
                startActivityForResult(intent, AppConstants.REQUEST_CHOOSE_PRODUCT);
            }

            @Override
            public void onClickComplete(final int position) {
                if (mPresenter.getListBill().get(position).getListProduct().isEmpty()) {
                    Toast.makeText(getContext(), R.string.error_not_import_product, Toast.LENGTH_SHORT).show();
                    return;
                }
                showConfirmDialog(getString(R.string.question_complete_sell_product), new DialogPositiveNegative.IPositiveNegativeDialogListener() {
                    @Override
                    public void onIPositiveNegativeDialogAnswerPositive(DialogPositiveNegative dialog) {
                        mPresenter.setCompleteBill(position);
                        dialog.dismiss();
                    }

                    @Override
                    public void onIPositiveNegativeDialogAnswerNegative(DialogPositiveNegative dialog) {
                    }
                });
            }

            @Override
            public void onEditQuantityProduct(final int positionParent, final int positionChild) {
                EditQuantitySellProductTapHoaDialog dialog = new EditQuantitySellProductTapHoaDialog(mPresenter.getProductSelected(positionParent, positionChild),
                        new EditQuantitySellProductTapHoaDialog.OnSaveListener() {
                            @Override
                            public void onSaveValue(int quantityWholesale, int quantityRetail) {
                                mPresenter.setUpdateQuantityProduct(positionParent, positionChild, quantityWholesale, quantityRetail);
                            }
                        });
                dialog.show(getFragmentManager(), "EditQuantitySellProductTapHoaDialog");
            }

            @Override
            public void onClickDeleteProduct(final int positionParent, final int positionChild) {
                showConfirmDialog(getString(R.string.question_delete_product), new DialogPositiveNegative.IPositiveNegativeDialogListener() {
                    @Override
                    public void onIPositiveNegativeDialogAnswerPositive(DialogPositiveNegative dialog) {
                        mPresenter.setDeleteItemProduct(positionParent, positionChild);
                        dialog.dismiss();
                    }

                    @Override
                    public void onIPositiveNegativeDialogAnswerNegative(DialogPositiveNegative dialog) {
                    }
                });
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
        ObjectContentList product = (ObjectContentList) data.getSerializableExtra(AppConstants.RESULT_CHOOSE_PRODUCT);
        if (product != null && product.chooseDtoList != null && !product.chooseDtoList.isEmpty()) {
            mPresenter.getListBill().get(positionBill).getListProduct().addAll(product.chooseDtoList);
            adapter.notifyItemChanged(positionBill);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_sell_tap_hoa;
    }

    @Override
    public void onNotifyAllAdapterBill() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onNotifyAdapterBillAtPosition(int position) {
        adapter.notifyItemChanged(position);
    }

    @Override
    public void onNotifyAdapterBillRemoveAtPosition(int position) {
        adapter.notifyItemRemoved(position);
    }

    @Override
    public void onNotifyAdapterBillInsertedAtPosition(int position) {
        adapter.notifyItemInserted(position);
        rcvBill.scrollToPosition(0);
    }

    @OnClick(R.id.btn_add_more_bill)
    void onClickAddMoreBill() {
        mPresenter.addMoreBillItem();
    }
}
