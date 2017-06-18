package com.akexorcist.beercatalog.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.akexorcist.beercatalog.R;
import com.akexorcist.beercatalog.api.NetworkManager;
import com.akexorcist.beercatalog.api.response.Beer;
import com.akexorcist.beercatalog.api.response.BeerResult;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_BEER_RESULT = "beer_result";

    private RecyclerView rvBeer;
    private FrameLayout layoutContent;
    private FrameLayout layoutLoading;
    private TextView tvEmptyBeer;

    private BeerContentAdapter beerContentAdapter;

    private BeerResult beerResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindView();
        setupView();

        if (savedInstanceState == null) {
            initialize();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_BEER_RESULT, beerResult);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        beerResult = savedInstanceState.getParcelable(KEY_BEER_RESULT);
        restoreView();
    }

    private void bindView() {
        rvBeer = (RecyclerView) findViewById(R.id.rv_beer);
        layoutContent = (FrameLayout) findViewById(R.id.layout_content);
        layoutLoading = (FrameLayout) findViewById(R.id.layout_loading);
        tvEmptyBeer = (TextView) findViewById(R.id.tv_empty_beer);
    }

    private void setupView() {
        beerContentAdapter = new BeerContentAdapter();
        rvBeer.setLayoutManager(new LinearLayoutManager(this));
        rvBeer.setAdapter(beerContentAdapter);
    }

    private void initialize() {
        showLoading();
        NetworkManager.getInstance().requestBeer(0, new NetworkManager.NetworkCallback<BeerResult>() {
            @Override
            public void onSuccess(BeerResult result) {
                beerResult = result;
                updateBeerContentAdapter(result);
            }

            @Override
            public void onFailure(Throwable t) {
                beerResult = null;
                updateBeerContentAdapter(null);
            }
        });
    }

    private void updateBeerContentAdapter(BeerResult result) {
        if (isBeerListAvailable(result)) {
            showBeerAvailable();
        } else {
            showBeerUnavailable();
        }
        List<Beer> beers = result != null ? result.getBeers() : null;
        beerContentAdapter.setBeerList(beers);
        beerContentAdapter.notifyDataSetChanged();
        showContent();
    }

    private boolean isBeerListAvailable(BeerResult result) {
        return result != null && result.getBeers() != null && !result.getBeers().isEmpty();
    }

    private void showContent() {
        layoutContent.setVisibility(View.VISIBLE);
        layoutLoading.setVisibility(View.INVISIBLE);
    }

    private void showLoading() {
        layoutContent.setVisibility(View.INVISIBLE);
        layoutLoading.setVisibility(View.VISIBLE);
    }

    private void showBeerAvailable() {
        rvBeer.setVisibility(View.VISIBLE);
        tvEmptyBeer.setVisibility(View.INVISIBLE);
    }

    private void showBeerUnavailable() {
        rvBeer.setVisibility(View.INVISIBLE);
        tvEmptyBeer.setVisibility(View.VISIBLE);
    }

    private void restoreView() {
        updateBeerContentAdapter(beerResult);
    }
}
