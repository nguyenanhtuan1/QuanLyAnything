package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Import.adapter;

import android.annotation.SuppressLint;
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

public class ProductImportAdapter extends RecyclerView.Adapter<ProductImportAdapter.ItemViewHolder> {
    private Context context;
    private List<ProductChooseDto> listProduct;
    private OnItemClickListener mCallBack;
    private boolean isHistory;

    public void setHistory(boolean history) {
        isHistory = history;
    }

    public ProductImportAdapter(Context context, List<ProductChooseDto> listProduct) {
        this.context = context;
        this.listProduct = listProduct;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_product_import, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.setUpData(listProduct.get(position));
    }

    @Override
    public int getItemCount() {
        return listProduct != null ? listProduct.size() : 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imv_photo_product)
        ImageView imvProduct;

        @BindView(R.id.tv_name_product)
        TextView tvNameProduct;

        @BindView(R.id.tv_quantity_product)
        TextView tvQuantityProduct;

        @BindView(R.id.tv_price_product)
        TextView tvPriceProduct;

        @BindView(R.id.tv_total_price_product)
        TextView tvTotalPriceProduct;

        @BindView(R.id.btn_delete_product)
        Button btnDelete;

        @BindView(R.id.btn_edit_quantity)
        Button btnEdit;

        ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @SuppressLint("SetTextI18n")
        void setUpData(ProductChooseDto data) {
            ImageLoader.loadImagePhoto(itemView.getContext(), data.photo, imvProduct);
            tvNameProduct.setText(data.name != null ? data.name : "");
            tvQuantityProduct.setText(CommonUtil.showQuantityHasUnit(data.quantityImport, data.unitImport));
            tvPriceProduct.setText(CommonUtil.showPriceHasCurrency(data.priceImport) + " / " + data.unitImport);
            tvTotalPriceProduct.setText(CommonUtil.showPriceHasCurrency(data.quantityImport * data.priceImport));
            if (isHistory) {
                btnDelete.setVisibility(View.GONE);
                btnEdit.setVisibility(View.GONE);
            }
        }

        @OnClick(R.id.btn_delete_product)
        void onClickDeleteProduct() {
            CommonUtil.delayButton(btnDelete);
            mCallBack.onClickDelete(getAdapterPosition());
        }

        @OnClick(R.id.btn_edit_quantity)
        void onClickEditQuantityProduct() {
            CommonUtil.delayButton(btnEdit);
            mCallBack.onClickEdit(getAdapterPosition());
        }
    }

    public void setOnItemClickListener(OnItemClickListener mCallBack) {
        this.mCallBack = mCallBack;
    }

    public interface OnItemClickListener {
        void onClickDelete(int position);

        void onClickEdit(int position);
    }
}
