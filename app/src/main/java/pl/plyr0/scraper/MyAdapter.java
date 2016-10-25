package pl.plyr0.scraper;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.plyr0.scraper.model.Row;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomViewHolder> {
    private List<Row> data;

    public MyAdapter(List<Row> data) {
        if (data == null) {
            data = new ArrayList<>(3);
            data.add(new Row("a", "a", "a", "a", "a", "a", "a", "a"));
            data.add(new Row("a", "a", "a", "a", "a", "a", "a", "a"));
            data.add(new Row("a", "a", "a", "a", "a", "a", "a", "a"));
        } else {
            this.data = data;
        }
        Log.d(MyAdapter.class.getName(), "ctor " + data.size());
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(MyAdapter.class.getName(), "onCreat");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new CustomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.mTextView.setText(data.get(position).getSubject());
        Log.d(MyAdapter.class.getName(), "onBind");
    }

    @Override
    public int getItemCount() {
        return (null != data ? data.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public CustomViewHolder(View v) {
            super(v);
            Log.d(MyAdapter.class.getName(), "ctor holder");
            mTextView = (TextView) v;
            mTextView.setText("aaa");
        }
    }
}
