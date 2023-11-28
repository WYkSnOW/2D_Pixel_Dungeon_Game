package com.example.myapplication.sprite5Test;

import static org.junit.Assert.assertNotEquals;

import com.example.myapplication.Model.environments.InitMap;

import org.junit.Test;

public class DifferentMapUnitTest {

    @Test
    public void differentMapUnitTest() {

        assertNotEquals(InitMap.initMapOne(), InitMap.initMapThree());
        assertNotEquals(InitMap.initMapOne(), InitMap.initMapTwo());
        assertNotEquals(InitMap.initMapTwo(), InitMap.initMapThree());
    }
}
