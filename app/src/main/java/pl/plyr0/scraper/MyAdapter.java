package pl.plyr0.scraper;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import pl.plyr0.scraper.databinding.Card2Binding;
import pl.plyr0.scraper.model.Row;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomViewHolder> {
    private List<Row> data;

    public MyAdapter(List<Row> data) {
        this.data = data;
    }

    public void setData(List<Row> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Card2Binding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.card2, parent, false);
        return new CustomViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Row row = data.get(position);
        holder.binding.setRow(row);

        /*
        holder.mTextView.setText(
                row.getSubject() + "\n" +
                        row.getDate() + "\n" +
                        row.getHourStart() + "\n" +
                        row.getHourEnd() + "\n" +
                        row.getHoursLength() + "\n" +
                        row.getClassroom1() + " / " + row.getClassroom2() + "\n" +
                        row.getLector() + "\n" +
                        row.getCode() + "\n");
                        */
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        public Card2Binding binding;

        public CustomViewHolder(Card2Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
