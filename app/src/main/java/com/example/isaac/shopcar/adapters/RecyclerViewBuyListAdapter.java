package com.example.isaac.shopcar.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.isaac.shopcar.R;
import com.example.isaac.shopcar.model.Product;
import com.example.isaac.shopcar.model.ProductBuy;

import java.util.ArrayList;

/**
 * Created by isaac on 10/21/17.
 */

public class RecyclerViewBuyListAdapter extends RecyclerView.Adapter<RecyclerViewBuyListAdapter.BuyRecordViewHolder>{

    private ArrayList<ProductBuy> products;
    private RecyclerViewClickListener listener;

    public RecyclerViewBuyListAdapter(ArrayList<ProductBuy> products, RecyclerViewClickListener listener) {
        this.products = products;
        this.listener = listener;
    }

    @Override
    public BuyRecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.buy_record_row, parent, false);

        return new RecyclerViewBuyListAdapter.BuyRecordViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(BuyRecordViewHolder holder, int position) {
        ProductBuy product = products.get(position);
        String subtotal = ""+(Float.parseFloat(product.getQuantity()) * Float.parseFloat(product.getProduct().getPrice()));

        holder.name.setText(product.getProduct().getName());
        holder.more.setText(product.getQuantity()+" de $"+product.getProduct().getPrice());
        holder.subtotal.setText(subtotal);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class BuyRecordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name;
        private TextView more;
        private TextView subtotal;

        private RecyclerViewClickListener listener;

        BuyRecordViewHolder(View view, RecyclerViewClickListener listener){
            super(view);

            name = (TextView) view.findViewById(R.id.buy_record_tv_name);
            more = (TextView) view.findViewById(R.id.buy_record_tv_more);
            subtotal = (TextView) view.findViewById(R.id.buy_record_tv_subtotal);

            this.listener = listener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }
}
