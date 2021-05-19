package com.lunarekatze.catfacts.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lunarekatze.catfacts.R;
import com.lunarekatze.catfacts.activities.FactDetailsActivity;
import com.lunarekatze.catfacts.database.FactItem;

import java.util.List;

public class FactsAdapter extends RecyclerView.Adapter<FactsAdapter.FactViewHolder> {

    Context mContext;
    List<FactItem> mFactList;

    public FactsAdapter (Context mContext, List<FactItem> mFactList) {
        this.mContext = mContext;
        this.mFactList = mFactList;
    }

    @NonNull
    @Override
    public FactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_layout, parent, false);
        final FactViewHolder holder = new FactViewHolder(view);

        view.setOnClickListener(v -> {
            FactItem fact = getFact(holder.getAbsoluteAdapterPosition());

            Intent intent = new Intent(v.getContext(), FactDetailsActivity.class);
            intent.putExtra("fact_id", fact.getFact_id());
            intent.putExtra("fact_number", holder.getAbsoluteAdapterPosition()+1);

            v.getContext().startActivity(intent);
        });
        return holder;
    }

    @Override
    public void  onBindViewHolder(@NonNull FactViewHolder holder, int position) {
        FactItem fact = mFactList.get(position);
        holder.textView.setText(fact.getText());
        holder.factTitleView.setText(String.format(this.mContext.getString(R.string.fact_number), position + 1));
    }

    @Override
    public int getItemCount() {
        return mFactList.size();
    }

    private FactItem getFact(int position) {
        if(mFactList != null) {
            if(mFactList.size() > 0) {
                return mFactList.get(position);
            }
        }
        return null;
    }

    class FactViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView factTitleView;

        FactViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.text_view);
            factTitleView = itemView.findViewById(R.id.fact_title_view);
        }
    }
}