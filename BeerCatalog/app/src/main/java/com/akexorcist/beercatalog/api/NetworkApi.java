package com.akexorcist.beercatalog.api;

import com.akexorcist.beercatalog.api.response.BeerResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by Akexorcist on 6/18/2017 AD.
 */

public interface NetworkApi {
    @GET(NetworkUrl.URL_BEER)
    Call<BeerResult> getBeer(@Query("page") int page);
}


