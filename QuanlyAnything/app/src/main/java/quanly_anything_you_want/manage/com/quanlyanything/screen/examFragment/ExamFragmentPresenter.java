package quanly_anything_you_want.manage.com.quanlyanything.screen.examFragment;


import quanly_anything_you_want.manage.com.quanlyanything.base.BasePresenter;
import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;


public class ExamFragmentPresenter extends BasePresenter implements ExamFragmentContact.Presenter {
    ExamFragmentPresenter(IBaseView view) {
        super.onCreate(view);
    }

    @Override
    public ExamFragmentContact.View getView() {
        return (ExamFragmentContact.View) super.getView();
    }

}
