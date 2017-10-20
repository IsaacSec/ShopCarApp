package com.example.isaac.shopcar;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.isaac.shopcar.database.ProductCRUD;
import com.example.isaac.shopcar.model.Product;

public class CreateProduct extends AppCompatActivity {

    /*----- Android View Components -----*/
    private EditText name;
    private EditText price;
    private Button save;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        initViewComponent();
    }

    public void initViewComponent(){
        final Context context = this;

        // Definitions
        name = (EditText) findViewById(R.id.create_product_et_name);
        price = (EditText) findViewById(R.id.create_product_et_price);
        save = (Button) findViewById(R.id.create_product_btn_save);
        cancel = (Button) findViewById(R.id.create_product_btn_cancel);

        // Configuration

        // Actions
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(MainActivity.class);
                ProductCRUD db = new ProductCRUD(context);
                Product product = new Product(
                        "NONE",
                        name.getText().toString(),
                        price.getText().toString(),
                        ""
                );
                db.registerProduct(product);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(MainActivity.class);
            }
        });

    }

    public void changeActivity(Class<? extends AppCompatActivity> aClass){
        Intent newActivity = new Intent(this, aClass);
        startActivity(newActivity);
    }

}
