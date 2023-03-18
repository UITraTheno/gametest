package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyRunnable implements Runnable{

    Texture enemy = new Texture("BoostRight.png");
    public static final float Speed = 200;
    public boolean remove = false;
    private float locX = 0;
    private float locY;
    private float time;

    DefenseGames game = new DefenseGames();




    public MyRunnable(float y, float time,DefenseGames game){
        this.game = game;
        this.locY = y;
        this.time = time;
    }

    @Override
    public void run() {
        locX += Speed * time;
        if(locX > 1366){
            remove = true;
        }
        game.batch.draw(enemy,locX,locY,40,20);
    }


}
