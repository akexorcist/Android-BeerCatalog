package com.akexorcist.beercatalog.api.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Akexorcist on 6/18/2017 AD.
 */

public class BeerResult implements Parcelable {
    private String status;
    private String message;
    private boolean nextBeerAvailable;
    private int nextBeerIndex;
    private List<Beer> beers;

    public BeerResult() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isNextBeerAvailable() {
        return nextBeerAvailable;
    }

    public void setNextBeerAvailable(boolean nextBeerAvailable) {
        this.nextBeerAvailable = nextBeerAvailable;
    }

    public List<Beer> getBeers() {
        return beers;
    }

    public void setBeers(List<Beer> beers) {
        this.beers = beers;
    }

    public int getNextBeerIndex() {
        return nextBeerIndex;
    }

    public void setNextBeerIndex(int nextBeerIndex) {
        this.nextBeerIndex = nextBeerIndex;
    }

    protected BeerResult(Parcel in) {
        status = in.readString();
        message = in.readString();
        nextBeerAvailable = in.readByte() != 0;
        nextBeerIndex = in.readInt();
        beers = in.createTypedArrayList(Beer.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(message);
        dest.writeByte((byte) (nextBeerAvailable ? 1 : 0));
        dest.writeInt(nextBeerIndex);
        dest.writeTypedList(beers);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BeerResult> CREATOR = new Creator<BeerResult>() {
        @Override
        public BeerResult createFromParcel(Parcel in) {
            return new BeerResult(in);
        }

        @Override
        public BeerResult[] newArray(int size) {
            return new BeerResult[size];
        }
    };
}
