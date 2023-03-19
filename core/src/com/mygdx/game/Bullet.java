package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.mygdx.game.Screens.MainGameScreen;

public class Bullet {

    public static final int SpeedB = 500;
    private static Texture bullet;
    float BulletX,BulletY;

    Collision collision;

    public boolean remove = false;

    public Bullet(float x, float y){
        this.BulletX = x;
        this.BulletY = y;
        this.collision = new Collision(BulletX,BulletY,60,20,1);
        if(bullet == null){
            bullet = new Texture("bullet.png");
        }
    }

    // check the state of the bullet
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

    public void render (SpriteBatch batch){
        batch.draw(bullet,BulletX,BulletY,9,4);
    }


    public Collision getCollision(){
        return collision;
    }

}
