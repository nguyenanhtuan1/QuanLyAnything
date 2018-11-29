package quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProductImport.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.custom_view.QLEditText;
import quanly_anything_you_want.manage.com.quanlyanything.dialog.InputUnitProductDialog;
import quanly_anything_you_want.manage.com.quanlyanything.images.ImageLoader;
import quanly_anything_you_want.manage.com.quanlyanything.popupCommon.selectUnit.SelectUnitProduct;
import quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProduct.adapter.ProductChooseDto;
import quanly_anything_you_want.manage.com.quanlyanything.utils.CommonUtil;

@SuppressLint("SetTextI18n")
public class PickProductImportAdapter extends RecyclerView.Adapter<PickProductImportAdapter.ViewItemHolder> {
    private Context context;
    private List<ProductChooseDto> listProduct;
    FragmentManager fragmentManager;

    public PickProductImportAdapter(Context context, List<ProductChooseDto> listProduct) {
        this.context = context;
        this.listProduct = listProduct;
    }

    @Override
    public PickProductImportAdapter.ViewItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_choose_import_produc_tap_hoa, parent, false);
        return new ViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(PickProductImportAdapter.ViewItemHolder holder, int position) {
        holder.setUpdateData(listProduct.get(position));
    }

    @Override
    public int getItemCount() {
        return listProduct != null ? listProduct.size() : 0;
    }

    class ViewItemHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imv_photo_product)
        ImageView imvPhoto;

        @BindView(R.id.tv_name_product)
        TextView tvName;

        @BindView(R.id.tv_unit_product)
        TextView tvUnitProduct;

        @BindView(R.id.tv_currency)
        TextView tvCurrency;

        @BindView(R.id.tv_total_amount)
        TextView tvTotalAmount;

        @BindView(R.id.tv_unit_quantity)
        TextView tvUnitQuantity;

        @BindView(R.id.edt_input_quantity)
        QLEditText edtQuantity;

        @BindView(R.id.edt_input_price)
        QLEditText edtPrice;

        @BindView(R.id.btn_choose_unit_product)
        Button btnUnit;
        ProductChooseDto data;

        ViewItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setUpdateData(final ProductChooseDto data) {
            this.data = data;
            edtQuantity.setShowClear(false);
            edtPrice.setShowClear(false);
            edtPrice.setInputPrice(true);
            ImageLoader.loadImagePhoto(context, data.photo, imvPhoto);
            tvName.setText(data.name != null ? data.name : "");

            edtQuantity.setOnKeyboardListener(new QLEditText.KeyboardListener() {
                @Override
                public void onDismissKeyBoard(QLEditText keyboardEditText) {
                    data.quantityImport = keyboardEditText.getValueInt();
                    tvTotalAmount.setText(CommonUtil.showPriceHasCurrency(data.quantityImport * data.priceImport));
                    itemView.setSelected(data.quantityImport > 0 && data.priceImport > 0);
                }
            });

            edtPrice.setOnKeyboardListener(new QLEditText.KeyboardListener() {
                @Override
                public void onDismissKeyBoard(QLEditText keyboardEditText) {
                    data.priceImport = keyboardEditText.getValueDoubleForVND();
                    tvTotalAmount.setText(CommonUtil.showPriceHasCurrency(data.quantityImport * data.priceImport));
                    itemView.setSelected(data.quantityImport > 0 && data.priceImport > 0);

                }
            });

            if (data.unitImport == null) {
                data.unitImport = data.unitProduct;
            }
            tvUnitProduct.setText(data.unitImport != null ? data.unitImport : "");
            tvUnitQuantity.setText(data.unitImport != null ? data.unitImport : "");

            itemView.setSelected(data.quantityImport > 0 && data.priceImport > 0);
        }

        @OnClick(R.id.btn_choose_unit_product)
        void onClickUnit() {
            SelectUnitProduct selectUnitProduct = new SelectUnitProduct(context);
            selectUnitProduct.showViewPopupUnitProduct(btnUnit, tvUnitProduct, new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (((TextView) v).getText().toString().isEmpty()) {
                        InputUnitProductDialog inputUnitProductDialog = new InputUnitProductDialog(new InputUnitProductDialog.OnClickSave() {
                            @Override
                            public void onSave(String text) {
                                tvUnitProduct.setText(text);
                                tvUnitQuantity.setText(text);
                                data.unitImport = tvUnitProduct.getText().toString();
                            }
                        });
                        inputUnitProductDialog.show(fragmentManager, "InputUnitProductDialog");
                    } else {
                        tvUnitQuantity.setText(tvUnitProduct.getText().toString());
                        data.unitImport = tvUnitProduct.getText().toString();
                    }
                }
            });
        }

    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }
}
