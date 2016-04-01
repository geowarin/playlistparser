package com.smartholiday.exam;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class PlaylistParserTest {

    @Test
    public void should_load_empty_file() throws Exception {
        PlaylistParser playlistParser = new PlaylistParser();
        assertThat(playlistParser.getFavoriteSongs("")).isEqualTo(Lists.emptyList());
    }

    private String loadFile(String filename) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filename)));
    }
}