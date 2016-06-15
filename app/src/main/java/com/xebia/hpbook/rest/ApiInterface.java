package com.xebia.hpbook.rest;

import com.xebia.hpbook.model.Books;
import com.xebia.hpbook.model.Offers;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * ApiInterface class
 */
public interface ApiInterface {


    @GET("/books")
    Call<List<Books>> getBooksList();//

    @GET("/books/{isbn}/commercialOffers")
    Call<Offers> getBestOffers(@Path("isbn") String param);

}
