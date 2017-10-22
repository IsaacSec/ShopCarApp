package com.example.isaac.shopcar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.isaac.shopcar.adapters.RecyclerViewBuyListAdapter;
import com.example.isaac.shopcar.adapters.RecyclerViewClickListener;
import com.example.isaac.shopcar.database.BuyListCRUD;
import com.example.isaac.shopcar.database.BuyRecordCRUD;
import com.example.isaac.shopcar.database.ProductCRUD;
import com.example.isaac.shopcar.model.BuyList;
import com.example.isaac.shopcar.model.BuyRecord;
import com.example.isaac.shopcar.model.Product;
import com.example.isaac.shopcar.model.ProductBuy;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class HistoryBuyListActivity extends AppCompatActivity {

    private TextView total;
    private TextView elements;
    private RecyclerView products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_buy_list);

        initViewComponents();
    }

    public void initViewComponents(){
        total = (TextView) findViewById(R.id.history_buy_list_tv_total);
        elements = (TextView) findViewById(R.id.history_buy_list_tv_elements);
        products = (RecyclerView) findViewById(R.id.history_buy_list_rv_lists);

        BuyList buyList = (BuyList) getIntent().getSerializableExtra("list");
        BuyRecordCRUD bdb = new BuyRecordCRUD(this);
        ProductCRUD pdb = new ProductCRUD(this);

        ArrayList<BuyRecord> records = bdb.getRecordsByListId(buyList.getId());
        //ArrayList<BuyRecord> records = bdb.getRecords();

        ArrayList<ProductBuy> productBuys = new ArrayList<>();

        for (BuyRecord record : records){
            Product product = pdb.getProductById(record.getProductId());
            //System.out.println(product.getName()+" "+record.getListId());
            productBuys.add(new ProductBuy(product, record.getQuantity()));
        }

        RecyclerViewBuyListAdapter adapter = new RecyclerViewBuyListAdapter(productBuys, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Nothing :D
            }
        });

        products.setHasFixedSize(true);
        products.setLayoutManager(new LinearLayoutManager(this));
        products.setAdapter(adapter);

        total.setText("$"+getTotal(productBuys));
        elements.setText(records.size()+" elementos");
    }

    private float getTotal(ArrayList<ProductBuy> buyRecords){
        float result = 0;

        for(ProductBuy p : buyRecords){
            result += Float.parseFloat(p.getQuantity()) * Float.parseFloat(p.getProduct().getPrice());
        }

        return result;
    }
}
