package com.blasters.game.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blasters.game.SuperPlanetBlasters;
import com.blasters.game.gameworld.GameWorld;

/**
 * The basic red fighter.
 */

public class RedFighter extends Fighter {
    public RedFighter(GameWorld world) {
        super(world);
    }

    public void defineFighter() {
        value = 1;
        health  = 2;
        TextureRegion fighter = world.getAtlas().findRegion("dracoShip"); //updated to reflect new atlas
        sprite = new Sprite(fighter);
        sprite.setScale(.5f, .5f);
        x = random.nextInt(SuperPlanetBlasters.WIDTH - sprite.getRegionWidth());
        y = random.nextInt(SuperPlanetBlasters.HEIGHT) + SuperPlanetBlasters.HEIGHT ;
        sprite.setPosition(x, y);
    }

    public void update(float delta) {
        move(delta);

        if (sprite.getY() + sprite.getHeight() < 0) {
            world.enemies.removeValue(this, true);
        }
        else if (sprite.getBoundingRectangle().overlaps(world.player.sprite.getBoundingRectangle())) {
            die();
        }
        for (Bullet bullet : world.bullets) {
            if (sprite.getBoundingRectangle().overlaps(bullet.laserSprite.getBoundingRectangle())) {
                health--;
                //bullet
                bullet.kill();
                if(health <= 0) {
                    die();
                }
            }
        }
    }

    private void die() {
        world.screen.hud.addScore(value);
        world.enemies.removeValue(this, true);
    }

    public void move(float delta){
        velocity.add(0, -500);
        velocity.scl(delta);
        sprite.translate(velocity.x, velocity.y);
    }
}
