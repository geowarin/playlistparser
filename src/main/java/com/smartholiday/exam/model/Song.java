package com.smartholiday.exam.model;

import com.google.common.base.MoreObjects;

import java.util.Objects;

public class Song {
    private final String artist;
    private final String name;
    private final String classifier;

    public Song(String artist, String name) {
        this.artist = artist;
        this.name = name;
        this.classifier = null;
    }

    public Song(String artist, String name, String classifier) {
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
        Song that = (Song) o;
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
        return MoreObjects.toStringHelper(this)
                .add("artist", artist)
                .add("name", name)
                .add("classifier", classifier)
                .toString();
    }
}
