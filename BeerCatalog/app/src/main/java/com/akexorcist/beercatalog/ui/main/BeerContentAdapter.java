package com.akexorcist.beercatalog.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akexorcist.beercatalog.R;
import com.akexorcist.beercatalog.api.response.Beer;

import java.util.List;

/**
 * Created by Akexorcist on 6/19/2017 AD.
 */

public class BeerContentAdapter extends RecyclerView.Adapter<BeerViewHolder> {
    private List<Beer> beerList;

    public BeerContentAdapter() {
    }

    public void setBeerList(List<Beer> beerList) {
        this.beerList = beerList;
    }

    @Override
    public BeerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_beer_item, parent, false);
        return new BeerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BeerViewHolder holder, int position) {
        Beer beer = beerList.get(position);
        holder.setName(beer.getName());
        holder.setVolume(beer.getVolume());
        holder.setAlcohol(beer.getAlcohol());
        holder.setPrice(beer.getPrice());
    }

    @Override
    public int getItemCount() {
        return beerList != null ? beerList.size() : 0;
    }
}
