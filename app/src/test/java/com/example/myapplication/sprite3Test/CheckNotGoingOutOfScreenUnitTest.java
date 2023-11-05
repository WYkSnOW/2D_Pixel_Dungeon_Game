package com.example.myapplication.sprite3Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.myapplication.Model.environments.GameMap;

import org.junit.Test;

public class CheckNotGoingOutOfScreenUnitTest {

    @Test
    public void checkNotGoingOutOfScreenUnitTest() {
        float mapWidth = 10;
        float mapHeight = 10;
        //this set of position is out of edge(negative value)
        float x1 = -1;
        float yTop1 = -1;
        float yBottom1 = -1;
        assertFalse(GameMap.checkEdge(x1, yTop1, yBottom1, mapWidth, mapHeight));

        //this set of position is out of edge(larger than mapWidth and mapHeight)
        float x2 = 11;
        float yTop2 = 11;
        float yBottom2 = 11;
        assertFalse(GameMap.checkEdge(x2, yTop2, yBottom2, mapWidth, mapHeight));

        //this set of position is in the edge
        float x3 = 5;
        float yTop3 = 5;
        float yBottom3 = 5;
        assertTrue(GameMap.checkEdge(x3, yTop3, yBottom3, mapWidth, mapHeight));




    }
}
