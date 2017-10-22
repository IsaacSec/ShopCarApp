package com.example.isaac.shopcar;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.isaac.shopcar.adapters.RecyclerViewClickListener;
import com.example.isaac.shopcar.adapters.RecyclerViewProductListAdapter;
import com.example.isaac.shopcar.database.CarShopDbHelper;
import com.example.isaac.shopcar.database.ProductCRUD;
import com.example.isaac.shopcar.model.Product;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*----- Android View Components -----*/
    private FloatingActionButton addProduct;
    private RecyclerView productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewComponent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }


    public void changeActivity(Class<? extends AppCompatActivity> aClass){
        Intent createProduct = new Intent(this, aClass);
        startActivity(createProduct);
    }

    public void initViewComponent(){
        // Definitions
        final Context context = this;

        addProduct = (FloatingActionButton) findViewById(R.id.main_fad_add_product);
        productList = (RecyclerView) findViewById(R.id.main_rv_product_list);

        // Configuration
        ProductCRUD db = new ProductCRUD(this);
        final ArrayList<Product> products = db.getProducts();
        RecyclerViewProductListAdapter adapter = new RecyclerViewProductListAdapter(products, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent newActivity = new Intent(context, EditProductActivity.class);
                newActivity.putExtra("product",products.get(position));
                startActivity(newActivity);
            }
        });

        productList.setHasFixedSize(true);
        productList.setLayoutManager(new LinearLayoutManager(this));
        productList.setAdapter(adapter);

        // Actions
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(CreateProduct.class);
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_show_list:
                changeActivity(BuyHistoryActivity.class);
                return true;

            case R.id.action_add_list:
                changeActivity(BuyListActivity.class);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
