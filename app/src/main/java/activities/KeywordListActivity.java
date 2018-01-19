package activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.akaash.testproject20.R;

import java.util.List;

import adapter.HistoryAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import database.DBHelper;
import model.History;
import utils.ToastUtils;

/**
 * Created by akaash on 18/1/18.
 */

public class KeywordListActivity extends AppCompatActivity {

    @BindView(R.id.rvKeywordList)
    RecyclerView rvKeywordList;

/*    @BindView(R.id.toolBar)
    Toolbar toolbar;*/

    private DBHelper dbHelper;
    //private SimpleCursorAdapter simpleCursorAdapter;
    private List<History> historyList;
    private HistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyword_list);
        ButterKnife.bind(this);

        /*setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.KEYWORD_TOOLBAR_TITLE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);*/

        dbHelper = new DBHelper(this);
        historyList = dbHelper.getHistory();
        rvKeywordList.setLayoutManager(new LinearLayoutManager(this));
        //historyList.add(new History("Akaash",1));
        if (historyList != null && historyList.size() > 0) {
            historyAdapter = new HistoryAdapter(historyList);
            rvKeywordList.setAdapter(historyAdapter);
        }
        else{
            ToastUtils.showToast(this,"List is empty");
        }
    }
}
