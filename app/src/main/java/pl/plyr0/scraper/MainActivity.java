package pl.plyr0.scraper;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.List;

import pl.plyr0.scraper.databinding.ActivityMainBinding;
import pl.plyr0.scraper.model.Row;

public class MainActivity extends AppCompatActivity implements ConsumerAble {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.activityMain.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        new Table(MainActivity.this).execute();
                    }
                }
        );
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.scrollView1.smoothScrollTo(0, 0);
            }
        });
        //new Table(MainActivity.this).execute();
        //binding.activityMain.setRefreshing(true);

        binding.recycler.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        binding.recycler.setLayoutManager(mLayoutManager);
        MyAdapter mAdapter = new MyAdapter(null);
        binding.recycler.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void setText(String text) {
        //binding.activityMainTextview.setText(Html.fromHtml(text));
        binding.activityMain.setRefreshing(false);
    }

    @Override
    public void setData(final List<Row> rows) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                binding.activityMain.setRefreshing(false);
            }
        });
    }
}
