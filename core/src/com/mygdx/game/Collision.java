package com.mygdx.game;

public class Collision {
    float x,y;
    int width,height;

    public Collision(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move(float x, float y){
        this.x = x;
        this.y = y;
    }

    public boolean isCollide(Collision c){
        return( x < c.x + c.width && y < c.y + c.height && x > c.x - width && y > c.y - c.height);
    }

}
