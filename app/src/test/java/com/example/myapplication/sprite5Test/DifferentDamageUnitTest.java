package com.example.myapplication.sprite5Test;

import static org.junit.Assert.assertNotEquals;


import com.example.myapplication.Model.entities.Player.playerStartegy.CharOne;
import com.example.myapplication.Model.entities.Player.playerStartegy.PlayerCharStrategy;
import com.example.myapplication.Model.entities.Player.playerStates.PlayerStates;

import org.junit.Test;

public class DifferentDamageUnitTest {
    @Test
    public void differentDamageUnitTest() {
        //player make different amount of damage during different state.

        PlayerStates atkState = PlayerStates.ATTACK;
        PlayerStates skillState = PlayerStates.SKILL_ONE;


        PlayerCharStrategy playerCharStrategy = new CharOne();
        int baseDamageNum = 100;

        assertNotEquals(
                playerCharStrategy.getCurrentDamage(atkState, baseDamageNum),
                playerCharStrategy.getCurrentDamage(skillState, baseDamageNum)
        );

    }
}
