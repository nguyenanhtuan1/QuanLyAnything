package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Sell.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.custom_view.QLEditText;

public class BillSellAdapter extends RecyclerView.Adapter<BillSellAdapter.ViewItemHolder> {
    private Context context;
    private List<BillSellTapHoa> sellList;

    public BillSellAdapter(Context context, List<BillSellTapHoa> sellList) {
        this.context = context;
        this.sellList = sellList;
    }

    @Override
    public ViewItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_bill_tap_hoa, parent, false);
        return new ViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewItemHolder holder, int position) {
        holder.setUpData(sellList.get(position));
    }

    @Override
    public int getItemCount() {
        return sellList != null ? sellList.size() : 0;
    }

    class ViewItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.edt_name_client)
        QLEditText edtNameClient;

        @BindView(R.id.tv_total_name_product)
        TextView tvTotalNameProduct;

        @BindView(R.id.tv_total_amount)
        TextView tvTotalAmount;

        @BindView(R.id.rcv_list_product)
        RecyclerView rcvProduct;

        ViewItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setUpData(BillSellTapHoa data) {
            edtNameClient.setText(data.nameClient != null ? data.nameClient : "");
            tvTotalNameProduct.setText(data.getNameTotalProduct());
            tvTotalAmount.setText(data.getTextTotalAmountProduct());
            ProductSelectedAdapter adapter = new ProductSelectedAdapter(context, data.getListProduct());
            rcvProduct.setLayoutManager(new LinearLayoutManager(context));
            rcvProduct.setHasFixedSize(true);
            rcvProduct.setAdapter(adapter);

            adapter.setOnItemClickListener(new ProductSelectedAdapter.OnClickListener() {
                @Override
                public void onClickEditQuantityProduct(int positionChild) {
                    mCallBack.onEditQuantityProduct(getAdapterPosition(), positionChild);
                }

                @Override
                public void onClickRemove(int positionChild) {
                    mCallBack.onClickDeleteProduct(getAdapterPosition(), positionChild);
                }
            });

        }

        @OnClick(R.id.btn_completed)
        void onClickComplete() {
            mCallBack.onClickComplete(getAdapterPosition());
        }

        @OnClick(R.id.btn_add_product)
        void onClickAddProduct() {
            mCallBack.onClickAddMoreProduct(getAdapterPosition());
        }

        @OnClick(R.id.btn_cancel_bill)
        void onClickCancelBill() {
            mCallBack.onClickCancelBill(getAdapterPosition());
        }
    }

    private OnClickItem mCallBack;

    public interface OnClickItem {

        void onClickCancelBill(int positionParent);

        void onClickAddMoreProduct(int position);

        void onClickComplete(int position);

        void onEditQuantityProduct(int positionParent, int positionChild);

        void onClickDeleteProduct(int positionParent, int positionChild);

    }

    public void setOnItemClickListener(OnClickItem mCallBack) {
        this.mCallBack = mCallBack;
    }
}
