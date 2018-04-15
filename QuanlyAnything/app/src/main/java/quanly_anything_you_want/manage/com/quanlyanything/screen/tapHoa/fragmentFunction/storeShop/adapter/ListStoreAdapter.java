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

    class ViewItemHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imv_photo_product)
        ImageView imvPhoto;

        @BindView(R.id.tv_name_product)
        TextView tvName;

        @BindView(R.id.tv_quantity_product)
        TextView tvQuantity;

        @BindView(R.id.tv_price_product)
        TextView tvPrice;

        ViewItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setUpdateData(ProductTapHoa data) {
            ImageLoader.loadImagePhoto(context, data.photo, imvPhoto);
            tvName.setText(data.name != null ? data.name : "");
            tvQuantity.setText(data.quantityAll + " " + data.unit);
            tvPrice.setText(CommonUtil.showPrice(data.currency, data.priceExport) + "/" + data.quantityExport + data.unit);
        }

        @OnClick(R.id.tv_add_quantity)
        void onClickAddQuantity() {
            mCallBack.onAddMoreQuantity(getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        void onAddMoreQuantity(int position);
    }
}
