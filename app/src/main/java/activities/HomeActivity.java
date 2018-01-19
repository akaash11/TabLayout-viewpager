package activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import com.example.akaash.testproject20.R;
import adapter.ViewPagerAdapter;
import butterknife.OnClick;
import fragments.SearchFragment;
import fragments.ThirdFragment;
import fragments.UserFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.toolBar)
    Toolbar toolBar;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.ivHistoryButton)
    ImageView ivHistoryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(toolBar);

        toolBar.setTitle(R.string.toolbar_title);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new UserFragment(), getString(R.string.USER_FRAGMENT_TITLE));
        adapter.addFragment(new SearchFragment(), getString(R.string.SEARCH_FRAGMENT_TITLE));
        adapter.addFragment(new ThirdFragment(), getString(R.string.THIRD_FRAGMENT_TITLE));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
    //Akaash Trivedi
    @OnClick(R.id.ivHistoryButton)
    public void onClickHistoryIcon(){
        Intent intent=new Intent(HomeActivity.this,KeywordListActivity.class);
        startActivity(intent);
    }

}
