package quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.contact;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.OnClick;
import quanly_anything_you_want.manage.com.quanlyanything.R;
import quanly_anything_you_want.manage.com.quanlyanything.base.BaseFragment;
import quanly_anything_you_want.manage.com.quanlyanything.screen.tapHoa.fragmentFunction.contact.adapter.PhoneContactAdapter;

public class ContactTapHoaFragment extends BaseFragment implements ContactTabHoaFragmentContact.View {
    @BindView(R.id.rcv_bill_list)
    RecyclerView rcvContact;

    private PhoneContactAdapter adapter;

    ContactTabHoaFragmentPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_contact_shop_tap_hoa;
    }

    @Override
    protected void onInitData() {
        mPresenter = new ContactTabHoaFragmentPresenter(this);
        adapter = new PhoneContactAdapter(getContext(), mPresenter.getContactList());
        rcvContact.setLayoutManager(new LinearLayoutManager(getContext()));
        rcvContact.setHasFixedSize(true);
        rcvContact.setAdapter(adapter);
    }

    @Override
    protected void onInitListener() {
        adapter.setOnClickListener(new PhoneContactAdapter.OnItemClickListener() {
            @Override
            public void onDeleteContact(int position) {

            }

            @Override
            public void onDetailContact(int position) {

            }

            @Override
            public void onCallContact(int position) {

            }
        });
    }

    @OnClick(R.id.btn_add_phone_number)
    void onClickAddPhoneNumber() {

    }

    @OnClick(R.id.btn_add_phone_number_from_device)
    void onClickAddPhoneNumberFromDevice() {

    }

}
