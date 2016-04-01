package com.smartholiday.exam;

import com.smartholiday.exam.model.FavoriteSong;

import java.util.*;
import java.util.function.Function;
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

        Map<FavoriteSong, Long> songsByOccurrence = Stream.of(playlistContent.split(System.lineSeparator()))
                .map(this::lineToSong)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return songsByOccurrence.entrySet().stream()
                .sorted(Comparator.comparingDouble(entry -> -entry.getValue()))
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
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
