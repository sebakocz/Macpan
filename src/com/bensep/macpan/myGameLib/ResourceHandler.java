package com.bensep.macpan.myGameLib;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by niklasbalazs on 17/01/2017.
 */
public class ResourceHandler {
    private static ResourceHandler instance;
    private Map<String, TextureAtlas> atlasMap = new HashMap<>();

    public static ResourceHandler getInstance() {
        if (instance == null) instance = new ResourceHandler();
        return instance;
    }

    public boolean addAtlas(String key, String path) {
        if (atlasMap.containsKey(key)) return false;
        atlasMap.put(key, new TextureAtlas(Gdx.files.internal(path)));
        return true;
    }

    public TextureAtlas getAtlas(String key) {
        return atlasMap.get(key);
    }

    public TextureRegion getTexture(String atlasKey, String textureName) {
        TextureAtlas atlas = getAtlas(atlasKey);
        if (atlas == null) return null;
        return atlas.findRegion(textureName);
    }
}
