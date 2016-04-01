package com.smartholiday.exam;

import com.smartholiday.exam.model.FavoriteSong;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PlaylistParserTest {

    @Test
    public void should_load_empty_file() throws Exception {
        PlaylistParser playlistParser = new PlaylistParser();
        String playlistContent = "";

        List favoriteSongs = playlistParser.getFavoriteSongs(createPlayList(playlistContent));
        assertThat(favoriteSongs).isEqualTo(Lists.emptyList());
    }

    @Test
    public void should_load_one_line_file() throws Exception {
        PlaylistParser playlistParser = new PlaylistParser();
        String playlistContent = createPlayList("Animal Collective|My Girls");

        List favoriteSongs = playlistParser.getFavoriteSongs(createPlayList(playlistContent));

        List<FavoriteSong> expected = Arrays.asList(
                new FavoriteSong("Animal Collective", "My Girls")
        );
        assertThat(favoriteSongs).isEqualTo(expected);
    }

    @Test
    public void should_load_two_indentic_songs() throws Exception {
        PlaylistParser playlistParser = new PlaylistParser();
        String playlistContent = createPlayList(
                "Animal Collective|My Girls",
                "Animal Collective|My Girls"
        );

        List favoriteSongs = playlistParser.getFavoriteSongs(playlistContent);

        List<FavoriteSong> expected = Arrays.asList(
                new FavoriteSong("Animal Collective", "My Girls")
        );
        assertThat(favoriteSongs).isEqualTo(expected);
    }

    private String createPlayList(String... songs) {
        return Stream.of(songs).collect(Collectors.joining(System.lineSeparator()));
    }

    private String loadFile(String filename) throws IOException {
        return new String(Files.readAllBytes(Paths.get(createPlayList(filename))));
    }
}