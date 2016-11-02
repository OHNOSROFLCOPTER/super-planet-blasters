package com.blasters.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blasters.game.gameworld.GameWorld;
import com.blasters.game.screens.GameScreen;

/**
 * Created by SHELIVES on 11/2/2016.
 */

public class HeroShip extends Fighter {
    private final float INVINCIBLETIME = 8f;
    private float timeInvincible;
    private TextureRegion blueHero;
    private TextureRegion redHero;
    private TextureRegion orangeHero;
    private TextureRegion greenHero;
    public boolean invincible;
    private boolean isDead;

    public enum State{ BLUE, GREEN, ORANGE, RED}
    State colorState;

    public HeroShip(GameWorld world) {
        super(world);
    }

    public void defineFighter() {
        value = 999999;
        blueHero = new TextureRegion(world.getAtlas().findRegion("playerShip2_blue"));
        redHero = new TextureRegion(world.getAtlas().findRegion("playerShip1_red"));
        orangeHero = new TextureRegion(world.getAtlas().findRegion("playerShip1_orange"));
        greenHero = new TextureRegion(world.getAtlas().findRegion("playerShip1_green"));
        sprite = new Sprite(blueHero);
        health = 2;
        invincible = false;
        timeInvincible = 0;
        isDead = false;
        sprite.setPosition(100, 100);
        sprite.setScale(.2f, .2f);
    }
    public void changeColor() {
        switch (colorState) {
            case BLUE:
                sprite.setRegion(redHero);
                colorState = State.RED;
                break;
            case RED:
                sprite.setRegion(orangeHero);
                colorState  = State.ORANGE;
                break;
            case ORANGE:
                sprite.setRegion(greenHero);
                colorState = State.GREEN;
                break;
            case GREEN:
            default:
                sprite.setRegion(blueHero);
                colorState = State.BLUE;
                break;
        }
    }

    public void update(float delta) {
        checkInput();
        if (invincible) {
            timeInvincible += delta;
            if (timeInvincible >= INVINCIBLETIME) {
                invincible = false;
                timeInvincible = 0;
            }
        }
        else {
            for(Fighter enemy : world.enemies) {
                if (sprite.getBoundingRectangle().overlaps(enemy.sprite.getBoundingRectangle()) && !invincible) {
                    health--;
                    if (health == 0) {
                        die();
                    } else {
                        invincible = true;
                    }
                }
            }
        }
    }

    private void checkInput() {
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            sprite.setPosition(Gdx.input.getX() - sprite.getWidth() / 2,
                    Gdx.graphics.getHeight() - Gdx.input.getY() - sprite.getHeight() / 2);
        }
        if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            sprite.setPosition(Gdx.graphics.getWidth() / 2 - sprite.getWidth() / 2,
                    Gdx.graphics.getHeight() / 2 - sprite.getHeight() / 2);
        }
    }
    private void die() {
        isDead = true;
    }

    public boolean isDead() {
        return isDead;
    }
}