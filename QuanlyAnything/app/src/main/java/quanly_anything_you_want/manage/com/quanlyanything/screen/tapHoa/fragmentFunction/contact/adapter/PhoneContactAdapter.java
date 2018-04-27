package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.contact.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.images.ImageLoader;
import quanly_anything_you_want.manage.com.quanlyanything.model.UserContact;

public class PhoneContactAdapter extends RecyclerView.Adapter<PhoneContactAdapter.ViewItemHolder> {
    private Context context;
    private List<UserContact> list;

    public PhoneContactAdapter(Context context, List<UserContact> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_contact, parent, false);
        return new ViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewItemHolder holder, int position) {
        UserContact item = list.get(position);
        holder.tvName.setText(item.name != null ? item.name : "");
        holder.tvPhone.setText(item.phone != null ? item.phone : "");
        ImageLoader.loadImagePhoto(context, item.photo, holder.imvContact);
        holder.tvDesc.setText(item.desc != null ? item.desc : "");
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class ViewItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imv_contact)
        CircleImageView imvContact;

        @BindView(R.id.tv_name_contact)
        TextView tvName;

        @BindView(R.id.tv_phone_contact)
        TextView tvPhone;

        @BindView(R.id.tv_desc)
        TextView tvDesc;

        public ViewItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallBack.onDetailContact(getAdapterPosition());
                }
            });
        }

        @OnClick(R.id.btn_delete_contact)
        void onClickDeleteContact() {
            mCallBack.onDeleteContact(getAdapterPosition());
        }

        @OnClick(R.id.btn_call_contact)
        void onClickCallContact() {
            mCallBack.onCallContact(getAdapterPosition());
        }
    }

    private OnItemClickListener mCallBack;

    public void setOnClickListener(OnItemClickListener mCallBack) {
        this.mCallBack = mCallBack;
    }

    public interface OnItemClickListener {
        void onDeleteContact(int position);

        void onDetailContact(int position);

        void onCallContact(int position);
    }
}
