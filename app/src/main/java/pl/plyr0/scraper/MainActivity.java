package pl.plyr0.scraper;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;

import pl.plyr0.scraper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements TextSettable {

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
        new Table(MainActivity.this).execute();
        binding.activityMain.setRefreshing(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void setText(String text) {
        binding.activityMainTextview.setText(Html.fromHtml(text));
        binding.activityMain.setRefreshing(false);
    }
}
