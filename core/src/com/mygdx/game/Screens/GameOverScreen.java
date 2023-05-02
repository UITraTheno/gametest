package com.mygdx.game.Screens;

//import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
//import com.mygdx.game.Building;
import com.mygdx.game.DefenseGames;

//import com.mygdx.game.MyRunnable;


//import javax.xml.soap.Text;

/**
 * Class name: GameOverScreen
 * Description: in this class, I'm trying to design the Screen for "Game Over" page. In this page, I set the button "Home"
 * which will help player get into the main game screen. Also， I set the special bgm and background for this screen.
 */

public class GameOverScreen implements Screen{

    DefenseGames game;
    Texture backGround;
    Texture building;
    Texture Button;
    Texture word;
    String PlayerName;
    Music backGroundMusic;
    //private Thread myThread1;
    //private Thread myThread2;
    //private Thread myThread3;

    /**
     * Method name: GameOverScreen
     * Description: This method is using for initializing the background image and bgm.
     */

    public GameOverScreen(DefenseGames game){
        this.game=game;
        backGround = new Texture("gameOver.png");
        building = new Texture("Fortress.png");
        backGroundMusic = Gdx.audio.newMusic(Gdx.files.internal("gameover.wav"));
        //Button = new Texture("home-button.png");
        word = new Texture("OverTxt.png");

    }

    // Method will be using for play bgm
    @Override
    public void show() {
        // Play the BGM
        backGroundMusic.setVolume(0.2f);
        backGroundMusic.play();
    }

    // The main part of this method is setting the action for the "Home" button. When player click the button, the screen
    // will go back to the menu.
    @Override
    public void render(float delta) {
        //Score score = new Score();
        //        Json json = new Json();
        //        long currentTimemil = System.currentTimeMillis();
        //        Date curTime = new Date(currentTimemil);
        //        String DateInfor = json.toJson(score.getFinalScore() + ": " + curTime.toString());
        //        FileHandle file = Gdx.files.local("Score.json");
        //        file.writeString(DateInfor, false);


        // starting to batch each element in the game
        game.batch.begin();
        game.batch.draw(backGround,0,0);
        game.batch.draw(building,1000,0,400,250);
        //myThread1 = new Thread(new MyRunnable(740,delta,game));
        //myThread2 = new Thread(new MyRunnable(670,delta,game));
        //myThread3 = new Thread(new MyRunnable(600,delta,game));
        //myThread1.start();
        //myThread2.start();
        //myThread3.start();
        game.batch.draw(word,Gdx.graphics.getWidth()/4.f,Gdx.graphics.getHeight()/2.f);

        /**
            when the mouse touch on the image home button, image will be changed and remind user ready to click on,
            if player click the button image, go to the menu Screen
         **/
        if(Gdx.input.getX() > DefenseGames.windowsWidth/3.f && Gdx.input.getX() < DefenseGames.windowsWidth/3.f + 170
        && DefenseGames.windowsHeight - Gdx.input.getY() > DefenseGames.windowsHeight/4.f &&
                DefenseGames.windowsHeight- Gdx.input.getY() < DefenseGames.windowsHeight/4.f + 100){
            Button = new Texture("home-button1.png");
            game.batch.draw(Button,DefenseGames.windowsWidth/3.f,DefenseGames.windowsHeight/4.f,200,80);
            if (Gdx.input.isTouched()){
                game.setScreen(new MenuScreen(game));
            }
        }
        else{
            Button = new Texture("home-button.png");
            game.batch.draw(Button,DefenseGames.windowsWidth/3.f,DefenseGames.windowsHeight/4.f,200,80);
        }

        //game.batch.draw(Button,Gdx.graphics.getWidth()/3.f,Gdx.graphics.getHeight()/4.f,200,80);
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

    }


}
