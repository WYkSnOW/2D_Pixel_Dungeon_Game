package com.example.myapplication.sprite5Test;

import static org.junit.Assert.assertNotEquals;

import android.graphics.PointF;
import android.view.RoundedCorner;

import com.example.myapplication.Model.entities.enemies.enemyStartegy.EnemyStrategy;
import com.example.myapplication.Model.entities.enemies.enemyStartegy.OgreStrategy;
import com.example.myapplication.Model.entities.enemies.enemyStartegy.RogueGoblinStrategy;
import com.example.myapplication.Model.entities.enemies.enemyStates.EnemyStates;

import org.junit.Test;

public class DifferentAttackRangeUnitTest {
    @Test
    public void differentAttackRange() {

        EnemyStrategy ogreStrategy = new OgreStrategy();
        EnemyStrategy rogueGoblinStrategy = new RogueGoblinStrategy();

        PointF atkRange1 = ogreStrategy.getAtkHitBoxSize();
        PointF atkRange2 = rogueGoblinStrategy.getAtkHitBoxSize();

        assertNotEquals(atkRange1, atkRange2);

    }
}
