package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Building {

    private int buildingHealth = 20;
    public static final float BuildX = DefenseGames.windowsWidth/2.f - 40;
    public static final float BuildY = DefenseGames.windowsHeight/2.f - 40;

    public Texture Fortress = new Texture("Fortress.png");

    public void setBuilding(DefenseGames game){
        game.batch.draw(Fortress,BuildX,BuildY,100,100);
    }

    Collision collision = new Collision(BuildX,BuildY,100,100);

    public int getBuildingHealth(){
        return buildingHealth;
    }

    public Collision getCollision(){
        return collision;
    }

    public void getDamage(){
        buildingHealth --;
    }

    public void disposeBuilding(){
        Fortress.dispose();
    }
}
