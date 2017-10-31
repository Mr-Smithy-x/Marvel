package com.smith.ufc.data.models.verbose;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Charlton on 10/29/17.
 */
public class Thumbnail extends RealmObject {

    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("extension")
    @Expose
    private String extension;


    @Override
    public String toString() {
        return ""+String.format("%s.%s", path, extension);
    }
}
