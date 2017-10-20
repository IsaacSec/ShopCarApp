package com.example.isaac.shopcar.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.isaac.shopcar.R;
import com.example.isaac.shopcar.model.Product;

import java.util.ArrayList;

/**
 * Created by isaac on 10/20/17.
 */

public class RecyclerViewProductListAdapter extends RecyclerView.Adapter<RecyclerViewProductListAdapter.ShopProductViewHolder>{

    private ArrayList<Product> products;
    private RecyclerViewClickListener listener;

    public RecyclerViewProductListAdapter(ArrayList<Product> products, RecyclerViewClickListener listener){
        this.products = products;
        this.listener = listener;
    }

    @Override
    public ShopProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_row, parent, false);

        //ShopProductViewHolder productViewHolder = new ShopProductViewHolder(v);
        //return productViewHolder;

        return new ShopProductViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(ShopProductViewHolder holder, int position) {
        Product product = products.get(position);

        if (product.getPhotoUrl() != null && !product.getPhotoUrl().isEmpty()) {
            holder.name.setText(product.getName());
            holder.price.setText(product.getPrice());
            // Load image with picasso
        } else {
            // Show default image
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    // Class model

    public static class ShopProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView photo;
        private TextView name;
        private TextView price;

        private RecyclerViewClickListener listener;

        ShopProductViewHolder(View view, RecyclerViewClickListener listener){
            super(view);

            photo = (ImageView) view.findViewById(R.id.product_list_iv_photo);
            name = (TextView) view.findViewById(R.id.product_list_tv_name);
            price = (TextView) view.findViewById(R.id.product_list_tv_price);

            this.listener = listener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }
}
