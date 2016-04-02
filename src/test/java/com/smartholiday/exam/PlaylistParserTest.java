package com.smartholiday.exam;

import com.smartholiday.exam.model.FavoriteSong;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PlaylistParserTest {

    @Test
    public void should_load_empty_file() throws Exception {
        PlaylistParser playlistParser = new PlaylistParser();
        String playlistContent = "";

        List favoriteSongs = playlistParser.getFavoriteSongs(createPlayList(playlistContent));
        assertThat(favoriteSongs).isEmpty();
    }

    @Test
    public void should_load_one_line_file() throws Exception {
        List<FavoriteSong> favoriteSongs = getFavoriteSongs(
                "Animal Collective|My Girls"
        );

        assertThat(favoriteSongs).containsExactly(
                new FavoriteSong("Animal Collective", "My Girls")
        );
    }

    @Test
    public void should_load_two_identical_songs() throws Exception {
        List<FavoriteSong> favoriteSongs = getFavoriteSongs(
                "Animal Collective|My Girls",
                "Animal Collective|My Girls"
        );

        assertThat(favoriteSongs).containsExactly(
                new FavoriteSong("Animal Collective", "My Girls")
        );
    }

    @Test
    public void should_handle_more_than_five_songs() throws Exception {
        List<FavoriteSong> favoriteSongs = getFavoriteSongs(
                "song1|band1",
                "song2|band1",
                "song3|band1",
                "song4|band1",
                "song5|band1",
                "song6|band1"
        );

        assertThat(favoriteSongs).hasSize(5);
    }

    @Test
    public void should_sort_songs() throws Exception {
        List<FavoriteSong> favoriteSongs = getFavoriteSongs(
                "song2|band1",
                "song2|band1",
                "song2|band1",
                "song1|band1",
                "song1|band1"
        );

        assertThat(favoriteSongs).containsExactly(
                new FavoriteSong("song2", "band1"),
                new FavoriteSong("song1", "band1")
        );
    }

    private List<FavoriteSong> getFavoriteSongs(String... playlist) {
        PlaylistParser playlistParser = new PlaylistParser();
        String playlistContent = createPlayList(playlist);

        return playlistParser.getFavoriteSongs(playlistContent);
    }

    private String createPlayList(String... songs) {
        return Stream.of(songs).collect(Collectors.joining(System.lineSeparator()));
    }
}