package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Import.adapter;

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
import quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProduct.adapter.ProductChooseDto;
import quanly_anything_you_want.manage.com.quanlyanything.utils.CommonUtil;

public class ProductImportAdapter extends RecyclerView.Adapter<ProductImportAdapter.ItemViewHolder> {
    private Context context;
    private List<ProductChooseDto> listProduct;
    private OnItemClickListener mCallBack;

    public ProductImportAdapter(Context context, List<ProductChooseDto> listProduct, OnItemClickListener mCallBack) {
        this.context = context;
        this.listProduct = listProduct;
        this.mCallBack = mCallBack;
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


        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @SuppressLint("SetTextI18n")
        void setUpData(ProductChooseDto data) {
            tvNameProduct.setText(data.name != null ? data.name : "");
            tvQuantityProduct.setText(CommonUtil.showQuantityHasUnit(data.quantityImport, data.unitImport));
            tvPriceProduct.setText(CommonUtil.showPriceHasCurrency(data.priceImport, data.currency) + " / " + data.unitImport);
            tvTotalPriceProduct.setText(CommonUtil.showPriceHasCurrency(data.quantityImport * data.priceImport, data.currency));
        }

        @OnClick(R.id.btn_delete_product)
        void onClickDeleteProduct() {
            mCallBack.onClickDelete(getAdapterPosition());
        }

        @OnClick(R.id.btn_edit_quantity)
        void onClickEditQuantityProduct() {
            mCallBack.onClickEdit(getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        void onClickDelete(int position);

        void onClickEdit(int position);
    }
}
