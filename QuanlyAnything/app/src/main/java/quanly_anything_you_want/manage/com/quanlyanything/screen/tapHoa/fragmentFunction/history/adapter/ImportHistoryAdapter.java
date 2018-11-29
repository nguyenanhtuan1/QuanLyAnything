package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.history.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Import.adapter.BillImportProduct;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Import.adapter.ProductImportAdapter;
import quanly_anything_you_want.manage.com.quanlyanything.utils.CommonUtil;
import quanly_anything_you_want.manage.com.quanlyanything.utils.DateUtils;

public class ImportHistoryAdapter extends RecyclerView.Adapter<ImportHistoryAdapter.ViewItemHolder> {
    private Context context;
    private List<BillImportProduct> list;

    public ImportHistoryAdapter(Context context, List<BillImportProduct> mSections) {
        this.context = context;
        this.list = mSections;
    }

    @Override
    public ImportHistoryAdapter.ViewItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_import_history, parent, false);
        return new ViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ImportHistoryAdapter.ViewItemHolder holder, int position) {
        holder.setUpData(list.get(position));
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
        @BindView(R.id.btn_delete_import_history)
        Button btnDelete;

        BillImportProduct itemBill;
        ProductImportAdapter adapter;

        public ViewItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        void setUpData(BillImportProduct data) {
            itemBill = data;
            tvDate.setText(DateUtils.formatFullDateVN(new Date(itemBill.id)));
            tvNameSeller.setText(itemBill.nameSeller != null ? itemBill.nameSeller : "");
            tvTotalNameProduct.setText(itemBill.getNameTotalProduct());
            tvTotalAmount.setText(itemBill.getTotalAmountProduct());
            imvDropDown.setRotation(itemBill.isShowProduct ? -90 : 0);

            adapter = new ProductImportAdapter(context, itemBill.getListProduct());
            adapter.setHistory(true);
            lvProduct.setLayoutManager(new LinearLayoutManager(context));
            lvProduct.setHasFixedSize(true);
            lvProduct.setAdapter(data.isShowProduct ? adapter : null);
        }

        @OnClick(R.id.btn_delete_import_history)
        void onClickDelete() {
            CommonUtil.delayButton(btnDelete);
            mCallBack.onItemDelete(getAdapterPosition());
        }

        @OnClick(R.id.btn_show_product)
        void onClickShowProduct() {
            itemBill.isShowProduct = !itemBill.isShowProduct;
            lvProduct.setAdapter(itemBill.isShowProduct ? adapter : null);
            imvDropDown.setRotation(itemBill.isShowProduct ? -90 : 0);
        }
    }

    private ImportHistoryAdapter.OnItemClickListener mCallBack;

    public void setOnItemClickListener(ImportHistoryAdapter.OnItemClickListener callBack) {
        mCallBack = callBack;
    }

    public interface OnItemClickListener {
        void onItemDelete(int positionHeader);

    }
}