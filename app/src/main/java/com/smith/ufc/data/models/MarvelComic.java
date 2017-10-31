
package com.smith.ufc.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smith.ufc.data.models.verbose.CharacterList;
import com.smith.ufc.data.models.verbose.ComicDate;
import com.smith.ufc.data.models.verbose.ComicPrice;
import com.smith.ufc.data.models.verbose.CommonURI;
import com.smith.ufc.data.models.verbose.CreatorList;
import com.smith.ufc.data.models.verbose.EventList;
import com.smith.ufc.data.models.verbose.SeriesSummary;
import com.smith.ufc.data.models.verbose.StoryList;
import com.smith.ufc.data.models.verbose.TextObject;
import com.smith.ufc.data.models.verbose.Thumbnail;
import com.smith.ufc.data.models.verbose.Url;

import org.jetbrains.annotations.Nullable;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

public class MarvelComic extends RealmObject {

    @Index
    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("digitalId")
    @Expose
    private Integer digitalId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("issueNumber")
    @Expose
    private Integer issueNumber;
    @SerializedName("variantDescription")
    @Expose
    private String variantDescription;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("isbn")
    @Expose
    private String isbn;
    @SerializedName("upc")
    @Expose
    private String upc;
    @SerializedName("diamondCode")
    @Expose
    private String diamondCode;
    @SerializedName("ean")
    @Expose
    private String ean;
    @SerializedName("issn")
    @Expose
    private String issn;
    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("pageCount")
    @Expose
    private Integer pageCount;
    @SerializedName("textObjects")
    @Expose
    private RealmList<TextObject> textObjects = null;
    @SerializedName("resourceURI")
    @Expose
    private String resourceURI;
    @SerializedName("urls")
    @Expose
    private RealmList<Url> urls = null;
    @SerializedName("series")
    @Expose
    private SeriesSummary series;
    @SerializedName("variants")
    @Expose
    private RealmList<CommonURI> variants = null;
    @SerializedName("collections")
    @Expose
    private RealmList<CommonURI> collections = null;
    @SerializedName("collectedIssues")
    @Expose
    private RealmList<CommonURI> collectedIssues = null;
    @SerializedName("dates")
    @Expose
    private RealmList<ComicDate> dates = null;
    @SerializedName("prices")
    @Expose
    private RealmList<ComicPrice> prices = null;
    @SerializedName("thumbnail")
    @Expose
    private Thumbnail thumbnail;
    @SerializedName("images")
    @Expose
    private RealmList<Thumbnail> images = null;
    @SerializedName("creators")
    @Expose
    private CreatorList creators;
    @SerializedName("characters")
    @Expose
    private CharacterList characters;
    @SerializedName("stories")
    @Expose
    private StoryList stories;
    @SerializedName("events")
    @Expose
    private EventList events;

    public Integer getId() {
        return id;
    }

    public Integer getDigitalId() {
        return digitalId;
    }

    public String getTitle() {
        return title;
    }

    public Integer getIssueNumber() {
        return issueNumber;
    }

    public String getVariantDescription() {
        return variantDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getModified() {
        return modified;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getUpc() {
        return upc;
    }

    public String getDiamondCode() {
        return diamondCode;
    }

    public String getEan() {
        return ean;
    }

    public String getIssn() {
        return issn;
    }

    public String getFormat() {
        return format;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public RealmList<TextObject> getTextObjects() {
        return textObjects;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public RealmList<Url> getUrls() {
        return urls;
    }

    public SeriesSummary getSeries() {
        return series;
    }

    public RealmList<CommonURI> getVariants() {
        return variants;
    }

    public RealmList<CommonURI> getCollections() {
        return collections;
    }

    public RealmList<CommonURI> getCollectedIssues() {
        return collectedIssues;
    }

    public RealmList<ComicDate> getDates() {
        return dates;
    }

    public RealmList<ComicPrice> getPrices() {
        return prices;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public RealmList<Thumbnail> getImages() {
        return images;
    }

    public CreatorList getCreators() {
        return creators;
    }

    public CharacterList getCharacters() {
        return characters;
    }

    public StoryList getStories() {
        return stories;
    }

    public EventList getEvents() {
        return events;
    }

    public static class Fields {
        @Nullable
        public static final String NAME = "title";
        @Nullable
        public static final String ID = "id";
    }
}
