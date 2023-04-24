package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;


/**
 *  Class name: Building.java
 *  Yilin Li
 *  Description: In this class, I designed the method for building management includes getting building's health,
 *  getting damage from the enemy, checking collision with building, set location and size of the building.
 */

public class Building {

    // initial the size of the image "Building"
    private int buildingHealth = 20;
    public static final float BuildX = DefenseGames.windowsWidth/2.f - 40;
    public static final float BuildY = DefenseGames.windowsHeight/2.f - 40;

    // initial the texture to be Fortress.png
    public Texture Fortress = new Texture("Fortress.png");

    // set the building following initial location I set
    public void setBuilding(DefenseGames game){
        game.batch.draw(Fortress,BuildX,BuildY,100,100);
    }

    // add collision for the building, it will be used for checking if there is the collision between building and enemy
    Collision collision = new Collision(BuildX,BuildY,100,100,1);

    // return the integer value for building's health
    public int getBuildingHealth(){
        return buildingHealth;
    }

    // check if there is collision with building
    public Collision getCollision(){
        return collision;
    }

    // if there is the collision with building, getting damage
    public void getDamage(){
        buildingHealth --;
    }

    // depose building from the game if the game is over
    public void disposeBuilding(){
        Fortress.dispose();
    }
}
