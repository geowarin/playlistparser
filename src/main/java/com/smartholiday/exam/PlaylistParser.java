package com.smartholiday.exam;

import com.smartholiday.exam.model.FavoriteSong;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlaylistParser {

    /**
     * @param playlistContent a string representation of the playlist content
     * @return a list of string representation of a song
     */
    public List<FavoriteSong> getFavoriteSongs(final String playlistContent) {
        if (playlistContent.trim().isEmpty()) {
            return Collections.emptyList();
        }

        Set<FavoriteSong> unique = Stream.of(playlistContent.split(System.lineSeparator()))
                .map(this::lineToSong)
                .collect(Collectors.toSet());
        return new ArrayList<>(unique);
    }

    private FavoriteSong lineToSong(String line) {
        String[] data = line.split("\\|");
        invariant(data.length == 2, "Could not parse line " + line);

        String artist = data[0];
        String name = data[1];
        return new FavoriteSong(artist, name);
    }

    private void invariant(boolean condition, String message) {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }
}
