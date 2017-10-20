package com.example.isaac.shopcar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.isaac.shopcar.database.ProductCRUD;
import com.example.isaac.shopcar.model.Product;

public class EditProductActivity extends AppCompatActivity {

    private Product product;

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private Button delete;
    private EditText productName;
    private EditText productPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        product = (Product) this.getIntent().getSerializableExtra("product");

        initViewComponents();
    }

    public void initViewComponents(){
        // Definition
        final Context context = this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        delete = (Button) findViewById(R.id.edit_product_btn_delete);
        productName = (EditText) findViewById(R.id.edit_product_et_name);
        productPrice = (EditText) findViewById(R.id.edit_product_et_price);

        // Configuration
        setSupportActionBar(toolbar);
        delete.setText("DELETE PRODUCT");
        productName.setHint(product.getName());
        productPrice.setHint(product.getPrice());

        // Actions
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductCRUD db = new ProductCRUD(context);

                String name = productName.getText().toString();
                String nameHint = productName.getHint().toString();
                String price = productPrice.getText().toString();
                String priceHint = productPrice.getHint().toString();

                if(name.isEmpty() &&  !nameHint.isEmpty()){
                    product.setName(nameHint);
                } else if (!name.isEmpty()) {
                    product.setName(name);
                }

                if(price.isEmpty() &&  !priceHint.isEmpty()){
                    product.setPrice(priceHint);
                } else if (!price.isEmpty()){
                    product.setPrice(price);
                }

                db.updateProduct(product);
                changeActivity(MainActivity.class);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductCRUD db = new ProductCRUD(context);
                db.deleteProduct(product);
                changeActivity(MainActivity.class);
            }
        });
    }

    public void changeActivity(Class<? extends AppCompatActivity> aClass){
        Intent createProduct = new Intent(this, aClass);
        startActivity(createProduct);
    }
}
