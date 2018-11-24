package com.example.miguelangelrubiocaballero.a05numbertrivia;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.v7.widget.RecyclerView.HORIZONTAL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private List<NumberJson> trivias;

    @BindView(R.id.recyclerViewTrivia)
    RecyclerView mRecyclerView;
    @BindView(R.id.addRandomTrivia)
    FloatingActionButton mAddRandomTrivia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        trivias = new ArrayList<>();

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new Adapter(trivias);
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration itemDecor = new DividerItemDecoration(this, HORIZONTAL);
        mRecyclerView.addItemDecoration(itemDecor);
    }

    @OnClick(R.id.addRandomTrivia)
    public void add() {
        requestData();
    }

    private void requestData() {
        ApiService service = ApiService.retrofit.create(ApiService.class);
        retrofit2.Call<NumberJson> call = service.getRandomQuote();

        call.enqueue(new Callback<NumberJson>() {
            @Override
            public void onResponse(Call<NumberJson> call, Response<NumberJson> response) {
                NumberJson dayQuoteItem = response.body();
                Log.i(TAG,"Number: "+dayQuoteItem.getNumber()+"  Quote:  "+dayQuoteItem.getText());
                trivias.add(0,new NumberJson(dayQuoteItem.getText(), dayQuoteItem.getNumber()));
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NumberJson> call, Throwable t) {
                Log.i(TAG, "Something went wrong: " + t.getMessage());
            }

        });
    }
}
