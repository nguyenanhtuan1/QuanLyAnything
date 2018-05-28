package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.report;

import android.widget.TextView;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseFragment;
import quanly_anything_you_want.manage.com.quanlyanything.dialog.PickTimeDialog;
import quanly_anything_you_want.manage.com.quanlyanything.utils.DateUtils;

public class ReportTapHoaFragment extends BaseFragment implements ReportTapHoaContact.View {

    @BindView(R.id.tv_time_filter_from)
    TextView tvTimeFrom;

    @BindView(R.id.tv_time_filter_to)
    TextView tvTimeTo;

    @BindView(R.id.tv_total_buy)
    TextView tvTotalBy;

    @BindView(R.id.tv_total_sell)
    TextView tvTotalSell;

    @BindView(R.id.tv_title_amount)
    TextView tvTitleAmount;

    @BindView(R.id.tv_total_amount)
    TextView tvTotalAmount;

    Date dateFrom;
    Date dateTo;
    ReportTapHoaPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_report_tap_hoa;
    }

    @Override
    protected void onInitData() {
        mPresenter = new ReportTapHoaPresenter(this);
        dateFrom = DateUtils.getCurrentDate();
        dateTo = DateUtils.getCurrentDate();
    }

    @Override
    protected void onInitListener() {

    }

    @OnClick(R.id.imv_pick_time_from)
    void onPickTimeFrom() {
        PickTimeDialog dialog = new PickTimeDialog(dateFrom, new PickTimeDialog.OnClickSave() {
            @Override
            public void onSave(Date time) {
                dateFrom = time;
                tvTimeFrom.setText(DateUtils.formatDateVN(dateFrom));
            }
        });
        dialog.show(getFragmentManager(), "PickTimeDialog");
    }

    @OnClick(R.id.imv_pick_time_to)
    void onPickTimeTo() {
        PickTimeDialog dialog = new PickTimeDialog(dateTo, new PickTimeDialog.OnClickSave() {
            @Override
            public void onSave(Date time) {
                dateTo = time;
                tvTimeTo.setText(DateUtils.formatDateVN(dateTo));
            }
        });
        dialog.show(getFragmentManager(), "PickTimeDialog");
    }


}
