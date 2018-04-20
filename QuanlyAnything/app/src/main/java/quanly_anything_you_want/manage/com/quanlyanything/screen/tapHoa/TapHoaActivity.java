package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.Button;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

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

    private TapHoaPresenter mPresenter;
    private ViewPagerAdapter vPagerAdapter;
    List<Fragment> listFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.tap_hoa_activity);
        super.onCreate(savedInstanceState);
        mPresenter = new TapHoaPresenter(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null && result.getContents() != null) {
            ((StoreShopTapHoaFragment) listFragment.get(0)).setValueBarcode(result.getContents());
        }
    }

    @Override
    public void onInitData() {
        btnStore.setSelected(true);
        listFragment = new ArrayList<>();
        listFragment.add(new StoreShopTapHoaFragment());
        listFragment.add(new SellTapHoaFragment());
        listFragment.add(new ReportTapHoaFragment());
        listFragment.add(new HistoryTapHoaFragment());
        vPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), listFragment);
        vPager.setAdapter(vPagerAdapter);
        vPager.setOffscreenPageLimit(4);
    }

    @Override
    public void onInitListener() {
        vPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setOnSelectButton(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick(R.id.btn_back)
    void onClickBack() {
        finish();
    }

    @OnClick(R.id.btn_store)
    void onClickStore() {
        if (!btnStore.isSelected())
            setOnSelectButton(true, false, false, false, 0);
    }

    @OnClick(R.id.btn_sell)
    void onClickSell() {
        if (!btnSell.isSelected())
            setOnSelectButton(false, true, false, false, 1);
    }

    @OnClick(R.id.btn_report)
    void onClickReport() {
        if (!btnReport.isSelected())
            setOnSelectButton(false, false, true, false, 2);
    }

    @OnClick(R.id.btn_history)
    void onClickHistory() {
        if (!btnHistory.isSelected())
            setOnSelectButton(false, false, false, true, 3);
    }

    private void setOnSelectButton(boolean selectStore, boolean selectSell, boolean selectReport, boolean selectHistory, int position) {
        btnStore.setSelected(selectStore);
        btnSell.setSelected(selectSell);
        btnReport.setSelected(selectReport);
        btnHistory.setSelected(selectHistory);
        vPager.setCurrentItem(position, true);
    }

    private void setOnSelectButton(int position) {
        btnStore.setSelected(false);
        btnSell.setSelected(false);
        btnReport.setSelected(false);
        btnHistory.setSelected(false);
        switch (position) {
            case 0:
                btnStore.setSelected(true);
                break;
            case 1:
                btnSell.setSelected(true);
                break;
            case 2:
                btnReport.setSelected(true);
                break;
            case 3:
                btnHistory.setSelected(true);
                break;
        }
    }

}
