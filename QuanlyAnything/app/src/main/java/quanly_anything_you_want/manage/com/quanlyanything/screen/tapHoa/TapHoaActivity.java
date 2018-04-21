package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseActivity;
import quanly_anything_you_want.manage.com.quanlyanything.base.ViewPagerAdapter;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.adapterTopBar.TabHeaderTopBarAdapter;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Import.ImportTapHoaFragment;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Sell.SellTapHoaFragment;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.history.HistoryTapHoaFragment;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.report.ReportTapHoaFragment;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.storeShop.StoreShopTapHoaFragment;

public class TapHoaActivity extends BaseActivity implements TapHoaContact.View {

    @BindView(R.id.view_pager_tap_hoa)
    ViewPager vPager;

    @BindView(R.id.scr_top_bar)
    RecyclerView rcvTabHeader;

    private TapHoaPresenter mPresenter;
    private ViewPagerAdapter vPagerAdapter;
    List<Fragment> listFragment;

    TabHeaderTopBarAdapter adapterTabHeader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.tap_hoa_activity);
        mPresenter = new TapHoaPresenter(this);
        super.onCreate(savedInstanceState);
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
        listFragment = new ArrayList<>();
        listFragment.add(new StoreShopTapHoaFragment());
        listFragment.add(new ImportTapHoaFragment());
        listFragment.add(new SellTapHoaFragment());
        listFragment.add(new ReportTapHoaFragment());
        listFragment.add(new HistoryTapHoaFragment());
        vPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), listFragment);
        vPager.setAdapter(vPagerAdapter);
        vPager.setOffscreenPageLimit(4);

        adapterTabHeader = new TabHeaderTopBarAdapter(this, mPresenter.getListTabHeader(),
                new TabHeaderTopBarAdapter.OnItemTabHeaderClick() {
                    @Override
                    public void onClickItem(int position) {
                        vPager.setCurrentItem(position);
                    }
                });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rcvTabHeader.setLayoutManager(layoutManager);
        rcvTabHeader.setAdapter(adapterTabHeader);
    }

    @Override
    public void onInitListener() {
        vPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 1) {
                    rcvTabHeader.smoothScrollToPosition(0);
                } else if (position == mPresenter.getListTabHeader().size() - 2) {
                    rcvTabHeader.smoothScrollToPosition(mPresenter.getListTabHeader().size() - 1);
                } else
                    rcvTabHeader.smoothScrollToPosition(position);

                adapterTabHeader.setPositionSelected(position);
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
}
