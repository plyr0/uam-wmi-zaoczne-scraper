package pl.plyr0.scraper;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pl.plyr0.scraper.model.Row;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomViewHolder> {
    private List<Row> data;

    public MyAdapter(List<Row> data) {
        this.data = data;
        Log.d(MyAdapter.class.getName(), "ctor " + data.size());
    }

    public void setData(List<Row> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(MyAdapter.class.getName(), "onCreat");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new CustomViewHolder((TextView) v);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.mTextView.setText(data.get(position).getSubject());
        Log.d(MyAdapter.class.getName(), "onBind");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public CustomViewHolder(TextView v) {
            super(v);
            Log.d(MyAdapter.class.getName(), "ctor holder");
            mTextView = v;
            mTextView.setText("aaa");
        }
    }
}
