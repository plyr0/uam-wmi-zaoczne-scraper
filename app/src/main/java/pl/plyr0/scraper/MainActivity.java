package pl.plyr0.scraper;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import pl.plyr0.scraper.databinding.ActivityMainBinding;

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
        new Table(MainActivity.this).execute();
        binding.activityMain.setRefreshing(true);
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
                LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                for (Row r : rows) {
                    //Log.d(MainActivity.class.getName(), r.toString());
                    TextView tv = new TextView(binding.linearLayout1.getContext());
                    //tv.setLayoutParams(lparams);
                    tv.setText(r.getDate() + " " + r.getHourStart() + " " + r.getClassroom1() + " " + r.getSubject());
                    binding.linearLayout1.addView(tv);
                }
                binding.activityMain.setRefreshing(false);
            }
        });
    }
}
