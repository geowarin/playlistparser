package com.smartholiday.exam.model;

import java.util.Objects;

public class FavoriteSong {
    private final String artist;
    private final String name;

    public FavoriteSong(String artist, String name) {
        this.artist = artist;
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteSong that = (FavoriteSong) o;
        return Objects.equals(artist, that.artist) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artist, name);
    }

    @Override
    public String toString() {
        return "FavoriteSong{" +
                "artist='" + artist + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
