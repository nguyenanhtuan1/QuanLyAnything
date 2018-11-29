package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.history.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Sell.adapter.BillSellProduct;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Sell.adapter.ProductSelectedAdapter;
import quanly_anything_you_want.manage.com.quanlyanything.utils.CommonUtil;
import quanly_anything_you_want.manage.com.quanlyanything.utils.DateUtils;

public class SellHistoryAdapter extends RecyclerView.Adapter<SellHistoryAdapter.ViewItemHolder> {
    private Context context;
    private List<HistoryBillSellProduct> list;

    public SellHistoryAdapter(Context context, List<HistoryBillSellProduct> mSections) {
        this.context = context;
        this.list = mSections;
    }

    @Override
    public ViewItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_sell_history, parent, false);
        return new ViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewItemHolder holder, int position) {
        holder.setupData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }


    public class ViewItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_date_import)
        TextView tvDate;

        @BindView(R.id.tv_name_seller)
        TextView tvNameSeller;

        @BindView(R.id.tv_total_name_product)
        TextView tvTotalNameProduct;

        @BindView(R.id.tv_total_amount)
        TextView tvTotalAmount;

        @BindView(R.id.imv_drop_down)
        ImageView imvDropDown;

        @BindView(R.id.rcv_product)
        RecyclerView lvProduct;

        HistoryBillSellProduct item;
        ProductSelectedAdapter adapter;

        public ViewItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setupData(HistoryBillSellProduct item) {
            this.item = item;
            tvDate.setText(DateUtils.formatFullDateVN(new Date(item.id)));
            tvNameSeller.setText(item.nameClient != null ? item.nameClient : "");
            tvTotalNameProduct.setText(item.getNameTotalProduct());
            tvTotalAmount.setText(item.getTextTotalAmountProduct());
            imvDropDown.setRotation(item.isShowProduct ? -90 : 0);

            adapter = new ProductSelectedAdapter(context, item.getListProduct());
            adapter.setShowHistory(true);
            lvProduct.setHasFixedSize(true);
            lvProduct.setLayoutManager(new LinearLayoutManager(context));
            lvProduct.setAdapter(item.isShowProduct ? adapter : null);
        }

        @OnClick(R.id.btn_delete_import_history)
        void onClickDelete() {
            mCallBack.onItemDelete(getAdapterPosition());
        }

        @OnClick(R.id.btn_show_product)
        void onClickShowProduct() {
            item.isShowProduct = !item.isShowProduct;
            lvProduct.setAdapter(item.isShowProduct ? adapter : null);
            imvDropDown.setRotation(item.isShowProduct ? -90 : 0);
        }
    }

    private OnItemClickListener mCallBack;

    public void setOnItemClickListener(OnItemClickListener callBack) {
        mCallBack = callBack;
    }

    public interface OnItemClickListener {
        void onItemDelete(int positionHeader);

    }
}
