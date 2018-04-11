package quanly_anything_you_want.manage.com.quanlyanything.interactor.event;


import org.greenrobot.eventbus.EventBus;

import quanly_anything_you_want.manage.com.quanlyanything.interactor.api.ApiManager;
import quanly_anything_you_want.manage.com.quanlyanything.interactor.event.type.Empty;

public class EventManager {
    public synchronized static final EventManager getInstance() {
        return INSTANCE;
    }

    private static final EventManager INSTANCE = new EventManager();

    public void register(Object obj) {
        EventBus.getDefault().register(obj);
    }

    public void unRegister(Object obj) {
        EventBus.getDefault().unregister(obj);
    }

    public void sendEvent(Empty event) {
        EventBus.getDefault().post(event);
    }


}
