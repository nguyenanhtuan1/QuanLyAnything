package quanly_anything_you_want.manage.com.quanlyanything.screen.chooseContact.adapter;

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
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.model.UserContact;

@SuppressLint("SetTextI18n")
public class PickContactAdapter extends RecyclerView.Adapter<PickContactAdapter.ViewItemHolder> {
    private Context context;
    private List<UserContact> listProduct;

    public PickContactAdapter(Context context, List<UserContact> listProduct) {
        this.context = context;
        this.listProduct = listProduct;
    }

    @Override
    public PickContactAdapter.ViewItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_choose_contact, parent, false);
        return new ViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(PickContactAdapter.ViewItemHolder holder, int position) {
        holder.setUpdateData(listProduct.get(position));
    }

    @Override
    public int getItemCount() {
        return listProduct != null ? listProduct.size() : 0;
    }

    class ViewItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imv_select)
        ImageView imvSelect;

        @BindView(R.id.tv_name_contact)
        TextView tvNameContact;

        @BindView(R.id.tv_phone_contact)
        TextView tvPhoneContact;

        private UserContact data;

        ViewItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data.isSelect = !data.isSelect;
                    imvSelect.setSelected(data.isSelect);
                }
            });
        }

        void setUpdateData(final UserContact data) {
            this.data = data;
            imvSelect.setSelected(data.isSelect);
            tvNameContact.setText(data.name != null ? data.name : "");
            tvPhoneContact.setText(data.phone != null ? data.phone : "");
        }

    }
}
