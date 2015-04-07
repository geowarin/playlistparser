package com.smartholiday.exam;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class PlaylistParserTest
{
    @Test
    public void testForEmptyPlaylist() {
        Assert.assertEquals(new ArrayList<String>(), new PlaylistParser().getFavoriteSongs(""));
    }
}
