package quanly_anything_you_want.manage.com.quanlyanything.interactor.caches;


public class CachesManager {

    public synchronized static final CachesManager getInstance() {
        return INSTANCE;
    }

    private static final CachesManager INSTANCE = new CachesManager();


    public CachesManager() {
    }

}
