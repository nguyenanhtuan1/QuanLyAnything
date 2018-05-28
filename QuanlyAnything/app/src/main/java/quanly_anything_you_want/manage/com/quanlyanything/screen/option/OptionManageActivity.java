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
    @BindView(R.id.imv_tap_hoa)
    ImageView imvTapHoa;

    @BindView(R.id.imv_shop_fashion)
    ImageView imvShopFashion;

    @BindView(R.id.imv_chi_tieu)
    ImageView imvChiTieu;

    @BindView(R.id.imv_shop_pet)
    ImageView imvShopPet;

    @BindView(R.id.imv_schedule)
    ImageView imvSchedule;

    @BindView(R.id.cs_container)
    ConstraintLayout constraintLayout;

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
        setOnStartAnimation(imvTapHoa, TapHoaActivity.class);
    }

    @OnClick(R.id.btn_shop_fashion)
    void onClickShopClothes() {
        setOnStartAnimation(imvShopFashion, TapHoaActivity.class);
    }

    @OnClick(R.id.btn_chi_tieu)
    void onClickChiTieu() {
        setOnStartAnimation(imvChiTieu, TapHoaActivity.class);
    }

    @OnClick(R.id.btn_shop_pet)
    void onClickShopPet() {
        setOnStartAnimation(imvShopPet, TapHoaActivity.class);
    }

    @OnClick(R.id.btn_schedule)
    void onClickSchedule() {
        setOnStartAnimation(imvSchedule, TapHoaActivity.class);
    }

    private void setOnStartAnimation(final ImageView view, final Class activity) {
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "rotationY", 0f, 360f);
//        objectAnimator.setDuration(500);
//        objectAnimator.addListener(new AnimatorListenerAdapter() {
//        });
//        objectAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
                Intent intent = new Intent(OptionManageActivity.this, activity);
                startActivity(intent);
//            }
//        });
//        objectAnimator.start();
    }

    @OnClick(R.id.btn_logout)
    void onClickLogout() {
        Intent intent = new Intent(OptionManageActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
