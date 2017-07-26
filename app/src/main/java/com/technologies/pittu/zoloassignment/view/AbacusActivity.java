package com.technologies.pittu.zoloassignment.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.technologies.pittu.zoloassignment.R;
import com.technologies.pittu.zoloassignment.ZoloApplication;
import com.technologies.pittu.zoloassignment.data.RealmDatabaseHelper;
import com.technologies.pittu.zoloassignment.databinding.ActivityRecyclerviewBinding;
import com.technologies.pittu.zoloassignment.model.Words;
import com.technologies.pittu.zoloassignment.network.API;
import com.technologies.pittu.zoloassignment.view.adapter.WordsAdapter;

import javax.inject.Inject;

import io.realm.RealmChangeListener;
import io.realm.RealmModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Copyright (c) on 22/07/17
 * All this files are belongs to Pittu Harish Reddy (harish)
 */

public class AbacusActivity extends AppCompatActivity {

    private ActivityRecyclerviewBinding activityRecyclerviewBinding;

    @Inject
    API api;
    WordsAdapter adapter;
    @Inject
    RealmDatabaseHelper realmDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        activityRecyclerviewBinding = DataBindingUtil.setContentView(this, R.layout.activity_recyclerview);
        adapter = new WordsAdapter();
        setupRecyclerView(activityRecyclerviewBinding.listPeople);
        ZoloApplication.getApplicationComponent().inject(this);
//        Words words = realmDatabaseHelper.getWords();
//        if(words!=null) {
//            words.addChangeListener(wordChangeListener);
//        }
        api.getWords().enqueue(new Callback<Words>() {
            @Override
            public void onResponse(Call<Words> call, Response<Words> response) {
                realmDatabaseHelper.copyORUpdate(response.body());
                adapter.setWordList(realmDatabaseHelper.getWords().getWords());
            }

            @Override
            public void onFailure(Call<Words> call, Throwable t) {

            }
        });
    }

    /**
     *
     * @param recyclerView recycler view
     */
    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * realm change listener for update
     */
    private RealmChangeListener wordChangeListener = new RealmChangeListener<RealmModel>() {
        @Override
        public void onChange(RealmModel realmModel) {
            Words words = (Words) realmModel;
            adapter.setWordList(words.getWords());
        }
    };

}
