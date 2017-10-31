package com.smith.ufc.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smith.ufc.data.models.MarvelComic;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Charlton on 10/24/17.
 */

public class MarvelData<T> {
    @SerializedName("offset")
    @Expose
    public Integer offset;
    @SerializedName("limit")
    @Expose
    public Integer limit;
    @SerializedName("total")
    @Expose
    public Integer total;
    @SerializedName("count")
    @Expose
    public Integer count;
    @SerializedName("results")
    @Expose

    public T results;


    public MarvelData() {

    }

    public MarvelData(T results) {
        if (results instanceof ArrayList) {
            this.results = results;
            this.total = ((ArrayList) results).size();
            this.offset = 0;
            this.limit = 42;
        }
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getCount() {
        return count;
    }

    public T getResults() {
        return results;
    }

}
