package com.akexorcist.beercatalog.api.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Akexorcist on 6/18/2017 AD.
 */

public class Beer implements Parcelable {
    public static final Creator<Beer> CREATOR = new Creator<Beer>() {
        @Override
        public Beer createFromParcel(Parcel in) {
            return new Beer(in);
        }

        @Override
        public Beer[] newArray(int size) {
            return new Beer[size];
        }
    };
    private String id;
    private String name;
    private int price;
    private String volume;
    private String alcohol;
    private String image;

    public Beer() {
    }

    protected Beer(Parcel in) {
        id = in.readString();
        name = in.readString();
        price = in.readInt();
        volume = in.readString();
        alcohol = in.readString();
        image = in.readString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(String alcohol) {
        this.alcohol = alcohol;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeInt(price);
        dest.writeString(volume);
        dest.writeString(alcohol);
        dest.writeString(image);
    }
}
