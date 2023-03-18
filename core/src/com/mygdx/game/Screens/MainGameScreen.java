package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.*;

import java.util.ArrayList;
import java.util.Random;


public class MainGameScreen implements Screen {

    DefenseGames game;
    Player player = new Player();
    Building build = new Building();
    Texture Background;
    Music BackgroundMusic;

    float enemySpawnTimer;
    ArrayList<Enemies> enemy;
    Random random;
    BitmapFont scoreFont;
    Score score = new Score();

    public static final float Min_SpawnTime = 1.8f;
    public static final  float Max_SpawnTime = 2.f;
    public MainGameScreen(DefenseGames game){
        this.game = game;
        scoreFont = new BitmapFont(Gdx.files.internal("score.fnt"));
        BackgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Game-BGM.mp3"));
        random = new Random();
        enemySpawnTimer = random.nextFloat() * (Max_SpawnTime-Min_SpawnTime) + Min_SpawnTime; // time period to create the enemies
        enemy = new ArrayList<>();


    }


    @Override
    public void show() {
 //       soldier = new Texture("IdleLeft.png");
        Background = new Texture("Game-map.png");
        // Play the BGM

        BackgroundMusic.setVolume(0.1f);
        BackgroundMusic.setLooping(true);
        BackgroundMusic.play();


    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);
// Player movement following key inputs
        /**
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            playerY += Speed;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            playerY -= Speed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            playerX -= Speed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            playerX += Speed;
        }
*/
        game.batch.begin(); // start to batch images for the game


        player.movement();
        game.batch.draw(Background, 0, 0);
        build.setBuilding(game);
        enemySpawnTimer -= delta;
        if(enemySpawnTimer <= 0){
            enemySpawnTimer = random.nextFloat() * (Max_SpawnTime-Min_SpawnTime) + Min_SpawnTime;
            enemy.add(new Enemies(random.nextInt(1366),true));
            enemy.add(new Enemies(random.nextInt(1366),false));
        }
        // update enemy
        ArrayList<Enemies> enemyToMove = new ArrayList<>();
        for (Enemies enemies:enemy){
            enemies.update(delta,true);
            if(enemies.remove){
                enemyToMove.add(enemies);
            }
        }

        for (Enemies enemies:enemy){
            if(enemies.getCollision().isCollide(build.getCollision())){
                enemyToMove.add(enemies);
                build.getDamage();
                if(build.getBuildingHealth() == 0){
                    // game over
                    BackgroundMusic.stop(); // stop music
                    game.setScreen(new GameOverScreen(game));

                }
            }
        }
        for (Bullet bullet: player.getBullets()){
            for (Enemies enemies: enemy){
                if (bullet.getCollision().isCollide(enemies.getCollision())){
                    enemyToMove.add(enemies);
                    score.addScore();
                    // System.out.println(score.getFinalScore());
                }
            }
        }
        enemy.removeAll(enemyToMove);
        player.shot(game,delta,enemy);

        for(Enemies enemy:enemy){
            enemy.render(game.batch);
        }

        for (Bullet bullet : player.getBullets()){
            bullet.render(game.batch);
        }


        player.setSoldier(game);

        GlyphLayout scoreLayout = new GlyphLayout(scoreFont, "" + score.getFinalScore());
        scoreFont.draw(game.batch, scoreLayout, Gdx.graphics.getWidth() / 2.f - scoreLayout.width / 2.f, Gdx.graphics.getHeight() - scoreLayout.height - 10);
        game.batch.end();

    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        //this.dispose();
        BackgroundMusic.dispose();
        game.batch.dispose();
        player.disposePlayer();
        Background.dispose();
        build.disposeBuilding();

    }
}
