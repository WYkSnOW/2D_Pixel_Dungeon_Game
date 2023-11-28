package com.example.myapplication.Model.entities.enemies.enemyStartegy;

import android.graphics.PointF;
import android.graphics.RectF;

import com.example.myapplication.Model.entities.enemies.normalEnemies.AbstractEnemy;
import com.example.myapplication.Model.entities.enemies.enemyStates.EnemyStates;
import com.example.myapplication.Model.helper.GameConstants;
import com.example.myapplication.R;

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

    abstract int getScore();

    abstract float adjustX(EnemyStates states, float scale);
    abstract float adjustY(EnemyStates states, float scale);


    abstract PointF getAtkHitBoxSize();

    abstract PointF getAtkDetectSize();

    default float getYBottom(float bottom, float scale) {
        return bottom - (GameConstants.Sprite.SIZE * 0.3f);
    }

    default RectF getAtkDetectBox(RectF enemy, int faceDir, float scale) {
        PointF size = getAtkDetectSize();
        float bottom = getYBottom(enemy.bottom, scale);

        if (faceDir == GameConstants.FaceDir.RIGHT) {
            return new RectF(enemy.right,
                    bottom - size.y,
                    enemy.right + size.x,
                    bottom);
        } else {
            return new RectF(enemy.left - size.x,
                    bottom - size.y,
                    enemy.left,
                    bottom);
        }
    }

    abstract boolean isMakingDamage(EnemyStates state, int idx);


}
