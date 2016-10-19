package pl.plyr0.scraper;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;

import pl.plyr0.scraper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements TextSettable {

    ActivityMainBinding binding;

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
    }

    @Override
    public void setText(String text) {
        binding.activityMainTextview.setText(Html.fromHtml(text));
        binding.activityMain.setRefreshing(false);
    }
}
