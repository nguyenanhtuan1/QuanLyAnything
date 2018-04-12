package quanly_anything_you_want.manage.com.quanlyanything.screen.examActivity;


import quanly_anything_you_want.manage.com.quanlyanything.base.BasePresenter;
import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;


public class ExamPresenter extends BasePresenter implements ExamContact.Presenter {
    ExamPresenter(IBaseView view) {
        super.onCreate(view);
    }

    @Override
    public ExamContact.View getView() {
        return (ExamContact.View) super.getView();
    }

}
