package com.smith.ufc.data.models.verbose;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Charlton on 10/29/17.
 */
public class ComicPrice extends RealmObject {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("price")
    @Expose
    private Double price;

    public String getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }


}
