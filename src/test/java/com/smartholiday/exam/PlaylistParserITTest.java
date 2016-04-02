package com.smartholiday.exam;

import com.smartholiday.exam.model.FavoriteSong;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlaylistParserITTest {

    @Test
    public void should_load_empty_file() throws Exception {
        String fileContent = loadFile("/playlist_input.txt");

        List<FavoriteSong> favoriteSongs = new PlaylistParser().getFavoriteSongs(fileContent);
        assertThat(favoriteSongs).containsExactly(
                new FavoriteSong("Yeah Yeah Yeahs", "Zero"),
                new FavoriteSong("Lady Gaga", "Bad Romance"),
                new FavoriteSong("Animal Collective", "What Would I Want? Sky"),
                new FavoriteSong("Animal Collective", "Brother Sport"),
                new FavoriteSong("Yeah Yeah Yeahs", "Heads Will Roll")
        );
    }

    private String loadFile(String filename) throws IOException, URISyntaxException {
        return new String(Files.readAllBytes(Paths.get(getClass().getResource(filename).toURI())));
    }
}