package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.history.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.zakariya.stickyheaders.SectioningAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProduct.adapter.ProductChooseDto;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Import.adapter.BillImportProductDto;
import quanly_anything_you_want.manage.com.quanlyanything.utils.CommonUtil;

public class ImportHistoryAdapter extends SectioningAdapter {

    private List<BillImportProductDto> list;

    public ImportHistoryAdapter(List<BillImportProductDto> mSections) {
        this.list = mSections;
        resetCollapsed();
    }

    class HeaderViewHolder extends SectioningAdapter.HeaderViewHolder {
        @BindView(R.id.tv_date_import)
        TextView tvDate;

        @BindView(R.id.tv_name_seller)
        TextView tvNameSeller;

        @BindView(R.id.tv_total_name_product)
        TextView tvTotalNameProduct;

        @BindView(R.id.tv_total_amount)
        TextView tvTotalAmount;

        @BindView(R.id.imv_drop_down)
        ImageView imvDropDown;

        HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.btn_delete_import_history)
        void onClickDelete() {
            mCallBack.onItemDelete(getSection());
        }

        @OnClick(R.id.btn_show_product)
        void onClickShowProduct() {
            setSectionIsCollapsed(getSection(), !isSectionCollapsed(getSection()));
            imvDropDown.setRotation(isSectionCollapsed(getSection()) ? 0 : -90);
        }
    }

    class ItemViewHolder extends SectioningAdapter.ItemViewHolder {

        @BindView(R.id.tv_name_product)
        TextView tvNameProduct;

        @BindView(R.id.tv_quantity_product)
        TextView tvQuantityProduct;

        @BindView(R.id.tv_price_product)
        TextView tvPriceProduct;

        @BindView(R.id.tv_total_price_product)
        TextView tvTotalAmount;


        @BindView(R.id.view_line_bottom)
        View vBottom;

        ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public SectioningAdapter.HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent, int headerType) {
        return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header_import_history, parent, false));
    }

    @Override
    public SectioningAdapter.ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int itemType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_import_history, parent, false));
    }

    @Override
    public void onBindHeaderViewHolder(SectioningAdapter.HeaderViewHolder viewHolder, final int sectionIndex, int headerType) {
        HeaderViewHolder holder = ((HeaderViewHolder) viewHolder);
        BillImportProductDto item = list.get(sectionIndex);
        holder.tvDate.setText(item.date != null ? item.date : "");
        holder.tvNameSeller.setText(item.nameSeller != null ? item.nameSeller : "");
        holder.tvTotalNameProduct.setText(item.getNameTotalProduct());
        holder.tvTotalAmount.setText(item.getTotalAmountProduct());
        holder.imvDropDown.setRotation(isSectionCollapsed(sectionIndex) ? 0 : -90);
    }

    @Override
    public void onBindItemViewHolder(SectioningAdapter.ItemViewHolder viewHolder, final int sectionIndex, final int itemIndex, int itemType) {
        ItemViewHolder holder = (ItemViewHolder) viewHolder;
        ProductChooseDto item = list.get(sectionIndex).getListProduct().get(itemIndex);
        holder.tvNameProduct.setText(item.name != null ? item.name : "");
        holder.tvQuantityProduct.setText(CommonUtil.showQuantityHasUnit(item.quantityImport, item.unitImport));
        holder.tvPriceProduct.setText(CommonUtil.showPriceHasCurrencyAndUnit(item.priceImport, item.currency, item.unitImport));
        holder.tvTotalAmount.setText(CommonUtil.showPriceHasCurrency(item.priceImport * item.quantityImport, item.currency));
        holder.vBottom.setVisibility(itemIndex == list.get(sectionIndex).getListProduct().size() - 1 ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public int getNumberOfSections() {
        return list != null ? list.size() : 0;
    }

    @Override
    public int getNumberOfItemsInSection(int sectionIndex) {
        return list.get(sectionIndex).getListProduct().size();
    }

    @Override
    public boolean doesSectionHaveHeader(int sectionIndex) {
        return true;
    }

    private OnItemClickListener mCallBack;

    public void setOnItemClickListener(OnItemClickListener callBack) {
        mCallBack = callBack;
    }

    public interface OnItemClickListener {
        void onItemDelete(int positionHeader);

    }

    @Override
    public void notifyAllSectionsDataSetChanged() {
        super.notifyAllSectionsDataSetChanged();
        resetCollapsed();
    }

    private void resetCollapsed() {
        if (list != null)
            for (int i = 0; i < list.size(); i++) {
                setSectionIsCollapsed(i, true);
            }
    }

}