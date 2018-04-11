package quanly_anything_you_want.manage.com.quanlyanything.screen.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.BindView;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseActivity;

public class LoginActivity extends BaseActivity implements LoginContact.View {

    @Override
    public boolean isTransparentStatusBar() {
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onInitData() {

    }


    @Override
    public void loginSuccess() {

    }


}
