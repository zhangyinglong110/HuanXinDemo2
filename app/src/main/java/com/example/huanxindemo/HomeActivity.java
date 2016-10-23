package com.example.huanxindemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.huanxin.apphx.contact.HxContactListFragment;
import com.example.huanxin.apphx.conversation.HxConversationListFragment;
import com.example.huanxindemo.book.BookFragment;
import com.example.huanxindemo.user.UserFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.text_books)
    TextView textBooks;
    @BindView(R.id.text_contacts)
    TextView textContacts;
    @BindView(R.id.text_conversations)
    TextView textConversations;
    @BindView(R.id.text_me)
    TextView textMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        viewPager.setAdapter(adapter);
        textBooks.setSelected(true);// 默认书友为选中状态
        viewPager.addOnPageChangeListener(this);
    }

    /*******
     * start ViewPager Listener
     ******/

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        textBooks.setSelected(position == 0);
        textContacts.setSelected(position == 1);
        textConversations.setSelected(position == 2);
        textMe.setSelected(position == 3);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /*******
     * end ViewPager Listener
     ******/
    private final FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new BookFragment();
                case 1:
                    return new HxContactListFragment();
                case 2:
                    return new HxConversationListFragment();
                case 3:
                    return new UserFragment();
                default:
                    throw new RuntimeException();
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    };


    @OnClick({R.id.text_books, R.id.text_contacts, R.id.text_conversations, R.id.text_me})
    public void chooseFragment(View view) {
        switch (view.getId()) {
            case R.id.text_books:
                viewPager.setCurrentItem(0, false);
                break;
            case R.id.text_contacts:
                viewPager.setCurrentItem(1, false);
                break;
            case R.id.text_conversations:
                viewPager.setCurrentItem(2, false);
                break;
            case R.id.text_me:
                viewPager.setCurrentItem(3, false);
                break;
            default:
                throw new RuntimeException();
        }
    }

}
