package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.Import;

import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseFragment;

public class ImportTapHoaFragment extends BaseFragment implements ImportTapHoaFragmentContact.View {
    ImportTapHoaFragmentPresenter mPresenter;

    @Override
    protected void onInitData() {
        mPresenter = new ImportTapHoaFragmentPresenter(this);
    }

    @Override
    protected void onInitListener() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_import_product_taphoa;
    }
}
