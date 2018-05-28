package quanly_anything_you_want.manage.com.quanlyanything.dialog;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CalendarView;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseDialogFragment;
import quanly_anything_you_want.manage.com.quanlyanything.custom_view.QLEditText;
import quanly_anything_you_want.manage.com.quanlyanything.model.UserContact;
import quanly_anything_you_want.manage.com.quanlyanything.utils.DateUtils;

@SuppressLint("ValidFragment")
public class PickTimeDialog extends BaseDialogFragment {

    @BindView(R.id.time_picker)
    CalendarView timePicker;

    @BindView(R.id.tv_title_time)
    TextView tvTimeTitleTime;

    private OnClickSave mCallBack;
    private Date time;
    private int mMonth;
    private int mYear;


    public PickTimeDialog(Date timeStart, OnClickSave callBack) {
        mCallBack = callBack;
        this.time = timeStart;
    }

    Calendar calendar;

    @Override
    protected int layoutID() {
        return R.layout.layout_dialog_pick_time;
    }

    @Override
    protected void initData() {
        calendar = Calendar.getInstance();
        calendar.setTime(time);
        mMonth = calendar.get(Calendar.MONTH);
        mYear = calendar.get(Calendar.YEAR);

        long milliTime = calendar.getTimeInMillis();
        timePicker.setDate(milliTime);
        tvTimeTitleTime.setText(DateUtils.formatDateVNMonthYear(time));
    }

    @Override
    protected void initListener() {
        timePicker.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                tvTimeTitleTime.setText(DateUtils.formatDateVNMonthYear(calendar.getTime()));
                mMonth = calendar.get(Calendar.MONTH);
                mYear = calendar.get(Calendar.YEAR);
            }
        });
    }

    @OnClick(R.id.fr_plus_month)
    void setOnClickPlusMonth() {
        mMonth++;
        if (mMonth == 12) {
            mYear++;
            mMonth = 0;
        }
        calendar.set(mYear, mMonth, calendar.get(Calendar.DAY_OF_MONTH));
        timePicker.setDate(calendar.getTimeInMillis());
        tvTimeTitleTime.setText(DateUtils.formatDateVNMonthYear(calendar.getTime()));
    }

    @OnClick(R.id.fr_minute_month)
    void setOnClickMinuteMonth() {
        mMonth--;
        if (mMonth < 0) {
            mYear--;
            mMonth = 0;
        }
        calendar.set(mYear, mMonth, calendar.get(Calendar.DAY_OF_MONTH));
        timePicker.setDate(calendar.getTimeInMillis());
        tvTimeTitleTime.setText(DateUtils.formatDateVNMonthYear(calendar.getTime()));
    }

    @OnClick(R.id.btn_cancel)
    void onClickCancel() {
        dismiss();
    }

    @OnClick(R.id.btn_save)
    void onClickSave() {
        mCallBack.onSave(calendar.getTime());
        dismiss();
    }

    public interface OnClickSave {
        void onSave(Date timeStart);
    }
}
