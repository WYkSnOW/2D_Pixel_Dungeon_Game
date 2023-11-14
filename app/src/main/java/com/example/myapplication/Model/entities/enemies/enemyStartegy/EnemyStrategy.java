package com.example.myapplication.Model.entities.enemies.enemyStartegy;

import android.graphics.PointF;
import android.graphics.RectF;

import com.example.myapplication.Model.entities.enemies.normalEnemies.AbstractEnemy;
import com.example.myapplication.Model.entities.enemies.enemyStates.EnemyStates;
import com.example.myapplication.Model.helper.GameConstants;

public interface EnemyStrategy {
    abstract int getAnimMaxIndex(EnemyStates enemyStates);
    abstract PointF getAtkBoxSize();
    default PointF getAtkBoxPos(AbstractEnemy enemy) {
        PointF size = getAtkBoxSize();
        if (enemy.getFaceDir() == GameConstants.FaceDir.RIGHT) {
            return new PointF(enemy.getHitBox().right, enemy.getHitBox().bottom - size.y);
        } else {
            return new PointF(enemy.getHitBox().left - size.x,  enemy.getHitBox().bottom - size.y);
        }
    }

//    default RectF getAtkHitBox(AbstractEnemy enemy) {
//        PointF pos = getAtkBoxPos(enemy);
//        PointF size = getAtkBoxSize();
//        return new RectF(pos.x, pos.y, pos.x + size.x, pos.y + size.y);
//
//    }

    abstract float adjustX(EnemyStates states, float scale);
    abstract float adjustY(EnemyStates states, float scale);


    default PointF getAtkHitBoxSize() {
        return new PointF(GameConstants.Sprite.SIZE * 2, GameConstants.Sprite.SIZE);
    }

    default PointF getAtkRangeSize() {
        return new PointF(GameConstants.Sprite.SIZE, GameConstants.Sprite.SIZE);
    }

    default boolean isMakingDamage(EnemyStates state, int idx) {
        if (state == EnemyStates.ATK) {
            return (13 <= idx && idx <= 16);
        }
        return false;
    }


}
