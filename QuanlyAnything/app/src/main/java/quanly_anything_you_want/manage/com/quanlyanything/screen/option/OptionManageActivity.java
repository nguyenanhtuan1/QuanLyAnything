package quanly_anything_you_want.manage.com.quanlyanything.screen.option;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseActivity;
import quanly_anything_you_want.manage.com.quanlyanything.screen.login.LoginActivity;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.TapHoaActivity;

public class OptionManageActivity extends BaseActivity implements OptionManageContact.View {

    OptionManagePresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.option_manage_activity);
        super.onCreate(savedInstanceState);
        mPresenter = new OptionManagePresenter(this);

    }

    @Override
    public void onInitData() {

    }

    @Override
    public void onInitListener() {

    }

    @OnClick(R.id.btn_tap_hoa)
    void onClickTapHoa() {
        setOnStartAnimation(TapHoaActivity.class);
    }

    @OnClick(R.id.btn_shop_fashion)
    void onClickShopClothes() {
        setOnStartAnimation(TapHoaActivity.class);
    }

    @OnClick(R.id.btn_chi_tieu)
    void onClickChiTieu() {
        setOnStartAnimation(TapHoaActivity.class);
    }

    @OnClick(R.id.btn_shop_pet)
    void onClickShopPet() {
        setOnStartAnimation(TapHoaActivity.class);
    }

    @OnClick(R.id.btn_schedule)
    void onClickSchedule() {
        setOnStartAnimation(TapHoaActivity.class);
    }

    private void setOnStartAnimation(final Class activity) {
        Intent intent = null;
        if (activity == TapHoaActivity.class) {
            intent = new Intent(OptionManageActivity.this, activity);
        }
        startActivity(intent);
    }

    @OnClick(R.id.btn_logout)
    void onClickLogout() {
        mPresenter.resetUser();
        Intent intent = new Intent(OptionManageActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
