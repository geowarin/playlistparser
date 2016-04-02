package com.smartholiday.exam.model;

import com.google.common.base.MoreObjects;

import java.util.Objects;

public class FavoriteSong {
    private final String artist;
    private final String name;
    private final String classifier;
    private final long listenings;

    public FavoriteSong(Song song, long listenings) {
        this.artist = song.getArtist();
        this.name = song.getName();
        this.classifier = song.getClassifier();
        this.listenings = listenings;
    }

    public FavoriteSong(String artist, String name, long listenings) {
        this.artist = artist;
        this.name = name;
        this.classifier = null;
        this.listenings = listenings;
    }

    public String getArtist() {
        return artist;
    }

    public String getName() {
        return name;
    }

    public String getClassifier() {
        return classifier;
    }

    public long getListenings() {
        return listenings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteSong that = (FavoriteSong) o;
        return listenings == that.listenings &&
                Objects.equals(artist, that.artist) &&
                Objects.equals(name, that.name) &&
                Objects.equals(classifier, that.classifier);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("artist", artist)
                .add("name", name)
                .add("classifier", classifier)
                .add("listenings", listenings)
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(artist, name, classifier, listenings);
    }

}
