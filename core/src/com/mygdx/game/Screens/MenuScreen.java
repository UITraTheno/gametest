package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.DefenseGames;
import com.mygdx.game.Score;
import com.mygdx.game.saveLoadController;


public class MenuScreen implements Screen {
    DefenseGames game;

    // Initial the size of width and height for the window

    Texture StartButton;
    Texture Background;
    Texture ScoreButton;
    Texture ExitButton;
    Music BackgroundMusic;
    public MenuScreen(DefenseGames game){
        this.game = game;
        Background = new Texture("main-picture.png");
        StartButton= new Texture("Start-Button.png");
        ScoreButton = new Texture("score-button.png");
        ExitButton = new Texture("Exit-button.png");
        BackgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Menu-BGM.mp3"));

    }

    @Override
    public void show() {
        // Play the BGM
        BackgroundMusic.setVolume(0.2f);
        BackgroundMusic.setLooping(true);
        BackgroundMusic.play();
    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(Background,0,0);
        // game.batch.draw(StartButton,DefenseGames.windowsWidth/2 - 20,DefenseGames.windowsHeight/5,100,50);   Start button Design
        /**
         * When player move the mouse to the Start Button, the Button's image will be changed to remind player the
         * mouse is on the valid area. If player click the button, game begin.
         */
        if(Gdx.input.getX() < DefenseGames.windowsWidth/2 + 80 && Gdx.input.getX() > DefenseGames.windowsWidth/2 - 120
                && DefenseGames.windowsHeight - Gdx.input.getY() < DefenseGames.windowsHeight/5 + 40 &&
                DefenseGames.windowsHeight - Gdx.input.getY() > DefenseGames.windowsHeight/5 + 5){

            StartButton = new Texture("Start-Button1.png");
            game.batch.draw(StartButton,DefenseGames.windowsWidth/2.f - 20,DefenseGames.windowsHeight/5.f,100,50);
            if (Gdx.input.isTouched()){
                BackgroundMusic.stop(); // BGM for menu will stop playing and get into main game screen.
                game.setScreen(new MainGameScreen(game));
                //debug
                saveLoadController s = new saveLoadController();
                Score scr = new Score();
                scr.addScore();
                s.saveData(scr);


            }

        }
        else{
            StartButton = new Texture("Start-Button.png");
            game.batch.draw(StartButton,DefenseGames.windowsWidth/2.f - 20,DefenseGames.windowsHeight/5.f,100,50);
        }

        // game.batch.draw(ScoreButton,DefenseGames.windowsWidth/2 - 15,DefenseGames.windowsHeight/8,90,50);  Score Button Design

        /**
         * When player move the mouse to the Score Button, the Button's image will be changed to remind player the
         * mouse is on the valid area. If player click the button, game will go to Score page.
         */
        if(Gdx.input.getX() < DefenseGames.windowsWidth/2.f + 80 && Gdx.input.getX() > DefenseGames.windowsWidth/2.f - 120
                && DefenseGames.windowsHeight - Gdx.input.getY() < DefenseGames.windowsHeight/8.f + 40 &&
                DefenseGames.windowsHeight - Gdx.input.getY() > DefenseGames.windowsHeight/8.f + 5){

            ScoreButton = new Texture("score-button1.png");
            game.batch.draw(ScoreButton,DefenseGames.windowsWidth/2.f - 15,DefenseGames.windowsHeight/8.f,90,50);
            if (Gdx.input.isTouched()){
                BackgroundMusic.stop(); // BGM for menu will stop playing and get into main game screen.
                game.setScreen(new GameOverScreen(game));
            }

        }
        else{
            ScoreButton = new Texture("score-button.png");
            game.batch.draw(ScoreButton,DefenseGames.windowsWidth/2.f - 15,DefenseGames.windowsHeight/8.f,90,50);
        }

        // game.batch.draw(ExitButton,DefenseGames.windowsWidth/2 - 15,DefenseGames.windowsHeight/23,90,48);  Exit Button Design
        /**
         * When player move the mouse to the Exit Button, the Button's image will be changed to remind player the
         * mouse is on the valid area. If player click the button, exit the game.
         */
        if(Gdx.input.getX() < DefenseGames.windowsWidth/2 + 80 && Gdx.input.getX() > DefenseGames.windowsWidth/2 - 120
                && DefenseGames.windowsHeight - Gdx.input.getY() < DefenseGames.windowsHeight/23 + 40 &&
                DefenseGames.windowsHeight - Gdx.input.getY() > DefenseGames.windowsHeight/23 + 5){

            ExitButton = new Texture("Exit-button1.png");
            game.batch.draw(ExitButton,DefenseGames.windowsWidth/2.f - 15,DefenseGames.windowsHeight/23.f,90,48);
            if (Gdx.input.isTouched()){
                Gdx.app.exit();
            }

        }
        else{
            ExitButton = new Texture("Exit-button.png");
            game.batch.draw(ExitButton,DefenseGames.windowsWidth/2.f - 15,DefenseGames.windowsHeight/23.f,90,48);
        }

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
        BackgroundMusic.dispose();
        StartButton.dispose();
        ScoreButton.dispose();
        ExitButton.dispose();
        Background.dispose();

    }
}
