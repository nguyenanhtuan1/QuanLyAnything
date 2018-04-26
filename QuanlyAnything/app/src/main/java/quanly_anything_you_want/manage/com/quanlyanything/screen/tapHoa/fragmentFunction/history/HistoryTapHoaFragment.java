package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.history;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import org.zakariya.stickyheaders.StickyHeaderLayoutManager;

import butterknife.BindView;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseFragment;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.history.adapter.ImportHistoryAdapter;

public class HistoryTapHoaFragment extends BaseFragment implements HistoryTapHoaContact.View {

    @BindView(R.id.btn_sell_history)
    Button btnSellHistory;

    @BindView(R.id.btn_import_history)
    Button btnImportHistory;

    @BindView(R.id.rcv_history)
    RecyclerView rcvHistory;

    HistoryTapHoaPresenter mPresenter;

    ImportHistoryAdapter adapterImport;

    @Override
    protected void onInitData() {
        mPresenter = new HistoryTapHoaPresenter(this);
        btnImportHistory.setSelected(true);

        adapterImport = new ImportHistoryAdapter(mPresenter.getListImport());
        rcvHistory.setLayoutManager(new StickyHeaderLayoutManager());
        rcvHistory.setAdapter(adapterImport);
    }

    @Override
    protected void onInitListener() {
        adapterImport.setOnItemClickListener(new ImportHistoryAdapter.OnItemClickListener() {
            @Override
            public void onItemDelete(int positionHeader) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_history_tap_hoa;
    }

    @OnClick(R.id.btn_sell_history)
    void onClickSellHistory() {
        btnSellHistory.setSelected(true);
        btnImportHistory.setSelected(false);
    }

    @OnClick(R.id.btn_import_history)
    void onClickImportHistory() {
        btnSellHistory.setSelected(false);
        btnImportHistory.setSelected(true);
    }

    @Override
    public void onNotifyAdapterImport() {
        adapterImport.notifyAllSectionsDataSetChanged();
    }

    @Override
    public void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }
}
