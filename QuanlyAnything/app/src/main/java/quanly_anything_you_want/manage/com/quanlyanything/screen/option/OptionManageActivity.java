package quanly_anything_you_want.manage.com.quanlyanything.screen.option;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseActivity;
import quanly_anything_you_want.manage.com.quanlyanything.screen.login.LoginActivity;

public class OptionManageActivity extends BaseActivity implements OptionManageContact.View {
    OptionManagePresenter optionManagePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.option_manage_activity);
        super.onCreate(savedInstanceState);
        optionManagePresenter = new OptionManagePresenter(this);

    }

    @Override
    public void onInitData() {

    }

    @OnClick(R.id.btn_tap_hoa)
    void onClickTapHoa() {


    }

    @OnClick(R.id.btn_shop_clothes)
    void onClickShopClothes() {

    }

    @OnClick(R.id.btn_chi_tieu)
    void onClickChiTieu() {

    }

    @OnClick(R.id.btn_shop_pet)
    void onClickShopPet() {

    }

    @OnClick(R.id.btn_schedule)
    void onClickSchedule() {

    }

    @OnClick(R.id.btn_logout)
    void onClickLogout() {
        Intent intent = new Intent(OptionManageActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
