package quanly_anything_you_want.manage.com.quanlyanything.model;

import java.io.Serializable;
import java.util.List;

import quanly_anything_you_want.manage.com.quanlyanything.screen.chooseProduct.adapter.ProductChooseDto;

public class ObjectContentList implements Serializable {
    public List<ProductChooseDto> chooseDtoList;
    public List<UserContact> userContacts;
}
