package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.mygdx.game.Screens.MainGameScreen;

/**
 *  Class name: Bullet.java
 *  Yilin Li
 *  Description: This class will be used for Bullet management which includes the methods:
 *  update(): updating the state and location for current bullet
 *  render (SpriteBatch batch): draw the bullet in the game
 *  Collision getCollision(): check if there is the collision between bullet and target objects.
 */

public class Bullet {

    public static final int SpeedB = 500;
    private static Texture bullet;
    float BulletX,BulletY;

    Collision collision;

    public boolean remove = false;

    /**
     * initial the collision, size, and location for the bullet
     */
    public Bullet(float x, float y){
        this.BulletX = x;
        this.BulletY = y;
        this.collision = new Collision(BulletX,BulletY,60,20,1);
        if(bullet == null){
            bullet = new Texture("bullet.png");
        }
    }

    /**
     * updating current state and location for each bullet object
     */
    public void update(float time,boolean faceleft){
        if(faceleft){
            BulletX -= SpeedB * time;
        }
        if(!faceleft){
            BulletX += SpeedB * time;
        }
        if(BulletX > Gdx.graphics.getWidth() || BulletX < 0) {
            remove = true;
        }

        collision.move(BulletX,BulletY);
    }

    // draw the bullet image into the game
    public void render (SpriteBatch batch){
        batch.draw(bullet,BulletX,BulletY,9,4);
    }


    // check if bullet has collision with other object
    public Collision getCollision(){
        return collision;
    }

}
