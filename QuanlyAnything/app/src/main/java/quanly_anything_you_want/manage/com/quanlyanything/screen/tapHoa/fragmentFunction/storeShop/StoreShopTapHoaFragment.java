package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.storeShop;

import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseFragment;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.storeShop.adapter.ListStoreAdapter;

public class StoreShopTapHoaFragment extends BaseFragment {
    ListStoreAdapter adapter;
    StoreShopTapHoaPresenter mPresenter;

    @Override
    protected void onInitData() {
        adapter=new ListStoreAdapter(getContext(),mPresenter);
    }

    @Override
    protected void onInitListener() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_store_shop_tap_hoa;
    }
}
