package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseActivity;
import quanly_anything_you_want.manage.com.quanlyanything.base.ViewPagerAdapter;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Sell.SellTapHoaFragment;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.history.HistoryTapHoaFragment;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.report.ReportTapHoaFragment;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.storeShop.StoreShopTapHoaFragment;

public class TapHoaActivity extends BaseActivity implements TapHoaContact.View {

    @BindView(R.id.btn_store)
    Button btnStore;

    @BindView(R.id.btn_sell)
    Button btnSell;

    @BindView(R.id.btn_report)
    Button btnReport;

    @BindView(R.id.btn_history)
    Button btnHistory;
    @BindView(R.id.view_pager_tap_hoa)
    ViewPager vPager;

    private TapHoaPresenter optionManagePresenter;
    private ViewPagerAdapter vPagerAdapter;
    List<Fragment> listFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.tap_hoa_activity);
        super.onCreate(savedInstanceState);
        optionManagePresenter = new TapHoaPresenter(this);
    }

    @Override
    public void onInitData() {
        btnStore.performClick();
        listFragment = new ArrayList<>();
        listFragment.add(new StoreShopTapHoaFragment());
        listFragment.add(new SellTapHoaFragment());
        listFragment.add(new ReportTapHoaFragment());
        listFragment.add(new HistoryTapHoaFragment());
        vPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), listFragment);
        vPager.setAdapter(vPagerAdapter);
    }

    @Override
    public void onInitListener() {

    }

    @OnClick(R.id.btn_back)
    void onClickBack() {
        finish();
    }

    @OnClick(R.id.btn_store)
    void onClickStore() {
        if (!btnStore.isSelected())
            setOnSelectButton(true, false, false, false);
    }

    @OnClick(R.id.btn_sell)
    void onClickSell() {
        if (!btnSell.isSelected())
            setOnSelectButton(false, true, false, false);
    }

    @OnClick(R.id.btn_report)
    void onClickReport() {
        if (!btnReport.isSelected())
            setOnSelectButton(false, false, true, false);
    }

    @OnClick(R.id.btn_history)
    void onClickHistory() {
        if (!btnHistory.isSelected())
            setOnSelectButton(false, false, false, true);
    }

    private void setOnSelectButton(boolean selectStore, boolean selectSell, boolean selectReport, boolean selectHistory) {
        btnStore.setSelected(selectStore);
        btnSell.setSelected(selectSell);
        btnReport.setSelected(selectReport);
        btnHistory.setSelected(selectHistory);
    }

}
