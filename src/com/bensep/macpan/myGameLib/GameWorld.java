package com.bensep.macpan.myGameLib;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public abstract class GameWorld {

    protected OrthographicCamera gameCamera;
    protected ArrayList<Entity> entities;
    protected WorldTile[][] worldGrid;
    protected float tileSize;
    private ArrayList<Entity> dead;

    public GameWorld(OrthographicCamera gameCamera, int worldWith, int worldHeight, float tileSize) {
        this.gameCamera = gameCamera;
        entities = new ArrayList<Entity>();
        worldGrid = new WorldTile[worldWith][worldHeight];
        this.tileSize = tileSize;
        dead = new ArrayList<>();
    }

    public void update() {
        entities.forEach(entity -> {
            entity.updateMovement();
            entity.update();
        });
        for (int x = 0; x < worldGrid.length; x++) {
            for (int y = 0; y < worldGrid[0].length; y++) {
                if (worldGrid[x][y]!=null) worldGrid[x][y].update();
            }
        }
        dead.forEach(entity -> {
            entities.remove(entity);
        });
        dead.removeAll(dead);
    }

    public abstract void render(SpriteBatch spriteBatch);

    public boolean collide(GameObject gameObject) {
        boolean out = false;
        for (int x = (int) ((gameObject.x + gameObject.width / 2) / tileSize - 1); x < (gameObject.x + gameObject.width / 2) / tileSize + 1; x++) {
            for (int y = (int) ((gameObject.y + gameObject.height / 2) / tileSize - 1); y < (gameObject.y + gameObject.height / 2) / tileSize + 1; y++) {
                if (x >= 0 && x < worldGrid.length && y >= 0 && y < worldGrid[0].length && worldGrid[x][y] != null && worldGrid[x][y].collide(gameObject)) {
                    out = true;
                }
            }
        }
        return out;
    }

    public boolean hitEntity(Rectangle dmdArea, double amount, byte dagCollide) {
        boolean out = false;
        for (Entity e:entities
        ) {
            if (e.hit(dmdArea, amount, dagCollide)) out = true;
        }
        return out;
    }

    public void killEntity(Entity entity) {
        dead.add(entity);
    }

    public void removeHoldingObject(float x, float y, GameObject object) {
        worldGrid[(int) (x+.5f / tileSize)][(int) (y+.5f / tileSize)].holdingObjects.remove(object);
    }

    public void addHoldingObject(float x, float y, GameObject object) {
        worldGrid[(int) (x+.5f / tileSize)][(int) (y+.5f / tileSize)].holdingObjects.add(object);
    }


}
