package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import com.example.myapplication.Model.entities.GameCharacters;
import com.example.myapplication.Model.entities.Player.Player;
import org.junit.Test;
import java.time.LocalDate;

public class StartHealthBaseOnDifficultyUnitTest {

    @Test
    public void testIsNameValid() {

        // get startHealth when difficulty is 1(easiest)
        int difficultyOne = 1;
        int healthWhenDiffOne
                = Player.calculateStartingHealth(difficultyOne);

        // get startHealth when difficulty is 2
        int difficultyTwo = 2;
        int healthWhenDiffTwo
                = Player.calculateStartingHealth(difficultyTwo);

        // get startHealth when difficulty is 3(hardest)
        int difficultyThree = 3;
        int healthWhenDiffThree
                = Player.calculateStartingHealth(difficultyThree);

        assertEquals(healthWhenDiffOne, 100);
        // expect that player's start health is 100 when difficulty is 1
        assertEquals(healthWhenDiffTwo, 50);
        // expect that player's start health is 50 when difficulty is 2
        assertEquals(healthWhenDiffThree, 33);
        // expect that player's start health is 33 when difficulty is 3

        // expect that player have different start health when difficulty is different
        assertNotEquals(healthWhenDiffOne, healthWhenDiffTwo);
        assertNotEquals(healthWhenDiffOne, healthWhenDiffThree);
        assertNotEquals(healthWhenDiffTwo, healthWhenDiffThree);

    }
}
