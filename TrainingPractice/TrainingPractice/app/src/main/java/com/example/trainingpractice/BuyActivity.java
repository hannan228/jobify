package com.example.trainingpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.*;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.trainingpractice.Adapter.BuyAdapter;
import com.example.trainingpractice.Adapter.MyAdapter;
import com.example.trainingpractice.Model.ItemList;
import com.example.trainingpractice.Model.ModelBuy;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuyActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private RecyclerView.Adapter adapter;

    private List<ModelBuy> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        recyclerView = (RecyclerView) findViewById(R.id.recycleViewBuyer);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

      AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<ModelBuy>> call = jsonPlaceHolderApi.getBuy();
        call.enqueue(new Callback<List<ModelBuy>>() {
            @Override
            public void onResponse(Call<List<ModelBuy>> call, Response<List<ModelBuy>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(BuyActivity.this, ""+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<ModelBuy> posts = response.body();

                list = new ArrayList<>();

                for (ModelBuy post : posts) {
                    ModelBuy itemList = new ModelBuy(
                            "Id: "+post.getId(),"Name: "+post.getName(), "Number: "+post.getPrice(),"quantity"+post.getQuantity(),"type"+post.getType());
                    list.add(itemList);
                }

                adapter = new BuyAdapter(getApplicationContext(),list);
                recyclerView.setAdapter(adapter);

            }
            @Override
            public void onFailure(Call<List<ModelBuy>> call, Throwable t) {
                Toast.makeText(BuyActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}