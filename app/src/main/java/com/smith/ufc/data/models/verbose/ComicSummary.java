package com.smith.ufc.data.models.verbose;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smith.ufc.data.models.MarvelComic;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by Charlton on 10/28/17.
 */

public class ComicSummary extends RealmObject {

    @SerializedName("resourceURI")
    @Expose
    private String resourceURI;
    @SerializedName("name")
    @Expose
    private String name;

    public String getResourceURI() {
        return resourceURI;
    }

    public int getResourceId(){
        String[] split = resourceURI.split("/");
        String str = split[split.length-1];
        return Integer.parseInt(str);
    }

    public MarvelComic getComic(){
        return Realm.getDefaultInstance().where(MarvelComic.class).equalTo("id", getResourceId()).findFirst();
    }


    public String getName() {
        return name;
    }

}
