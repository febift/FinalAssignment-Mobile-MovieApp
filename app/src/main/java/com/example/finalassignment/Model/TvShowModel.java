package com.example.finalassignment.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class TvShowModel implements Parcelable {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("backdrop_path")
    private String backdrop_image;
    @SerializedName("poster_path")
    private String poster_image;
    @SerializedName("first_air_date")
    private String first_air_date;
    @SerializedName("vote_average")
    private Float rating;
    @SerializedName("overview")
    private String synopsis;
    @SerializedName("original_language")
    private String language;
    @SerializedName("popularity")
    private Number popularity;

    public TvShowModel(int id, String name, String backdrop_image, String poster_image, String first_air_date, Float rating, String synopsis, String language, Number popularity) {
        this.id = id;
        this.name = name;
        this.backdrop_image = backdrop_image;
        this.poster_image = poster_image;
        this.first_air_date = first_air_date;
        this.rating = rating;
        this.synopsis = synopsis;
        this.language = language;
        this.popularity = popularity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackdrop_image() {
        return backdrop_image;
    }

    public void setBackdrop_image(String backdrop_image) {
        this.backdrop_image = backdrop_image;
    }

    public String getPoster_image() {
        return poster_image;
    }

    public void setPoster_image(String poster_image) {
        this.poster_image = poster_image;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Number getPopularity() {
        return popularity;
    }

    public void setPopularity(Number popularity) {
        this.popularity = popularity;
    }

    protected TvShowModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        backdrop_image = in.readString();
        poster_image = in.readString();
        first_air_date = in.readString();
        if (in.readByte() == 0) {
            rating = null;
        } else {
            rating = in.readFloat();
        }
        synopsis = in.readString();
        language = in.readString();
        popularity = in.readDouble();
    }

    public static final Creator<TvShowModel> CREATOR = new Creator<TvShowModel>() {
        @Override
        public TvShowModel createFromParcel(Parcel in) {
            return new TvShowModel(in);
        }

        @Override
        public TvShowModel[] newArray(int size) {
            return new TvShowModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(backdrop_image);
        parcel.writeString(poster_image);
        parcel.writeString(first_air_date);
        if (rating == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeFloat(rating);
        }
        parcel.writeString(synopsis);
        parcel.writeString(language);
        parcel.writeDouble(popularity.doubleValue());
    }
}

