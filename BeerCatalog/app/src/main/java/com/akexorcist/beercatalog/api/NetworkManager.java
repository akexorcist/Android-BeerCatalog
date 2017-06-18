package com.akexorcist.beercatalog.api;

import com.akexorcist.beercatalog.api.response.BeerResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.akexorcist.beercatalog.api.NetworkUrl.BASE_URL;

/**
 * Created by Akexorcist on 6/18/2017 AD.
 */

public class NetworkManager {
    public static final String SUCCESS = "SUCCESS";

    private static NetworkManager instance;
    private static NetworkApi api;

    public interface NetworkCallback<T> {
        void onSuccess(T result);

        void onFailure(Throwable t);
    }

    public static NetworkManager getInstance() {
        if (instance == null) {
            instance = new NetworkManager();
        }
        return instance;
    }

    private NetworkManager() {
    }

    public static void setApi(NetworkApi mockApi) {
        api = mockApi;
    }

    public void requestBeer(int page, final NetworkCallback<BeerResult> callback) {
        requestBeerCall(page).enqueue(new Callback<BeerResult>() {
            @Override
            public void onResponse(Call<BeerResult> call, Response<BeerResult> response) {
                if (callback != null) {
                    if (isBeerResultSuccess(response)) {
                        callback.onSuccess(response.body());
                    } else {
                        callback.onFailure(new Throwable("Response invalid."));
                    }
                }
            }

            @Override
            public void onFailure(Call<BeerResult> call, Throwable t) {
                if (!call.isCanceled()) {
                    if (callback != null) {
                        callback.onFailure(t);
                    }
                }
            }
        });
    }

    private Call<BeerResult> requestBeerCall(int page) {
        return NetworkService.newInstance(BASE_URL)
                .getApi(api)
                .getBeer(page);
    }

    private boolean isBeerResultSuccess(Response<BeerResult> response) {
        if (response.isSuccessful()) {
            BeerResult result = response.body();
            return result != null && SUCCESS.equals(result.getStatus()) && result.getBeers() != null;
        }
        return false;
    }
}
