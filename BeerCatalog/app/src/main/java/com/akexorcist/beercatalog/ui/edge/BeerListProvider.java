package com.akexorcist.beercatalog.ui.edge;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RemoteViews;

import com.akexorcist.beercatalog.R;
import com.akexorcist.beercatalog.api.NetworkManager;
import com.akexorcist.beercatalog.api.response.Beer;
import com.akexorcist.beercatalog.api.response.BeerResult;
import com.akexorcist.beercatalog.db.DatabaseManager;
import com.akexorcist.beercatalog.ui.edge.list.BeerListAdapterService;
import com.samsung.android.sdk.look.cocktailbar.SlookCocktailManager;
import com.samsung.android.sdk.look.cocktailbar.SlookCocktailProvider;

import java.util.List;

import static com.akexorcist.beercatalog.R.id.lv_beer;

/**
 * Created by Akexorcist on 6/18/2017 AD.
 */

public class BeerListProvider extends SlookCocktailProvider {

    @Override
    public void onUpdate(Context context, SlookCocktailManager cocktailManager, int[] cocktailIds) {
        super.onUpdate(context, cocktailManager, cocktailIds);

        RemoteViews remoteViews = setupRemoteViews(context, false);
        if (cocktailIds != null) {
            for (int id : cocktailIds) {
                cocktailManager.updateCocktail(id, remoteViews);
            }
        }
    }

    @Override
    public void onVisibilityChanged(final Context context, final int cocktailId, int visibility) {
        super.onVisibilityChanged(context, cocktailId, visibility);

        if (visibility == SlookCocktailManager.COCKTAIL_VISIBILITY_SHOW) {
            requestBeer(context, cocktailId);
        }
    }

    private void requestBeer(final Context context, final int cocktailId) {
        RemoteViews remoteViews = setupRemoteViews(context, false);
        SlookCocktailManager.getInstance(context).updateCocktail(cocktailId, remoteViews);

        NetworkManager.getInstance().requestBeer(0, new NetworkManager.NetworkCallback<BeerResult>() {
            @Override
            public void onSuccess(BeerResult result) {
                saveBeerToDatabase(result.getBeers());
                updateBeerListAdapter(context, cocktailId);
            }

            @Override
            public void onFailure(Throwable t) {
                deleteBeerFromDatabase();
                updateBeerListAdapter(context, cocktailId);
            }
        });
    }

    private void saveBeerToDatabase(List<Beer> beerList) {
        DatabaseManager.getInstance().setBeer(beerList);
    }

    private void deleteBeerFromDatabase() {
        DatabaseManager.getInstance().deleteBeer();
    }

    private void updateBeerListAdapter(Context context, int cocktailId) {
        RemoteViews remoteViews = setupRemoteViews(context, true);
        SlookCocktailManager.getInstance(context).updateCocktail(cocktailId, remoteViews);
        SlookCocktailManager.getInstance(context).notifyCocktailViewDataChanged(cocktailId, R.id.lv_beer);
    }

    private RemoteViews setupRemoteViews(Context context, boolean isContentShowing) {
        Intent intent = new Intent(context, BeerListAdapterService.class);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_edge_beer);
        remoteViews.setRemoteAdapter(lv_beer, intent);
        remoteViews.setEmptyView(lv_beer, R.id.tv_empty_beer);
        remoteViews.setViewVisibility(R.id.layout_loading, isContentShowing ? View.INVISIBLE : View.VISIBLE);
        remoteViews.setViewVisibility(R.id.layout_content, isContentShowing ? View.VISIBLE : View.INVISIBLE);
        return remoteViews;
    }
}
