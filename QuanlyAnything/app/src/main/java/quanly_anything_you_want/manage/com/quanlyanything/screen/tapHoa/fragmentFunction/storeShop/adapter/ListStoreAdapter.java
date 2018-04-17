package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.storeShop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.images.ImageLoader;
import quanly_anything_you_want.manage.com.quanlyanything.model.ProductTapHoa;
import quanly_anything_you_want.manage.com.quanlyanything.utils.CommonUtil;

@SuppressLint("SetTextI18n")
public class ListStoreAdapter extends RecyclerView.Adapter<ListStoreAdapter.ViewItemHolder> {
    private Context context;
    private List<ProductTapHoa> listProduct;
    private OnItemClickListener mCallBack;

    public ListStoreAdapter(Context context, List<ProductTapHoa> listProduct, OnItemClickListener callBack) {
        this.context = context;
        this.listProduct = listProduct;
        mCallBack = callBack;
    }

    @Override
    public ListStoreAdapter.ViewItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_produc_tap_hoa, parent, false);
        return new ViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ListStoreAdapter.ViewItemHolder holder, int position) {
        holder.setUpdateData(listProduct.get(position));
    }

    @Override
    public int getItemCount() {
        return listProduct != null ? listProduct.size() : 0;
    }

    class ViewItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.imv_photo_product)
        ImageView imvPhoto;

        @BindView(R.id.tv_name_product)
        TextView tvName;

        @BindView(R.id.tv_quantity_product)
        TextView tvQuantity;

        @BindView(R.id.tv_price_retail_product)
        TextView tvPriceRetail;

        @BindView(R.id.tv_price_wholesale_product)
        TextView tvPriceWholesale;

        @BindView(R.id.tv_status_product)
        TextView tvStatusProduct;

        ViewItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        void setUpdateData(ProductTapHoa data) {
            ImageLoader.loadImagePhoto(context, data.photo, imvPhoto);
            tvName.setText(data.name != null ? data.name : "");
            tvQuantity.setText(data.totalQuantity + " " + data.unitTotalQuantity);
            tvPriceRetail.setText(CommonUtil.showPriceHasCurrency(data.currency, data.priceRetail) + "/ 1" + data.unitRetail);
            tvPriceWholesale.setText(CommonUtil.showPriceHasCurrency(data.currency, data.priceWholesale) + "/ 1" + data.unitWholesale);
            tvStatusProduct.setVisibility(data.status?View.GONE:View.VISIBLE);
        }

        @OnClick(R.id.tv_add_quantity)
        void onClickAddQuantity() {
            mCallBack.onAddMoreQuantity(getAdapterPosition());
        }

        @Override
        public void onClick(View v) {
            if (v == itemView) {
                mCallBack.onClickDetail(getAdapterPosition());
            }
        }
    }

    public interface OnItemClickListener {
        void onAddMoreQuantity(int position);

        void onClickDetail(int position);
    }
}
