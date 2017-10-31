package com.smith.ufc.data.models.verbose;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Charlton on 10/29/17.
 */
public class SeriesList extends RealmObject {

    @SerializedName("available")
    @Expose
    public Integer available;
    @SerializedName("collectionURI")
    @Expose
    public String collectionURI;
    @SerializedName("items")
    @Expose
    public RealmList<SeriesSummary> items = null;
    @SerializedName("returned")
    @Expose
    public Integer returned;

    public Integer getAvailable() {
        return available;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public RealmList<SeriesSummary> getItems() {
        return items;
    }

    public Integer getReturned() {
        return returned;
    }
}
