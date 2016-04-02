package com.smartholiday.exam;

import com.smartholiday.exam.model.FavoriteSong;
import com.smartholiday.exam.model.Song;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class PlaylistParser {

    /**
     * @param playlistContent a string representation of the playlist content
     * @return a list of string representation of a song
     */
    public List<FavoriteSong> getFavoriteSongs(final String playlistContent) {
        if (playlistContent.trim().isEmpty()) {
            return Collections.emptyList();
        }

        Map<Song, Long> songsByOccurrence = split(playlistContent)
                .map(this::parseLine)
                .collect(groupByCount());

        return entries(songsByOccurrence)
                .sorted(byValuesDesc())
                .limit(5)
                .map(songEntry -> new FavoriteSong(songEntry.getKey(), songEntry.getValue()))
                .collect(toList());
    }

    private Song parseLine(String line) {
        String[] data = line.split("\\|");
        invariant(data.length == 2 || data.length == 3, "Could not parse line " + line);

        String artist = data[0];
        String name = data[1];
        String classifier = data.length > 2 ? data[2] : null;
        return new Song(artist, name, classifier);
    }

    private Comparator<Map.Entry<Song, Long>> byValuesDesc() {
        return Comparator.comparingDouble(entry -> -entry.getValue());
    }

    private Stream<Map.Entry<Song, Long>> entries(Map<Song, Long> songsByOccurrence) {
        return songsByOccurrence.entrySet().stream();
    }

    private Stream<String> split(String playlistContent) {
        return Stream.of(playlistContent.split("\n|\r\n"));
    }

    private Collector<Song, ?, Map<Song, Long>> groupByCount() {
        return groupingBy(Function.identity(), Collectors.counting());
    }

    private void invariant(boolean condition, String message) {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }
}
