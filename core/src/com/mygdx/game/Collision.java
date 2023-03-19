package com.mygdx.game;

public class Collision {
    int objID = 0; // 1 - player, building bullet     2 - enemy
    float x,y;
    int width,height;

    public Collision(float x, float y, int width, int height, int objid){
        this.x = x;
        this.y = y;
        this.width = width;
        objID = objid;
        this.height = height;
    }

    public void move(float x, float y){
        this.x = x;
        this.y = y;
    }

    public boolean isCollide(Collision c){
        if (objID != c.objID){
            return( x < c.x + c.width && y < c.y + c.height && x > c.x - width && y > c.y - c.height);
        }
        return false;
    }

}
