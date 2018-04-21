package quanly_anything_you_want.manage.com.quanlyanything.screen.examActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseActivity;

public class ExamActivity extends BaseActivity implements ExamContact.View {
    ExamPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        setContentView(R.layout.option_manage_activity);
        mPresenter = new ExamPresenter(this);
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onInitData() {

    }

    @Override
    public void onInitListener() {

    }

}
