package com.akexorcist.beercatalog.ui.edge.list;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.akexorcist.beercatalog.R;
import com.akexorcist.beercatalog.api.response.Beer;
import com.akexorcist.beercatalog.db.DatabaseManager;

import java.util.List;

/**
 * Created by Akexorcist on 6/18/2017 AD.
 */

public class BeerListAdapterFactory implements RemoteViewsService.RemoteViewsFactory {
    private Context context;
    private List<Beer> beers;

    public BeerListAdapterFactory(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        // Update beer list from database every time
        // when notify data changed was called
        beers = DatabaseManager.getInstance().getBeer();
        return beers != null ? beers.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        final RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.view_beer_item);
        try {
            Beer beer = beers.get(position);
            remoteViews.setTextViewText(R.id.tv_beer_name, beer.getName());
            remoteViews.setTextViewText(R.id.tv_beer_price, beer.getPrice() + " ฿");
            remoteViews.setTextViewText(R.id.tv_beer_volume, beer.getVolume());
            remoteViews.setTextViewText(R.id.tv_beer_alcohol, beer.getAlcohol());
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return remoteViews;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }
}
