package com.smartholiday.exam;

import com.smartholiday.exam.model.FavoriteSong;
import com.smartholiday.exam.model.Song;

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

        Map<Song, Long> songsByOccurrence = Stream.of(playlistContent.split("\n|\r\n"))
                .map(this::lineToSong)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return songsByOccurrence.entrySet().stream()
                .sorted(Comparator.comparingDouble(entry -> -entry.getValue()))
                .limit(5)
                .map(songEntry -> new FavoriteSong(songEntry.getKey(), songEntry.getValue()))
                .collect(Collectors.toList());
    }

    private Song lineToSong(String line) {
        String[] data = line.split("\\|");
        invariant(data.length == 2 || data.length == 3, "Could not parse line " + line);

        String artist = data[0];
        String name = data[1];
        String classifier = data.length > 2 ? data[2] : null;
        return new Song(artist, name, classifier);
    }

    private void invariant(boolean condition, String message) {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }
}
