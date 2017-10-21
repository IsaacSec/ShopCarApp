package com.example.isaac.shopcar;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.isaac.shopcar.adapters.RecyclerViewBuyListAdapter;
import com.example.isaac.shopcar.adapters.RecyclerViewClickListener;
import com.example.isaac.shopcar.database.ProductCRUD;
import com.example.isaac.shopcar.model.Product;
import com.example.isaac.shopcar.model.ProductBuy;

import java.util.ArrayList;

public class BuyListActivity extends AppCompatActivity {

    private Spinner products;
    private Toolbar toolbar;

    private FloatingActionButton fab;
    private RecyclerView buyRecordList;
    private EditText quantity;
    private TextView total;

    private ArrayList<ProductBuy> buyRecords = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_list);

        initViewComponents();
    }

    public void initViewComponents(){
        final Context context = this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        products = (Spinner) findViewById(R.id.buy_list_spin_products);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        buyRecordList = (RecyclerView) findViewById(R.id.buy_list_rv_list);
        quantity = (EditText) findViewById(R.id.buy_list_spin_quantity);
        total = (TextView) findViewById(R.id.buy_list_tv_total);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getProductNames());
        products.setAdapter(adapter);
        setSupportActionBar(toolbar);

        RecyclerViewBuyListAdapter listAdapter = new RecyclerViewBuyListAdapter(buyRecords, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Nothing
            }
        });
        buyRecordList.setHasFixedSize(true);
        buyRecordList.setLayoutManager(new LinearLayoutManager(this));
        buyRecordList.setAdapter(listAdapter);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String target = (String) products.getSelectedItem();
                System.out.println(target);
                ProductCRUD db = new ProductCRUD(context);
                Product p = db.getProductByName(target);
                buyRecords.add(new ProductBuy(p, quantity.getText().toString()));
                RecyclerViewBuyListAdapter listAdapter = new RecyclerViewBuyListAdapter(buyRecords, new RecyclerViewClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        //Nothing
                    }
                });
                buyRecordList.setAdapter(listAdapter);
                total.setText("$"+getTotal());
            }
        });
    }

    private float getTotal(){
        float result = 0;

        for(ProductBuy p : buyRecords){
            result += Float.parseFloat(p.getQuantity()) * Float.parseFloat(p.getProduct().getPrice());
        }

        return result;
    }

    private String[] getProductNames(){
        ProductCRUD db = new ProductCRUD(this);
        ArrayList<Product> products = db.getProducts();
        String list[] = new String[products.size()];
        for (int i=0; i<products.size(); i++){
            list[i] = products.get(i).getName();
        }
        return list;
    }
}
