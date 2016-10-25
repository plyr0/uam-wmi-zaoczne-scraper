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
import pl.plyr0.scraper.events.UpdateAble;
import pl.plyr0.scraper.model.Row;
import pl.plyr0.scraper.web.RequestTableTask;

public class MainActivity extends AppCompatActivity implements UpdateAble {

    private ActivityMainBinding binding;
    private MyAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewAndListeners();
        initRecycler();
        new RequestTableTask(MainActivity.this).execute();
        binding.refreshLayout.setRefreshing(true);
    }

    private void initViewAndListeners() {
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.refreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        new RequestTableTask(MainActivity.this).execute();
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
    }

    private void initRecycler() {
        List<Row> data = new ArrayList<>(3);
        data.add(new Row("a", "a", "a", "a", "Empty", "a", "a", "a", "a"));
        recyclerAdapter = new MyAdapter(data);
        binding.recycler.setAdapter(recyclerAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void setData(final List<Row> rows) {
        recyclerAdapter.setData(rows);
        binding.refreshLayout.setRefreshing(false);
        binding.textViewWarning.setVisibility(View.GONE);
    }

    @Override
    public void setError(String error) {
        binding.refreshLayout.setRefreshing(false);
        binding.textViewWarning.setText(error);
        binding.textViewWarning.setVisibility(View.VISIBLE);
    }
}
