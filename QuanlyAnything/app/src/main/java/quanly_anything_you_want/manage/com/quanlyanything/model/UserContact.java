package quanly_anything_you_want.manage.com.quanlyanything.model;

import java.io.Serializable;

public class UserContact implements Serializable{

    public String name;
    public String phone;
    public String desc;
    public boolean isSelect;

    public UserContact(String name, String phone, String desc) {
        this.name = name;
        this.phone = phone;
        this.desc = desc;
    }

    public UserContact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public UserContact() {

    }
}
