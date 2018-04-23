package quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProduct.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.custom_view.QLEditText;
import quanly_anything_you_want.manage.com.quanlyanything.images.ImageLoader;

@SuppressLint("SetTextI18n")
public class PickProductAdapter extends RecyclerView.Adapter<PickProductAdapter.ViewItemHolder> {
    private Context context;
    private List<ProductChooseDto> listProduct;

    public PickProductAdapter(Context context, List<ProductChooseDto> listProduct) {
        this.context = context;
        this.listProduct = listProduct;
    }

    @Override
    public PickProductAdapter.ViewItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_choose_produc_tap_hoa, parent, false);
        return new ViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(PickProductAdapter.ViewItemHolder holder, int position) {
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

        @BindView(R.id.tv_unit_quantity_wholesale)
        TextView tvUnitWholesale;

        @BindView(R.id.tv_unit_quantity_retail)
        TextView tvUnitRetail;

        @BindView(R.id.edt_input_quantity_wholesale)
        QLEditText edtQuantityWholesale;

        @BindView(R.id.edt_input_quantity_retail)
        QLEditText edtQuantityRetail;

        @BindView(R.id.btn_minus_wholesale)
        Button btnMinusWholesale;

        @BindView(R.id.btn_plus_wholesale)
        Button btnPlusWholesale;

        private ProductChooseDto data;

        ViewItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setUpdateData(final ProductChooseDto data) {
            this.data = data;
            edtQuantityWholesale.setShowClear(false);
            edtQuantityRetail.setShowClear(false);
//            ImageLoader.loadImagePhoto(context, data.photo, imvPhoto);
            ImageLoader.loadImagePhoto(context, "https://taphoahoanganh.com/wp-content/uploads/2017/08/sua-dac-ong-tho-3.jpg", imvPhoto);
            tvName.setText(data.name != null ? data.name : "");
            tvUnitWholesale.setText(data.unitWholesale);
            tvUnitRetail.setText(data.unitRetail);
            edtQuantityWholesale.setEnabled(!(data.priceWholesale == 0));

            btnMinusWholesale.setEnabled(!(data.priceWholesale == 0));
            btnPlusWholesale.setEnabled(!(data.priceWholesale == 0));

            btnMinusWholesale.setBackground(data.priceWholesale == 0 ? context.getDrawable(R.drawable.bg_btn_grey_circle) : context.getDrawable(R.drawable.bg_btn_pressed_blue_bluetrans_circle));
            btnPlusWholesale.setBackground(data.priceWholesale == 0 ? context.getDrawable(R.drawable.bg_btn_grey_circle) : context.getDrawable(R.drawable.bg_btn_pressed_blue_bluetrans_circle));

            edtQuantityWholesale.setOnKeyboardListener(new QLEditText.KeyboardListener() {
                @Override
                public void onDismissKeyBoard(QLEditText keyboardEditText) {
                    data.quantityWholesale = keyboardEditText.getValueInt();
                }
            });

            edtQuantityRetail.setOnKeyboardListener(new QLEditText.KeyboardListener() {
                @Override
                public void onDismissKeyBoard(QLEditText keyboardEditText) {
                    data.quantityRetail = keyboardEditText.getValueInt();
                }
            });
        }

        @OnClick(R.id.btn_minus_wholesale)
        void onClickMinusWholesale() {
            if (data.quantityWholesale == 0) return;
            data.quantityWholesale--;
            edtQuantityWholesale.setText(data.quantityWholesale != 0 ? String.valueOf(data.quantityWholesale) : "");

            itemView.setSelected(data.quantityWholesale > 0 || data.quantityRetail > 0);
        }

        @OnClick(R.id.btn_plus_wholesale)
        void onClickPlusWholesale() {
            data.quantityWholesale++;
            edtQuantityWholesale.setText(data.quantityWholesale != 0 ? String.valueOf(data.quantityWholesale) : "");

            itemView.setSelected(data.quantityWholesale > 0 || data.quantityRetail > 0);
        }

        @OnClick(R.id.btn_minus_retail)
        void onClickMinusRetail() {
            if (data.quantityRetail == 0) return;
            data.quantityRetail--;
            edtQuantityRetail.setText(data.quantityRetail != 0 ? String.valueOf(data.quantityRetail) : "");

            itemView.setSelected(data.quantityWholesale > 0 || data.quantityRetail > 0);
        }

        @OnClick(R.id.btn_plus_retail)
        void onClickPlusRetail() {
            data.quantityRetail++;
            edtQuantityRetail.setText(data.quantityRetail != 0 ? String.valueOf(data.quantityRetail) : "");

            itemView.setSelected(data.quantityWholesale > 0 || data.quantityRetail > 0);
        }
    }
}
