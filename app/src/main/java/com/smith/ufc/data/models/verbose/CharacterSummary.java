package com.smith.ufc.data.models.verbose;

import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.smith.ufc.data.models.MarvelCharacter;
import com.smith.ufc.data.models.MarvelComic;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by Charlton on 10/28/17.
 */

public class CharacterSummary extends RealmObject {

    @SerializedName("resourceURI")
    String resourceURI;

    @SerializedName("name")
    String name;

    @SerializedName("role")
    String role;


    public MarvelCharacter getCharacter(){
        return Realm.getDefaultInstance().where(MarvelCharacter.class).equalTo("id", getResourceId()).findFirst();
    }


    public int getResourceId(){
        String[] split = resourceURI.split("/");
        String str = split[split.length-1];
        return Integer.parseInt(str);
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
