package com.smith.ufc.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smith.ufc.data.models.verbose.ComicList;
import com.smith.ufc.data.models.verbose.EventList;
import com.smith.ufc.data.models.verbose.SeriesList;
import com.smith.ufc.data.models.verbose.StoryList;
import com.smith.ufc.data.models.verbose.Thumbnail;
import com.smith.ufc.data.models.verbose.Url;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;


/**
 * Created by Charlton on 10/24/17.
 */

public class MarvelCharacter extends RealmObject {
    public void setName(String name) {
        this.name = name;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setComics(ComicList comics) {
        this.comics = comics;
    }

    @Index
    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("thumbnail")
    @Expose
    private Thumbnail thumbnail = new Thumbnail();
    @SerializedName("resourceURI")
    @Expose
    private String resourceURI;
    @SerializedName("comics")
    @Expose
    private ComicList comics;
    @SerializedName("series")
    @Expose
    private SeriesList series;
    @SerializedName("stories")
    @Expose
    private StoryList stories;
    @SerializedName("events")
    @Expose
    private EventList events;
    @SerializedName("urls")
    @Expose
    private RealmList<Url> urls = null;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getModified() {
        return modified;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public ComicList getComics() {
        return comics;
    }

    public SeriesList getSeries() {
        return series;
    }

    public StoryList getStories() {
        return stories;
    }

    public EventList getEvents() {
        return events;
    }

    public RealmList<Url> getUrls() {
        return urls;
    }

    public static class Fields {
        public static final String ID = "id";
        public static final String NAME = "name";
    }
}
