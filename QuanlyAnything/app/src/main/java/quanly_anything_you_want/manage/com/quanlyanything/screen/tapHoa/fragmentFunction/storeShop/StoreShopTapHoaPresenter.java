package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.storeShop;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import quanly_anything_you_want.manage.com.quanlyanything.base.BasePresenter;
import quanly_anything_you_want.manage.com.quanlyanything.base.IBaseView;
import quanly_anything_you_want.manage.com.quanlyanything.model.ProductTapHoa;


public class StoreShopTapHoaPresenter extends BasePresenter implements StoreShopTapHoaContact.Presenter {

    private List<ProductTapHoa> listDisplay = new ArrayList<>();
    private List<ProductTapHoa> listStore = new ArrayList<>();

    List<ProductTapHoa> getListProduct() {
        return listDisplay;
    }

    StoreShopTapHoaPresenter(IBaseView view) {
        super.onCreate(view);
        //String name, String photo, double priceExport, int quantityAll,int quantityExport, String unit, String currency)
        listDisplay.add(new ProductTapHoa("Sữa ông thọ",
                "https://upload.wikimedia.org/wikipedia/vi/thumb/1/1f/Sua_Ong_Tho.jpg/220px-Sua_Ong_Tho.jpg",
                10000, 200, 1, "Thùng", "VND"));
        listDisplay.add(new ProductTapHoa("Sữa Dutch lady",
                "https://sieuthikid.com/wp-content/uploads/2013/08/tu-van-chon-thuong-hieu-san-pham-sua-cho-be-tot-nhat-cho-nam-2013-6.jpg",
                30000, 200, 1, "Hộp", "VND"));
        listDisplay.add(new ProductTapHoa("Sữa alpha gold",
                "https://i1.wp.com/singlemum.vn/wp-content/uploads/2016/07/sua-cong-thuc-nao-tot-cho-tre-so-sin-singlemum.vn-2.jpg?fit=600%2C400",
                100000, 100, 1, "Hộp", "VND"));
        listDisplay.add(new ProductTapHoa("Sữa start gold",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS_euyhtl0krA_T7Lu0J4kYB_8iXyYLy0j61Nofczw4ZNlOTCik",
                20000, 150, 1, "Hộp", "VND"));
        listDisplay.add(new ProductTapHoa("Sữa ensure",
                "https://chamsocsuckhoe365site.files.wordpress.com/2016/12/sua-bot-ensure.jpg?w=600&h=400&crop=1",
                10000, 200, 1, "Hộp", "VND"));
        listDisplay.add(new ProductTapHoa("Sữa tươi vinamilk",
                "https://3.bp.blogspot.com/-ZS6Ndf91Dbs/WWr-_5R1V1I/AAAAAAAAAUQ/r3PweDiztKcSBxrziuDYqnGMnhH9hy7xgCEwYBhgL/s640/sua%2Btuoi.png",
                20000, 100, 1, "Thùng", "VND"));
        listDisplay.add(new ProductTapHoa("Sữa Humana",
                "http://www.humana.com.vn/EditorImage/HINH/Humana-gold-4_800g--a.jpg",
                10000, 200, 1, "Thùng", "VND"));
        listDisplay.add(new ProductTapHoa("Sữa Frisolac",
                "https://cdn.phunusuckhoe.vn/uploads/data/nguyenthivananh/cach-chon-sua-bot-cho-em-be-1.jpg",
                10000, 150, 1, "Hộp", "VND"));
        listDisplay.add(new ProductTapHoa("Sữa BA gold",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS-T8iovMyAqb7byyqo1PVU2s_bGIbQJPVDS5q11VknS5OjNAxg",
                10000, 200, 1, "Thùng", "VND"));
        listDisplay.add(new ProductTapHoa("Sữa Clinutren",
                "https://phunu24h.com.vn/images/companies/1/Japan-items/clin.jpg?1508298502205",
                10000, 200, 1, "Thùng", "VND"));

        listStore.addAll(listDisplay);
        Collections.sort(listDisplay, new CustomComparator());
    }

    public class CustomComparator implements Comparator<ProductTapHoa> {
        @Override
        public int compare(ProductTapHoa o1, ProductTapHoa o2) {
            return o1.name.compareTo(o2.name);
        }
    }

    @Override
    public void onSearchProduct(String text) {
        listDisplay.clear();
        for (int i = 0; i < listStore.size(); i++) {
            if (listStore.get(i).name.toLowerCase().contains(text.toLowerCase())) {
                listDisplay.add(listStore.get(i));
            }
        }
        getView().onNotifyAdapterProduct();
    }

    @Override
    public StoreShopTapHoaContact.View getView() {
        return (StoreShopTapHoaContact.View) super.getView();
    }

}
