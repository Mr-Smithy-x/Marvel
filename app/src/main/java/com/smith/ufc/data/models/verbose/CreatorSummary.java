package com.smith.ufc.data.models.verbose;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Charlton on 10/28/17.
 */

public class CreatorSummary extends RealmObject {

    @SerializedName("resourceURI")
    String resourceURI;

    @SerializedName("name")
    String name;

    @SerializedName("role")
    String role;

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
