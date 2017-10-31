package com.smith.ufc.data.models.verbose;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smith.ufc.data.models.MarvelCharacter;

import java.util.List;
import java8.util.Objects;

import io.realm.RealmList;
import io.realm.RealmObject;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;

/**
 * Created by Charlton on 10/29/17.
 */
public class CharacterList extends RealmObject {

    @SerializedName("available")
    @Expose
    private Integer available;
    @SerializedName("collectionURI")
    @Expose
    private String collectionURI;
    @SerializedName("items")
    @Expose
    private RealmList<CharacterSummary> items = null;
    @SerializedName("returned")
    @Expose
    private Integer returned;

    public Integer getAvailable() {
        return available;
    }


    public String getCollectionURI() {
        return collectionURI;
    }



    public List<MarvelCharacter> getCharacters(){
        return StreamSupport.stream(items).map(CharacterSummary::getCharacter).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public RealmList<CharacterSummary> getItems() {
        return items;
    }


    public Integer getReturned() {
        return returned;
    }


}
