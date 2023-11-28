package com.example.myapplication.sprite5Test;

import static org.junit.Assert.assertNotEquals;

import com.example.myapplication.Model.environments.InitMap;

import org.junit.Test;


public class DifferentMapUnitTest {

    //Tests if different maps are used in the game and that they are changed when player moves back and forth- Nikhil
    @Test
    public void differentMapUnitTest() {

        assertNotEquals(InitMap.initMapOne(), InitMap.initMapThree());
        assertNotEquals(InitMap.initMapOne(), InitMap.initMapTwo());
        assertNotEquals(InitMap.initMapTwo(), InitMap.initMapThree());
    }
}

