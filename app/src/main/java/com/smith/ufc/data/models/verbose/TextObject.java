package com.smith.ufc.data.models.verbose;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Charlton on 10/27/17.
 */

public class TextObject extends RealmObject {
    @Expose
    @SerializedName("type")
    public String type;
    @Expose
    @SerializedName("language")
    public String language;
    @Expose
    @SerializedName("text")
    public String text;


    public String getType() {
        return type;
    }

    public String getLanguage() {
        return language;
    }

    public String getText() {
        return text;
    }
}
