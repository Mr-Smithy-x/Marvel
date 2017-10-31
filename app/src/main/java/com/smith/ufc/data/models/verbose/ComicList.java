package com.smith.ufc.data.models.verbose;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smith.ufc.data.models.MarvelComic;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import java8.util.Objects;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;

/**
 * Created by Charlton on 10/29/17.
 */
public class ComicList extends RealmObject {

    @SerializedName("available")
    @Expose
    private Integer available;
    @SerializedName("collectionURI")
    @Expose
    private String collectionURI;
    @SerializedName("items")
    @Expose
    private RealmList<ComicSummary> items = null;
    @SerializedName("returned")
    @Expose
    private Integer returned;

    public List<MarvelComic> getComics() {
        List<MarvelComic> collect = StreamSupport.stream(items).map(ComicSummary::getComic).filter(Objects::nonNull).collect(Collectors.toList());
        Log.e("COLLECT", String.valueOf(collect.size()));
        return collect;
    }

    public List<ComicSummary> getComicSummary() {
        List<ComicSummary> collect = StreamSupport.stream(items).filter(Objects::nonNull).collect(Collectors.toList());
        return collect;
    }

    public List<ComicSummary> getNulledSummary() {
        List<ComicSummary> collect = StreamSupport.stream(items).filter(r -> r.getComic() == null).collect(Collectors.toList());
        return collect;
    }

    public Integer getAvailable() {
        return available;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public RealmList<ComicSummary> getItems() {
        return items;
    }

    public Integer getReturned() {
        return returned;
    }
}
