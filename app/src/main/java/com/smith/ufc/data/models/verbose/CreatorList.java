package com.smith.ufc.data.models.verbose;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Charlton on 10/29/17.
 */
public class CreatorList extends RealmObject {

    @SerializedName("available")
    @Expose
    private Integer available;
    @SerializedName("collectionURI")
    @Expose
    private String collectionURI;
    @SerializedName("items")
    @Expose
    private RealmList<CreatorSummary> items = null;
    @SerializedName("returned")
    @Expose
    private Integer returned;

    public Integer getAvailable() {
        return available;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public RealmList<CreatorSummary> getItems() {
        return items;
    }

    public Integer getReturned() {
        return returned;
    }


}
