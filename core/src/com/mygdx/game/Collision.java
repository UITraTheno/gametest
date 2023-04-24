package com.mygdx.game;

/**
 *  Class name: Enemies.java
 *  Yilin Li
 *  Description: This class will be used for collision management includes the methods:
 *  isCollide(Collision c): it will used to check if two target objects has collision or not
 *  move(float x, float y): update the location of collision following target's location.
 */

public class Collision {
    int objID = 0; // 1 - player, building bullet     2 - enemy
    float x,y;
    int width,height;

    /**
     * Initial the information of collision for each object. If target is player, build, bullet, object ID is 1.
     * Otherwise, object ID is 2. This method also initial the location and range of collision.
     */
    public Collision(float x, float y, int width, int height, int objid){
        this.x = x;
        this.y = y;
        this.width = width;
        objID = objid;
        this.height = height;
    }

    // initial the collision location following the location of target
    public void move(float x, float y){
        this.x = x;
        this.y = y;
    }

    // check if there is a collision between two objects
    public boolean isCollide(Collision c){
        if (objID != c.objID){
            return( x < c.x + c.width && y < c.y + c.height && x > c.x - width && y > c.y - c.height);
        }
        return false;
    }

}
