package com.akexorcist.beercatalog.ui.edge.list;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by Akexorcist on 6/18/2017 AD.
 */

public class BeerListAdapterService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new BeerListAdapterFactory(this.getApplicationContext());
    }
}
