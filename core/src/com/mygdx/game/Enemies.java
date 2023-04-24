package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *  Class name: Enemies.java
 *  Yilin Li
 *  Description: This class will be used for enemy management which includes the method:
 *  update(float time,boolean right): update the location for the current enemy.
 *  render (SpriteBatch batch): draw the enemy in the game
 *  Collision getCollision(): check if there is a collision between enemy and target object.
 */

public class Enemies {

    float x,y;

    Collision collision;
    public boolean remove = false;

    public static final int SpeedEnemy = 100;
    public static Texture enemy;

    // Initialize the location, and size of the enemy.
    public Enemies(float y, boolean right){
        //this.x = x;
        this.y = y;
        if (enemy == null){
            if(right){
                this.x = 0;
                enemy = new Texture("BoostRight.png");
            }
            else{
                this.x = 1366;
                enemy = new Texture("BoostLeft.png");
            }
        }
        this.collision = new Collision(x,y,60,30,2);
    }

    // update the location of the enemies. If they have collision which other objects, move it out
    public void update(float time,boolean right){

        if(right){
            x += SpeedEnemy * time;
            if (y < Building.BuildY){
                y += (SpeedEnemy-50) * time;
            }
            else{
                y -= (SpeedEnemy-50) * time;
            }
            if (x >= 1366){
                remove = true;
            }
        }
        else{
            x -= SpeedEnemy * time;
            if (y < Building.BuildY){
                y += SpeedEnemy * time;
            }
            else{
                y -= SpeedEnemy * time;
            }
            if (x < 0){
                remove = true;
            }
        }
        collision.move(x,y);
    }

    // render enemy image
    public void render (SpriteBatch batch){
        batch.draw(enemy,x,y,40,20);
    }

    // check if enemy get collision with other object
    public Collision getCollision(){
        return collision;
    }



}
