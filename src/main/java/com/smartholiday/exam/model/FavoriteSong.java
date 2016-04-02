package com.smartholiday.exam.model;

import java.util.Objects;

public class FavoriteSong {
    private final String artist;
    private final String name;
    private final String classifier;

    public FavoriteSong(String artist, String name) {
        this.artist = artist;
        this.name = name;
        this.classifier = null;
    }

    public FavoriteSong(String artist, String name, String classifier) {
        this.artist = artist;
        this.name = name;
        this.classifier = classifier;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteSong that = (FavoriteSong) o;
        return Objects.equals(artist, that.artist) &&
                Objects.equals(name, that.name) &&
                Objects.equals(classifier, that.classifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artist, name, classifier);
    }

    @Override
    public String toString() {
        return "FavoriteSong{" +
                "artist='" + artist + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
