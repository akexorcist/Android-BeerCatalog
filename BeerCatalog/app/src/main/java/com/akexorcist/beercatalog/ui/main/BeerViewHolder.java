package com.akexorcist.beercatalog.ui.main;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.akexorcist.beercatalog.R;

/**
 * Created by Akexorcist on 6/19/2017 AD.
 */

public class BeerViewHolder extends RecyclerView.ViewHolder {
    private TextView tvName;
    private TextView tvAlcohol;
    private TextView tvPrice;
    private TextView tvVolume;

    public BeerViewHolder(View itemView) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.tv_beer_name);
        tvAlcohol = (TextView) itemView.findViewById(R.id.tv_beer_alcohol);
        tvPrice = (TextView) itemView.findViewById(R.id.tv_beer_price);
        tvVolume = (TextView) itemView.findViewById(R.id.tv_beer_volume);
    }

    public void setName(String name) {
        tvName.setText(name);
    }

    public void setAlcohol(String alcohol) {
        tvAlcohol.setText(alcohol);
    }

    public void setPrice(int price) {
        tvPrice.setText(getResource().getString(R.string.beer_price, price));
    }

    public void setVolume(String volume) {
        tvVolume.setText(volume);
    }

    private Resources getResource() {
        return itemView.getResources();
    }


}
