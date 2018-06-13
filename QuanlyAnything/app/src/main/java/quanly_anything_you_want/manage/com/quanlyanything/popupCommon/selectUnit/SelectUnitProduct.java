package quanly_anything_you_want.manage.com.quanlyanything.popupCommon.selectUnit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.utils.CommonUtil;

import static android.widget.ListPopupWindow.WRAP_CONTENT;

public class SelectUnitProduct extends PopupWindow {
    private LinearLayout layoutParent;
    private Context mContext;

    public SelectUnitProduct(Context context) {
        super(context);
        mContext = context;
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setFocusable(true);
        ScrollView scrollView = (ScrollView) LayoutInflater.from(context).inflate(R.layout.layout_popup_pick_unit, null, false);
        layoutParent = (LinearLayout) scrollView.getChildAt(0);
        setContentView(scrollView);
    }

    public void showViewPopupUnitCurrency(View anchorView, final TextView key, final View.OnClickListener mListener) {
        setWidth(WRAP_CONTENT);
        setHeight(WRAP_CONTENT);

        for (int i = 0; i < getListUnitCurrency().size(); i++) {
            final LinearLayout ln = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_item_unit, null, false);
            final TextView tv = (TextView) ln.getChildAt(0);
            tv.setText(getListUnitCurrency().get(i));
            tv.setSelected(key.getText().toString().equalsIgnoreCase(getListUnitCurrency().get(i)));
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    key.setText(tv.getText().toString());
                    mListener.onClick(v);
                    dismiss();
                }
            });
            layoutParent.addView(ln);
        }
        showAsDropDown(anchorView, -(anchorView.getWidth() * 2 - 20), -anchorView.getHeight());
    }

    public void showViewPopupUnitProduct(View anchorView, final TextView key, final View.OnClickListener mListener) {
        setWidth(WRAP_CONTENT);
        setHeight(WRAP_CONTENT);

        for (int i = 0; i < getListUnitProduct().size(); i++) {
            @SuppressLint("InflateParams") final LinearLayout ln = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_item_unit, null, false);
            final TextView tv = (TextView) ln.getChildAt(0);
            tv.setText(getListUnitProduct().get(i));
            if (!getListUnitProduct().get(i).isEmpty()) {
                tv.setSelected(key.getText().toString().equalsIgnoreCase(getListUnitProduct().get(i)));
            }
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    key.setText(tv.getText().toString());
                    mListener.onClick(v);
                    dismiss();
                }
            });
            layoutParent.addView(ln);
        }
        showAsDropDown(anchorView, -(anchorView.getWidth() * 2 - 20), -anchorView.getHeight());
    }

    private List<String> getListUnitCurrency() {
        List<String> list = new ArrayList<>();
        list.add("VND");
        list.add("USD");
        return list;
    }

    private List<String> getListUnitProduct() {
        List<String> list = new ArrayList<>();
        list.add("Thùng");
        list.add("Hộp");
        list.add("Chai");
        list.add("Lọ");
        list.add("Lon");
        list.add("Gói");
        list.add("Túi");
        list.add("Kg");
        list.add("");
        return list;
    }
}
