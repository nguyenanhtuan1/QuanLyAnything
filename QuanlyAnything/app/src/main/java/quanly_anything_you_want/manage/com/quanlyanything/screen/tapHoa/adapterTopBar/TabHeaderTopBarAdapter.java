package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.adapterTopBar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import quanly_anything_you_want.manage.com.quanlyanything.R;

public class TabHeaderTopBarAdapter extends RecyclerView.Adapter<TabHeaderTopBarAdapter.ViewItemHolder> {
    private Context context;
    private List<String> list;
    private int positionSelected = 0;

    public TabHeaderTopBarAdapter(Context context, List<String> list, OnItemTabHeaderClick mCallBack) {
        this.context = context;
        this.list = list;
        this.mCallBack = mCallBack;
    }

    public void setPositionSelected(int positionSelected) {
        this.positionSelected = positionSelected;
        notifyDataSetChanged();
    }

    @Override
    public ViewItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_tab_header, parent, false);
        return new ViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewItemHolder holder, final int position) {
        Button button = (Button) holder.itemView;
        button.setText(list.get(position));
        button.setSelected(position == positionSelected);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onClickItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class ViewItemHolder extends RecyclerView.ViewHolder {
        public ViewItemHolder(View itemView) {
            super(itemView);
        }
    }

    OnItemTabHeaderClick mCallBack;

    public interface OnItemTabHeaderClick {
        void onClickItem(int position);
    }
}
