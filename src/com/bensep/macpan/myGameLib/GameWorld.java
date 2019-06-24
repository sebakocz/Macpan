package com.bensep.macpan.myGameLib;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public abstract class GameWorld extends Rectangle{

    protected ArrayList<Entity> entities;
    protected WorldTile[][] worldGrid;
    protected float tileSize;
    private ArrayList<Entity> dead;
    private boolean loop;

    public GameWorld(int worldWidth, int worldHeight, float tileSize) {
        super(0, 0, worldWidth * tileSize - 1f, worldHeight * tileSize - 1f);
        entities = new ArrayList<Entity>();
        worldGrid = new WorldTile[worldWidth][worldHeight];
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
            removeHoldingObject(entity);
        });
        entities.removeAll(dead);
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

    public boolean checkCollide(float x, float y, byte Collide) {
        try {
            return worldGrid[(int) (x / tileSize)][(int) (y / tileSize)].checkCollide(Collide);
        } catch (Exception ignored) {}
        return false;
    }

    public boolean hitEntity(Rectangle dmdArea, float amount, byte dmgCollide) {
        boolean out = false;
        for (Entity e:entities
        ) {
            if (e.hit(dmdArea, amount, dmgCollide)) out = true;
        }
        return out;
    }

    public void hitEntity(float x, float y, float amount, byte dmgCollide) {
        try {
            if (worldGrid[(int) (x / tileSize)][(int) (y / tileSize)] != null) {
                worldGrid[(int) (x / tileSize)][(int) (y / tileSize)].holdingObjects.forEach(object -> {
                    try {
                        ((Entity) object).hit(object, amount, dmgCollide);
                    } catch (Exception ignored) {}
                });
            }
        }catch (Exception ignored){}
    }

    public void hitEntity(Vector2 pos, float amount, byte dmgCollide) {
        hitEntity(pos.x, pos.y, amount, dmgCollide);
    }

    public void killEntity(Entity entity) {
        dead.add(entity);
    }

    public void removeHoldingObject(float x, float y, GameObject object) {
        worldGrid[(int) (x / tileSize)][(int) (y / tileSize)].holdingObjects.remove(object);
    }

    public void removeHoldingObject(GameObject object) {
        removeHoldingObject(object.getCenter().x, object.getCenter().y, object);
    }

    public void addHoldingObject(float x, float y, GameObject object) {
        worldGrid[(int) (x / tileSize)][(int) (y / tileSize)].holdingObjects.add(object);
    }

    public void addHoldingObject(GameObject object) {
        addHoldingObject(object.getCenter().x, object.getCenter().y, object);
    }

    public void spawnEntity(Entity entity) {
        entities.add(entity);
    }

    public WorldTile getTileAt(float x, float y) {
        return worldGrid[(int) (x / tileSize)][(int) (y / tileSize)];
    }

    public WorldTile getTileAt(Vector2 pos) {
        return getTileAt(pos.x, pos.y);
    }



}
