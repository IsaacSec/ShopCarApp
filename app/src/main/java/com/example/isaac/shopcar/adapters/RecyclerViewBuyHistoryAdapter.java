package com.example.isaac.shopcar.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.isaac.shopcar.R;
import com.example.isaac.shopcar.model.BuyList;

import java.util.ArrayList;

/**
 * Created by isaac on 10/22/17.
 */

public class RecyclerViewBuyHistoryAdapter extends RecyclerView.Adapter<RecyclerViewBuyHistoryAdapter.BuyHistoryHolder>{

    private ArrayList<BuyList> products;
    private RecyclerViewClickListener listener;

    public RecyclerViewBuyHistoryAdapter(ArrayList<BuyList> products, RecyclerViewClickListener listener) {
        this.products = products;
        this.listener = listener;
    }

    @Override
    public BuyHistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_list_row, parent, false);

        return new BuyHistoryHolder(v,listener);
    }

    @Override
    public void onBindViewHolder(BuyHistoryHolder holder, int position) {
        BuyList history = products.get(position);

        holder.date.setText("Compra "+history.getDate());
        holder.elements.setText(history.getElements()+" Elementos");
        holder.total.setText(history.getTotal());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class BuyHistoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView date;
        private TextView elements;
        private TextView total;

        private RecyclerViewClickListener listener;

        public BuyHistoryHolder(View view, RecyclerViewClickListener listener) {
            super(view);

            date = (TextView) view.findViewById(R.id.history_row_date);
            elements = (TextView) view.findViewById(R.id.history_row_elements);
            total = (TextView) view.findViewById(R.id.history_row_total);

            this.listener = listener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }
}
