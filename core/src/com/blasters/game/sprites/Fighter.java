package com.blasters.game.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.blasters.game.gameworld.GameWorld;
import com.blasters.game.screens.GameScreen;

/**
 * Created by SHELIVES on 11/2/2016.
 */

public abstract class Fighter{
    public int value;
    public Vector2 velocity;
    protected GameWorld world;
    protected int health;
    public Sprite sprite;
    
    public Fighter(GameWorld world) {
        this.world = world;
        velocity = new Vector2(0,0);
        defineFighter();
    }

    protected abstract void defineFighter();
    public abstract void update(float delta);
}