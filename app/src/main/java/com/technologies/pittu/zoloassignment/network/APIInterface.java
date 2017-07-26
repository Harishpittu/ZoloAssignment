package com.technologies.pittu.zoloassignment.network;

import com.technologies.pittu.zoloassignment.model.Words;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by harish on 09/11/16.
 */

public interface APIInterface {
    @GET("vocab/words.json")
    Call<Words> getWords();
}
