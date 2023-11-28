package com.example.myapplication.sprite5Test;

import static org.junit.Assert.assertNotEquals;


import com.example.myapplication.Model.entities.Player.playerStartegy.CharOne;
import com.example.myapplication.Model.entities.Player.playerStartegy.PlayerCharStrategy;
import com.example.myapplication.Model.entities.Player.playerStates.PlayerStates;

import org.junit.Test;

public class DifferentDamageUnitTest {
    @Test
    public void differentDamageUnitTest() {
        //player enforces different amount of damage during different player states.

        PlayerStates atkState = PlayerStates.ATTACK;
        PlayerStates skillState = PlayerStates.SKILL_ONE;


        PlayerCharStrategy playerCharStrategy = new CharOne();
        int baseDamage = 100;

        assertNotEquals(
                playerCharStrategy.getCurrentDamage(atkState, baseDamage),
                playerCharStrategy.getCurrentDamage(skillState, baseDamage)
        );

    }
}
