package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Sell.adapter;

import android.content.Context;
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
import quanly_anything_you_want.manage.com.quanlyanything.images.ImageLoader;
import quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProduct.adapter.ProductChooseDto;
import quanly_anything_you_want.manage.com.quanlyanything.utils.CommonUtil;

public class ProductSelectedAdapter extends RecyclerView.Adapter<ProductSelectedAdapter.ViewItemHolder> {
    private Context context;
    private List<ProductChooseDto> listProduct;
    private boolean isShowHistory;

    public ProductSelectedAdapter(Context context, List<ProductChooseDto> listProduct) {
        this.context = context;
        this.listProduct = listProduct;
    }

    public void setShowHistory(boolean showHistory) {
        isShowHistory = showHistory;
    }

    @Override
    public ProductSelectedAdapter.ViewItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_produc_selected, parent, false);
        return new ViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductSelectedAdapter.ViewItemHolder holder, int position) {
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

        @BindView(R.id.tv_price_wholesale)
        TextView tvPriceWholesale;

        @BindView(R.id.tv_quantity_wholesale_product)
        TextView tvQuantityWholesale;

        @BindView(R.id.tv_price_retail)
        TextView tvPriceRetail;

        @BindView(R.id.tv_quantity_retail_product)
        TextView tvQuantityRetail;

        @BindView(R.id.btn_edit_quantity)
        Button btnEdit;

        @BindView(R.id.btn_delete_product)
        Button btnDelete;

        private ProductChooseDto data;

        ViewItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setUpdateData(final ProductChooseDto data) {
            this.data = data;
            ImageLoader.loadImagePhoto(context, data.photo, imvPhoto);
            tvName.setText(data.name != null ? data.name : "");
            tvPriceWholesale.setText(data.priceWholesale != 0 ? CommonUtil.showPriceHasCurrency(data.priceWholesale) + " / 1 " + data.unitWholesale : "");
            tvPriceRetail.setText(data.priceRetail != 0 ? CommonUtil.showPriceHasCurrency(data.priceRetail) + " / 1 " + data.unitRetail : "");

            StringBuilder stringWholesale = new StringBuilder();
            stringWholesale.append(data.quantityWholesale);
            stringWholesale.append(data.unitWholesale);
            stringWholesale.append(" = ");
            stringWholesale.append(CommonUtil.showPriceHasCurrency(data.quantityWholesale * data.priceWholesale));
            tvQuantityWholesale.setText(data.quantityWholesale != 0 ? stringWholesale : "");

            StringBuilder stringRetail = new StringBuilder();
            stringRetail.append(data.quantityRetail);
            stringRetail.append(data.unitRetail);
            stringRetail.append(" = ");
            stringRetail.append(CommonUtil.showPriceHasCurrency(data.quantityRetail * data.priceRetail));
            tvQuantityRetail.setText(data.quantityRetail != 0 ? stringRetail : "");

            if (isShowHistory){
                btnEdit.setVisibility(View.GONE);
                btnDelete.setVisibility(View.GONE);
            }
        }

        @OnClick(R.id.btn_edit_quantity)
        void onClickEditQuantity() {
            CommonUtil.delayButton(btnEdit);
            mCallBack.onClickEditQuantityProduct(getAdapterPosition());
        }

        @OnClick(R.id.btn_delete_product)
        void onClickDelete() {
            CommonUtil.delayButton(btnDelete);
            mCallBack.onClickRemove(getAdapterPosition());
        }

    }

    OnClickListener mCallBack;

    public interface OnClickListener {
        void onClickEditQuantityProduct(int position);

        void onClickRemove(int position);
    }

    public void setOnItemClickListener(OnClickListener mCallBack) {
        this.mCallBack = mCallBack;
    }
}
