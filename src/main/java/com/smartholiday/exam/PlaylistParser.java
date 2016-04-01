package com.smartholiday.exam;

import com.smartholiday.exam.model.FavoriteSong;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlaylistParser {

    /**
     * @param playlistContent a string representation of the playlist content
     * @return a list of string representation of a song
     */
    public List<FavoriteSong> getFavoriteSongs(final String playlistContent) {
        ArrayList<FavoriteSong> favoriteSongs = new ArrayList<>();
        try (Scanner scanner = new Scanner(playlistContent)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split("\\|");

                invariant(data.length == 2, "Could not parse line " + line);

                String artist = data[0];
                String name = data[1];
                favoriteSongs.add(new FavoriteSong(artist, name));
            }
        }
        return favoriteSongs;
    }

    private void invariant(boolean condition, String message) {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }
}
