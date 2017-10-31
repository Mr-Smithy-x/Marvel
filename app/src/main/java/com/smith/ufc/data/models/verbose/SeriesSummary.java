package com.smith.ufc.data.models.verbose;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Charlton on 10/29/17.
 */
public class SeriesSummary extends RealmObject {

    @SerializedName("resourceURI")
    @Expose
    private String resourceURI;
    @SerializedName("name")
    @Expose
    private String name;

    public String getResourceURI() {
        return resourceURI;
    }


    public String getName() {
        return name;
    }


}
