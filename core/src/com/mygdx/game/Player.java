package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;

import java.util.ArrayList;

public class Player {

    public float playerX = DefenseGames.windowsWidth/3.f;
    public float playerY = DefenseGames.windowsHeight/2.f;
    public static final float Speed = 4;
    public Texture soldier = new Texture("IdleLeft.png");
    ArrayList<Bullet> bullets = new ArrayList<>();
    private TextureAtlas animationLeftMove;
    private TextureAtlas getAnimationRightMove;
    private Animation animation;
    private float playTime = 0f;


    Building build = new Building();

    boolean faceLeft = true;


    /**
     * Draw the image "Soldier" on the game board
     */
    public void setSoldier(DefenseGames game){

        game.batch.draw(soldier, playerX, playerY);

    }


    /**
     * Setting the input "Key-K" for shooting action
     */
    public void shot(DefenseGames game, float time, ArrayList<Enemies> enemy){
        if (Gdx.input.isKeyJustPressed(Input.Keys.K)){
            bullets.add(new Bullet(playerX+20, playerY + 35));
        }

        ArrayList<Bullet> bulletToRemove = new ArrayList<>();
        for (Bullet bullet : bullets){
            bullet.update(time,faceLeft);
            if (bullet.remove){
                bulletToRemove.add(bullet);

            }
        }
        for (Bullet bullet: bullets){
            for (Enemies enemies: enemy){
                if (bullet.getCollision().isCollide(enemies.getCollision())){
                    bulletToRemove.add(bullet);
                }
            }
        }

        bullets.removeAll(bulletToRemove);

        //for (Bullet bullet : bullets){
          //  bullet.render(game.batch);
        //}


    }

    /**
     * Setting keys for movement action:
     * Key-W: up
     * Key-S: down
     * Key-A: left
     * Key-D: right
     */
    public void movement(){

        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            //playerY += Speed;
            // set collision for character when moving up
            if(playerX >= Building.BuildX - 40 && playerX <= Building.BuildX + 80 && playerY >= Building.BuildY - 80 && playerY <= Building.BuildY + 80){
                System.out.println("ERROR");
            }
            else if(playerY > 700){
                System.out.println("ERROR");
            }
            else{
                playerY += Speed;
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            //playerY -= Speed;
            // set collision for character when moving down
            if(playerX >= Building.BuildX - 40 && playerX <= Building.BuildX + 80 && playerY >= Building.BuildY - 70 && playerY <= Building.BuildY + 100){
                System.out.println("ERROR");
            }
            else if(playerY < 0){
                System.out.println("ERROR");
            }
            else{
                playerY -= Speed;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            if(!faceLeft){
                bullets.removeAll(bullets);
                faceLeft = true;
            }
            soldier = new Texture("IdleLeft.png");
            //playerX -= Speed;
            // set collision for character when moving left
            if(playerX >= Building.BuildX - 40 && playerX <= Building.BuildX + 90 && playerY >= Building.BuildY - 70 && playerY <= Building.BuildY + 80){
                System.out.println("ERROR");
            }
            else if(playerX < 0){
                System.out.println("ERROR");
            }
            else{
                playerX -= Speed;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            if(faceLeft){
                bullets.removeAll(bullets);
                faceLeft = false;
            }
            soldier = new Texture("IdleRight.png");
            //playerX += Speed;
            // set collision for character when moving right
            if(playerX >= Building.BuildX - 60 && playerX <= Building.BuildX + 60 && playerY >= Building.BuildY - 70 && playerY <= Building.BuildY + 80){
                System.out.println("ERROR");
            }
            else if(playerX > 1310){
                System.out.println("ERROR");
            }
            else{
                playerX += Speed;
            }
        }

    }


    public ArrayList<Bullet> getBullets(){
        return this.bullets;
    }

    /**
     * Dispose player if we don't need it anymore.
     */
    public void disposePlayer(){
        soldier.dispose();
    }




}
