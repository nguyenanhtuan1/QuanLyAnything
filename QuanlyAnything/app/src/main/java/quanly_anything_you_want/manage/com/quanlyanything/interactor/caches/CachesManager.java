package quanly_anything_you_want.manage.com.quanlyanything.interactor.caches;


import java.util.ArrayList;
import java.util.List;

import quanly_anything_you_want.manage.com.quanlyanything.model.ProductTapHoa;

public class CachesManager {

    public synchronized static CachesManager getInstance() {
        return INSTANCE;
    }

    private static final CachesManager INSTANCE = new CachesManager();


    private CachesManager() {
    }

    public static List<ProductTapHoa> listProduct = new ArrayList<>();


}
