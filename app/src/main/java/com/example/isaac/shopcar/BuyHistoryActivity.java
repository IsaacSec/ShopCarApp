package com.example.isaac.shopcar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.isaac.shopcar.adapters.RecyclerViewBuyHistoryAdapter;
import com.example.isaac.shopcar.adapters.RecyclerViewClickListener;
import com.example.isaac.shopcar.database.BuyListCRUD;
import com.example.isaac.shopcar.database.BuyRecordCRUD;
import com.example.isaac.shopcar.model.BuyList;

import java.util.ArrayList;

public class BuyHistoryActivity extends AppCompatActivity {

    private RecyclerView historyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_history);

        initViewComponents();
    }

    public void initViewComponents(){
        historyList = (RecyclerView) findViewById(R.id.buy_history_list);

        BuyListCRUD db = new BuyListCRUD(this);
        ArrayList<BuyList> list = db.getBuyLists();

        RecyclerViewBuyHistoryAdapter adapter = new RecyclerViewBuyHistoryAdapter(list, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {

            }
        });

        historyList.setHasFixedSize(true);
        historyList.setLayoutManager(new LinearLayoutManager(this));
        historyList.setAdapter(adapter);
    }
}
