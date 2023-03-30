package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;


/**
 *  Class name: MainGameScreen
 *  Description: In this class, I designed the main screen for the video game. It includes several elements in this class:
 *  1) player
 *  2) enemy
 *  3) build
 *  4) Collision
 *  I also set the special bgm for this screen.
 */

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
    private final static String DATABASE_URL = "jdbc:sqlite:D:\\gametest\\database\\gameData.db";

    /**
     * In this method, I will initialize the image, bgm, and enemy arraylist.
     */
    public MainGameScreen(DefenseGames game){
        this.game = game;
        scoreFont = new BitmapFont(Gdx.files.internal("score.fnt"));
        BackgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Game-BGM.mp3"));
        random = new Random();
        enemySpawnTimer = random.nextFloat() * (Max_SpawnTime-Min_SpawnTime) + Min_SpawnTime; // time period to create the enemies
        enemy = new ArrayList<>();


    }


    /**
     * This method is used for playing the background music
     */
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

        // update the location of the player
        player.movement();
        game.batch.draw(Background, 0, 0);
        build.setBuilding(game);
        enemySpawnTimer -= delta;
        // In following part, I will use arraylist to save different enemy in the game until the boolean value of remove
        // is false (It means player killed the enemy or enemy move out of the screen)
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


        // check the collision for both building and enemy. if building get 20 damages from enemy, game over. Save the
        // final score to the database and go to gameover screen.
        for (Enemies enemies:enemy){
            if(enemies.getCollision().isCollide(build.getCollision())){
                enemyToMove.add(enemies);
                build.getDamage();
                if(build.getBuildingHealth() == 0){
                    // game over insert score to database
                    try {
                        Class.forName("org.sqlite.JDBC");
                    } catch (ClassNotFoundException e) {


                    }
                    try(Connection connection = DriverManager.getConnection(DATABASE_URL);
                        Statement statement = connection.createStatement()){
                        int gameScore = score.getFinalScore();
                        String sql = "insert into gameScore (Score) values('" + gameScore + "')";
                        int rowAffected = statement.executeUpdate(sql);
                        if(rowAffected > 0){
                            System.out.println("Data has already be inserted");
                        }
                        else{
                            System.out.println("Data cannot be inserted");
                        }
                    }catch (SQLException e){
                        System.err.println(e.getMessage());
                    }
                    BackgroundMusic.stop(); // stop music
                    game.setScreen(new GameOverScreen(game));

                }
            }
        }

        // check the collision for both bullet and enemy. both enemy and bullet will be removed if they touch together.
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


        // batch enemy, bullet, player here
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

    /**
     * This method will be used for disposing unnecessary elements from the game.
     */
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
