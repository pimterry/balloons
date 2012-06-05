package org.housered.balloons;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

public class WorldManager
{
    private final Map<Long, Spatial> entities;
    private final AtomicLong nextEntityId = new AtomicLong();
    private final AssetManager assetManager;

    public WorldManager(AssetManager assetManger)
    {
        this.assetManager = assetManger;
        this.entities = new HashMap<Long, Spatial>();
    }

    //FIXME: temporary method really, shouldn't be here
    public Spatial createEntity(long id)
    {
        // TODO: create different types of entity
        Box box = new Box(new Vector3f(1, -1, 1), 1, 1, 1);
        Geometry entity = new Geometry("box", box);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        entity.setMaterial(mat);

        entities.put(id, entity);

        return entity;
    }
    
    //FIXME: threading etc
    public Collection<Spatial> getEntities()
    {
        return entities.values();
    }

    public Spatial createEntity()
    {
        return createEntity(generateNextId());
    }

    private long generateNextId()
    {
        return nextEntityId.getAndIncrement();
    }
}
