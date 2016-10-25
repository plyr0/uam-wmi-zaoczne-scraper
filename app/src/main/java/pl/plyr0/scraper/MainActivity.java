package pl.plyr0.scraper;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import pl.plyr0.scraper.databinding.ActivityMainBinding;
import pl.plyr0.scraper.model.Row;

public class MainActivity extends AppCompatActivity implements ConsumerAble {

    private ActivityMainBinding binding;
    private MyAdapter recyclerAdapter;

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
                binding.recycler.scrollToPosition(0);
            }
        });

        binding.recycler.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recycler.setLayoutManager(mLayoutManager);

        List<Row> data = new ArrayList<>(3);
        data.add(new Row("a", "a", "a", "a", "a", "a", "a", "a"));
        data.add(new Row("a", "a", "a", "a", "a", "a", "a", "a"));
        data.add(new Row("a", "a", "a", "a", "a", "a", "a", "a"));
        recyclerAdapter = new MyAdapter(data);
        binding.recycler.setAdapter(recyclerAdapter);
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
                recyclerAdapter.setData(rows);
                binding.activityMain.setRefreshing(false);
            }
        });
    }
}
