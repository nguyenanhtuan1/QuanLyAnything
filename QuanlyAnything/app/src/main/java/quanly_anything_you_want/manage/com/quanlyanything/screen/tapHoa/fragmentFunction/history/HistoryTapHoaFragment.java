package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.history;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseFragment;
import quanly_anything_you_want.manage.com.quanlyanything.dialog.dialogPositiveNegative.DialogPositiveNegative;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.history.adapter.ImportHistoryAdapter;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.history.adapter.SellHistoryAdapter;

public class HistoryTapHoaFragment extends BaseFragment implements HistoryTapHoaContact.View {

    @BindView(R.id.btn_sell_history)
    Button btnSellHistory;

    @BindView(R.id.btn_import_history)
    Button btnImportHistory;

    @BindView(R.id.rcv_history)
    RecyclerView rcvHistory;

    HistoryTapHoaPresenter mPresenter;

    SellHistoryAdapter adapterSell;
    ImportHistoryAdapter adapterImport;

    @Override
    protected void onInitData() {
        mPresenter = new HistoryTapHoaPresenter(this);
        btnImportHistory.setSelected(true);

        adapterImport = new ImportHistoryAdapter(getContext(), mPresenter.getListImport());
        rcvHistory.setLayoutManager(new LinearLayoutManager(getContext()));
        rcvHistory.setAdapter(adapterImport);

        adapterSell = new SellHistoryAdapter(getContext(), mPresenter.getListSell());

    }

    @Override
    protected void onInitListener() {
        adapterImport.setOnItemClickListener(new ImportHistoryAdapter.OnItemClickListener() {
            @Override
            public void onItemDelete(final int positionHeader) {
                showConfirmDialog(getString(R.string.question_delete_import_history_product), new DialogPositiveNegative.IPositiveNegativeDialogListener() {
                    @Override
                    public void onIPositiveNegativeDialogAnswerPositive(DialogPositiveNegative dialog) {
                        mPresenter.setDeleteImportHistory(positionHeader);
                        dialog.dismiss();
                    }

                    @Override
                    public void onIPositiveNegativeDialogAnswerNegative(DialogPositiveNegative dialog) {
                    }
                });
            }
        });

        adapterSell.setOnItemClickListener(new SellHistoryAdapter.OnItemClickListener() {
            @Override
            public void onItemDelete(final int positionHeader) {
                showConfirmDialog(getString(R.string.question_delete_sell_history_product), new DialogPositiveNegative.IPositiveNegativeDialogListener() {
                    @Override
                    public void onIPositiveNegativeDialogAnswerPositive(DialogPositiveNegative dialog) {
                        mPresenter.setDeleteSellHistory(positionHeader);
                        dialog.dismiss();
                    }

                    @Override
                    public void onIPositiveNegativeDialogAnswerNegative(DialogPositiveNegative dialog) {
                    }
                });
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
        rcvHistory.setAdapter(adapterSell);
    }

    @OnClick(R.id.btn_import_history)
    void onClickImportHistory() {
        btnSellHistory.setSelected(false);
        btnImportHistory.setSelected(true);
        rcvHistory.setAdapter(adapterImport);

    }

    @Override
    public void onNotifyAdapterImport() {
        adapterImport.notifyDataSetChanged();
    }

    @Override
    public void onNotifyAdapterSell() {
        adapterSell.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }
}
