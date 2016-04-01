package com.smartholiday.exam;

import com.smartholiday.exam.model.FavoriteSong;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlaylistParserTest {

    @Test
    public void should_load_empty_file() throws Exception {
        PlaylistParser playlistParser = new PlaylistParser();
        String playlistContent = "";

        List favoriteSongs = playlistParser.getFavoriteSongs(playlistContent);
        assertThat(favoriteSongs).isEqualTo(Lists.emptyList());
    }

    @Test
    public void should_load_one_line_file() throws Exception {
        PlaylistParser playlistParser = new PlaylistParser();
        String playlistContent = "Animal Collective|My Girls";

        List favoriteSongs = playlistParser.getFavoriteSongs(playlistContent);

        List<FavoriteSong> expected = Arrays.asList(
                new FavoriteSong("Animal Collective", "My Girls")
        );
        assertThat(favoriteSongs).isEqualTo(expected);
    }

    private String loadFile(String filename) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filename)));
    }
}