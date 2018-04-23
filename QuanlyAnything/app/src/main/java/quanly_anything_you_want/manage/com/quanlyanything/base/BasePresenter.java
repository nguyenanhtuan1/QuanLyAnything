package quanly_anything_you_want.manage.com.quanlyanything.base;


import org.greenrobot.eventbus.Subscribe;

import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.ApiManager;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.caches.CachesManager;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.event.EventManager;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.event.type.Empty;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.prefer.PreferManager;

public abstract class BasePresenter {

    private IBaseView mView;

    public IBaseView getView() {
        if (mView == null) {
            throw new IllegalStateException("Presenter must be attach IView");
        }
        return mView;
    }

    public boolean isViewAttached() {
        return mView != null;
    }


    public BasePresenter() {
    }

    protected ApiManager getApiManager() {
        return ApiManager.getInstance();
    }

    public EventManager getEventManager() {
        return EventManager.getInstance();
    }

    public PreferManager getPreferManager() {
        return PreferManager.getInstance();
    }

    public CachesManager getCachesManager() {
        return CachesManager.getInstance();
    }

    public void onCreate(IBaseView view) {
        mView = view;
    }

    public void onDestroy() {
        mView = null;
    }

    @Subscribe
    public void onEvent(Empty empty) {
    }

}

