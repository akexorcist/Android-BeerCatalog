package com.akexorcist.beercatalog.db;

import android.content.Context;

import com.akexorcist.beercatalog.api.response.Beer;
import com.orhanobut.hawk.Hawk;

import java.util.List;

/**
 * Created by Akexorcist on 6/18/2017 AD.
 */

public class DatabaseManager {
    private static final String KEY_BEER = "beer";
    private static DatabaseManager databaseManager;

    public static DatabaseManager getInstance() {
        if (databaseManager == null) {
            databaseManager = new DatabaseManager();
        }
        return databaseManager;
    }

    public void init(Context context) {
        Hawk.init(context.getApplicationContext()).build();
    }

    public void deleteBeer() {
        Hawk.delete(KEY_BEER);
    }

    public void setBeer(List<Beer> beerList) {
        boolean status = Hawk.put(KEY_BEER, beerList);
    }

    public List<Beer> getBeer() {
        return Hawk.get(KEY_BEER);
    }

    public boolean isBeerAvailable() {
        List<Beer> beers = getBeer();
        return beers != null && !beers.isEmpty();
    }
}
