package quanly_anything_you_want.manage.com.quanlyanything.screen.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseActivity;
import quanly_anything_you_want.manage.com.quanlyanything.custom_view.QLEditText;
import quanly_anything_you_want.manage.com.quanlyanything.screen.option.OptionManageActivity;

public class LoginActivity extends BaseActivity implements LoginContact.View {

    @BindView(R.id.edt_name_account)
    QLEditText edtName;

    @BindView(R.id.edt_password)
    QLEditText edtPassword;

    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.login_activity);
        super.onCreate(savedInstanceState);
        loginPresenter = new LoginPresenter(this);

    }

    @Override
    public void onInitData() {

    }

    @Override
    public void onInitListener() {

    }


    @Override
    public void loginSuccess() {
        Intent intent = new Intent(LoginActivity.this, OptionManageActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btn_login)
    void onClickLogin() {
        if (edtName.getText().toString().isEmpty() || edtPassword.getText().toString().isEmpty()) {
            return;
        }
//        loginPresenter.login(edtName.getText().toString(), edtPassword.getText().toString());
    }

    @OnClick(R.id.tv_create_account)
    void onClickCreateAccount() {
        if (edtName.getText().toString().isEmpty() || edtPassword.getText().toString().isEmpty()) {
            return;
        }
//        loginPresenter.createAccount(edtName.getText().toString(), edtPassword.getText().toString());
    }


}
